import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
//import org.omg.CORBA_2_3.portable.OutputStream;

import art.copy.ArticleOLD;

public class DownloadTicket extends HttpServlet {
	
	//private static final int BYTES_DOWNLOAD = 1024;
	
	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
		
//		System.out.println(request.getParameter("file"));
//		System.out.println("ok");
//		
//		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
//		String json = "";
//		
//		if (br != null) {
//			json = br.readLine();
//		}
//		
//		System.out.println();
//		System.out.println(json);
//		
//		ObjectMapper mapper = new ObjectMapper();
//		Ticket ticket = mapper.readValue(json, Ticket.class);
		
//		String odp = "xcvxvxcvxcvxcvzsdfsdf";
//		byte[] bufor = new byte[odp.length()];
//		
//		response.setContentType("text/plain");
//		response.addHeader("Content-Disposition", "attachment;filename=bilet.txt");
//		OutputStream os = response.getOutputStream();
//		os.write(bufor);
//		os.flush();
		
	    private static final int BYTES_DOWNLOAD = 1024;

//	    public void init(ServletConfig config) throws ServletException {
//	        super.init(config);
//	    }

	    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
	                                                                                       IOException 
	    
	    {
	    	
	    	
	    	System.out.println("request " + request.getParameter("seatNumber"));
	    	System.out.println("request " + request.getParameter("pesel"));
	    	System.out.println("request " + request.getParameter("arrivalTime"));
	    	System.out.println("request " + request.getParameter("departureTime"));
	    	System.out.println("request " + request.getParameter("name"));
	    	System.out.println("request " + request.getParameter("lastName"));
	    	System.out.println("request " + request.getParameter("arrivalPlace"));
	    	System.out.println("request " + request.getParameter("departurePlace"));
	    	
	    	
	    	String seatNumber = request.getParameter("seatNumber");
	    	String pesel = request.getParameter("pesel");
	    	String arrivalTime = request.getParameter("arrivalTime");
	    	String departureTime = request.getParameter("departureTime");
	    	String name = request.getParameter("name");
	    	String lastName =request.getParameter("lastName");
	    	String arrivalPlace = request.getParameter("arrivalPlace");
	    	String departurePlace = request.getParameter("departurePlace");
	    	String date = request.getParameter("date");
	    	
//			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
//			String json = "";
//			
//			if (br != null)
//			{
//				json = br.readLine();
//			}
//			
//			System.out.println("json " + json);
//			ObjectMapper mapper = new ObjectMapper();
			//
			//ObjectMapper mapper = new ObjectMapper();
			
			//Ticket aTicket = mapper.readValue(json, Ticket.class);
			
			//System.out.println("aTicket " + aTicket.pesel);
			//System.out.println("aTicket " + aTicket.arrivalPlace);

	            
	    	response.setContentType("text/plain");
	        response.setHeader("Content-Disposition", "attachment;filename=downloadname.txt");

	        
	        String s = "Bilet lotniczy \r\n" + 
	        		"\r\n" + 
	        		"WAT AIRLINES SA\r\n" + 
	        		"ul. Wojskowa 1/2\r\n" + 
	        		"00-001 Warszawa\r\n" + 
	        		"\r\n" + 
	        		"Imie:\r\n" + " " + name +"\r\n" +
	        		"Nazwisko:\r\n" + " " + lastName + "\r\n" +
	        		"PESEL:\r\n" + " " + pesel + "\r\n" +
	        		"\r\n" + 
	        		"Miejscowość odlotu:\r\n" + " " + departurePlace + "\r\n" +
	        		"Miejscowość przylotu:\r\n" + " " + arrivalPlace + "\r\n" +
	        		"Godzina odlotu: \r\n" + " " + arrivalTime + "\r\n" +
	        		"Godzina przylotu:\r\n" + " " + departureTime + "\r\n" +
	        		"Data lotu:\r\n" + " " + date + "\r\n" +
	        		"\r\n" + 
	        		"Cena: 350zł ;";
	        
	        InputStream input = new ByteArrayInputStream(s.getBytes("UTF8"));

	        int read = 0;
	        byte[] bytes = new byte[BYTES_DOWNLOAD];
	        OutputStream os = response.getOutputStream();

	        while ((read = input.read(bytes)) != -1) {
	            os.write(bytes, 0, read);
	        }
	        System.out.println("os");
	        System.out.println(os);
	        os.flush();
	        System.out.println("os.flush()");
	        System.out.println();
	        //os.close();
	        
//			ObjectMapper mapper = new ObjectMapper();
//			Ticket ticket = mapper.readValue(json, Ticket.class);
//			
//			mapper.writeValue(response.getOutputStream(), ticket);
	    }
		
	        //System.out.println("ok");
	
	}
	


