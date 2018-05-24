package helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import common.StudentRollInfo;
import database.StudentRollModel;

/**
 * 学生学籍信息Controller
 * 
 * @author Silence
 *
 */
public class StudentRoll {
	/**
	 * 学生学籍信息Model
	 */
	private StudentRollModel model;
	
	public StudentRoll() {
		this.model = new StudentRollModel();
	}
	
	/**
	 * 查找学生信息响应函数
	 * 根据提供的不同key（学号，姓名）返回结果
	 * 
	 * @param info 查找的key
	 * @return 所查询学生详细信息
	 */
	public StudentRollInfo query(StudentRollInfo info) {
		try {
			ResultSet rs = (ResultSet)model.search(info);
			
			if (rs.next()) {
				return new StudentRollInfo(rs.getString("ID"), rs.getString("stuName"), rs.getString("age"), rs.getString("gender"), 
					rs.getString("birthday"), rs.getString("birthPlace"), rs.getString("entranceTime"), rs.getString("photo"), rs.getString("nation"), 
					rs.getString("department"), rs.getString("major"), rs.getString("dormitory"));
			}
			
			return null;
			
		} catch (SQLException e) {
			System.out.println("Database exception");
			e.printStackTrace();

			return null;
		} 
	}
	
	/**
	 * 查询所有学生信息响应函数
	 * 
	 * @return 所有学生详细信息
	 */
	public StudentRollInfo[] queryAll() {
		try {
			ResultSet rs = (ResultSet)model.search(null);
			Vector<StudentRollInfo> v = new Vector<StudentRollInfo>();
			
			while (rs.next()) {
				StudentRollInfo temp = new StudentRollInfo(rs.getString("ID"), rs.getString("stuName"), rs.getString("age"), rs.getString("gender"), 
						rs.getString("birthday"), rs.getString("birthPlace"), rs.getString("entranceTime"), rs.getString("photo"), rs.getString("nation"), 
						rs.getString("department"), rs.getString("major"), rs.getString("dormitory"));
				v.add(temp);
			}
			
			return (StudentRollInfo[])v.toArray(new StudentRollInfo[rs.getRow()]);
			
		} catch (SQLException e) {
			System.out.println("Database exception");
			e.printStackTrace();

			return null;
		} 
	}

	/**
	 * 添加学生信息响应函数
	 * 
	 * @param info 要添加的学生
	 * @return 是否添加成功
	 */
	public boolean addInfo(StudentRollInfo info) {
		return model.insert(info);
	}
	
	/**
	 * 修改学生信息响应函数
	 * 
	 * @param info 要修改的学生
	 * @return 是否修改成功
	 */
	public boolean modifyInfo(StudentRollInfo info) {
		return model.modify(info);
	}
	
	/**
	 * 删除学生信息响应函数
	 * 
	 * @param info 要删除的学生
	 * @return 是否删除成功
	 */
	public boolean deleteInfo(StudentRollInfo info) {
		return model.delete(info);
	}
	
}
