package br.jus.trf2.sistemaprocessual;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.crivano.swaggerservlet.PresentableUnloggedException;

import br.jus.trf2.sistemaprocessual.ISistemaProcessual.IUsuarioUsernameProcessoConsultarGet;
import br.jus.trf2.sistemaprocessual.ISistemaProcessual.Processo;

public class UsuarioUsernameProcessoConsultarGet implements IUsuarioUsernameProcessoConsultarGet {

	@Override
	public String getContext() {
		return "consultar processo pelo cpf,cnpj,oab ou nome da parte";
	}

	@Override
	public void run(Request req, Response resp, SistemaProcessualContext ctx) throws Exception {
		int limiteConsultaProcessual = 5;
		try{
	           limiteConsultaProcessual = Integer.valueOf(ApoloServlet.INSTANCE.getProperty("limite.consulta.processo")).intValue() ;
	        }
	        catch (Exception ex){
	        	throw new PresentableUnloggedException("Erro ao acessar limite.consulta.processo", ex);
	        }
		try (Connection conn = Utils.getConnection();
				PreparedStatement q = conn.prepareStatement(Utils
						.getSQL("processo-consultar-" + (req.documento != null ? "documento" :
							                                  req.nomeparte != null ? "nome" : "oab") + "-get"))) {

			if (req.documento != null) {
				q.setString(1, req.documento);
				q.setString(2, req.documento);
				q.setInt(3, limiteConsultaProcessual);
			} else if (req.nomeparte != null)  {
				q.setString(1, req.nomeparte);
				q.setString(2, req.nomeparte);
				q.setInt(3, limiteConsultaProcessual);
			}else {
				q.setString(1, req.oab);
				q.setString(2, req.oab);
				q.setInt(3, limiteConsultaProcessual);
			}
			ResultSet rs = q.executeQuery();

			resp.list = new ArrayList<>();
			while (rs.next()) {
				Processo p = new Processo();
				p.numero = rs.getString("numero");
				p.orgao = ApoloServlet.INSTANCE.getProperty("orgao.sigla");
				p.unidade = rs.getString("unidade");
				p.localNaUnidade = rs.getString("localnaunidade"); // Apresentar
																	// isso
																	// apenas
																	// para o
																	// público
																	// interno
				p.usuarioautorizado = true; // Esse nós ainda precisamos
											// descobrir como fazer para
											// pesquisar?
				p.segredodejusticadesistema = "S".equals(rs.getString("segredodejusticadesistema"));
				p.segredodejusticaabsoluto = "S".equals(rs.getString("segredodejusticaabsoluto"));
				p.segredodejustica = p.segredodejusticadesistema || p.segredodejusticaabsoluto;
				p.eletronico = true;
				p.sentenciado = "S".equals(rs.getString("sentenciado"));
				p.baixado = "S".equals(rs.getString("baixado"));
				p.perdecompetencia = "S".equals(rs.getString("perdecompetencia"));
				//p.cdas = rs.getString("cdas");
				p.dataultimomovimento = Utils.formatarDataHoraMinuto(rs.getTimestamp("dataultimomovimento"));
				p.autor = rs.getString("autor");
				p.reu = rs.getString("reu");
				resp.list.add(p);
			}
		}

	}
}
