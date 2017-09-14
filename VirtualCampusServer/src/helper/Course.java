package helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import common.CourseInfo;
import common.CourseStatusInfo;
import database.CourseModel;
import database.CourseStatusModel;

public class Course {
	private CourseModel cModel;
	private CourseStatusModel csModel;
	
	public Course(){
		cModel = new CourseModel();
		csModel = new CourseStatusModel();
	}
	
	public CourseInfo queryCourse(CourseInfo info) {
		try {
			ResultSet rs = (ResultSet)cModel.search(info);
			
			if (rs.next()) {	
				return new CourseInfo(rs.getInt("ID"), rs.getString("courseName"), rs.getString("teacher"), rs.getString("place"), 
						rs.getString("time"), rs.getInt("credit"));
				
			}
			
			return null;
			
		} catch (SQLException e) {
			System.out.println("Database exception");
			e.printStackTrace();

			return null;
		}
	}
	
	public boolean addCourse(CourseInfo info) {
		return cModel.insert(info);
	}
	
	public boolean deleteCourse(CourseInfo info) {
		return cModel.delete(info);
	}
	
	public boolean modifyCourse(CourseInfo info) {
		return cModel.modify(info);
	}
	
	public boolean selectCourse(CourseStatusInfo info) {
		return csModel.insert(info);
	}
	
	public boolean deselectCourse(CourseStatusInfo info) {
		return csModel.delete(info);
	}
	
	public CourseInfo[] queryCurriculum(CourseStatusInfo info) {
		try {
			ResultSet rs = (ResultSet)csModel.search(info);
			Vector<CourseInfo> v = new Vector<CourseInfo>();
			Vector<CourseInfo> v1 = new Vector<CourseInfo>();
			
			while (rs.next()) {
				CourseInfo temp = new CourseInfo(rs.getInt("ID"), "", "", "", "", 0);
				
				v1.add(temp);
			}
			
			for (CourseInfo i: v1) {
				rs = (ResultSet)cModel.search(i);
				
				if (rs.next()) {	
					CourseInfo temp = new CourseInfo(rs.getInt("ID"), rs.getString("courseName"), rs.getString("teacher"), rs.getString("place"), 
							rs.getString("time"), rs.getInt("credit"));
					v.add(temp);
				}
			}

			CourseInfo arr[] = (CourseInfo[])v.toArray(new CourseInfo[rs.getRow()]);
			
			return arr;
			
		} catch (SQLException e) {
			System.out.println("Database exception");
			e.printStackTrace();

			return null;
		}
	}
	
	public CourseStatusInfo[] queryStatus(CourseStatusInfo info) {
		try {
			ResultSet rs = (ResultSet)csModel.search(info);
			Vector<CourseStatusInfo> v = new Vector<CourseStatusInfo>();
		
			while (rs.next()) {	
				CourseStatusInfo temp = new CourseStatusInfo(rs.getInt("ID"), rs.getString("courseName"), rs.getString("selector"));
				v.add(temp);
			}

			CourseStatusInfo arr[] = (CourseStatusInfo[])v.toArray(new CourseStatusInfo[rs.getRow()]);
			
			return arr;
			
		} catch (SQLException e) {
			System.out.println("Database exception");
			e.printStackTrace();

			return null;
		}
	}
}
