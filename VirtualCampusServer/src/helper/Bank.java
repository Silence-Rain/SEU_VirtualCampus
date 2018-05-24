package helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import common.BankInfo;
import database.BankModel;

/**
 * 银行模块Controller
 * 
 * @author Silence
 *
 */
public class Bank {
	/**
	 * 银行模块Model
	 */
	private BankModel model;
	
	public Bank() {
		this.model = new BankModel();
	}
	
	/**
	 * 查询余额响应函数
	 * 返回当前用户所有余额记录中，按转账时间升序，最后一条余额
	 * 
	 * @param info 当前用户信息
	 * @return 用户余额
	 */
	public double queryBalance(BankInfo info) {
		
		try{

			ResultSet rs = (ResultSet)model.search(info);
			
			rs.last();//取最后一条记录
			
			return Double.parseDouble(rs.getString("balance"));

		}
		catch (SQLException e) {
			System.out.println("Database exception");
			e.printStackTrace();

			return 0;
		} 
		
	}
	
	/**
	 * 转账消息响应函数
	 * 每次转账向表中写入两条转账信息（转账人和被转账人各一条）
	 * 新添加的记录中同时修改两人的余额信息
	 * 
	 * @param info 转账信息
	 * @return 是否转账成功
	 */
	public boolean transfer(BankInfo info) {
		
		info.setBalance(info.getBalance() - info.getTransferAmount());
		
		if (model.insert(info)) {
			BankInfo temp = new BankInfo(info.getTransferTo(), 0, info.getPwd(), info.getId(), -info.getTransferAmount(), info.getTransferDate());
			
			ResultSet rs = (ResultSet)model.search(temp);
			try {
				if (rs.last())
					temp.setBalance(rs.getDouble("balance") - temp.getTransferAmount());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if (model.insert(temp)) {
				return true;
			}
		}
		
		return false;

	}
	
	/**
	 * 查询转账记录响应函数
	 * 
	 * @param info 当前用户信息
	 * @return 当前用户所有转账记录
	 */
	public BankInfo[] queryTranferRecord(BankInfo info) {
		
		try{

			ResultSet rs = (ResultSet)model.search(info);
			Vector<BankInfo> v = new Vector<BankInfo>();

			while (rs.next()) {
				BankInfo temp = new BankInfo(rs.getString("userID"), rs.getDouble("balance"), rs.getString("pwd"), rs.getString("transferTo"),
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
}
