package seu.vCampus.main;
import javax.swing.JFrame;

import seu.vCampus.view.*;
import seu.vCampus.view.Course.StudentView;

public class main {
	public static void main(String[] args){
		StudentView studentView = new StudentView();
		studentView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		studentView.setVisible(true);
		System.out.println("OK");
	}
}
