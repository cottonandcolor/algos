package ccake;

/**
 * Created by predave on 5/17/17.
 */
public class StockPick {

    // 10 2 100 1 30 200 40
    //10 5  4 1
    private static int findMax(int[] stocks){
        int size = stocks.length;
        if(size < 2){
            return 0;
        }

        int min = stocks[0];
        int max = stocks[1] - stocks[0];


        int index  = 1;

        while(index < stocks.length ){
            min = Math.min(min, stocks[index]);
            int currentprice = stocks[index];
            int potentialprofit = currentprice - min;
            max = Math.max(max, potentialprofit);
            index++;
        }
        return max;
    }

    public static void main(String[] args){
        int stocks[] = {1,2,3,4,5,6,7,8,9,10};

        System.out.println(findMax(stocks));
    }
}
