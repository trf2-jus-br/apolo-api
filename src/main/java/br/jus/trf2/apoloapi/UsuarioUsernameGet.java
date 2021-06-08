package br.jus.trf2.apoloapi;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.crivano.swaggerservlet.ISwaggerPublicMethod;
import com.crivano.swaggerservlet.PresentableException;
import com.crivano.swaggerservlet.SwaggerAuthorizationException;
import com.crivano.swaggerservlet.SwaggerServlet;
import com.crivano.swaggerservlet.SwaggerUtils;

import br.jus.trf2.sistemaprocessual.ISistemaProcessual.IUsuarioUsernameGet;
import br.jus.trf2.sistemaprocessual.SistemaProcessualContext;

public class UsuarioUsernameGet implements IUsuarioUsernameGet, ISwaggerPublicMethod {

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

		String username = login.toUpperCase();
		String hashUpper = SwaggerUtils
				.base64Encode(Utils.calcSha1(password.toUpperCase().getBytes(StandardCharsets.US_ASCII)));
		String hash = SwaggerUtils.base64Encode(Utils.calcSha1(password.getBytes(StandardCharsets.US_ASCII)));
		try (Connection conn = Utils.getConnection();
				PreparedStatement q = conn.prepareStatement(Utils.getSQL("usuario-username-get"))) {
			q.setString(1, username);
			q.setString(2, hashUpper);
			q.setString(3, username);
			q.setString(4, hash);
			ResultSet rs = q.executeQuery();

			while (rs.next()) {
				resp.interno = rs.getBoolean("usuinterno");
				resp.codusu = rs.getString("codusu");
				resp.codentidade = rs.getString("codentidade");
				resp.entidade = rs.getString("entidade");
				resp.codunidade = rs.getString("codunidade");
				resp.unidade = rs.getString("unidade");
				resp.nome = rs.getString("nome");
				resp.cpf = rs.getString("cpf");
				resp.email = rs.getString("email");
				resp.perfil = Utils.slugify(rs.getString("perfil"), true, false);
				break;
			}

			if (resp.codusu == null)
				throw new SwaggerAuthorizationException(
						"Não foi possível localizar informações para o usuário '" + req.username + "'");
		}
	}

	@Override
	public String getContext() {
		return "obter informações de usuário";
	}
}
