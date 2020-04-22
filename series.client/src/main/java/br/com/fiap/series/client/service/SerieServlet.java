package br.com.fiap.series.client.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.series.client.domain.Serie;

@WebServlet("/serie")
public class SerieServlet extends HttpServlet {

	private static final long serialVersionUID = -7772149966242521620L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nome = req.getParameter("nome");
		
		Serie serie = new Serie(nome);
		try {
			new SerieService().adiciona(serie);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.parseLong(req.getParameter("id"));
		
		// Chamada para o service
		
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
	
}
