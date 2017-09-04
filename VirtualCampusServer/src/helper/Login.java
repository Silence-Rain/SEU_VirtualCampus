package helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.UserInfo;
import database.LoginModel;

public class Login {
	private LoginModel model;
	private UserInfo info;
	private boolean loginFlag;
	
	public Login() {
		this.model = new LoginModel();
		this.info = new UserInfo();
		this.loginFlag = false;
	}
	
	public boolean login(BufferedReader br, PrintWriter pw) {
		
		try{
			
			String token = br.readLine();
			info.setInfoByQuery(token);
			ResultSet rs = (ResultSet)model.search(info);

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
		
		return loginFlag ? true : false;
	}

}