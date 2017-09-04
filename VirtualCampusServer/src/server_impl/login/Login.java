package server_impl.login;

import java.io.*;
import java.net.*;
import java.sql.*;

import common.UserInfo;
import database.LoginModel;

public class Login {
	private LoginModel model;
	private UserInfo info;
	private boolean loginFlag;

	public static void main(String[] args) {
		
		Login lg = new Login();

		try {
			lg.login();
		} catch (IOException e) {
			System.out.println("Fail to release resource");
			e.printStackTrace();
		}
	}
	
	public Login() {
		this.model = new LoginModel();
		this.info = new UserInfo();
		this.loginFlag = false;
	}
	
	public boolean login() throws IOException {

		ServerSocket server = null;
		Socket socket = null;
		
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		
		OutputStream os = null;
		PrintWriter pw = null;
		
		try{
			
			server = new ServerSocket(9001);
			socket = server.accept();			
			
			is = socket.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			
			String detail = br.readLine();
			info.setInfoByQuery(detail);
			ResultSet rs = (ResultSet)model.search(info);
			
			os = socket.getOutputStream();
			pw = new PrintWriter(os);
				
			rs.next();
			if (info.getPwd().equals(rs.getString("u_Pwd")) && info.getType().equals(rs.getString("u_Type"))) {
				pw.println("Login Success");
				pw.flush();

				loginFlag = true;
			}
			else{
				pw.println("Wrong username or password");
				pw.flush();

				loginFlag = false;
			}

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
		finally {
			pw.close();
			os.close();
			br.close();
			isr.close();
			is.close();
			socket.close();
			server.close();
		}
		
		return loginFlag ? true : false;
	}

}
