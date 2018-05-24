package view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;

public class RoundedButton_MY extends JButton {
	// 决定圆角的弧度 
	public static int radius = 30;
	public static Color COLOR1, COLOR2;
	// 灰白 
	public static Color ashen1 = new Color(250, 250, 250);
	public static Color ashen2 = new Color(197, 197, 197);
	// 光标进入按钮判断 
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
		setFont(new Font("微软雅黑", Font.PLAIN, 12));
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
		if (!hover) {/* 鼠标离开/进入时的透明度改变量 */
			tran = 0.6F;
		}
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		/* GradientPaint是颜色渐变类 */
		GradientPaint p1;
		GradientPaint p2;

		p1 = new GradientPaint(0, 0, new Color(0, 0, 0), 0, height, new Color(100, 100, 100), true);
		p2 = new GradientPaint(0, 1, new Color(0, 0, 0, 50), 0, height, new Color(255, 255, 255, 100), true);
		
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, tran));
		RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(0, 0, with - 1, height - 1, radius, radius);
		// 最后两个参数数值越大，按钮越圆，以下同理
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
}