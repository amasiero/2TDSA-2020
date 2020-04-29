package br.com.fiap.series.resources;

import java.net.URI;
import java.util.List;

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

import br.com.fiap.series.dao.SerieDAO;
import br.com.fiap.series.domain.Serie;

@Path("/series")
public class SerieResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON) // MediaType.APPLICATION_JSON == "application/xml"
	public List<Serie> buscaTodos() {
		List<Serie> series = new SerieDAO().busca("");
		return series;
	}
	
	@Path("/{nome}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Serie> busca(@PathParam("nome") String nome) {
		List<Serie> series = new SerieDAO().busca(nome);
		return series;
	}
	
	@Path("/serie/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Serie busca(@PathParam("id") Long id) {
		Serie serie = new SerieDAO().buscaPorId(id);
		return serie;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response adiciona(Serie serie) {
		new SerieDAO().adiciona(serie);
		URI uri = URI.create("/series/series/" + serie.getNome().replace(" ", "%20"));
		return Response.created(uri).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response atualiza(Serie value) {
		Serie serie = new SerieDAO().buscaPorId(value.getId());
		serie.setNome(value.getNome());
		return Response.ok().build();
	}
	
	@Path("/{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response exclui(@PathParam("id") Long id) {
		new SerieDAO().exclui(id);	
		return Response.ok().build();
	}
	
}
