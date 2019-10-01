package br.jus.trf2.apoloapi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.jus.trf2.sistemaprocessual.ISistemaProcessual.IPessoaJuridicaDocumentoGet;
import br.jus.trf2.sistemaprocessual.ISistemaProcessual.Pessoa;
import br.jus.trf2.sistemaprocessual.ISistemaProcessual.PessoaJuridicaDocumentoGetRequest;
import br.jus.trf2.sistemaprocessual.ISistemaProcessual.PessoaJuridicaDocumentoGetResponse;

public class PessoaJuridicaDocumentoGet implements IPessoaJuridicaDocumentoGet {

	@Override
	public void run(PessoaJuridicaDocumentoGetRequest req, PessoaJuridicaDocumentoGetResponse resp) throws Exception {
		throw new Exception("Não implementado");
	}

	@Override
	public String getContext() {
		return "obter documento de pessoa jurídica";
	}
}
