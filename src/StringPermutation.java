/**
 * Created by predave on 4/23/17.
 */
public class StringPermutation {

    private static void findPerm(String str , String prefix ){
        if(str.length() == 0) {
            System.out.println(prefix);
        }
        else {
            for(int i = 0 ; i < str.length() ; i++){
                String rem = str.substring(0,i) + str.substring(i+1);
                findPerm(rem, prefix + str.charAt(i));
            }
        }
    }
    //abc

    public static void main(String[] args){
        findPerm("apple","");
    }
}
