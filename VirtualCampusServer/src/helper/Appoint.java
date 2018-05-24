package helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import common.AppointInfo;
import common.AppointStatusInfo;
import database.AppointModel;
import database.AppointStatusModel;

/**
 * 场馆预约模块Controller
 * 
 * @author Silence
 *
 */
public class Appoint {
	
	/**
	 * 预约项目Model
	 */
	private AppointModel am;
	/**
	 * 预约记录Model
	 */
	private AppointStatusModel asm;
	
	public Appoint() {
		am = new AppointModel();
		asm = new AppointStatusModel();
	}
	
	/**
	 * 查询预约项目响应函数
	 * 向客户端返回所有项目的信息
	 * 
	 * @return 所有项目的信息
	 */
	public AppointInfo[] queryAppointItem() {
		try {
			ResultSet rs = (ResultSet)am.search(null);
			Vector<AppointInfo> v = new Vector<AppointInfo>();
			
			while (rs.next()) {
				AppointInfo temp = new AppointInfo(rs.getString("item"), rs.getString("itemRemain"));
				v.add(temp);				
			}
			
			AppointInfo[] arr = (AppointInfo[])v.toArray(new AppointInfo[rs.getRow()]);
			
			return arr;
			
		} catch (SQLException e) {
			System.out.println("Database exception");
			e.printStackTrace();

			return null;
		}
	}
	
	/**
	 * 添加项目响应函数
	 * 
	 * @param info 要添加的项目
	 * @return 是否添加成功
	 */
	public boolean addItem(AppointInfo info) {
		return am.insert(info);
	}
	
	/**
	 * 删除项目响应函数
	 * 
	 * @param info 要删除的项目
	 * @return 是否删除成功
	 */
	public boolean deleteItem(AppointInfo info) {
		return am.delete(info);
	}
	
	/**
	 * 修改项目信息响应函数
	 * 
	 * @param info 要修改的项目
	 * @return 是否修改成功
	 */
	public boolean modifyItem(AppointInfo info) {
		return am.modify(info);
	}
	
	/**
	 * 添加预约响应函数
	 * 如果添加成功，则在项目信息中将此时间段剩余次数减1
	 * 
	 * @param info 要预约的项目
	 * @return 是否预约成功
	 */
	public boolean addStatus(AppointStatusInfo info) {
		
		AppointInfo temp = new AppointInfo(info.getItem(), "");
		ResultSet rs = (ResultSet)am.search(temp);
		
		try {
			rs.next();
			temp.setItemRemain(rs.getString("itemRemain"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String[][] cur = temp.getItemRemain();
		cur[info.getAppointDate()][info.getAppointTime()] = String.valueOf(Integer.parseInt(cur[info.getAppointDate()][info.getAppointTime()]) - 1);
		
		return asm.insert(info) && am.modify(temp);
	}
	
	/**
	 * 取消预约响应函数
	 * 
	 * @param info 要取消的预约
	 * @return 是否取消成功
	 */
	public boolean deleteStatus(AppointStatusInfo info) {
		return asm.delete(info);
	}
	
	/**
	 * 修改预约响应函数
	 * 
	 * @param info 要修改的预约
	 * @return 是否修改成功
	 */
	public boolean modifyStatus(AppointStatusInfo info) {
		return asm.modify(info);
	}
	
	/**
	 * 查询预约记录响应函数
	 * 
	 * @param info 当前用户信息
	 * @return 当前用户的所有预约记录
	 */
	public AppointStatusInfo[] queryStatus(AppointStatusInfo info) {
		try {
			ResultSet rs = (ResultSet)asm.search(info);
			Vector<AppointStatusInfo> v = new Vector<AppointStatusInfo>();
			
			while (rs.next()) {
				AppointStatusInfo temp = new AppointStatusInfo(rs.getString("userID"), rs.getString("item"), rs.getInt("appointDate")
						, rs.getInt("appointTime"), rs.getInt("timestamp"));
				v.add(temp);				
			}
			
			AppointStatusInfo[] arr = (AppointStatusInfo[])v.toArray(new AppointStatusInfo[rs.getRow()]);
			
			return arr;
			
		} catch (SQLException e) {
			System.out.println("Database exception");
			e.printStackTrace();

			return null;
		}
	}

}
