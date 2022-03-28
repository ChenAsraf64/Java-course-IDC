/*
Assignment number : 6

File name : ImageEditing.java

Name (First Last) : Chen Asraf

Email : chen.asraf@post.idc.ac.il

*/

public class ImageEditing {

	public static void main(String[] args) {

	}

	/**
	 * Renders an image using StdDraw. The input array is assumed to contain
	 * integers in the range [0,255]. With the third dimension being of size 3.
	 *
	 * @param pic - the image to show.
	 */
	public static void show(int[][][] pic) {
		StdDraw.setCanvasSize(pic[0].length, pic.length);
		int height = pic.length;
		int width = pic[0].length;
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);
		StdDraw.show(30);
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				StdDraw.setPenColor(pic[i][j][0], pic[i][j][1], pic[i][j][2]);
				StdDraw.filledRectangle(j + 0.5, height - i - 0.5, 0.5, 0.5);
			}
		}
		StdDraw.show();
	}

	/**
	 * Reads a PPM image file and convert it into 3d array.
	 * 
	 * @param fileName - PPM image file name.
	 * @return - a 3d array that represent the image's pixels.
	 */
	public static int[][][] read(String filename) {
		StdIn.setInput(filename);
		StdIn.readString();
		int columns = StdIn.readInt();
		int rows = StdIn.readInt();
		StdIn.readInt();
		int[][][] array = new int[rows][columns][3];
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				for (int k = 0; k < 3; k++) {
					array[r][c][k] = StdIn.readInt();
				}
			}
		}

		return array;

	}

	/**
	 * Receiving an 3d array picture code and print the picture code as 3d array.
	 * 
	 * @param source - the image that we will print her code.
	 */
	private static void print(int[][][] source) {
		for (int i = 0; i < source.length; i++) {
			for (int j = 0; j < source[0].length; j++) {
				for (int k = 0; k < 3; k++) {
					StdOut.printf("%1s %3d", "", source[i][j][k]);
				}

				StdOut.printf("%3s", "");
			}
			StdOut.println();
		}
	}

	/**
	 * Receiving an image from the user and flip the given image horizontally.
	 * 
	 * @param source - the image that we want to flip horizontally.
	 * @return - the flipping image.
	 */
	public static int[][][] flipHorizontally(int[][][] source) {
		int rowLength = source.length;
		int colomnsLength = source[0].length;
		int[][][] flipHorizon = new int[rowLength][colomnsLength][3];
		for (int i = 0; i < rowLength; i++) {
			for (int j = 0; j < colomnsLength; j++) {
				for (int k = 0; k < 3; k++) {
					flipHorizon[i][colomnsLength - 1 - j][k] = source[i][j][k];
				}
			}
		}

		return flipHorizon;
	}

	/**
	 * Receiving an image from the user and flip the given image vertically.
	 * 
	 * @param source - the image that we want to flip vertically.
	 * @return - the flipping image.
	 */
	public static int[][][] flipVertically(int[][][] source) {
		int rowLength = source.length;
		int colomnsLength = source[0].length;
		int[][][] flipVert = new int[rowLength][colomnsLength][3];
		for (int i = 0; i < rowLength; i++) {
			for (int j = 0; j < colomnsLength; j++) {
				for (int k = 0; k < 3; k++) {
					flipVert[rowLength - 1 - i][j][k] = source[i][j][k];
				}
			}
		}

		return flipVert;
	}

	/**
	 * Takes a digital image and returns the average of its colors. The function
	 * should sum the red,green and blue values from all the pixels and then compute
	 * and return the average.
	 * 
	 * @param pixel - image that we want to sum the values of her colors.
	 * @return - the average of the values of the image colors.
	 */
	public static double average(int[][][] pixel) {
		double sum = 0.0;
		double average = 0.0;
		int rowLength = pixel.length;
		int colomnsLength = pixel[0].length;
		int numbersOfPixels = rowLength * colomnsLength * 3;

		for (int i = 0; i < rowLength; i++) {
			for (int j = 0; j < colomnsLength; j++) {
				for (int k = 0; k < 3; k++) {
					sum += pixel[i][j][k];
				}

			}
		}
		average = sum / numbersOfPixels;
		return average;
	}

	/**
	 * Takes an RGB image as input and returns a segmented version of this image.
	 * 
	 * @param source - image that we want to turn into black and white colors.
	 * @return - black and white image.
	 */
	public static int[][][] segement(int[][][] source) {
		int rowLength = source.length;
		int colomnsLength = source[0].length;
		int[][][] blackAndWhite = new int[rowLength][colomnsLength][3];
		double average = average(source);
		double currentPixelAverage = 0.0;
		for (int i = 0; i < rowLength; i++) {
			for (int j = 0; j < colomnsLength; j++) {
				currentPixelAverage = 0.0;
				for (int k = 0; k < 3; k++) {
					currentPixelAverage += source[i][j][k];
				}
				currentPixelAverage = currentPixelAverage / 3;
				if (currentPixelAverage < average) {
					blackAndWhite[i][j][0] = 0;
					blackAndWhite[i][j][1] = 0;
					blackAndWhite[i][j][2] = 0;
				} else {
					blackAndWhite[i][j][0] = 255;
					blackAndWhite[i][j][1] = 255;
					blackAndWhite[i][j][2] = 255;

				}

			}

		}
		return blackAndWhite;

	}

	/**
	 * changing the proportions of a given image (scaling either the width and/or
	 * the height of the image).
	 * 
	 * @param source - image that we want to change her proportions. width- width we
	 *               want to change to. height- height we want to change to.
	 * @return - scaled image.
	 */
	public static int[][][] scale(int[][][] source, int width, int height) {
		int[][][] scaling = new int[height][width][3];
		for (int i = 0; i < scaling.length; i++) {
			int hightChange = (int) i * source.length / height;
			for (int j = 0; j < scaling[0].length; j++) {
				int widthChange = (int) j * source[0].length / width;
				for (int k = 0; k < 3; k++) {
					scaling[i][j][k] = source[hightChange][widthChange][k];
				}
			}

		}
		return scaling;
	}

	/**
	 * Blending two pixels is a new pixel whose 3 RGB values are weighted averages
	 * of the RGB values of the two input pixels that we receiving from the user.
	 * 
	 * @param pixel1 - one pixel. pixel2- another pixel. alpha- real number from 0
	 *               to 1 that determines how to blend the two inputs.
	 * @return - blended pixel.
	 */
	public static int[] blend(int[] pixel1, int[] pixel2, double alpha) {
		int[] blending = new int[pixel1.length];
		for (int i = 0; i < 3; i++) {
			blending[i] = (int) (pixel1[i] * alpha) + (int) (pixel2[i] * (1 - alpha));

		}
		return blending;
	}

	/**
	 * Combined two images by blending all the corresponding input pixels using a
	 * given real number from 0 to 1 that determines how to blend the two images.
	 * 
	 * @param source1 - first image that we want to combine. source2- second image
	 *                that we want to combine with the first image. alpha- real
	 *                number from 0 to 1 that determines how to blend the two
	 *                inputs.
	 * @return - combine of 2 images.
	 */
	public static int[][][] combine(int[][][] source1, int[][][] source2, double alpha) {
		int[][][] combining = new int[source1.length][source1[0].length][3];
		for (int i = 0; i < source1.length; i++) {
			for (int j = 0; j < source1[0].length; j++) {
				combining[i][j] = blend(source1[i][j], source2[i][j], alpha);
			}

		}

		return combining;
	}

	/**
	 * Morph a source image gradually into a target image in steps. the numbers of
	 * steps are given by the user.
	 * 
	 * @param source - original image. target- the image after transformation. n-
	 *               the number of the steps that the image will change from source
	 *               to target.
	 */
	public static void morph(int[][][] source, int[][][] target, int n) {
		int anotherSize[][][] = scale(target, source[0].length, source.length);
		for (int i = 0; i <= n; i++) {
			double alpha = (double) (n - i) / n;
			int[][][] steps = (combine(source, anotherSize, alpha));
			show(steps);
		}
	}
}
