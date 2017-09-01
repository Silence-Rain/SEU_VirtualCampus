package server_impl.login;

import java.io.*;
import java.net.*;
import java.sql.*;

public class Login {

	public static void main(String[] args) {
		
		Login lg = new Login();
		Connection con = null;
		
		try{
			Class.forName("com.hxtt.sql.access.AccessDriver");
			System.out.println("Driver loaded");
			
			String url = "jdbc:Access:///d:/VirtualCampus/db/vCampus.accdb";
			con = DriverManager.getConnection(url);
			System.out.println("Database connected");
		}
		catch (ClassNotFoundException e){
			System.out.println("Fail to load driver");
		}
		catch (SQLException e){
			System.out.println("Fail to connect database");
		}
		
		lg.login(con);
	}
	
	public boolean login(Connection con) {
		
		boolean loginFlag = false;
		
		ServerSocket server = null;
		Socket socket = null;
		
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		
		OutputStream os = null;
		PrintWriter pw = null;
		
		try{
			
			server = new ServerSocket(8080);
			socket = server.accept();			
			
			is = socket.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			
			String usr = br.readLine();
			String pwd = br.readLine();

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from tbUser where u_ID='" + usr + "';");
			
			os = socket.getOutputStream();
			pw = new PrintWriter(os);
				
			rs.next();
			if (pwd.equals(rs.getString("u_Pwd"))) {
				pw.println("Login Success");
				pw.flush();

				loginFlag = true;
			}
			else{
				pw.println("Wrong username or password");
				pw.flush();

				loginFlag = false;
			}
			
			pw.close();
			os.close();
			br.close();
			isr.close();
			is.close();
			socket.close();
			server.close();
			
			return loginFlag ? true : false;

		}
		catch (SQLException e) {
			System.out.println("Database exception");
			e.printStackTrace();

			return false;
		} 
		catch (IOException e) {
			System.out.println("IO exception");
			e.printStackTrace();

			return false;
		}
	}

}
