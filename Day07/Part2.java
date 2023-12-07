package Day07;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Part2 {

	public static void main(String[] args) {
		long total = 0l;
		String[] originalArray = readFileToArray("./input.txt", "\n");
		Arrays.sort(originalArray, new compareHands());
		for (int i = 0; i < originalArray.length; i++) {
			long bid = Long.parseLong(originalArray[i].split(" ")[1]);
			total += bid * (i + 1);
		}
		System.out.println(total);
	}

	public static String changeJ(String original) {
		String change = "";
		if (original.equals("JJJJJ")) {
			return "AAAAA";
		}
		for (int i = 0; i < original.length(); i++) {
			char actualChar = original.charAt(i);
			if (actualChar != 'J') {
				change += actualChar;
			} else {
				change += bestChar(original);
			}
		}
		return change;
	}

	public static char bestChar(String original) {
		char best = '_';
		String hand = original.split(" ")[0].replace("J", "");
		Map<Character, Integer> repeatChara = new HashMap<>();
		for (int i = 0; i < hand.length(); i++) {
			char actual = hand.charAt(i);
			if (repeatChara.containsKey(actual)) {
				Integer actualValue = repeatChara.get(actual);
				repeatChara.put(actual, actualValue + 1);
			} else {
				repeatChara.put(actual, 1);
			}
		}
		int maxValue = 0;
		for (Map.Entry<Character, Integer> entry : repeatChara.entrySet()) {
			char actualKey = entry.getKey();
			int actualValue = entry.getValue();
			if (actualValue > maxValue) {
				maxValue = actualValue;
				best = actualKey;
			}
		}
		return best;
	}

	public static class compareHands implements Comparator<Object> {
		@Override
		public int compare(Object o1, Object o2) {
			int order = 0;
			String original1 = (String) o1;
			String original2 = (String) o2;
			String firstBid = changeJ(original1);
			String secondBid = changeJ(original2);
			if (type(firstBid) == type(secondBid)) {
				int firstStr = 0;
				int secondStr = 0;
				String firstHand = original1.split(" ")[0];
				String seconHand = original2.split(" ")[0];
				for (int i = 0; i < firstHand.length(); i++) {
					firstStr = getStrenght(firstHand.charAt(i));
					secondStr = getStrenght(seconHand.charAt(i));
					if (firstStr == secondStr) {
					} else if (firstStr > secondStr) {
						order = 1;
						break;
					} else {
						order = -1;
						break;
					}
				}
			} else if (type(firstBid) > type(secondBid)) {
				order = 1;
			} else {
				order = -1;
			}
			return order;
		}
	}

	public static int getStrenght(char character) {
		switch (character) {
		case 'J':
			return 0;
		case '2':
			return 1;
		case '3':
			return 2;
		case '4':
			return 3;
		case '5':
			return 4;
		case '6':
			return 5;
		case '7':
			return 6;
		case '8':
			return 7;
		case '9':
			return 8;
		case 'T':
			return 9;
		case 'Q':
			return 10;
		case 'K':
			return 11;
		case 'A':
			return 12;
		default:
			return 0;
		}
	}

	public static int type(String bid) {
		int type = 1;
		String hand = bid.split(" ")[0];
		Map<Character, Integer> repeatChara = new HashMap<>();
		for (int i = 0; i < hand.length(); i++) {
			char actual = hand.charAt(i);
			if (repeatChara.containsKey(actual)) {
				Integer actualValue = repeatChara.get(actual);
				repeatChara.put(actual, actualValue + 1);
			} else {
				repeatChara.put(actual, 1);
			}
		}
		Iterator<Character> iterator = repeatChara.keySet().iterator();
		Character first, second = null;
		Integer fr = null, sr = null;
		switch (repeatChara.size()) {
		case 1:
			type = 7;
			break;
		case 2:
			first = iterator.next();
			second = iterator.next();
			fr = repeatChara.get(first);
			sr = repeatChara.get(second);
			if (fr == 4 || sr == 4) {
				type = 6;
			} else if ((fr == 3 && sr == 2) || (fr == 2 && sr == 3)) {
				type = 5;
			}
			break;
		case 3:
			first = iterator.next();
			second = iterator.next();
			Character third = iterator.next();
			fr = repeatChara.get(first);
			sr = repeatChara.get(second);
			Integer tr = repeatChara.get(third);
			if ((tr == 3 && fr == 1 && sr == 1) || (tr == 1 && fr == 3 && sr == 1) || (tr == 1 && fr == 1 && sr == 3)) {
				type = 4;
			} else if ((tr == 2 && fr == 2 && sr == 1) || (tr == 2 && fr == 1 && sr == 2)
					|| (tr == 1 && fr == 2 && sr == 2)) {
				type = 3;
			}
			break;
		case 4:
			type = 2;
			break;
		case 5:
			type = 1;
			break;
		}
		return type;
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

}
