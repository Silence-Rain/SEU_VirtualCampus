package helper;

import java.sql.ResultSet;
import java.sql.SQLException;

import common.StudentRollInfo;
import common.UserInfo;
import database.LoginModel;
import database.StudentRollModel;

public class Login{
	private LoginModel model;
	private StudentRollModel srm;
	
	public Login() {
		this.model = new LoginModel();
		this.srm = new StudentRollModel();
	}
	
	public boolean login(UserInfo info) {
		
		try{

			ResultSet rs = (ResultSet)model.search(info);

			rs.next();
			return info.getPwd().equals(rs.getString("u_Pwd")) && info.getType().equals(rs.getString("u_Type"));

		}
		catch (SQLException e) {
			System.out.println("Database exception");
			e.printStackTrace();

			return false;
		} 
		
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