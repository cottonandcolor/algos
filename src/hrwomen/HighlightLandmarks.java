package hrwomen;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by predave on 4/21/17.
 */
public class HighlightLandmarks {
    public static void doHighlight(){

    }

    public static void main(String[] args){
        Set<String> landmarks = new HashSet();
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        int numDest = in.nextInt();


        for(int i = 0 ; i < numDest; i++){
            landmarks.add(in.next());
        }

        String[] words = input.split(" ");

        for(int i = 0 ; i < words.length ; i++){
            if(landmarks.contains(words[i])){
                System.out.print("<b>"+words[i]+"</b>");
            }
            else {
                System.out.print(words[i]);
            }
            System.out.print(" ");

        }

    }
}
