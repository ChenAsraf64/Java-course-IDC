/*
Assignment number : 6

File name : Editor2.java

Name (First Last) : Chen Asraf

Email : chen.asraf@post.idc.ac.il

*/

public class Editor2 {
	public static void main(String[] args) {
		String fileName = args[0];
		int[][][] array = ImageEditing.read(fileName);
		int width = Integer.parseInt(args[1]);
		int height = Integer.parseInt(args[2]);
		array = ImageEditing.scale(array, width, height);
		ImageEditing.show(array);

	}

}
