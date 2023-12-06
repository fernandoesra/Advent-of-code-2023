package Day05;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {

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

		long low = 99999999999999999l;
		for (long i = 0;; i++) {
			if (i % 1000000 == 0) {
				System.out.println("Script for: " + i + " m");
			}
			long trace = i;
			for (int j = 7; j > 0; j--) {
				for (int j2 = 0; j2 < list[j].length; j2++) {
					String actual = list[j][j2];
					long find = trace;
					trace = findTraceReverse(trace, actual);
					if (find != trace)
						break;
				}
			}
			if (betweenSeeds(trace, list[0])) {
				if (trace < low) {
					low = i;
					break;
				}
			}
		}

		System.out.println(low);

	}

	public static boolean betweenSeeds(long search, String[] seeds) {
		boolean between = false;
		String[] seedsList = splitSeeds(seeds[0]);
		for (int i = 0; i < seedsList.length; i++) {
			long start = seedsRange(seedsList[i])[0];
			long end = seedsRange(seedsList[i])[1];
			if (search >= start && search <= end)
				return true;
		}
		return between;
	}

	public static long[] seedsRange(String seedsList) {
		long start = Long.parseLong(seedsList.split(" ")[0]);
		long end = Long.parseLong(seedsList.split(" ")[1]);
		long endValue = start + end - 1;
		long[] seedsRange = { start, endValue };
		return seedsRange;
	}

	public static String[] splitSeeds(String seeds) {
		String[] separated1 = seeds.split(" ");
		String[] split = new String[(int) Math.ceil(separated1.length / 2)];
		for (int i = 0; i < split.length; i++) {
			split[i] = separated1[i * 2] + " " + separated1[(i * 2) + 1];
		}
		return split;
	}

	public static long findTraceReverse(long source, String register) {
		long destination = source;
		String[] split = register.split(" ");
		long start = Long.valueOf(split[0]);
		long end = start + Long.valueOf(split[2]) - 1;
		if (source >= start && source <= end) {
			long displacement = source - start;
			long destinationStart = Long.valueOf(split[1]);
			destination = destinationStart + displacement;
		}
		return destination;
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

	public static long destinationValue(String register, long displacement) {
		String[] separated = register.split(" ");
		long start = Long.valueOf(separated[0]);
		return start + displacement;
	}

	public static long[] sourceRange(String register) {
		String[] separated = register.split(" ");
		long start = Long.valueOf(separated[1]);
		long finish = start + (Long.valueOf(separated[2]) - 1);
		long[] toReturn = { start, finish };
		return toReturn;
	}

	public static String[] separateCategory(String completeCategory) {
		return completeCategory.split("\n");
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

}