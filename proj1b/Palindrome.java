/**
 * Created by nithin on 2/9/17.
 */
public class Palindrome {
    public static void main(String[] args) {
        System.out.print(isPalindrome("hello"));
        System.out.print(isPalindrome("flake", new OffByOne()));
    }

    public static Deque<Character> wordToDeque(String word) {
        char[] array = word.toCharArray();
        Deque<Character> result = new ArrayDeque<>();
        for (int i = 0; i < array.length; i++) {
            result.addLast(array[i]);
        }
        return result;
    }

    public static boolean isPalindrome(String word) {
        char[] array = word.toCharArray();
        if (array.length == 1 || array.length == 0) {
            return true;
        } else {
            for (int i = 0; i < array.length / 2; i++) {
                if (array[i] != array[array.length - i - 1]) {
                    return false;
                }
            }
            return true;
        }
    }

    public static boolean isPalindrome(String word, CharacterComparator cc) {
        char[] array = word.toCharArray();
        if (array.length == 1 || array.length == 0) {
            return true;
        } else {
            for (int i = 0; i < array.length / 2; i++) {
                if (!cc.equalChars(array[i], array[array.length - i - 1])) {
                    return false;
                }
            }
            return true;
        }
    }
}
