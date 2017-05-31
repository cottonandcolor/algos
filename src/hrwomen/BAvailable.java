package hrwomen;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BAvailable {

    private static class BookingDealArray{
        BookingDeal[] bookingarr;
        boolean isSorted = false;
        public BookingDealArray(int size){
            bookingarr = new BookingDeal[size];
        }
        public void sort(){
            if(bookingarr != null) {
                Arrays.sort(bookingarr);
                isSorted = true;
            }
        }
    }

    private static class BookingDeal implements Comparator<BookingDeal>, Comparable<BookingDeal>{
         int price;
        int priceType;

        public BookingDeal( int price, int priceType){
            this.price = price;
            this.priceType = priceType;
        }

        @Override
        public int compare(BookingDeal o1, BookingDeal o2) {
            return o1.price - o2.price;
        }

        @Override
        public int compareTo(BookingDeal o) {
            return this.price - o.price;
        }
    }
//day 1 min,max ,pric

    static BookingDealArray[] bookings;
    static int[][] minStay;
    static int[][] maxStay;
    static int numNights;
    static int numPrices;
    static int numQueries;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        numNights = in.nextInt();
        numPrices = in.nextInt();
        numQueries = in.nextInt();

        bookings = new BookingDealArray[numNights];
        minStay = new int[numPrices][numNights];
        maxStay = new int[numPrices][numNights];


        for (int p = 0; p < numPrices; p++) {
            for (int i = 0; i < numNights; i++) {
                int price = in.nextInt();
                if (bookings[i] == null) {
                    bookings[i] = new BookingDealArray(numPrices);
                }
                bookings[i].bookingarr[p] = new BookingDeal(price, p);

            }

        }

        for (int p = 0; p < numPrices; p++) {
            for (int i = 0; i < numNights; i++) {
                int mins = in.nextInt();
                minStay[p][i] = mins;
            }
        }

        for (int p = 0; p < numPrices; p++) {
            for (int i = 0; i < numNights; i++) {
                int maxs = in.nextInt();
                maxStay[p][i] = maxs;
            }
        }

        for ( int q = 0 ; q < numQueries; q++){
            int day = in.nextInt();
            int numDays = in.nextInt();
            System.out.println(findCheapestPrice(day  , numDays));
        }

    }

    private static String findCheapestPrice(int day, int numDays) {
        if(day + numDays > numNights) {
            return "-1";
        }
        int totalPrice = 0;
        for(int i = 0 ; i < numDays ; i++) {
            if(!bookings[day - 1 + i].isSorted){
                bookings[day - 1 + i].sort();
            }
            BookingDeal[] barr = bookings[day - 1 + i].bookingarr;
            boolean found = false;
            for(int b = 0 ; b < barr.length ; b++){
                int priceType = barr[b].priceType;
                if(barr[b].price != 0 && numDays >= minStay[priceType][day-1 + i] && numDays <= maxStay[priceType][day-1 + i]){
                    totalPrice += barr[b].price;
                    found = true;
                    break;
                }
                if(b == barr.length-1 && !found){
                    return "-1";
                }
            }
        }
        if(totalPrice == 0)
          return "-1";

        return String.valueOf(totalPrice);
    }

}
