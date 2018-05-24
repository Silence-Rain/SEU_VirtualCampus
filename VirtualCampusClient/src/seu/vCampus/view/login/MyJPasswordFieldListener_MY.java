package seu.vCampus.view.login;

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
	public void focusGained(FocusEvent e) {// ��ý����ʱ��,�����ʾ����
		String temp = String.valueOf(jpf.getPassword());
	//	if (temp.equals(info)) {
			jpf.setText("");
			jpf.setEchoChar('●');
	//	}
	}

	@Override
	public void focusLost(FocusEvent e) {// ʧȥ�����ʱ��,�ж����Ϊ��,����ʾ��ʾ����
		String temp = String.valueOf(jpf.getPassword());
		if (temp.equals("")) {
			jpf.setText(info);
			jpf.setEchoChar('\0');
		}
	}

}
