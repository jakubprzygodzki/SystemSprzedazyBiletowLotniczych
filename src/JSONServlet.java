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

import art.copy.Article;

public class JSONServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/***************************************************
	 * URL: /jsonservlet doPost(): receives JSON data, parse it, map it and send
	 * back as JSON
	 ****************************************************/
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		List<Dane> daneToJS = new LinkedList<Dane>();

		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String json = "";
		
		if (br != null) {
			json = br.readLine();
		}
		System.out.println("json!!!!!!!!!!!!!!!");
		System.out.println(json);
		ObjectMapper mapper = new ObjectMapper();
		Article article = mapper.readValue(json, Article.class);
	
		response.setContentType("application/json");


		ConnectionToDB connection = new ConnectionToDB();

		LinkedList<Dane> lista = connection.connectToDB(article);

		for (int i = 0; i < lista.size(); i++) {
			daneToJS.add(lista.get(i));
		}

		mapper.writeValue(response.getOutputStream(), lista);

		
	}
}
