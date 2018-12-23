
import java.beans.Statement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.codehaus.jackson.map.ObjectMapper;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.sql.Connection.*;
import java.beans.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class ServletSeats extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public LinkedList<Seats> connectToDB(String IDlotu) {

		System.out.println("IDlotu:");
		System.out.println(IDlotu);
		LinkedList<Seats> listaSiedzen = new LinkedList<>();

		final int BUFFER_SIZE = 414096;

		String dbURL = "jdbc:mysql://localhost:3306/flights";
		String dbUser = "root";
		String dbPass = "";

		Connection conn = null; // connection to the database

		String sql = null;

		java.sql.Statement stmt = null;

		try {

			Driver sterownik = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(sterownik);
			Connection conn1 = sterownik.connect("jdbc:mysql://localhost:3306/flights?user=root&password=", null);
			stmt = conn1.createStatement();

			if (stmt.execute("SELECT id, class, available FROM `" + IDlotu + "`")) {

				ResultSet zbior = stmt.getResultSet();
				
				while (zbior.next()) {

					Seats dane = new Seats();

					dane.nr = zbior.getString("id");
					dane.seatClass = zbior.getString("class");
					dane.available = zbior.getString("available");

					listaSiedzen.add(dane);
				}
				return listaSiedzen;
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Dane> daneToJS = new LinkedList<Dane>();

		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String json = "";

		if (br != null) {
			json = br.readLine();
		}


		ObjectMapper mapper = new ObjectMapper();
		Seats article = mapper.readValue(json, Seats.class);

		response.setContentType("application/json");

		LinkedList<Seats> lista = this.connectToDB(article.nr);

		for (int i = 0; i < lista.size(); i++) {
			System.out.println(lista.get(i).nr);
			System.out.println(lista.get(i).seatClass);
			System.out.println(lista.get(i).available);
		}

		mapper.writeValue(response.getOutputStream(), lista);
	}

}
