package seu.vCampus.view.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.SystemColor;
import java.awt.image.BufferedImage;
import java.util.Random;

public class CreateIdentity {
	public static final char[] CHARS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	public static Random random = new Random();
	public static String identity;

	public static String getRandomString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			sb.append(CHARS[random.nextInt(CHARS.length)]);
		}
		return sb.toString();
	}

//	public static Color getRandomColor() {
//		return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
//	}
	public static Color getRandomColor() {
		return SystemColor.textHighlight;
	}

//	public static Color getReverseColor(Color c) {
//		return new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue());
//	}
	public static Color getReverseColor(Color c) {
		return SystemColor.window;
	}


	public BufferedImage getIdentity() {
		identity = getRandomString();
		int width = 100;
		int height = 30;
		Color color = getRandomColor();
		Color reverseColor = getReverseColor(color);

		BufferedImage image = new BufferedImage(width, height, 1);
		Graphics2D g = image.createGraphics();
		g.setFont(new Font("SansSerif", 1, 16));
		g.setColor(color);
		g.fillRect(0, 0, width, height);
		g.setColor(reverseColor);
		g.drawString(identity, 20, 20);
		int i = 0;
		for (int n = random.nextInt(100); i < n; i++) {
			g.drawRect(random.nextInt(width), random.nextInt(height), 1, 1);
		}

		return image;
	}
}