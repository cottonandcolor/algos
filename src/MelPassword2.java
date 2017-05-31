/**
 * Created by predave on 3/19/17.
 */
public class MelPassword2 {
    static char[] vowels = {'a' , 'e' , 'i' , 'o' , 'u'};
    static char[] constants = {'b' , 'c' , 'd' ,'f' , 'g' , 'h' , 'j' , 'k' , 'l' , 'm' , 'n' , 'p' , 'q', 'r' , 's' , 't', 'v' , 'w', 'x', 'z'};
    static int n = 5;
    private static boolean iswordLength(String word){
        if(word.length() == n) {
            System.out.println(word);
            word = "";
            return true;
        }
        return false;
    }
    public static void passwd(String word , int index , boolean isVowel  ) {
        if(iswordLength(word)){
            return;
        }
        if(isVowel) {
            word = word + vowels[index];
        }
        else {
            word = word + constants[index];
        }
        if(iswordLength(word)){
            return;
        }
        if(isVowel) {
            for (int i = 0; i < constants.length ; i++) {
                passwd(word, i , false);
            }
        }
        else {
            for (int i = 0; i < vowels.length ; i++) {
                passwd(word, i , true);
            }
        }

    }

    public static void passwd2(String word , int vowelIndex , int constantIndex ) {
        if(word.length() == n) {
            System.out.println(word);
            word = "";
            return;
        }
        if( word.length() != n){
            word = word + constants[constantIndex];
        }
        else {
            System.out.println(word);
            word = "";
            return;
        }

        for( int i = 0 ; i < vowelIndex ; i++) {
            for(int j = 0 ; j < constantIndex; j++) {
                passwd2(word, i, j);
            }
        }

    }




    public static void main(String[]  args) {

        if (n == 1) {
            for (int i = 0; i < vowels.length; i++) {
                System.out.println(vowels[i]);
            }
            for (int i = 0; i < constants.length; i++) {
                System.out.println(constants[i]);
            }
        }
        else {
            for(int i = 0 ; i < vowels.length ; i++) {
                    passwd("", i, true);
            }
            for(int i = 0 ; i < constants.length ; i++) {
                passwd("", i, false);
            }
        }
    }
}
