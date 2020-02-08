package br.com.fiap.estoque.modelo.item;

import javax.xml.bind.annotation.XmlEnumValue;

public enum TipoItem {

	@XmlEnumValue("Livro")
	LIVRO("Livro"), 
	@XmlEnumValue("Celular")
	CELULAR("Celular"),
	@XmlEnumValue("Tablet")
	TABLET("Tablet");
	
	private String nome;

	TipoItem(String nome) { 
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public static boolean existe(String valor) {
		try{
			//Lan�a IllegalArgumentException caso n�o exista
			TipoItem.valueOf(valor.toUpperCase());
		}catch(IllegalArgumentException e) {
			return false;
		}
		return true;
	}
}
