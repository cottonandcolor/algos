package lcode35;

public class CompressedStringIter {
    String cs;
    int index=0;
    int nextcounter = 0 ;
    public CompressedStringIter(String compressedString) {
        cs = compressedString;
        index = 0;
    }

    public char next() {
        if(!hasNext()) return ' ';
      if(cs == null || cs.isEmpty()) return ' ';
        if(index > cs.length() -1) return  ' ';
        char c =  cs.charAt(index);
        int j = index+1;
        while(j < cs.length()){
            if(cs.charAt(j) >= '0' && cs.charAt(j) <= '9'){
                j++;
            } else break;
        }

        int count = (j==(index+1))? Integer.parseInt(String.valueOf(cs.charAt(j))) : Integer.parseInt(String.valueOf(cs.substring(index+1,j)));
        if(nextcounter == 0) nextcounter = count;
        if(count == 1 || nextcounter == 1) {
            index = j;
            nextcounter = 0;
        } else {
            nextcounter = nextcounter -1;
        }
        return c;
    }

    public boolean hasNext() {
        if(cs == null || cs.isEmpty()) return false;
        if(index >= cs.length() - 1) return false;
        return true;
    }

    public static void main(String[] args ){
        String s = "p8B12s4I7n18";
                //"G4X10v8G17x15A12c12d6F1A13K3z17U11Z17Z1F5J14L16o18o13M18h20n6R20Y8B5Q3f16C5y2b13W11B10A15p5O20K10v14U1e5k10e12l12E4s18p11";//"L10e2t1C1o1d1e11";
        CompressedStringIter iterator = new CompressedStringIter(s);
        //char param_1 = iterator.next();
        //boolean param_2 = iterator.hasNext();
        System.out.println(iterator.next()); // return 'L'
        System.out.println(iterator.next()); // return 'e'
        System.out.println(iterator.next()); // return 'e'
        System.out.println(iterator.next()); // return 't'
        System.out.println(iterator.next()); // return 'C'
        System.out.println(iterator.next()); // return 'o'
        System.out.println(iterator.next()); // return 'd'
        System.out.println(iterator.hasNext()); // return true
        System.out.println(iterator.next()); // return 'e'
        System.out.println(iterator.hasNext()); // return false
        System.out.println(iterator.next()); // return ' '
        System.out.println(iterator.hasNext()); // return false
        for(int i =0 ; i < 1000; i++) {
            System.out.println(iterator.next()); // return ' '
        }
    }
}
/**
 * Your StringIterator object will be instantiated and called as such:
 * StringIterator obj = new StringIterator(compressedString);
 * char param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
