package helper;

import java.sql.ResultSet;
import java.sql.SQLException;

import common.StudentRollInfo;
import common.UserInfo;
import database.LoginModel;
import database.StudentRollModel;

public class Login{
	private LoginModel model;
	private boolean loginFlag;
	private StudentRollModel srm;
	
	public Login() {
		this.model = new LoginModel();
		this.loginFlag = false;
		this.srm = new StudentRollModel();
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
		StudentRollInfo temp = new StudentRollInfo(info.getStuId(), null, null, null, null, null, null, null, null, null, null, null);
		
		try {
			if (((ResultSet)srm.search(temp)).next())
				return model.insert(info);
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}