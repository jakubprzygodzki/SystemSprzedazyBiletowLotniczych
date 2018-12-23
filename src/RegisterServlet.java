import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;



public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String json = "";

		if (br != null) {
			json = br.readLine();
		}

		System.out.println(json);

		ObjectMapper mapper = new ObjectMapper();
		RegisterData register = mapper.readValue(json, RegisterData.class);

		// size of byte buffer to send file
		final int BUFFER_SIZE = 414096;

		// database connection settings
		String dbURL = "jdbc:mysql://localhost:3306/flights";
		String dbUser = "root";
		String dbPass = "";

		Connection conn = null; // connection to the database

		String sql = null;
		java.sql.Statement stmt = null;
		java.sql.Statement stmt2 = null;

		try {
			Driver sterownik = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(sterownik);
			Connection conn1 = sterownik.connect("jdbc:mysql://localhost:3306/flights?user=root&password=", null);

			stmt = conn1.createStatement();
			stmt2 = conn1.createStatement();

			if (stmt.execute("Select login, email, password from uzytkownicy where login = '" + register.login + "'")) {

				ResultSet zbior = stmt.getResultSet();

				if (zbior.next()) {

					mapper.writeValue(response.getOutputStream(), "Uzytkownik o podanym loginie już istnieje!");
				} else {

					boolean bStatement2 = stmt2.execute(
							"INSERT INTO uzytkownicy (login, email, password) VALUES (" + "'" + register.login + "'"
									+ ", " + "'" + register.email + "'" + ", " + "'" + register.password + "'" + ")");
					mapper.writeValue(response.getOutputStream(), "Zarejestrowano!");
				}
				System.out.println("pooo");
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				stmt.close();
				stmt2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}