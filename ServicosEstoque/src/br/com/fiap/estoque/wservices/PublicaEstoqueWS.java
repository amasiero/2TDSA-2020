package br.com.fiap.estoque.wservices;

import javax.xml.ws.Endpoint;

public class PublicaEstoqueWS {

	public static void main(String[] args) {
		EstoqueWS service = new EstoqueWS();
		String url = "http://localhost:8080/estoquews";
		
		System.out.println("Web Service executando: " + url);
		Endpoint.publish(url, service);
	}

}

