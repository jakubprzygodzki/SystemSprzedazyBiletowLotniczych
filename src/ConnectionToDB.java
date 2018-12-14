
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

import art.copy.Article;

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

public class ConnectionToDB {

	private static final long serialVersionUID = 1L;
	private LinkedList<Dane> listaPasujacych = new LinkedList<>();

	public LinkedList<Dane> connectToDB (Article article) {

// size of byte buffer to send file
	final int BUFFER_SIZE = 414096;

	// database connection settings
	String dbURL = "jdbc:mysql://localhost:3306/flights";
	String dbUser = "root";
	String dbPass = "";

	Connection conn=null; // connection to the database

	//String req = request.getParameter("fromCity");
		System.out.println("przerzucam do post");
		System.out.println("request:" + article);



		String sql = null;
		java.sql.Statement stmt = null;

		try {
			Driver sterownik = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(sterownik);
			Connection conn1 = sterownik.connect("jdbc:mysql://localhost:3306/flights?user=root&password=", null);
			stmt = conn1.createStatement();

			String haslo = "";
			System.out.println("1111");
			if (stmt.execute("Select fromCity, toCity, departureTime, ID_Lotu from connections where fromCity LIKE '%"+article.inputFromCity+"%' "
					+ " and toCity LIKE '%"+article.inputToCity+"%'")) {

				ResultSet zbior = stmt.getResultSet();

				while (zbior.next()) {
					
					Dane dane = new Dane();
				
					dane.setSkad(zbior.getString("fromCity"));
					dane.setDokad(zbior.getString("toCity"));	
					dane.setOdlot(zbior.getString("departureTime"));
					dane.setIDLotu(zbior.getString("ID_Lotu"));
				
					listaPasujacych.add(dane);

				}
				System.out.println("pooo");

				return listaPasujacych;
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
		//return null;
		return null;
	}
}
