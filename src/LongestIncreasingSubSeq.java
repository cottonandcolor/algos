/**
 * Created by predave on 4/21/17.
 */
public class LongestIncreasingSubSeq {
    public static int longest = 0;
    public static char[] output ;

    public static int findSubSequence(char[] input){
        output = new char[input.length];
        if(input.length == 1){
            longest = 1;
        }
        //123546
        for(int i = 0; i < input.length; i++){
            char start = input[i];

        }
        return longest;

    }

    public static void main(String[] args){
        System.out.println(findSubSequence(args[0].toCharArray()));
    }
}
