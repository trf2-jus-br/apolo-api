package br.jus.trf2.apoloapi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.jus.trf2.sistemaprocessual.ISistemaProcessual.IPeticaoInicialTiposDocumentoGet;
import br.jus.trf2.sistemaprocessual.ISistemaProcessual.IdNome;
import br.jus.trf2.sistemaprocessual.ISistemaProcessual.PeticaoInicialTiposDocumentoGetRequest;
import br.jus.trf2.sistemaprocessual.ISistemaProcessual.PeticaoInicialTiposDocumentoGetResponse;

public class PeticaoInicialTiposDocumentoGet implements IPeticaoInicialTiposDocumentoGet {

	@Override
	public void run(PeticaoInicialTiposDocumentoGetRequest req, PeticaoInicialTiposDocumentoGetResponse resp)
			throws Exception {
		throw new Exception("Não implementado");
	}

	@Override
	public String getContext() {
		return "obter lista de tipos de documento para peticao inicial";
	}
}
