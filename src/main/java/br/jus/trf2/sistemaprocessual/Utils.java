package br.jus.trf2.sistemaprocessual;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.Scanner;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.crivano.swaggerservlet.SwaggerServlet;

public class Utils {
	public static Connection getConnection() throws Exception {
		String dsName = ApoloServlet.INSTANCE.getProperty("datasource.name");
		if (dsName != null) {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:");
			DataSource ds = (DataSource) envContext.lookup(dsName);
			Connection connection = ds.getConnection();
			if (connection == null)
				throw new Exception("Can't open connection to database.");
			return connection;
		} else {
			Connection connection = null;

			Class.forName("oracle.jdbc.OracleDriver");

			String dbURL = ApoloServlet.INSTANCE.getProperty("datasource.url");
			String username = ApoloServlet.INSTANCE.getProperty("datasource.username");
			;
			String password = ApoloServlet.INSTANCE.getProperty("datasource.password");
			;
			connection = DriverManager.getConnection(dbURL, username, password);
			if (connection == null)
				throw new Exception("Can't open connection to database.");

			if (ApoloServlet.INSTANCE.getProperty("datasource.schema") != null) {
				PreparedStatement pstmt = null;
				try {
					pstmt = connection.prepareStatement(
							"alter session set current_schema=" + ApoloServlet.INSTANCE.getProperty("datasource.schema"));
					pstmt.execute();
				} finally {
					if (pstmt != null)
						pstmt.close();
				}
			}
			return connection;
		}
	}

	public static String getSQL(String filename) {
		try (Scanner scanner = new Scanner(ApoloServlet.class.getResourceAsStream(filename + ".sql"), "UTF-8")) {
			String text = scanner.useDelimiter("\\A").next();
			return text;
		}
	}

	public static byte[] calcSha256(byte[] content) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.reset();
		md.update(content);
		byte[] output = md.digest();
		return output;
	}

	public static byte[] calcSha1(byte[] content) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		md.reset();
		md.update(content);
		byte[] output = md.digest();
		return output;
	}

	/**
	 * Transoforma array de bytes em String
	 * 
	 * @param buf
	 * @return
	 */
	public static String asHex(byte buf[]) {
		StringBuffer strbuf = new StringBuffer(buf.length * 2);
		int i;

		for (i = 0; i < buf.length; i++) {
			if (((int) buf[i] & 0xff) < 0x10)
				strbuf.append("0");

			strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
		}

		return strbuf.toString();
	}

	private static final DateTimeFormatter dtfBRHHMM = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm");

	public static String formatarDataHoraMinuto(Date d) {
		DateTime dt = new DateTime(d.getTime());
		return dt.toString(dtfBRHHMM);
	}

	private static final DateTimeFormatter dtfBR = DateTimeFormat.forPattern("dd/MM/yyyy");

	public static String formatarData(Date d) {
		DateTime dt = new DateTime(d.getTime());
		return dt.toString(dtfBR);
	}

	public static String removeAcento(String acentuado) {
		if (acentuado == null)
			return null;
		String temp = new String(acentuado);
		temp = temp.replaceAll("[ÃÂÁÀ]", "A");
		temp = temp.replaceAll("[ÉÈÊ]", "E");
		temp = temp.replaceAll("[ÍÌÎ]", "I");
		temp = temp.replaceAll("[ÕÔÓÒ]", "O");
		temp = temp.replaceAll("[ÛÚÙÜ]", "U");
		temp = temp.replaceAll("[Ç]", "C");
		temp = temp.replaceAll("[ãâáà]", "a");
		temp = temp.replaceAll("[éèê]", "e");
		temp = temp.replaceAll("[íìî]", "i");
		temp = temp.replaceAll("[õôóò]", "o");
		temp = temp.replaceAll("[ûúùü]", "u");
		temp = temp.replaceAll("[ç]", "c");
		return temp;
	}

	public static String slugify(String string, boolean lowercase, boolean underscore) {
		if (string == null)
			return null;
		string = string.trim();
		if (string.length() == 0)
			return null;
		string = removeAcento(string);
		// Apostrophes.
		string = string.replaceAll("([a-z])'s([^a-z])", "$1s$2");
		string = string.replaceAll("[^\\w]", "-").replaceAll("-{2,}", "-");
		// Get rid of any - at the start and end.
		string.replaceAll("-+$", "").replaceAll("^-+", "");

		if (underscore)
			string.replaceAll("-", "_");

		return (lowercase ? string.toLowerCase() : string);
	}
}