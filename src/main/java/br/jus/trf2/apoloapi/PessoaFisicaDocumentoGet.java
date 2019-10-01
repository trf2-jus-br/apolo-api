package br.jus.trf2.apoloapi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.jus.trf2.sistemaprocessual.ISistemaProcessual.IPessoaFisicaDocumentoGet;
import br.jus.trf2.sistemaprocessual.ISistemaProcessual.Pessoa;
import br.jus.trf2.sistemaprocessual.ISistemaProcessual.PessoaFisicaDocumentoGetRequest;
import br.jus.trf2.sistemaprocessual.ISistemaProcessual.PessoaFisicaDocumentoGetResponse;

public class PessoaFisicaDocumentoGet implements IPessoaFisicaDocumentoGet {

	@Override
	public void run(PessoaFisicaDocumentoGetRequest req, PessoaFisicaDocumentoGetResponse resp) throws Exception {
		throw new Exception("Não implementado");
	}

	@Override
	public String getContext() {
		return "obter documento de pessoa física";
	}
}
