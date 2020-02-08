package br.com.fiap.estoque.modelo.usuario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;


public class TokenDao {

	private static Map<TokenUsuario, Usuario> USUARIOS = new LinkedHashMap<>();

	public TokenDao() {
		popularUsuariosNoMapa();
	}
	
	public boolean ehValido(TokenUsuario usuario) {
		return USUARIOS.containsKey(usuario);
	}
	
	private void popularUsuariosNoMapa() {
		USUARIOS.put(new TokenUsuario("AAA", parseDate("10/02/2020")), new Usuario.Builder().comNome("Alexandre").comLogin("ale").comSenha("pass").build());
		USUARIOS.put(new TokenUsuario("BBB", parseDate("10/02/2020")), new Usuario.Builder().comNome("Andrey").comLogin("andrey").comSenha("pass").build());
		USUARIOS.put(new TokenUsuario("CCC", parseDate("10/02/2020")), new Usuario.Builder().comNome("Calixto").comLogin("calixto").comSenha("pass").build());
		USUARIOS.put(new TokenUsuario("DDD", parseDate("10/02/2020")), new Usuario.Builder().comNome("Thiago").comLogin("thiago").comSenha("pass").build());
	}


	private Date parseDate(String dataComoString) {
		try {
			return new SimpleDateFormat("dd/MM/yyyy").parse(dataComoString);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}



}
