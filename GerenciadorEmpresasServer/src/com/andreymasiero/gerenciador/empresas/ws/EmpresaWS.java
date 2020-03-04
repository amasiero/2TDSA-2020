package com.andreymasiero.gerenciador.empresas.ws;

import java.util.Collection;

import com.andreymasiero.gerenciador.empresas.bean.Empresa;
import com.andreymasiero.gerenciador.empresas.dao.EmpresaDAO;

public class EmpresaWS {
	
	public Collection<Empresa> buscaEmpresas(String nome) {
		return new EmpresaDAO().buscaPorSimilaridade(nome);
	}
	
	public Empresa adiciona(String nome) {
		Empresa empresa = new Empresa(nome);
		new EmpresaDAO().adiciona(empresa);
		return empresa;
	}
}
