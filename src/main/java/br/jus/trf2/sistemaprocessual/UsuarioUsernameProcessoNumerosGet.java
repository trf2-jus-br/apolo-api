package br.jus.trf2.sistemaprocessual;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.jus.trf2.sistemaprocessual.ISistemaProcessual.IUsuarioUsernameProcessoNumerosGet;
import br.jus.trf2.sistemaprocessual.ISistemaProcessual.Processo;
import br.jus.trf2.sistemaprocessual.SistemaProcessualContext;

public class UsuarioUsernameProcessoNumerosGet implements IUsuarioUsernameProcessoNumerosGet {

	@Override
	public void run(Request req, Response resp, SistemaProcessualContext ctx) throws Exception {
		String[] list = req.numeros.split(",");
		char[] markers = new char[list.length * 2 - 1];
		for (int i = 0; i < markers.length; i++)
			markers[i] = ((i & 1) == 0 ? '?' : ',');

		String statement = Utils.getSQL("processo-validar-numero-get");
		statement = statement.replace(":list", new String(markers));
		try (Connection conn = Utils.getConnection(); PreparedStatement q = conn.prepareStatement(statement)) {
			int i = 1;
			for (int j = 0; j < 4; j++)
				for (String s : list)
					q.setString(i++, s);
			ResultSet rs = q.executeQuery();

			resp.list = new ArrayList<>();
			while (rs.next()) {
				if (!"S".equals(rs.getString("ativo")))
					continue;
				Processo p = new Processo();
				p.numero = rs.getString("numero");
				p.unidade = rs.getString("unidade");
				p.localNaUnidade = rs.getString("localnaunidade");
				// Esse nós ainda precisamos
				// descobrir como fazer para
				// pesquisar?
				p.usuarioautorizado = true;
				p.segredodejusticadesistema = "S".equals(rs.getString("segredodejusticadesistema"));
				p.segredodejusticaabsoluto = "S".equals(rs.getString("segredodejusticaabsoluto"));
				p.segredodejustica = p.segredodejusticadesistema || p.segredodejusticaabsoluto;
				p.eletronico = "S".equals(rs.getString("eletronico"));
				;
				p.baixado = "S".equals(rs.getString("baixado"));
				p.perdecompetencia = "S".equals(rs.getString("perdecompetencia"));
				// p.cdas = rs.getString("cdas");
				p.dataultimomovimento = Utils.formatarDataHoraMinuto(rs.getTimestamp("dataultimomovimento"));
				p.sentenciado = "S".equals(rs.getString("sentenciado"));
				p.autor = rs.getString("autor");
				p.reu = rs.getString("reu");

				resp.list.add(p);
			}
		}
	}

	@Override
	public String getContext() {
		return "validar número de processo";
	}
}
