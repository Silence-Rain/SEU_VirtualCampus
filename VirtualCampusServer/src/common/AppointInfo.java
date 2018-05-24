package common;

import java.io.Serializable;

/**
 * 场馆预约项目信息
 * （即tbAppoint表的结构）
 * 
 * @author Silence
 *
 */
public class AppointInfo implements Serializable{

	private static final long serialVersionUID = 6;
	/**
	 * 项目名称
	 */
	private String item;
	/**
	 * 用二维数组保存各个时间段剩余场次
	 */
	private String itemRemain[][];
	
	public AppointInfo(String item, String itemRemain) {
		this.item = item;
		setItemRemain(itemRemain);
	}
	
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}

	/**
	 * 返回二维数组形式的剩余场次（给客户端）
	 * 
	 * @return 二维数组形式的剩余场次
	 */
	public String[][] getItemRemain(){
		return itemRemain;
	}

	/**
	 * 返回字符串形式的剩余场次（给数据库）
	 * 字符串格式：不同日期之间以";"分隔，一天不同时段之间以"&"分隔
	 * 
	 * @return 字符串形式的剩余场次
	 */
	public String getItemRemainStr() {
		String temp = "";
		
		for (int i = 0; i < itemRemain.length; i++) {  
            for (int j = 0; j < itemRemain[i].length; j++) {                
                temp += itemRemain[i][j] + "&";  
            }  
           	
            temp += ";";
        } 
		
		return temp;
	}
	

	/**
	 * 用字符串形式设置二维数组形式
	 * 
	 * @param itemRemain 字符串形式剩余场次
	 */
	public void setItemRemain(String itemRemain) {
		String temp[] = itemRemain.split(";");
		String res[][] = new String [temp.length][];
		
		for (int i = 0; i < temp.length; i++) {
			res[i] = temp[i].split("&");
		}

		this.itemRemain = res;
	}
}
