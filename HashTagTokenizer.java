

public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = lowerCase(args[0]);
		String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		for (int i=0 ; i<dictionary.length; i++){
			dictionary[i] = in.readString();
		}

		return dictionary;
	}

	public static boolean existInDictionary(String word, String []dictionary) {
		boolean exist = false;
		for (int i=0 ; i<dictionary.length; i++){
			if(word.equals(dictionary[i])){
				exist = true;
			}
		}
		return exist;
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {

		// Base case: do nothing (return) if hashtag is an empty string.
        if (hashtag.isEmpty()) {
            return;
        }
 
        int N = hashtag.length();

        for (int i = 1; i <= N; i++) {
			String prefix = hashtag.substring(0, i);
			if (existInDictionary(prefix, dictionary)) {
				System.out.println(prefix);
				breakHashTag(hashtag.substring(i), dictionary);
				return;
			}
		}
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


