package seu.vCampus.bz;

import java.util.List;

import common.AppointInfo;

public interface IAppointAdmin {
	public abstract boolean addItemAdmin(String name,String num);
	public abstract boolean deleteItemAdmin(String name);
	public abstract AppointInfo[] inquireItemAdmin();
	public abstract boolean modifyItemAdmin(String name,String[][] array);
}
