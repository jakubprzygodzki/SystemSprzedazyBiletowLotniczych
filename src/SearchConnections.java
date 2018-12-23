
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

import art.copy.ArticleOLD;

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

public class SearchConnections {

	private static final long serialVersionUID = 1L;

	private LinkedList<Dane> aConnectionInfoList = new LinkedList<>();

	public LinkedList<Dane> connectToDB(Dane article) {

		// database connection settings
		String dbURL = "jdbc:mysql://localhost:3306/flights";
		String dbUser = "root";
		String dbPass = "";

		java.sql.Statement aStatement = null;

		try {
			Driver aMySQLDriver = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(aMySQLDriver);
			Connection aConn = aMySQLDriver.connect("jdbc:mysql://localhost:3306/flights?user=root&password=", null);
			aStatement = aConn.createStatement();
			String sSQLquestion = "";
			
			if(article.date.equals("")) {
				
				 sSQLquestion = "Select fromCity, toCity, departureTime, arrivalTime, available, ID_Lotu, DestinationLocal from connections where fromCity LIKE '%"
						+ article.inputFromCity + "%' " + " and toCity LIKE '%" + article.inputToCity + "%'";
			}
			else
			{
				sSQLquestion= "Select fromCity, toCity, departureTime, arrivalTime, available, ID_Lotu, DestinationLocal from connections where fromCity LIKE '%"
						+ article.inputFromCity + "%' " + " and toCity LIKE '%" + article.inputToCity + "%' and available = '" + article.date + "'";
			}
			if (aStatement.execute(sSQLquestion)) 
			{

				ResultSet aData = aStatement.getResultSet();

				while (aData.next()) {

					Dane aConnValues = new Dane();

					aConnValues.setSkad(aData.getString("fromCity"));
					aConnValues.setDokad(aData.getString("toCity"));
					aConnValues.setDeparture(aData.getString("departureTime"));
					aConnValues.setArrival(aData.getString("arrivalTime"));
					aConnValues.setIDLotu(aData.getString("ID_Lotu"));
					aConnValues.setDate(aData.getString("available"));
					aConnValues.setUluru(aData.getString("DestinationLocal"));

					aConnectionInfoList.add(aConnValues);

				}
				return aConnectionInfoList;
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				aStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
