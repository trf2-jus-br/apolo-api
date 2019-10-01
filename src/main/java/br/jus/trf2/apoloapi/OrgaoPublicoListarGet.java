package br.jus.trf2.apoloapi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.jus.trf2.sistemaprocessual.ISistemaProcessual.IOrgaoPublicoListarGet;
import br.jus.trf2.sistemaprocessual.ISistemaProcessual.OrgaoPublicoListarGetRequest;
import br.jus.trf2.sistemaprocessual.ISistemaProcessual.OrgaoPublicoListarGetResponse;
import br.jus.trf2.sistemaprocessual.ISistemaProcessual.Pessoa;

public class OrgaoPublicoListarGet implements IOrgaoPublicoListarGet {

	@Override
	public void run(OrgaoPublicoListarGetRequest req, OrgaoPublicoListarGetResponse resp) throws Exception {
		throw new Exception("Não implementado");
	}

	@Override
	public String getContext() {
		return "obter lista de órgãos públicos";
	}
}
