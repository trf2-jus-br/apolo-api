package br.jus.trf2.apoloapi;

import br.jus.trf2.sistemaprocessual.ISistemaProcessual.IUsuarioUsernameAvisoConfirmadoExportarGet;
import br.jus.trf2.sistemaprocessual.ISistemaProcessual.UsuarioUsernameAvisoConfirmadoExportarGetRequest;
import br.jus.trf2.sistemaprocessual.ISistemaProcessual.UsuarioUsernameAvisoConfirmadoExportarGetResponse;

public class UsuarioUsernameAvisoConfirmadoExportarGet implements IUsuarioUsernameAvisoConfirmadoExportarGet {

	@Override
	public void run(UsuarioUsernameAvisoConfirmadoExportarGetRequest req,
			UsuarioUsernameAvisoConfirmadoExportarGetResponse resp) throws Exception {
		throw new Exception("Não implementado");
	}

	@Override
	public String getContext() {
		return "exportar avisos confirmados";
	}
}
