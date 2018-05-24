package common;

import java.io.Serializable;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Bank implements Serializable {

	
	String cardNum;
	String password;
	double balance;
	String money;
	String receiverNum;
	Long date;

	public String getcardNum() {
		return this.cardNum;
	}

	public void setcardNum(String bCad) {
		this.cardNum = bCad;
	}

	public String getpassword() {
		return this.password;
	}

	public void setpassword(String password) {
		this.password = password;
	}

	public double getbalance() {
		return this.balance;
	}

	public void setbalance(double balance) {
		this.balance = balance;
	}

	public String getmoney() {
		return this.money;
	}

	public void setmoney(String money2) {
		this.money = money2;
	}

	public String getreceiverNum() {
		return this.receiverNum;
	}

	public void setreceiverNum(String receiver) {
		this.receiverNum = receiver;
	}

	public Long getdate() {
		return this.date;
	}

	// public void setdate(Date date)
	// {
	// this.date = date;
	// }
	public void setdate(String year, String month, String day) throws ParseException {
		// TODO Auto-generated method stub
		// SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String dstr = year + "-" + month + "-" + day;
		// java.util.Date date1 = sdf.parse(dstr);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dstr);
		Date date2 = simpleDateFormat.parse(dstr);
		date = date2.getTime();

	}

}
