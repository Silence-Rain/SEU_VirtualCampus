package helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import common.AppointInfo;
import common.AppointStatusInfo;
import database.AppointModel;
import database.AppointStatusModel;

public class Appoint {
	
	private AppointModel am;
	private AppointStatusModel asm;
	
	public Appoint() {
		am = new AppointModel();
		asm = new AppointStatusModel();
	}
	
	public AppointInfo[] queryAppointItem(AppointInfo info) {
		try {
			ResultSet rs = (ResultSet)am.search(info);
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
	
	public boolean addItem(AppointInfo info) {
		return am.insert(info);
	}
	
	public boolean deleteItem(AppointInfo info) {
		return am.delete(info);
	}
	
	public boolean modifyItem(AppointInfo info) {
		return am.modify(info);
	}
	
	public boolean addStatus(AppointStatusInfo info) {
		return asm.insert(info);
	}
	
	public boolean deleteStatus(AppointStatusInfo info) {
		return asm.delete(info);
	}
	
	public boolean modifyStatus(AppointStatusInfo info) {
		return asm.modify(info);
	}
	
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
