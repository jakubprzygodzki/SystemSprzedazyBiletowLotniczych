import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
//import org.omg.CORBA_2_3.portable.OutputStream;



public class DownloadTicket extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final int BYTES_DOWNLOAD = 1024;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		System.out.println("request " + request.getParameter("seatNumber"));
//		System.out.println("request " + request.getParameter("pesel"));
//		System.out.println("request " + request.getParameter("arrivalTime"));
//		System.out.println("request " + request.getParameter("departureTime"));
//		System.out.println("request " + request.getParameter("name"));
//		System.out.println("request " + request.getParameter("lastName"));
//		System.out.println("request " + request.getParameter("arrivalPlace"));
//		System.out.println("request " + request.getParameter("departurePlace"));
//		System.out.println("request " + request.getParameter("idLotu"));
//		System.out.println("request " + request.getParameter("lang"));

		String seatNumber = request.getParameter("seatNumber");
		String pesel = request.getParameter("pesel");
		String arrivalTime = request.getParameter("arrivalTime");
		String departureTime = request.getParameter("departureTime");
		String name = request.getParameter("name");
		String lastName = request.getParameter("lastName");
		String arrivalPlace = request.getParameter("arrivalPlace");
		String departurePlace = request.getParameter("departurePlace");
		String date = request.getParameter("date");
		String idLotu = request.getParameter("idLotu");
		String lang = request.getParameter("lang");

		java.sql.Statement aStatement = null;

		try {
			Driver aMySQLDriver = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(aMySQLDriver);
			Connection aConn = aMySQLDriver.connect("jdbc:mysql://localhost:3306/flights?user=root&password=", null);
			aStatement = aConn.createStatement();
			String sSQLquestion = "";

			sSQLquestion = "UPDATE `" + idLotu + "` SET available = 1" + " WHERE id = " + seatNumber;

			aStatement.execute(sSQLquestion);

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				aStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		response.setContentType("text/plain");
		response.setHeader("Content-Disposition", "attachment;filename=TICKET.txt");
		
		String sTicketText = "";
				
		if(lang.equals("PL")) {
			
		
			sTicketText = "Bilet lotniczy \r\n" +
		"\r\n" + "WAT AIRLINES SA\r\n" + 
				"ul. Wojskowa 1/2\r\n"
				+ "00-001 Warszawa\r\n" +
				"\r\n" + "Imie:\r\n" + 
				" " + name + "\r\n" +
				"Nazwisko:\r\n" + " " + lastName
				+ "\r\n" + "PESEL:\r\n" + " " + pesel + 
				"\r\n" + "\r\n" + "Miejscowość odlotu:\r\n" + " "
				+ departurePlace +
				"\r\n" + "Miejscowość przylotu:\r\n" + " " + arrivalPlace + 
				"\r\n"
				+ "Godzina odlotu: \r\n" + " " + arrivalTime + 
				"\r\n" + "Godzina przylotu:\r\n" + " " + departureTime
				+ "\r\n" + "Data lotu:\r\n" + " " + date + 
				"\r\n" + "Nr miejsca: \r\n" + seatNumber + 
				"\r\n" + "\r\n"
				+ "Cena: 350zł ;";
		}
		else if (lang.equals("EN")) 
		{
			sTicketText = "Ticket \r\n" +
					"\r\n" + "WAT AIRLINES SA\r\n" + 
							"ul. Wojskowa 1/2\r\n"
							+ "00-001 Warsaw\r\n" +
							"\r\n" + "Name:\r\n" + 
							" " + name + "\r\n" +
							"Lastname:\r\n" + " " + lastName
							+ "\r\n" + "PESEL:\r\n" + " " + pesel + 
							"\r\n" + "\r\n" + "Departure city:\r\n" + " "
							+ departurePlace +
							"\r\n" + "Arrival city:\r\n" + " " + arrivalPlace + 
							"\r\n"
							+ "Departure hour: \r\n" + " " + arrivalTime + 
							"\r\n" + "Arrival hour:\r\n" + " " + departureTime
							+ "\r\n" + "Date:\r\n" + " " + date + 
							"\r\n" + "Seat number: \r\n" + seatNumber + 
							"\r\n" + "\r\n"
							+ "Price: 350zł ;";
		}
		InputStream input = new ByteArrayInputStream(sTicketText.getBytes("UTF8"));

		int read = 0;
		byte[] bytes = new byte[BYTES_DOWNLOAD];
		OutputStream os = response.getOutputStream();

		while ((read = input.read(bytes)) != -1) {
			os.write(bytes, 0, read);
		}

		os.flush();
	}
}
