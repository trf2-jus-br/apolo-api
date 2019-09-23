package br.jus.trf2.sistemaprocessual;

import com.crivano.swaggerservlet.SwaggerTestSupport;

import br.jus.trf2.apoloapi.ApoloServlet;

public class SistemaProcessualServiceTest extends SwaggerTestSupport {

	@SuppressWarnings("rawtypes")
	@Override
	protected Class getAPI() {
		return ApoloServlet.class;
	}

	@Override
	protected String getPackage() {
		return "br.jus.trf2.sistemaprocessual";
	}

	public void testNothing_Simple_Success() {
		assertEquals("1", "1");
	}

}
