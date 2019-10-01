package br.jus.trf2.apoloapi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.jus.trf2.sistemaprocessual.ISistemaProcessual.IUsuarioUsernameProcessoNumeroGet;
import br.jus.trf2.sistemaprocessual.ISistemaProcessual.UsuarioUsernameProcessoNumeroGetRequest;
import br.jus.trf2.sistemaprocessual.ISistemaProcessual.UsuarioUsernameProcessoNumeroGetResponse;

public class UsuarioUsernameProcessoNumeroGet implements IUsuarioUsernameProcessoNumeroGet {

	@Override
	public void run(UsuarioUsernameProcessoNumeroGetRequest req, UsuarioUsernameProcessoNumeroGetResponse resp)
			throws Exception {
		try (Connection conn = Utils.getConnection();
				PreparedStatement q = conn.prepareStatement(Utils.getSQL("processo-validar-numero-get"));
				PreparedStatement q2 = conn.prepareStatement(Utils.getSQL("processo-validar-numero-get-localizacao"));
				PreparedStatement q3 = conn.prepareStatement(Utils.getSQL("processo-validar-numero-get-sentenciado"))) {
			q.setString(1, req.numero);
			ResultSet rs = q.executeQuery();

			while (rs.next()) {
				if (!"S".equals(rs.getString("ativo")))
					continue;
				resp.numero = rs.getString("numero");
				resp.unidade = rs.getString("unidade");
				resp.localNaUnidade = rs.getString("localnaunidade");
				// Esse nós ainda precisamos
				// descobrir como fazer para
				// pesquisar?
				resp.usuarioautorizado = true; 
				resp.segredodejusticadesistema = "S".equals(rs.getString("segredodejusticadesistema"));
				resp.segredodejusticaabsoluto = "S".equals(rs.getString("segredodejusticaabsoluto"));
				resp.segredodejustica = resp.segredodejusticadesistema || resp.segredodejusticaabsoluto;
				resp.eletronico = "S".equals(rs.getString("eletronico"));;
				resp.baixado = "S".equals(rs.getString("baixado"));
				resp.perdecompetencia = "S".equals(rs.getString("perdecompetencia"));
				// resp.cdas = rs.getString("cdas");
				resp.dataultimomovimento = Utils.formatarDataHoraMinuto(rs.getTimestamp("dataultimomovimento"));
				break;
			}

			q2.setString(1, req.numero);
			q2.setString(2, req.numero);
			q2.setString(3, req.numero);
			ResultSet rs2 = q2.executeQuery();

			while (rs2.next()) {
				resp.localNaUnidade = rs2.getString("local");
				break;
			}

			q3.setString(1, req.numero);
			ResultSet rs3 = q3.executeQuery();

			while (rs3.next()) {
				resp.sentenciado = "S".equals(rs3.getString("sentenciado"));
				break;
			}
		}
	}

	@Override
	public String getContext() {
		return "validar número de processo";
	}
}
