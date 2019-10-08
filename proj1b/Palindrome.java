import java.lang.Object;

public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        char[] woord = word.toCharArray();
        LinkedListDeque l = new LinkedListDeque();
        for(int i = 0; i < woord.length; i++){
            l.addLast(woord[i]);
        }
        return l;
    }

    public boolean isPalindrome(String word){
        if(word.length() == 0 || word.length() == 1 ){
            return true;
        }

        char[] wOrd = word.toCharArray();
        char[] reverse = new char[wOrd.length];
        for(int i = 0; i < wOrd.length; i++){
            if(wOrd[i] != reverse[wOrd.length - 1 - i]){
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String one, String two){
        if(one.length() != two.length()){
            return false;
        }

        for(int i = 0; i < one.length(); i++){
            if(one.charAt(i) != two.charAt(two.length() - 1 - i)){
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        OffByOne b = new OffByOne();
        char[] woord = word.toCharArray();
        if(woord.length == 0 || woord.length == 1){
            return true;
        }

        for(int i = 0; i < woord.length; i++){
            if(woord.length % 2 == 1 && i == (woord.length - 1) / 2 - 1){
                continue;
            }
            if(!b.equalChars(woord[i], woord[woord.length - 1 - i])){
                return false;
            }
        }
        return true;
    }


}
