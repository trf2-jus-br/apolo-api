package br.jus.trf2.apoloapi;

import br.jus.trf2.sistemaprocessual.ISistemaProcessual.IPeticaoInicialTiposDocumentoGet;
import br.jus.trf2.sistemaprocessual.SistemaProcessualContext;

public class PeticaoInicialTiposDocumentoGet implements IPeticaoInicialTiposDocumentoGet {

	@Override
	public void run(Request req, Response resp, SistemaProcessualContext ctx) throws Exception {
		throw new Exception("Não implementado");
	}

	@Override
	public String getContext() {
		return "obter lista de tipos de documento para peticao inicial";
	}
}
