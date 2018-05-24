package common;

import java.io.Serializable;

public class AppointInfo implements Serializable{

	private static final long serialVersionUID = 6;
	private String item;
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
	public String[][] getItemRemain(){
		return itemRemain;
	}
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
	public void setItemRemain(String itemRemain) {
		String temp[] = itemRemain.split(";");
		String res[][] = new String [temp.length][];
		
		for (int i = 0; i < temp.length; i++) {
			res[i] = temp[i].split("&");
		}

		this.itemRemain = res;
	}
}
