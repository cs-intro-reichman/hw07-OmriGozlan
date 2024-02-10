
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		if (str.length() == 1){
			return "";
		} else {
			return str.substring(1);
		}
	}

	public static int levenshtein(String word1, String word2) {
		word1 = lowerCase(word1);
		word2 = lowerCase(word2);

		if (word1.length() == 0) {
			return word2.length();
		}
		if (word2.length() == 0) {
			return word1.length();
		}
		if (word1.charAt(0) == word2.charAt(0)) {
			return levenshtein(tail(word1), tail(word2));
		}
		// #feedback - for consistency, you should use the tail method here as well.
		return 1 + Math.min(
				levenshtein(word1.substring(1), word2),
				Math.min(
						levenshtein(word1, word2.substring(1)),
						levenshtein(word1.substring(1), word2.substring(1))
				)
		);
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		for (int i=0 ; i<dictionary.length; i++){
			dictionary[i] = in.readString();
		}

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int minDistance =Integer.MAX_VALUE;
		int distance = 0;
		String res = "";
		for (int i = 0; i <dictionary.length ; i++) {
			distance = levenshtein(word,dictionary[i]);
			if(distance < minDistance){
				minDistance = distance;
				res = dictionary[i];
			}
		}
		if (minDistance > threshold){
			return word;
		}
		return res;
	}
	public static String lowerCase(String s) {
		String ans = "";
		for (int i=0; i<s.length(); i++){
			if('A'<= s.charAt(i) && s.charAt(i) <= 'Z'){
				ans += (char) (s.charAt(i) + 32);
			}
			else {
				ans += s.charAt(i);
			}
		}
		return ans;
	}
}



