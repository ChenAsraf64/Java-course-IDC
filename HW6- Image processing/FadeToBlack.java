/*
Assignment number : 6

File name : FadeToBlack.java

Name (First Last) : Chen Asraf

Email : chen.asraf@post.idc.ac.il

*/

public class FadeToBlack {
	public static void main(String[] args) {
		String fileName = args[0];
		int[][][] array = ImageEditing.read(fileName);
		int steps = Integer.parseInt(args[1]);
		int[][][] array1 = ImageEditing.segement(array);
		ImageEditing.morph(array, array1, steps);
	}
}
