package Template;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Template {

	public static void main(String[] args) {
		long total = 0l;
		String[] originalArray = readFileToArray("./src/Day00/test.txt", "\n");
		String originalString = readFileToString("./src/Day00/test.txt", "\n");
		
		// TODO

		System.out.println(total);
	}

	public static String readFileToString(String path, String splitChara) {
		String filePath = path;
		String toReturn = "";
		try {
			File file = new File(filePath);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuilder fileContent = new StringBuilder();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				fileContent.append(line).append(splitChara);
			}
			bufferedReader.close();
			fileReader.close();
			String contentAsString = fileContent.toString();
			toReturn = contentAsString;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return toReturn;
	}

	public static String[] readFileToArray(String path, String splitChara) {
		String[] linesArray = null;
		try {
			String content = Files.readString(Path.of(path));
			content = content.replace("\r", "");
			linesArray = content.split(splitChara);
		} catch (Exception e) {
		}
		return linesArray;
	}

	public static char[][] createMap(String path, String splitChara) {
		String[] lines = readFileToArray(path, splitChara);
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

}
