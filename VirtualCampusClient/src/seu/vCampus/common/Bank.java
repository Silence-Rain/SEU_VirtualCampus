package seu.vCampus.common;
import java.io.Serializable;
//序列化，使对象可以序列化
import java.util.Date;
import java.text.SimpleDateFormat;

public class Bank implements Serializable{

	double cardNum;
	String password;
	double balance;
	double money;
	double receiverNum;
	Date date;


public double getcardNum()
{
	return this.cardNum;
}
public void setcardNum(double cardNum)
{
	this.cardNum = cardNum;
}
public String getpassword()
{
	return this.password;
}
public void setpassword(String password)
{
	this.password = password;
}
public double getbalance()
{
	return this.balance;
}
public void setbalance(double balance)
{
	this.balance = balance;
}
public double getmoney()
{
	return this.money;
}
public void setmoney(double money)
{
	this.money = money;
}
public double getreceiverNum()
{
	return this.receiverNum;
}
public void setreceiverNum(double receiverNum)
{
	this.receiverNum = receiverNum;
}
public Date getdate()
{
	return this.date;
}
public void setdate(Date date)
{
	this.date = date;
}

}
