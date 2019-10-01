package br.jus.trf2.apoloapi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.jus.trf2.sistemaprocessual.ISistemaProcessual.ClasseCNJ;
import br.jus.trf2.sistemaprocessual.ISistemaProcessual.ILocalidadeIdEspecialidadeId2ClasseGet;
import br.jus.trf2.sistemaprocessual.ISistemaProcessual.IdNomeClasseCNJ;
import br.jus.trf2.sistemaprocessual.ISistemaProcessual.LocalidadeIdEspecialidadeId2ClasseGetRequest;
import br.jus.trf2.sistemaprocessual.ISistemaProcessual.LocalidadeIdEspecialidadeId2ClasseGetResponse;

public class LocalidadeIdEspecialidadeId2ClasseGet implements ILocalidadeIdEspecialidadeId2ClasseGet {

	@Override
	public void run(LocalidadeIdEspecialidadeId2ClasseGetRequest req,
			LocalidadeIdEspecialidadeId2ClasseGetResponse resp) throws Exception {
		throw new Exception("Não implementado");
	}

	@Override
	public String getContext() {
		return "obter classes da especialidade da localidade";
	}
}
