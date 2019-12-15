package src;

public class EnglishToInt {
    //Given a word, return a int
    public static int toInt(String word){
        int length = word.length();
        int num = 0;
        for(int i = 0; i < length; i++){
            int order =  word.charAt(i) - 96;
            num += order * Math.pow(27.0, (double)length - 1- i);
        }
        return num;
    }

    public static void main(String[] args) {
        System.out.println(toInt("a"));
        System.out.println(toInt("z"));
        System.out.println(toInt("bee"));
        System.out.println(toInt("cat"));
    }
}
