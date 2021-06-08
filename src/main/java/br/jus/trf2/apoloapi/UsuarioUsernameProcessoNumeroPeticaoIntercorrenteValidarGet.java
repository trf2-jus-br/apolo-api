package br.jus.trf2.apoloapi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.jus.trf2.sistemaprocessual.ISistemaProcessual.IUsuarioUsernameProcessoNumeroPeticaoIntercorrenteValidarGet;
import br.jus.trf2.sistemaprocessual.ISistemaProcessual.TipoPeticaoIntercorrente;
import br.jus.trf2.sistemaprocessual.SistemaProcessualContext;

public class UsuarioUsernameProcessoNumeroPeticaoIntercorrenteValidarGet
		implements IUsuarioUsernameProcessoNumeroPeticaoIntercorrenteValidarGet {

	@Override
	public void run(Request req, Response resp, SistemaProcessualContext ctx) throws Exception {
		resp.tipos = new ArrayList<>();

		try (Connection conn = Utils.getConnection();
				PreparedStatement q = conn.prepareStatement(
						Utils.getSQL("usuario-username-processo-numero-peticao-intercorrente-tipos-documento-get"))) {

			ResultSet rs = q.executeQuery();

			while (rs.next()) {
				TipoPeticaoIntercorrente in = new TipoPeticaoIntercorrente();
				in.id = rs.getString("id");
				if (in.id.equals("138") && ApoloServlet.INSTANCE.getProperty("orgao.sigla").equals("JFRJ"))
					continue;
				in.descricao = rs.getString("nome");
				resp.tipos.add(in);
			}
			resp.sigilo = 1.0;
		}
	}

	@Override
	public String getContext() {
		return "obter lista de tipos de petição intercorrente";
	}

}
