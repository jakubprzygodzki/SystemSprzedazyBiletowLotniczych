import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;



public class JSONServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		BufferedReader aBr = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String sInputData = "";
		
		if (aBr != null)
		{
			sInputData = aBr.readLine();
		}
		
		
		System.out.println(sInputData);

		ObjectMapper mapper = new ObjectMapper();
		Dane aConnectionUserParams = mapper.readValue(sInputData, Dane.class);
	
		response.setContentType("application/json");

		SearchConnections connection = new SearchConnections();

		LinkedList<Dane> lista = connection.connectToDB(aConnectionUserParams);

		mapper.writeValue(response.getOutputStream(), lista);

		
	}
}
