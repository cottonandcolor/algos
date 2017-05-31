import java.util.Scanner;


public class PasswordEncoding {

    public static double sqrt(int c ) {
        double epsilon = 1e-15;    // relative error tolerance
        double t = c;              // estimate of the square root of c

        // repeatedly apply Newton update step until desired precision is achieved
        while (abs(t - c/t) > epsilon*t) {
            t = (c/t + t) / 2.0;
        }
        return t;
    }

    public static double abs(double n ){
        if(n < 0)
            return 0 - (n);
        else return n;
    }


    public static void main(String[] args) {
        //System.out.print(sqrt(10));
        Scanner in = new Scanner(System.in);
        String passwd = in.next();
        char[] passArray = passwd.toCharArray();
        double sq = sqrt(passwd.length());
        int rows = 0;
        int cols = 0;
        if((sq*10) % 10 == 0){
            rows = (int)sq;
        }
        else {
            rows = (int)sq + 1;
        }
        double dcol = passwd.length() / (double)rows;
        if((dcol*10) % 10 == 0) {
            cols = (int)dcol ;
        }
        else {
            cols = (int)dcol + 1;
        }

        if(rows > cols){
            int temp = rows;
            rows = cols ;
            cols = temp;
        }

        for(int c = 0 ; c < cols ;  c++){
            int start_index = c;
            for ( int r = 0 ; r < rows ; r++)
            {
                int k = start_index + (cols * r);
                if(k < passwd.length())
                    System.out.print( passArray[k]  );

            }
            System.out.print(" ");
        }

    }
}
