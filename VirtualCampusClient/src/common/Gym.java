package common;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Gym implements Serializable{
	String type;
	int time;
	String period;
	String first_name;
	String second_name;
	int gymSurplus;//剩余场地数
	long gymDate;
	  
	public String gettype()
	{
	    return this.type;
	}
	public void settype(String type)
	{
		this.type = type;
	}
	  
	public void settime(int time2)
	{
		this.time = time2;
	}
	public int gettime(){
		return time;
	}
	
	public String getperiod()
	{
	    return this.period;
	}
	public void setperiod(String a)
	{
		this.period = a;
	}
	
	public String getfirst_name()
	{
	    return this.first_name;
	}
	public void setfirst_name(String name)
	{
		this.first_name = name;
	}
	
	public void setsecond_name(String second_name)
	{
		this.second_name = second_name;
	}
	
	public String getsecond_name()
	{
	    return this.second_name;
	}
	  
	public void setgymSurplus(int gymSurplus)
	{
		this.gymSurplus = gymSurplus;
	}
	
	public int getgymSurplus()
	{
		return this.gymSurplus;
		
	}
	public long getDate()
	{
		return this.gymDate;
	}
	public void setdate(String year, String month, String day) throws ParseException {
		String dstr = year+"-"+month+"-"+day;
		SimpleDateFormat simpleDateFormat =new SimpleDateFormat(dstr);
	    Date date2=simpleDateFormat .parse(dstr);
	    gymDate = date2.getTime();
		
	}
	
}
