package seu.vCampus.bz;
import java.util.List;

import common.StudentRollInfo;
import common.UserInfo;
public abstract interface IUser{
	
	public abstract StudentRollInfo getMoreInfo(StudentRollInfo sri);
	
	public abstract boolean UserRegister(UserInfo user);//ע���û�
	
	public abstract boolean UserLogout(UserInfo user);//�ǳ��û�
	
}