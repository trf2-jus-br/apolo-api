package br.jus.trf2.sistemaprocessual;

import br.jus.trf2.sistemaprocessual.ISistemaProcessual.IUsuarioUsernameAvisoConfirmadoExportarGet;
import br.jus.trf2.sistemaprocessual.SistemaProcessualContext;

public class UsuarioUsernameAvisoConfirmadoExportarGet implements IUsuarioUsernameAvisoConfirmadoExportarGet {

	@Override
	public void run(Request req, Response resp, SistemaProcessualContext ctx) throws Exception {
		throw new Exception("Não implementado");
	}

	@Override
	public String getContext() {
		return "exportar avisos confirmados";
	}
}
