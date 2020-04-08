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
		Carrinho carrinho = this.target.path("/carrinhos/1").request().get(Carrinho.class);
		Assert.assertTrue(carrinho.getRua().contains("Lins de Vasconcelos"));
	}
	
	@Test
	public void testeCadastroNovoCarrinho() {
		Carrinho carrinho = new Carrinho();
		carrinho.adiciona(new Produto(3435l, "Mouse Gamer", 120, 1));
		carrinho.setRua("Avenida Lins de Vasconcelos 1222, 10 andar");
		carrinho.setCidade("São Paulo");
		
		Entity<Carrinho> entity = Entity.entity(carrinho, MediaType.APPLICATION_JSON);
		
		Response resp = target.path("/carrinhos").request().post(entity);
		Assert.assertEquals(201, resp.getStatus());
		
		String location = resp.getHeaderString("Location");
		Carrinho carrinhoResposta = client.target(location).request().get(Carrinho.class);
		Assert.assertEquals(carrinhoResposta.getProdutos().get(0).getNome(), carrinho.getProdutos().get(0).getNome());
	}
	
	@Test
	public void testeRemoveProdutoCarrinho() {
		Response resp = target.path("carrinhos/1/produto/6924").request().delete();
		Assert.assertEquals(200, resp.getStatus());
		
		Carrinho carrinho = target.path("carrinhos/1").request().get(Carrinho.class);
		Assert.assertEquals(1, carrinho.getProdutos().size());
	}
	
	@Test
	public void testeAtualizacaoQuantidadeProduto() {
		Carrinho carrinho = this.target.path("/carrinhos/1").request().get(Carrinho.class);
				
		Produto produto = (Produto) carrinho.getProdutos().stream().filter(p -> p.getId() == 6924l).toArray()[0];
		produto.setQuantidade(10);
		
		Entity<Produto> entity = Entity.entity(produto, MediaType.APPLICATION_JSON);
		
		Response resp = target.path("carrinhos/1/produto/6924/quantidade").request().put(entity);
		Assert.assertEquals(200, resp.getStatus());
		
		carrinho = this.target.path("/carrinhos/1").request().get(Carrinho.class);
		Assert.assertEquals(10, carrinho.getProdutos().get(0).getQuantidade());
	}
}




