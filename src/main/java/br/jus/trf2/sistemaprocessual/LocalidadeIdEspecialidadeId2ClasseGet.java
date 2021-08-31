package br.jus.trf2.sistemaprocessual;

import br.jus.trf2.sistemaprocessual.ISistemaProcessual.ILocalidadeIdEspecialidadeId2ClasseGet;
import br.jus.trf2.sistemaprocessual.SistemaProcessualContext;

public class LocalidadeIdEspecialidadeId2ClasseGet implements ILocalidadeIdEspecialidadeId2ClasseGet {

	@Override
	public void run(Request req, Response resp, SistemaProcessualContext ctx) throws Exception {
		throw new Exception("Não implementado");
	}

	@Override
	public String getContext() {
		return "obter classes da especialidade da localidade";
	}
}
