package br.com.fiap.loja.tests;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fiap.loja.model.Carrinho;
import br.com.fiap.loja.model.Produto;

public class CarrinhoTest {
	
	private Client client;
	private WebTarget target;
	
	@Before
	public void inicia() {
		this.client = ClientBuilder.newClient();
		this.target = client.target("http://localhost/loja");
	}
	
	@Test
	public void testeDeConsultaDoCarrinho() {
		String conteudo = this.target.path("/carrinhos/1").request().get(String.class);
		Assert.assertTrue(conteudo.contains("Lins de Vasconcelos"));
	}
	
	@Test
	public void testeCadastroNovoCarrinho() {
		Carrinho carrinho = new Carrinho();
		carrinho.adiciona(new Produto(3435l, "Mouse Gamer", 120, 1));
		carrinho.setRua("Avenida Lins de Vasconcelos 1222, 10 andar");
		carrinho.setCidade("São Paulo");
		
		String xml = carrinho.toXML();
		
		Entity<String> entity = Entity.entity(xml, MediaType.APPLICATION_XML);
		
		Response resp = target.path("/carrinhos").request().post(entity);
		Assert.assertEquals(201, resp.getStatus());
		
		String location = resp.getHeaderString("Location");
		String conteudo = client.target(location).request().get(String.class);
		Assert.assertTrue(conteudo.contains(carrinho.getProdutos().get(0).getNome()));
	}
	
	@Test
	public void testeRemoveProdutoCarrinho() {
		Response resp = target.path("carrinhos/1/produto/6924").request().delete();
		Assert.assertEquals(200, resp.getStatus());
		
		String conteudo = target.path("carrinhos/1").request().get(String.class);
		Assert.assertFalse(conteudo.contains("Notebook"));
	}
}




