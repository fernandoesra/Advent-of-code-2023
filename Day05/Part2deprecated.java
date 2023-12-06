package Day05;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2deprecated {

	public static void main(String[] args) {
		long total = 0l;
		String original = readFileToString("./input.txt", "\n");

		/**
		 * 0 seeds, 1 soil, 2 fertilizer, 3 water, 4 light, 5 temperature, 6 humidity, 7
		 * location
		 */
		String[] filters = { "seeds:", "seed-to-soil map:", "soil-to-fertilizer map:", "fertilizer-to-water map:",
				"water-to-light map:", "light-to-temperature map:", "temperature-to-humidity map:",
				"humidity-to-location map:", "$" };

		String[][] list = new String[8][0];
		for (int i = 0; i < 8; i++) {
			String subList = regexBetween(original, filters[i], filters[i + 1]);
			list[i] = separateCategory(subList);
		}

		// System.out.prlongln(findTrace(list[1][1],79));

		String[] seedsString = list[0][0].split(" ");
		long[] seeds = new long[seedsString.length];
		for (int i = 0; i < seedsString.length; i++) {
			seeds[i] = Long.parseLong(seedsString[i]);
		}

		// System.out.println(Arrays.toString(seeds));

		long lowest = 999999999999999l;
		
		String[] separatedSeeds = splitSeeds(list[0][0]);
		for (int p = 0; p < separatedSeeds.length; p++) {
			long[] actualRange = seedsRange(separatedSeeds[p]);
			for (long r = actualRange[0]; r <= actualRange[1]; r++) {
				long trace = r;
				for (int j = 1; j < list.length; j++) {
					for (int k = 0; k < list[j].length; k++) {
						// System.out.print(" " + j + ": (" + trace + ")");
						long test = trace;
						trace = findTrace(list[j][k], trace);
						if (test != trace)
							break;
					}
				}
				// System.out.println(" Final: " + trace);
				if (trace < lowest)
					lowest = trace;
			}
		}
		
		System.out.println(lowest);
		// System.out.prlongln(total);
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
		// System.out.println(Arrays.toString(separated1));
		String[] split = new String[(int) Math.ceil(separated1.length / 2)];
		for (int i = 0; i < split.length; i++) {
			split[i] = separated1[i * 2] + " " + separated1[(i * 2) + 1];
		}
		return split;
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
		// System.out.prlongln("Source - Start: " + start + " Finish: " + finish);
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
