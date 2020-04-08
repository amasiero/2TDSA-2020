package br.com.fiap.loja.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thoughtworks.xstream.XStream;

import br.com.fiap.loja.dao.CarrinhoDAO;
import br.com.fiap.loja.model.Carrinho;
import br.com.fiap.loja.model.Produto;

@Path("/carrinhos")
public class CarrinhoResource {
	
	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String busca( @PathParam("id") long id) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		return carrinho.toXML();
	}

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response adiciona(String conteudo) {
		Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
		new CarrinhoDAO().adiciona(carrinho);
		URI uri = URI.create("/loja/carrinhos/" + carrinho.getId());
		
		return Response.created(uri).build();
	}
	
	@Path("/{id}/produto/{produtoId}")
	@DELETE
	public Response removeProduto(@PathParam("id") long id, @PathParam("produtoId") long produtoId) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		carrinho.remove(produtoId);
		
		return Response.ok().build();
	}
	
	@Path("/{id}/produto/{produtoId}")
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response atualizaProduto(@PathParam("id") long id, @PathParam("produtoId") long produtoId, String conteudo) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		Produto produto = (Produto) new XStream().fromXML(conteudo);
		carrinho.troca(produto);
		return Response.ok().build();
	}
}











