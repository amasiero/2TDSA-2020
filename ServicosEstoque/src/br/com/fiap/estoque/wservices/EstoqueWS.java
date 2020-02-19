package br.com.fiap.estoque.wservices;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.ResponseWrapper;

import br.com.fiap.estoque.modelo.item.Filtro;
import br.com.fiap.estoque.modelo.item.Filtros;
import br.com.fiap.estoque.modelo.item.Item;
import br.com.fiap.estoque.modelo.item.ItemDao;
import br.com.fiap.estoque.modelo.item.ListaItens;
import br.com.fiap.estoque.modelo.usuario.AutorizacaoException;
import br.com.fiap.estoque.modelo.usuario.TokenDao;
import br.com.fiap.estoque.modelo.usuario.TokenUsuario;

@WebService
public class EstoqueWS {
	
	ItemDao dao = new ItemDao();
	
	@WebMethod(operationName="TodosItens")
	@ResponseWrapper(localName = "itens")
	@WebResult(name="item")
	public ListaItens getItens(@WebParam(name="filtros") Filtros filtros) {
		System.out.println("Buscando itens...");
		List<Filtro> lista = filtros == null ? null : filtros.getLista();
		List<Item> resultado = dao.todosItens(lista);
		return new ListaItens(resultado);
	}
	
	@WebMethod(operationName = "CadastrarItem")
	@WebResult(name="item")
	public Item cadastrarItem(@WebParam(name="tokenUsuario", header=true) TokenUsuario token, 
			@WebParam(name="item") Item item) throws AutorizacaoException {
		
		boolean valido = new TokenDao().ehValido(token);
		
		if(!valido) {
			throw new AutorizacaoException("Token inválido.");
		}
		
		System.out.println("Cadastrando " + item + ", " + token);
		this.dao.cadastrar(item);
		return item;
	}

}
	

