package br.jus.trf2.sistemaprocessual;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import com.crivano.swaggerservlet.SwaggerServlet;
import com.crivano.swaggerservlet.dependency.TestableDependency;

import br.jus.trf2.sistemaprocessual.ISistemaProcessual;

public class ApoloServlet extends SwaggerServlet {
	private static final long serialVersionUID = 1756711359239182178L;

	static ApoloServlet INSTANCE = null;
	
	@Override
	public void initialize(ServletConfig config) throws ServletException {
		INSTANCE = this;
		
		setAPI(ISistemaProcessual.class);

		setActionPackage("br.jus.trf2.sistemaprocessual");

		addPrivateProperty("password", null);
		super.setAuthorization(getProperty("password"));

		addPublicProperty("orgao.sigla");
		addPrivateProperty("limite.consulta.processo");
		addRestrictedProperty("datasource.url", null);
		if (getProperty("datasource.url") != null) {
			addRestrictedProperty("datasource.username");
			addPrivateProperty("datasource.password");
			addRestrictedProperty("datasource.name", null);
			addRestrictedProperty("datasource.schema");
		} else {
			addRestrictedProperty("datasource.username", null);
			addPrivateProperty("datasource.password", null);
			addRestrictedProperty("datasource.name");
			addRestrictedProperty("datasource.schema", null);
		}

		addDependency(new TestableDependency("database", "apoloapids", false, 0, 10000) {
			@Override
			public String getUrl() {
				String url = getProperty("datasource.name");
				if (url == null)
					url = getProperty("datasource.url");
				return url;
			}

			@Override
			public boolean test() throws Exception {
				try (Connection conn = Utils.getConnection();
						PreparedStatement q = conn.prepareStatement("select sysdate from dual")) {
					ResultSet rs = q.executeQuery();
					while (rs.next()) {
						return true;
					}
				}
				return false;
			}

			@Override
			public boolean isPartial() {
				return false;
			}
		});

	}

	@Override
	public int errorStatus(Exception e) {
		return e.getMessage() == null || !e.getMessage().endsWith("(Alerta)") ? super.errorStatus(e) : 400;
	}

	@Override
	public String getService() {
		return "sistemaprocessual";
	}

}
