/**
 * Created by predave on 3/19/17.
 */
public class MelPassword {
    static char[] vowels = {'a' , 'e' , 'i' , 'o' , 'u'};
    static char[] constants = {'b' , 'c' , 'd' ,'f' , 'g' , 'h' , 'j' , 'k' , 'l' , 'm' , 'n' , 'p' , 'q', 'r' , 's' , 't', 'v' , 'w', 'x', 'z'};

    public static void passwd(int n ){
        if(n == 1){
            for(int i = 0; i < vowels.length ; i ++) {
                System.out.println(vowels[i]);
            }
            for(int i = 0; i < constants.length ; i ++) {
                System.out.println(constants[i]);
            }
        }
        else {
            int vowel_index = 0;
            int constant_index = 0;
            char[] passwd = new char[n];
            int start_vowel_index = 0;
            int start_constant_index = 0;

            //if even max vow and max const = n/2
            //if odd max vow == n/2 +1 , max const = n/2 + 1

            int next_index = 1;
            while(start_vowel_index  < vowels.length  ) {
                passwd[0] = vowels[start_vowel_index] ;
                vowel_index = 2;

                if(n%2 == 1 && vowel_index > n/2 + 1) {
                    addConstants(passwd , n/2);
                }
                else {
                    for(int i = 0 ; i  < vowels.length ; i++){
                        /*if(i == start_vowel_index){
                            continue;
                        }*/
                        if (vowel_index >= n) {
                            addConstants(passwd , n/2);
                            vowel_index = 2;
                            if(n == 2) break;
                            //break;
                        }

                        passwd[vowel_index] = vowels[i];
                        vowel_index += 2;
                        if (vowel_index >= n) {
                            addConstants(passwd , n/2);
                            vowel_index = 2;
                            //break;
                        }
                    }
                }
                start_vowel_index ++ ;
            }

            // repeat above for const first
            while(start_constant_index  < constants.length  ) {
                passwd[0] = constants[start_constant_index] ;
                constant_index = 2;

                if(n%2 == 1 && constant_index > n/2 + 1) {
                    addVowels(passwd , n/2);
                }
                else {
                    for(int i = 0 ; i  < constants.length ; i++){
                       /* if(i == start_constant_index){
                            continue;
                        }*/
                        if (constant_index >= n) {
                            addVowels(passwd , n/2);
                            vowel_index = 2;
                            //break;
                            if (n ==2 ) break;
                        }
                        passwd[constant_index] = constants[i];
                        constant_index += 2;
                        if (constant_index >= n-1) {
                            addVowels(passwd , n/2);
                            constant_index = 2;
                            //break;
                        }
                    }
                }
                start_constant_index ++ ;
            }
        }

    }

    private static void addConstants(char[] passwd, int count) {
        int start_index = 1;
        int totalCount = 0;
        for ( int i = 0 ; i < constants.length; i++){
            if(start_index >= passwd.length){
                break;
            }
            passwd[start_index] = constants[i];
            start_index += 2;
            totalCount ++ ;

            for(int j = i  ; j < constants.length ; j++) {
               if( totalCount <  count) {
                   passwd[start_index] = constants[j];
                   start_index += 2;
                   totalCount ++;
               }
                else {
                   System.out.println(passwd);
                   start_index = 1;
                   totalCount = 0;
                   break;
               }
            }
        }
    }

    private static void addVowels(char[] passwd, int count) {
        int start_index = 1;
        int totalCount = 0;
        for ( int i = 0 ; i < vowels.length; i++){
            if(start_index >= passwd.length){
                break;
            }
            passwd[start_index] = vowels[i];
            start_index += 2;
            totalCount ++ ;

            for(int j = 0; j < vowels.length ; j++) {
                if( totalCount <  count) {
                    passwd[start_index] = vowels[j];
                    totalCount ++ ;
                    start_index += 2;
                }
                else {
                    System.out.println(passwd);
                    start_index = 1;
                    totalCount = 0;
                    break;
                }
            }
        }
    }

    public static void main(String[]  args) {
        passwd(4);
    }
}
