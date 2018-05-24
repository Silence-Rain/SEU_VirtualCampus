package view;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JPasswordField;

public class MyJPasswordFieldListener_MY implements FocusListener {
	String info;
	JPasswordField jpf;

	public MyJPasswordFieldListener_MY(String info, JPasswordField jpf) {
		this.info = info;
		this.jpf = jpf;
	}

	@Override
	public void focusGained(FocusEvent e) {// 获得焦点的时候,清空提示文字
		String temp = String.valueOf(jpf.getPassword());
		if (temp.equals(info)) {
			jpf.setText("");
			jpf.setEchoChar('●');
		}
	}

	@Override
	public void focusLost(FocusEvent e) {// 失去焦点的时候,判断如果为空,就显示提示文字
		String temp = String.valueOf(jpf.getPassword());
		if (temp.equals("")) {
			jpf.setText(info);
			jpf.setEchoChar('\0');
		}
	}

}
