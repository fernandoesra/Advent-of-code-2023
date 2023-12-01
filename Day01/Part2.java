
import java.nio.file.Files;
import java.nio.file.Path;

public class Part2 {

	public static void main(String[] args) throws Exception {
		String content = Files.readString(Path.of("./input.txt"));
		content = content.replace("\r", "");
		content = content.replace("\n", ",");
		String[] linesArray = content.split(",");
		int total = 0;
		for (int i = 0; i < linesArray.length; i++) {
			String actualLine = parseNumbers(linesArray[i]);
			total += Integer.valueOf(actualLine.charAt(0) + "" + actualLine.charAt(actualLine.length() - 1));
		}
		System.out.println(total);
	}

	public static String parseNumbers(String toParse) {
		String toReturn = "";
		String[] numbers = { "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
		for (int i = 0; i < toParse.length(); i++) {
			String actualSplit = toParse.substring(i);
			for (int j = 0; j < numbers.length; j++) {
				if (actualSplit.startsWith(numbers[j]) || actualSplit.startsWith(String.valueOf(j + 1))) {
					toReturn += j + 1;
				}
			}
		}
		return toReturn;
	}

}
