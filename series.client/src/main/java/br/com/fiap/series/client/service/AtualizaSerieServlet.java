package br.com.fiap.series.client.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.series.client.domain.Serie;

@WebServlet("/atualiza")
public class AtualizaSerieServlet extends HttpServlet {

	private static final long serialVersionUID = -2130863823970544489L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.parseLong(req.getParameter("id"));
		String nome = req.getParameter("nome");
		Serie serie = new Serie(nome);
		serie.setId(id);
		try {
			new SerieService().atualiza(serie);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
}
