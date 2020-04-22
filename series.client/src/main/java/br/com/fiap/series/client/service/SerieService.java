package br.com.fiap.series.client.service;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import br.com.fiap.series.client.domain.Serie;

public class SerieService {
	
	private Client client;
	private WebTarget target;
	
	public SerieService() {
		this.client = ClientBuilder.newClient();
		this.target = client.target("http://localhost/series");
	}
	
	public List<Serie> getSeries() {
		List<Serie> series = this.target.path("/series").request().get(List.class);
		return series;
	}
}
