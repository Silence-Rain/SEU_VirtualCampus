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
	
	public Bank() {
		this.model = new BankModel();
	}
	
	public double queryBalance(BankInfo info) {
		
		try{

			ResultSet rs = (ResultSet)model.search(info);
			
			rs.last();
			
			return Double.parseDouble(rs.getString("balance"));

		}
		catch (SQLException e) {
			System.out.println("Database exception");
			e.printStackTrace();

			return 0;
		} 
		
	}
	
	public boolean transfer(BankInfo info) {
		
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
	
	public BankInfo[] queryTranferRecord(BankInfo info) {
		
		try{

			ResultSet rs = (ResultSet)model.search(info);
			Vector<BankInfo> v = new Vector<BankInfo>();
			

			while (rs.next()) {
				BankInfo temp = new BankInfo(rs.getString("userID"), rs.getDouble("balance"), rs.getString("transferTo"),
						rs.getDouble("transferAmount"), rs.getLong("transferDate"));

				v.add(temp);
			}
			
			BankInfo arr[] = (BankInfo[])v.toArray(new BankInfo[rs.getRow()]);
			
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
