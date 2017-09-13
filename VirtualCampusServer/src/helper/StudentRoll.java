package helper;

import java.sql.ResultSet;
import java.sql.SQLException;

import common.StudentRollInfo;
import database.StudentRollModel;

public class StudentRoll {
	private StudentRollModel model;
	
	public StudentRoll() {
		this.model = new StudentRollModel();
	}
	
	public StudentRollInfo query(StudentRollInfo info) {
		try {
			ResultSet rs = (ResultSet)model.search(info);
			
			rs.next();
			
			return new StudentRollInfo(rs.getString("ID"), rs.getString("stuName"), rs.getString("age"), rs.getString("gender"), 
					rs.getString("birthday"), rs.getString("birthPlace"), rs.getString("entranceTime"), rs.getString("photo"), rs.getString("nation"), 
					rs.getString("department"), rs.getString("major"), rs.getString("dormitory"));
			
		} catch (SQLException e) {
			System.out.println("Database exception");
			e.printStackTrace();

			return null;
		} 
	}

	public boolean addInfo(StudentRollInfo info) {
		return model.insert(info);
	}
	
	public boolean modifyInfo(StudentRollInfo info) {
		return model.modify(info);
	}
	
	public boolean deleteInfo(StudentRollInfo info) {
		return model.delete(info);
	}
	
}
