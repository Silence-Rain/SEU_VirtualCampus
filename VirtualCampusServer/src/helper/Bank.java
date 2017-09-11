package helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import common.BankInfo;
import database.BankModel;

public class Bank {
	private BankModel model;
	private BankInfo info;
	
	public Bank() {
		this.model = new BankModel();
		this.info = new BankInfo();
	}
	
	public String queryBalance(String token) {
		
		try{

			info.setInfoByQuery(token);
			ResultSet rs = (ResultSet)model.search(info);
			
			rs.last();
			
			return rs.getString("balance");

		}
		catch (SQLException e) {
			System.out.println("Database exception");
			e.printStackTrace();

			return null;
		} 
		
	}
	
	public boolean transfer(String token) {
			
		info.setInfoByQuery(token);
		
		info.setBalance(info.getBalance() + info.getTransferAmount());
		
		if (model.insert(info)) {
			String temp = info.getId();
			info.setId(info.getTransferTo());
			info.setTransferTo(temp);
			info.setTransferAmount(-info.getTransferAmount());
			
			ResultSet rs = (ResultSet)model.search(info);
			try {
				rs.last();
				info.setBalance(rs.getDouble("balance") + info.getTransferAmount());
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if (model.insert(info)) {
				return true;
			}
		}
		
		return false;

	}
	
	public String[] queryTranferRecord(String token) {
		
		try{

			info.setInfoByQuery(token);
			ResultSet rs = (ResultSet)model.search(info);
			Vector<String> v = new Vector<String>();
			

			while (rs.next()) {
				String row = rs.getString("userID") + "\t" + rs.getString("balance") + "\t" + rs.getString("transferTo") + "\t" + rs.getString("transferAmount") + "\t" 
						+ timestampToDate(rs.getString("transferDate"));
				v.add(row);
			}
			
			String arr[] = (String[])v.toArray(new String[rs.getRow()]);
			
			return arr;

		}
		catch (SQLException e) {
			System.out.println("Database exception");
			e.printStackTrace();

			return null;
		} 
		
	}
	
	private String timestampToDate(String time) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    long timeL = Long.valueOf(Long.parseLong(time));
	    return sdf.format(new Date(timeL * 1000L));
	}
}
