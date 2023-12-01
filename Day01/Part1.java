
import java.nio.file.Files;
import java.nio.file.Path;

public class Part1 {

	public static void main(String[] args) throws Exception {
		String content = Files.readString(Path.of("./input.txt"));
		content = content.replace("\r", "");
		content = content.replace("\n", ",");
		String[] linesArray = content.split(",");
		int total = 0;
		for (int i = 0; i < linesArray.length; i++) {
			total += Integer.valueOf(firstNumber(linesArray[i]) + "" + lastNumber(linesArray[i]));
		}
		System.out.println(total);
	}

	public static char firstNumber(String line) {
		char toReturn = '0';
		for (int i = 0; i < line.length(); i++) {
			if (Character.isDigit(line.charAt(i))) {
				toReturn = line.charAt(i);
				break;
			}
		}
		return toReturn;
	}

	public static char lastNumber(String line) {
		char toReturn = '0';
		for (int i = line.length() - 1; i >= 0; i--) {
			if (Character.isDigit(line.charAt(i))) {
				toReturn = line.charAt(i);
				break;
			}
		}
		return toReturn;
	}

}
