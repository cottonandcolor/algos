import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by predave on 3/21/17.
 */
public class ModuloEx {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int p = in.nextInt();
        int q = in.nextInt();
        int[] a = new int[p];
        for(int a_i=0; a_i < p; a_i++){
            a[a_i] = in.nextInt();
        }
        int[] b = new int[q];
        for(int b_i=0; b_i < q; b_i++){
            b[b_i] = in.nextInt();
        }
        // Write Your Code Here
        /*
        // n == 5
        p = 2 , q = 2
        p=1,4
        q=1,3
        */
        Set<Integer> notAllowed = new HashSet();

            for(int j = 0 ; j < p ; j++){
                for(int k = 0; k < q; k++){
                    int sum = a[j] + b[k] ;
                     int remainder =  n - (sum % n) ;
                     notAllowed.add(remainder);
                }
            }

        int output = n + 1;
        for(int l = 1; l <= n; l++){
            if(!notAllowed.contains(l)){

               output = l < output ? l : output;
            }
        }
        if(output == n && notAllowed.contains(0)){
            System.out.println(1);
        }
        else if(output == n+1)
            System.out.println(1);
        else
            System.out.println(output);
    }
    /*
    200 66 164
100 26 110 37 194 175 74 75 84 53 9 22 93 177 193 48 180 85 95 64 98 55 155 182 44 27 125 150 66 82 3 40 126 115 108 43 36 143 88 141 13 54 154 90 107 122 46 173 188 11 30 152 167 109 32 73 49 5 102 120 151 198 178 25 8 94
23 198 28 184 129 163 15 16 106 181 179 5 134 188 113 1 168 169 197 104 75 164 77 62 78 111 182 123 32 148 33 107 99 162 76 53 124 183 138 52 51 191 90 142 42 153 120 139 71 49 145 100 103 72 175 35 26 86 186 147 136 45 56 96 55 133 65 132 193 67 73 89 6 176 135 131 117 110 159 47 37 61 29 156 195 143 46 3 38 54 41 84 85 130 174 60 95 116 19 150 4 171 199 11 88 57 161 80 79 9 94 34 114 165 13 105 39 154 10 158 102 2 81 50 68 69 172 64 58 160 128 66 121 109 192 8 17 97 70 151 82 112 12 125 140 152 189 173 178 30 141 18 170 137 74 127 24 21 118 126 48 144 157 155
     */
}
