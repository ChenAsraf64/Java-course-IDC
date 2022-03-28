/*
Assignment number : 6

File name : Editor1.java

Name (First Last) : Chen Asraf

Email : chen.asraf@post.idc.ac.il

*/

public class Editor1 {
	public static void main(String[] args) {
		int[][][] array = ImageEditing.read(args[0]);
		String functions = args[1];
		if (functions.equals("fh")) {
			array = ImageEditing.flipHorizontally(array);
			ImageEditing.show(array);
		} else if (functions.equals("fv")) {
			array = ImageEditing.flipVertically(array);
			ImageEditing.show(array);
		} else {
			array = ImageEditing.segement(array);
			ImageEditing.show(array);

		}

	}
}