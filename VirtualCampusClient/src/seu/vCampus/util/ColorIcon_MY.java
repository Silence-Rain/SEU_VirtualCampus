package seu.vCampus.util;


import java.awt.*;
import javax.swing.*;

public class ColorIcon_MY implements Icon {
	private Color color;
	private int width;
	private int height;

	public ColorIcon_MY(Color color, int width, int height) {
		this.color = color;
		this.width = width;
		this.height = height;
	}

	public int getIconWidth() {
		return width;
	}

	public int getIconHeight() {
		return height;
	}

	public void paintIcon(Component c, Graphics g, int x, int y) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}


	public static void createAndShowGUI() {
		JPanel panel = new JPanel(new GridLayout(2, 2));

		for (int i = 0; i < 4; i++) {
			Icon icon = new ColorIcon_MY(Color.RED, 50, 50);
			JButton label = new JButton(icon);
			label.setText("" + i);
			label.setHorizontalTextPosition(JButton.CENTER);
			label.setVerticalTextPosition(JButton.CENTER);
			panel.add(label);
		}

		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(panel);
		f.setSize(200, 200);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
}
