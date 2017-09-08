package seu.vCampus.bz;
import seu.vCampus.common.Bank;


public abstract interface IBank {
	public abstract String checkAccount(String paramString1, String paramString2);

	public abstract boolean transferAccount(String money,String receiveID );

}

