package br.com.fiap.series.client.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SerieServlet extends HttpServlet {

	private static final long serialVersionUID = -7772149966242521620L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nome = req.getParameter("nome");
		
		// Chamar o serviço
		
		req.getRequestDispatcher("index.jsp").forward(req, resp);

	}
	
}
