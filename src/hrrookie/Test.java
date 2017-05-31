package hrrookie;

/**
 * Created by predave on 5/5/17.
 */
public class Test {

    public static class TestTime implements Comparable<TestTime>{
        Integer hour;
        Integer min;
        boolean isAM;

        public  TestTime(String timestr){
            String[] strs = timestr.split(":");
            hour = Integer.valueOf(strs[0]);
            isAM = strs[1].endsWith("AM");
            min = Integer.valueOf(strs[1].substring(0,2));

        }



        @Override
        public int compareTo(TestTime o) {
            if(this.isAM == o.isAM) {
                if (this.hour != 12 && o.hour != 12 && this.hour < o.hour) {
                        return -1;
                }
                else if (this.hour != 12 && o.hour != 12 && this.hour > o.hour) {
                    return 1;
                }

                else if (this.hour.equals(o.hour) ) {
                    if(this.min.equals(o.min)){
                        return 0;
                    }
                    else if (this.min < o.min){
                        return -1;
                    }
                    else {
                        return 1;
                    }
                }
                else if(this.hour == 12 ){
                    return -1;
                }
                else if(o.hour == 12 ){
                    return 1;
                }

            }
            else if(this.isAM && !o.isAM){ //this is lesser
                return -1;
            }
            return 1;//this is greater
        }
    }
    /*
    10:19PM 02:49AM
08:49AM 09:10AM
     */
    public static void main(String[] args){

      TestTime t1 = new TestTime("10:19PM");
        TestTime t2 = new TestTime("02:49AM");
        System.out.print(t1.compareTo(t2) < 0 ? "First" : "Second");

        t1 = new TestTime("08:49AM");
        t2 = new TestTime("09:10AM");
        System.out.print(t1.compareTo(t2) < 0 ? "First" : "Second");
    }
}
