package br.jus.trf2.apoloapi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.jus.trf2.sistemaprocessual.ISistemaProcessual.ILocalidadeIdEspecialidadeGet;
import br.jus.trf2.sistemaprocessual.ISistemaProcessual.IdNome;
import br.jus.trf2.sistemaprocessual.ISistemaProcessual.LocalidadeIdEspecialidadeGetRequest;
import br.jus.trf2.sistemaprocessual.ISistemaProcessual.LocalidadeIdEspecialidadeGetResponse;

public class LocalidadeIdEspecialidadeGet implements ILocalidadeIdEspecialidadeGet {

	@Override
	public void run(LocalidadeIdEspecialidadeGetRequest req, LocalidadeIdEspecialidadeGetResponse resp)
			throws Exception {
		throw new Exception("Não implementado");
	}

	@Override
	public String getContext() {
		return "obter especialidades da localidade";
	}
}
