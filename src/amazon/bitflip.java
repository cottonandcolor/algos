package amazon;

/**
 * Created by predave on 6/8/17.
 */
public class bitflip {
    //find count of number of bits that need to be flipped to convert one num to other
    public static void main(String[] args){
        int a = 1;
        int b = 4;

        int c = a ^ b;
        int count = 0;
        while(c > 0){
            count = count + (c&1);
            c = c >> 1;
        }
        System.out.println(count);
    }
}
