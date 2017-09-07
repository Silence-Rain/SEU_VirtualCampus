package seu.vCampus.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JDesktopPane;
import javax.swing.JSplitPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTree;
import java.awt.Color;
import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Toolkit;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.Component;
import javax.swing.Box;

public class login {

	private JFrame a;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					window.a.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		a = new JFrame();
		a.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\13672\\Pictures\\Camera Roll\\DSC_8162.JPG"));
		a.setType(Type.POPUP);
		a.setTitle("VirtualCampus");
		a.setForeground(new Color(0, 0, 0));
		a.getContentPane().setBackground(new Color(255, 255, 255));
		a.setBounds(100, 100, 469, 340);
		a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		a.getContentPane().setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(285, 197, -205, -95);
		a.getContentPane().add(desktopPane);
		
		JList list = new JList();
		list.setBounds(103, 158, 100, -78);
		a.getContentPane().add(list);
	}
}
