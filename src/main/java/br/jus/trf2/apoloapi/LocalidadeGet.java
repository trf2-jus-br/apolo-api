package br.jus.trf2.apoloapi;

import br.jus.trf2.sistemaprocessual.ISistemaProcessual.ILocalidadeGet;
import br.jus.trf2.sistemaprocessual.ISistemaProcessual.LocalidadeGetRequest;
import br.jus.trf2.sistemaprocessual.ISistemaProcessual.LocalidadeGetResponse;

public class LocalidadeGet implements ILocalidadeGet {

	@Override
	public void run(LocalidadeGetRequest req, LocalidadeGetResponse resp) throws Exception {
		throw new Exception("Não implementado");
	}

	@Override
	public String getContext() {
		return "obter localidade";
	}
}
