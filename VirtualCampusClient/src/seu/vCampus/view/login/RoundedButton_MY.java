package seu.vCampus.view.login;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RoundedButton_MY extends JButton {
	// ����Բ�ǵĻ��� 
	public static int radius = 30;
	public static Color COLOR1, COLOR2;
	// �Ұ� 
	public static Color ashen1 = new Color(250, 250, 250);
	public static Color ashen2 = new Color(197, 197, 197);
	// �����밴ť�ж� 
	private boolean hover;

	public RoundedButton_MY() {
		this("", 0);
	}

	public RoundedButton_MY(String name) {
		this(name, 0);
	}

	public RoundedButton_MY(String name, int style) {
		super.setText(name);
		COLOR1 = ashen1;
		COLOR2 = ashen2;
		paintcolor(COLOR1, COLOR2);
	}

	private void paintcolor(Color COLOR1, Color COLOR2) {
		setMargin(new Insets(0, 0, 0, 0));
		setFont(new Font("΢���ź�", Font.PLAIN, 12));
		setBorderPainted(false);
		setForeground(Color.black);
		setFocusPainted(false);
		setContentAreaFilled(false);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hover = true;
				repaint();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				hover = false;
				repaint();
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		int height = getHeight();
		int with = getWidth();
		float tran = 1F;
		if (!hover) {/* ����뿪/����ʱ��͸���ȸı��� */
			tran = 0.6F;
		}
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		/* GradientPaint����ɫ������ */
		GradientPaint p1;
		GradientPaint p2;
//		if (getModel().isPressed()) {
			p1 = new GradientPaint(0, 0, new Color(0, 0, 0), 0, height, new Color(100, 100, 100), true);
			p2 = new GradientPaint(0, 1, new Color(0, 0, 0, 50), 0, height, new Color(255, 255, 255, 100), true);
//		} else {
//			p1 = new GradientPaint(0, 0, new Color(100, 100, 100), 0, height, new Color(0, 0, 0), true);
//			p2 = new GradientPaint(0, 1, new Color(255, 255, 255, 100), 0, height, new Color(0, 0, 0, 50), true);
//		}
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, tran));
		RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(0, 0, with - 1, height - 1, radius, radius);
		// �������������ֵԽ�󣬰�ťԽԲ������ͬ��
		Shape clip = g2d.getClip();
		g2d.clip(r2d);
		GradientPaint gp = new GradientPaint(0.0F, 0.0F, COLOR1, 0.0F, height / 2, COLOR2, true);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, with, height);
		g2d.setClip(clip);
		g2d.setPaint(p1);
		g2d.drawRoundRect(0, 0, with - 3, height - 3, radius, radius);
		g2d.setPaint(p2);
		g2d.drawRoundRect(1, 1, with - 3, height - 3, radius, radius);
		g2d.dispose();
		super.paintComponent(g);
	}

//	public static void main(String args[]) {
//		JFrame Frame_Login = new JFrame();
//		Frame_Login.setLayout(null);
//		Frame_Login.setBounds(600, 300, 250, 300);
//		
//		RoundedButton_MY Button_Login    = new RoundedButton_MY("��¼", 0);
//		RoundedButton_MY Button_Register = new RoundedButton_MY("ע��", 0);
//		Button_Login.setBounds(40, 60, 150, 30);
//		Button_Register.setBounds(40, 120, 150, 30);
//
//		Frame_Login.add(Button_Login);
//		Frame_Login.add(Button_Register);
//	//	Frame_Login.setDefaultCloseOperation(3);
//		Frame_Login.setVisible(true);
//	}
}