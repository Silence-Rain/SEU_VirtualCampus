package helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import common.CourseInfo;
import common.CourseStatusInfo;
import database.CourseModel;
import database.CourseStatusModel;

/**
 * 课程模块Controller
 * 
 * @author Silence
 *
 */
public class Course {
	/**
	 * 课程信息Model
	 */
	private CourseModel cModel;
	/**
	 * 课程选择状态Model
	 */
	private CourseStatusModel csModel;
	
	public Course(){
		cModel = new CourseModel();
		csModel = new CourseStatusModel();
	}
	
	/**
	 * 查询全部课程响应函数
	 * 
	 * @return 当前数据库中全部课程信息
	 */
	public CourseInfo[] queryCourse() {
		try {
			ResultSet rs = (ResultSet)cModel.search(null);
			Vector<CourseInfo> v = new Vector<CourseInfo>();
			
			while (rs.next()) {	
				CourseInfo temp = new CourseInfo(rs.getString("ID"), rs.getString("courseName"), rs.getString("teacher"), rs.getString("place"), 
						rs.getString("time"), rs.getDouble("credit"));
				v.add(temp);
				
			}
			
			return (CourseInfo[])v.toArray(new CourseInfo[rs.getRow()]);
			
		} catch (SQLException e) {
			System.out.println("Database exception");
			e.printStackTrace();

			return null;
		}
	}
	
	/**
	 * 增加课程响应函数
	 * 	
	 * @param info 要增加的课程
	 * @return 是否增加成功
	 */
	public boolean addCourse(CourseInfo info) {
		return cModel.insert(info);
	}
	
	/**
	 * 删除课程响应函数
	 * 	
	 * @param info 要删除的课程
	 * @return 是否删除成功
	 */
	public boolean deleteCourse(CourseInfo info) {
		return cModel.delete(info);
	}
	
	/**
	 * 修改课程信息响应函数
	 * 	
	 * @param info 要修改的课程
	 * @return 是否修改成功
	 */
	public boolean modifyCourse(CourseInfo info) {
		return cModel.modify(info);
	}
	
	/**
	 * 选择课程响应函数
	 * 	
	 * @param info 要选择的课程
	 * @return 是否选课成功
	 */
	public boolean selectCourse(CourseStatusInfo info) {
		return csModel.insert(info);
	}
	
	/**
	 * 退选课程响应函数
	 * 	
	 * @param info 要退选的课程
	 * @return 是否退选成功
	 */
	public boolean deselectCourse(CourseStatusInfo info) {
		return csModel.delete(info);
	}
	
	/**
	 * 学生查询课表响应函数
	 * 在tbCourseStatus表中获得用户信息及所选课程ID，再用课程ID在tbCourse表中查询课程详细信息
	 * 
	 * @param info 学生用户信息
	 * @return 当前已选择的课程列表
	 */
	public CourseInfo[] queryCurriculum(CourseStatusInfo info) {
		try {
			ResultSet rs = (ResultSet)csModel.search(info);
			Vector<CourseInfo> v = new Vector<CourseInfo>();
			Vector<CourseInfo> v1 = new Vector<CourseInfo>();
			
			while (rs.next()) {
				CourseInfo temp = new CourseInfo(rs.getString("ID"), "", "", "", "", 0);
				
				v1.add(temp);
			}
			
			for (int i = 0; i < v1.size(); i++) {
				rs = (ResultSet)cModel.search(v1.get(i));
				
				if (rs.next()) {
					CourseInfo temp = new CourseInfo(rs.getString("ID"), rs.getString("courseName"), rs.getString("teacher"), rs.getString("place"), 
						rs.getString("time"), rs.getDouble("credit"));
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
		
	/**
	 * 教师查询选课学生响应函数
	 * 
	 * @param info 要查询的课程
	 * @return 选择课程的学生列表
	 */
	public CourseStatusInfo[] queryStatus(CourseInfo info) {
		try {
			ResultSet rs = (ResultSet)cModel.search(info);
			Vector<CourseStatusInfo> v = new Vector<CourseStatusInfo>();
			
			if (rs.next()) {
				CourseStatusInfo temp = new CourseStatusInfo(rs.getString("ID"), null);
			
				rs = (ResultSet)csModel.search(temp);
		
				while (rs.next()) {	
					CourseStatusInfo temp1 = new CourseStatusInfo(rs.getString("ID"), rs.getString("selector"));
					v.add(temp1);
				}
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
