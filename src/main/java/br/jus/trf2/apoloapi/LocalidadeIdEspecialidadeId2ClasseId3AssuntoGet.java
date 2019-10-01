package br.jus.trf2.apoloapi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.jus.trf2.sistemaprocessual.ISistemaProcessual.ILocalidadeIdEspecialidadeId2ClasseId3AssuntoGet;
import br.jus.trf2.sistemaprocessual.ISistemaProcessual.IdNome;
import br.jus.trf2.sistemaprocessual.ISistemaProcessual.LocalidadeIdEspecialidadeId2ClasseId3AssuntoGetRequest;
import br.jus.trf2.sistemaprocessual.ISistemaProcessual.LocalidadeIdEspecialidadeId2ClasseId3AssuntoGetResponse;

public class LocalidadeIdEspecialidadeId2ClasseId3AssuntoGet
		implements ILocalidadeIdEspecialidadeId2ClasseId3AssuntoGet {

	@Override
	public void run(LocalidadeIdEspecialidadeId2ClasseId3AssuntoGetRequest req,
			LocalidadeIdEspecialidadeId2ClasseId3AssuntoGetResponse resp) throws Exception {
		throw new Exception("Não implementado");
	}

	@Override
	public String getContext() {
		return "obter classes da especialidade da localidade";
	}
}
