package br.jus.trf2.sistemaprocessual;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.crivano.swaggerservlet.ISwaggerPublicMethod;
import com.crivano.swaggerservlet.PresentableException;
import com.crivano.swaggerservlet.SwaggerAuthorizationException;
import com.crivano.swaggerservlet.SwaggerServlet;
import com.crivano.swaggerservlet.SwaggerUtils;

import br.jus.trf2.sistemaprocessual.ISistemaProcessual.IUsuarioUsernameTrocarSenhaPost;
import br.jus.trf2.sistemaprocessual.UsuarioUsernameGet.PasswordHash;

public class UsuarioUsernameTrocarSenhaPost implements IUsuarioUsernameTrocarSenhaPost, ISwaggerPublicMethod {

	@Override
	public void run(Request req, Response resp, SistemaProcessualContext ctx) throws Exception {
		String login;
		String password;
		String auth = SwaggerServlet.getHttpServletRequest().getHeader("Authorization");
		if (auth == null)
			throw new Exception("Autorização necessária");
		String authBasic = auth.split("\\s+")[1];
		String[] authBasicParts = new String(SwaggerUtils.base64Decode(authBasic)).split(":");
		login = authBasicParts[0];
		password = authBasicParts[1];

		if (login == null)
			throw new PresentableException("É necessário informar o login");
		if (password == null)
			throw new PresentableException("É necessário informar a senha");

		// Trocar a senha
		PasswordHash ph = new PasswordHash(login, password);
		try (Connection conn = Utils.getConnection();
				PreparedStatement qUsuario = conn.prepareStatement(Utils.getSQL("usuario-username-trocar-senha-post-usuario"));
				PreparedStatement qUsuarioWeb = conn.prepareStatement(Utils.getSQL("usuario-username-trocar-senha-post-usuarioweb"))) {
			qUsuario.setString(1, ph.hashUpper);
			qUsuario.setString(2, ph.username);
			qUsuario.executeUpdate();
			qUsuarioWeb.setString(1, ph.hash);
			qUsuarioWeb.setString(2, ph.username);
			qUsuarioWeb.executeUpdate();
		}

		resp.status = "OK";
	}

	@Override
	public String getContext() {
		return "obter informações de usuário";
	}
}
