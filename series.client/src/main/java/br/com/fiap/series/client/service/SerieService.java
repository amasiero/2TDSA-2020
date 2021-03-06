package br.com.fiap.series.client.service;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
	
	public Serie busca(Long id) {
		Serie serie = this.target.path("/series/serie/" + id).request().get(Serie.class);
		return serie;
	}
	
	public void adiciona(Serie serie) throws Exception {
		Entity<Serie> entity = Entity.entity(serie, MediaType.APPLICATION_JSON);
		Response resp = this.target.path("/series").request().post(entity);
		if(resp.getStatus() != 201) throw new Exception("Ocorreu um problema ao criar a série");
	}
	
	public void excluir(Long id) throws Exception {
		Response resp = this.target.path("/series/" + id).request().delete();
		if(resp.getStatus() != 200) throw new Exception("Ocorreu um problema ao excluir a série");
	}
	
	public void atualiza(Serie serie) throws Exception {
		Entity<Serie> entity = Entity.entity(serie, MediaType.APPLICATION_JSON);
		Response resp = this.target.path("/series").request().put(entity);
		if(resp.getStatus() != 200) throw new Exception("Ocorreu um problema ao atualizar a série");
	}
}
