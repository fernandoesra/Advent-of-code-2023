package Day05;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

	public static void main(String[] args) {
		String original = readFileToString("./input.txt", "\n");

		String[] filters = { "seeds:", "seed-to-soil map:", "soil-to-fertilizer map:", "fertilizer-to-water map:",
				"water-to-light map:", "light-to-temperature map:", "temperature-to-humidity map:",
				"humidity-to-location map:", "$" };
		String[][] list = new String[8][0];
		for (int i = 0; i < 8; i++) {
			String subList = regexBetween(original, filters[i], filters[i + 1]);
			list[i] = separateCategory(subList);
		}

		String[] seedsString = list[0][0].split(" ");
		long[] seeds = new long[seedsString.length];
		for (int i = 0; i < seedsString.length; i++) {
			seeds[i] = Long.parseLong(seedsString[i]);
		}
		
		long lowest = 999999999999999l;
		for (int i = 0; i < seeds.length; i++) {
			long trace = seeds[i];
			for (int j = 1; j < list.length; j++) {
				for (int k = 0; k < list[j].length; k++) {
					long test = trace;
					trace = findTrace(list[j][k], trace);
					if (test != trace)
						break;
				}
			}
			if (trace < lowest)
				lowest = trace;
		}

		System.out.println(lowest);
		
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

	public static String regexBetween(String text, String from, String to) {
		String toReturn = "";
		String regex = from + "(.*?)(" + to + ")";
		Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
		Matcher matcher = pattern.matcher(text);
		if (matcher.find()) {
			toReturn = matcher.group(1).trim();
		}
		return toReturn;
	}

	public static String[] separateCategory(String completeCategory) {
		return completeCategory.split("\n");
	}

	public static long[] sourceRange(String register) {
		String[] separated = register.split(" ");
		long start = Long.valueOf(separated[1]);
		long finish = start + (Long.valueOf(separated[2]) - 1);
		long[] toReturn = { start, finish };
		return toReturn;
	}

	public static long destinationValue(String register, long displacement) {
		String[] separated = register.split(" ");
		long start = Long.valueOf(separated[0]);
		return start + displacement;
	}

	public static long findTrace(String register, long source) {
		long destination = source;
		long start = sourceRange(register)[0];
		long end = sourceRange(register)[1];
		if (source >= start && source <= end) {
			long displacement = source - start;
			destination = destinationValue(register, displacement);
		}
		return destination;
	}

}
