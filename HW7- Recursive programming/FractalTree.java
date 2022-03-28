/*
Assignment number : 7

File name : FactalTree.java

Name (First Last) : Chen Asraf

Email : chen.asraf@post.idc.ac.il

*/

import java.awt.Color;

public class FractalTree {
	private static final int recursionDepth = 14;
	private static final Color rootColor = Color.BLACK;
	private static final Color leafColor = new Color(195, 255, 112);
	private static final double trunkWidth = 0.008;
	private static final double factor = 0.8;
	private static final int canvasWidth = 600;
	private static final int canvasHeight = 400;
	private static final double branchAngle = Math.PI / 4;
	private static final double anglePerturb = 6.0;
	private static final double minWidth = 0.002;
	// private static int counter = 0;

	private static Color adjustColor(int level) {
		double w = (double) level / recursionDepth;
		double red = w * rootColor.getRed() + (1 - w) * leafColor.getRed();
		double green = w * rootColor.getGreen() + (1 - w) * leafColor.getGreen();
		double blue = w * rootColor.getBlue() + (1 - w) * leafColor.getBlue();

		return new Color((int) red, (int) green, (int) blue);
	}

	private static double adjustWidth(int level) {
		double w = (double) level / recursionDepth;
		return Math.max(minWidth, trunkWidth * w);
	}

	public static void drawBranch(int level, double x0, double y0, double length, double angle) {
		if (level == 0) {
			return;
		}
		double randomNumber = Math.random() * length;
		double newX = x0 + randomNumber * Math.cos(angle);
		double newY = y0 + randomNumber * Math.sin(angle);
		StdDraw.setPenColor(adjustColor(level));
		StdDraw.setPenRadius(adjustWidth(level));
		StdDraw.line(x0, y0, newX, newY);
		double angle1 = Math.random() * branchAngle * (anglePerturb / level);
		double angle2 = Math.random() * branchAngle * (anglePerturb / level);
		drawBranch(level - 1, newX, newY, length * factor, angle1 + angle);
		drawBranch(level - 1, newX, newY, length * factor, angle - angle2);

	}

	public static void main(String[] args) {
		StdDraw.setXscale(0, canvasWidth);
		StdDraw.setYscale(0, canvasHeight);
		drawBranch(recursionDepth, canvasWidth / 2.0, -50, 100, Math.PI / 2);
	}
}
