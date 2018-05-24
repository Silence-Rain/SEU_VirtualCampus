package seu.vCampus.bz;
import java.util.List;

import common.Bank;


public abstract interface IBank {
	public abstract double checkAccount(String bCad);

	public abstract boolean transferAccount(double money,String receiver,String pwd,Long time,double b);
	
	public abstract List record();

}

