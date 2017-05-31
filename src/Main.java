import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
   static class Calculator {

        int power(int n,int p) throws Exception{
            if(n < 0 || p < 0){
                throw new Exception("n and p should be non-negative.");
            }
            int val = n;
            for (int i = 0; i < p-1; i++){
                val = val*n;
            }
            return val;
        }
    }
    public static int counting(String input){
        int totalcount = 0;

        int numFirst = 1;
        int numSecond = 1;
        boolean flipped = false;
//input = "1111100001110";
//00110

//10101
//10001

        for(int i = 0 ; i < input.length() - 1  ; i ++ ) {
            if(input.charAt(i) == input.charAt(i+1)){
                   if(!flipped) {
                       numFirst++;
                   }
                   else {
                       numSecond++;
                   }
            }
            else {
                if(flipped || i == input.length() - 2) {
                    totalcount += numSecond <= numFirst ? numSecond : numFirst;
                    numFirst = numSecond;
                    numSecond = 1;
                }
                flipped = !flipped;

            }
        }
        totalcount += numSecond <= numFirst ? numSecond : numFirst;
        return totalcount;
    }

    public static void main(String[] args) {
        Calculator cal = new Calculator();
        try {
            System.out.println(cal.power(2,5));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(counting("1111100001111"));
        /*Scanner in = new Scanner(System.in);
        final String fileName = System.getenv("OUTPUT_PATH");
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(fileName));

            int res;
            String _s;
            try {
                _s = in.nextLine();
         } catch (Exception e) {
             _s = null;
            }

         res = counting(_s);
            bw.write(String.valueOf(res));
            bw.newLine();

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
