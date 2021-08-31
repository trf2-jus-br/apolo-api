package br.jus.trf2.sistemaprocessual;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.jus.trf2.sistemaprocessual.ISistemaProcessual.IUsuarioUsernamePeticaoIntercorrenteListarGet;
import br.jus.trf2.sistemaprocessual.ISistemaProcessual.PeticaoIntercorrente;
import br.jus.trf2.sistemaprocessual.SistemaProcessualContext;

public class UsuarioUsernamePeticaoIntercorrenteListarGet implements IUsuarioUsernamePeticaoIntercorrenteListarGet {

	@Override
	public void run(Request req, Response resp, SistemaProcessualContext ctx) throws Exception {
		try (Connection conn = Utils.getConnection();
				PreparedStatement q = conn
						.prepareStatement(Utils.getSQL("usuario-username-peticao-intercorrente-listar-get"))) {
			q.setString(1, req.username.toUpperCase());
			q.setString(2, req.data);
			q.setString(3, req.data.substring(0, 10) + " 23:59:59");
			q.setString(4, req.username.toUpperCase());
			q.setString(5, req.data);
			q.setString(6, req.data.substring(0, 10) + " 23:59:59");
			ResultSet rs = q.executeQuery();

			resp.list = new ArrayList<>();
			while (rs.next()) {
				PeticaoIntercorrente p = new PeticaoIntercorrente();
				p.classe = rs.getString("classe_processo");
				p.dataprotocolo = rs.getString("data_protocolo");
				p.protocolo = rs.getString("protocolo");
				p.processo = rs.getString("num_processo");
				// p.unidade = rs.getString("sig_orgao");
				resp.list.add(p);
			}
		}
	}

	@Override
	public String getContext() {
		return "listar petições intercorrentes";
	}
}
