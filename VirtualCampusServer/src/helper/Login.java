package helper;

import java.sql.ResultSet;
import java.sql.SQLException;

import common.UserInfo;
import database.LoginModel;

public class Login{
	private LoginModel model;
	private boolean loginFlag;
	private boolean regFlag;
	
	public Login() {
		this.model = new LoginModel();
		this.loginFlag = false;
	}
	
	public boolean login(UserInfo info) {
		
		try{

			ResultSet rs = (ResultSet)model.search(info);

			rs.next();
			loginFlag =  info.getPwd().equals(rs.getString("u_Pwd")) && info.getType().equals(rs.getString("u_Type"));

		}
		catch (SQLException e) {
			System.out.println("Database exception");
			e.printStackTrace();

			return false;
		} 
		
		return loginFlag;
	}
	
	public boolean register(UserInfo info) {
			
		regFlag = model.insert(info);
		
		return regFlag;
	}

}