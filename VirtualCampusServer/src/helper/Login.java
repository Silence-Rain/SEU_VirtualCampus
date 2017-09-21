package helper;

import java.sql.ResultSet;
import java.sql.SQLException;

import common.StudentRollInfo;
import common.UserInfo;
import database.LoginModel;
import database.StudentRollModel;

/**
 * 登录模块Controller
 * 
 * @author Silence
 *
 */
public class Login{
	/**
	 * 登录模块Model
	 */
	private LoginModel model;
	/**
	 * 学生学籍模块Model
	 */
	private StudentRollModel srm;
	
	public Login() {
		this.model = new LoginModel();
		this.srm = new StudentRollModel();
	}
	
	/**
	 * 登录操作响应函数
	 * 当用户的ID，密码，类型均与数据库中记录匹配时，登录成功
	 * 
	 * @param info 当前登录用户信息
	 * @return 是否登录成功
	 */
	public boolean login(UserInfo info) {
		
		try{

			ResultSet rs = (ResultSet)model.search(info);

			if (rs.next())
				return info.getPwd().equals(rs.getString("u_Pwd")) && info.getType().equals(rs.getString("u_Type"));
			
			return false;

		}
		catch (SQLException e) {
			System.out.println("Database exception");
			e.printStackTrace();

			return false;
		} 
		
	}
	
	/**
	 * 注册操作响应函数
	 * 当用户输入的ID，姓名与学籍信息中记录匹配时，可以注册
	 * 
	 * @param info 当前注册用户信息
	 * @return 是否注册成功
	 */
	public boolean register(UserInfo info) {
		StudentRollInfo temp = new StudentRollInfo(info.getStuId(), info.getName(), null, null, null, null, null, null, null, null, null, null);
		
		try {
			ResultSet rs = (ResultSet)srm.search(temp);
			if (rs.next())
				return model.insert(info);
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}