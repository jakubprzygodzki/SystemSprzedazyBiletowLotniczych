import java.sql.*;

public class Validate {
	public static boolean checkUser(String login, String pass) {
		boolean st = false;
		try {

			// loading drivers for mysql
			Class.forName("com.mysql.jdbc.Driver");

			// creating connection with the database
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/flights", "root", "");
			PreparedStatement ps = con.prepareStatement("select login from uzytkownicy where login=? and password=?");
			ps.setString(1, login);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();
			st = rs.next();

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("st" + st);
		return st;
	}
}