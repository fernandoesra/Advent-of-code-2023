package Template;

import java.nio.file.Files;
import java.nio.file.Path;

public class Part2 {

	public static void main(String[] args) {
		long total = 0l;

		// TODO

		System.out.println(total);
	}

	public static char[][] createMap(String path, String splitChara) {
		String[] lines = readFile(path, splitChara);
		int height = lines.length;
		int width = lines[0].length();
		char[][] schematicMap = new char[height][width];
		for (int i = 0; i < lines.length; i++) {
			String actualLine = lines[i];
			for (int j = 0; j < actualLine.length(); j++) {
				schematicMap[i][j] = actualLine.charAt(j);
			}
		}
		return schematicMap;
	}

	public static String[] readFile(String path, String splitChara) {
		String[] linesArray = null;
		try {
			String content = Files.readString(Path.of(path));
			content = content.replace("\r", "");
			linesArray = content.split(splitChara);
		} catch (Exception e) {
		}
		return linesArray;
	}

}
