public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        char[] woord = word.toCharArray();
        LinkedListDeque l = new LinkedListDeque();
        for(int i = 0; i < woord.length; i++){
            l.addFirst(woord[i]);
        }
        return l;
    }
}
