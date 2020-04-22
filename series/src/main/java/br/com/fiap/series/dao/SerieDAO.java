package br.com.fiap.series.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import br.com.fiap.series.domain.Serie;

public class SerieDAO {

	private static final Map<Long, Serie> BANCO_DADOS = new HashMap<Long, Serie>();
	private static final AtomicInteger GENERATED_ID = new AtomicInteger(1);

	static {
		geraIdEAdiciona(new Serie("La Casa de Papel"));
		geraIdEAdiciona(new Serie("Lost"));
		geraIdEAdiciona(new Serie("Chigado Fire"));
		geraIdEAdiciona(new Serie("Chigado PD"));
		geraIdEAdiciona(new Serie("Law and Order SVU"));
	}

	public void adiciona(Serie serie) {
		geraIdEAdiciona(serie);
	}

	public List<Serie> busca(String value) {
		if (value.isEmpty() || value == null) {
			return new ArrayList<Serie>(BANCO_DADOS.values());
		}
		List<Serie> series = new ArrayList<Serie>();
		for (Serie serie : BANCO_DADOS.values()) {
			if (serie.getNome().toLowerCase().contains(value.toLowerCase())) {
				series.add(serie);
			}
		}
		return series;
	}
	
	public Serie buscaPorId(Long id) {
		return BANCO_DADOS.get(id);
	}
	
	public void exclui(Long id) {
		BANCO_DADOS.remove(id);
	}

	private static void geraIdEAdiciona(Serie serie) {
		long id = GENERATED_ID.getAndIncrement();
		serie.setId(id);
		BANCO_DADOS.put(serie.getId(), serie);
	}

}
