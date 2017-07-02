package lcode38;

/**
 * Created by predave on 6/25/17.
 *
 * Excel(3,"C");
 // construct a 3*3 2D array with all zero.
 //   A B C
 // 1 0 0 0
 // 2 0 0 0
 // 3 0 0 0

 Set(1, "A", 2);
 // set C(1,"A") to be 2.
 //   A B C
 // 1 2 0 0
 // 2 0 0 0
 // 3 0 0 0

 Sum(3, "C", ["A1", "A1:B2"]);
 // set C(3,"C") to be the sum of value at C(1,"A") and the values sum of the rectangle range whose top-left cell is C(1,"A") and bottom-right cell is C(2,"B"). Return 4.
 //   A B C
 // 1 2 0 0
 // 2 0 0 0
 // 3 0 0 4

 Set(2, "B", 2);
 // set C(2,"B") to be 2. Note C(3, "C") should also be changed.
 //   A B C
 // 1 2 0 0
 // 2 0 2 0
 // 3 0 0 6
 */
public class Excel2 {
    public Excel2(int H, char W) {

    }

    public void set(int r, char c, int v) {

    }

   /* public int get(int r, char c) {

    }

    public int sum(int r, char c, String[] strs) {

    }*/

    /**
     * Your Excel object will be instantiated and called as such:
     * Excel obj = new Excel(H, W);
     * obj.set(r,c,v);
     * int param_2 = obj.get(r,c);
     * int param_3 = obj.sum(r,c,strs);
     */
}
