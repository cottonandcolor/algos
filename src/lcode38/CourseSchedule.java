package lcode38;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by predave on 6/24/17.
 * public int scheduleCourse(int[][] courses) {
 int r = courses.length,c=0,ans=0,l=r,temp=0;

 if(r != 0){
 c = courses[0].length;
 }else{
 return 0;
 }
 Arrays.sort(courses, new Comparator<int[]>(){
 public int compare(int[] a,int[] b){
 return a[1]-b[1];
 }
 });


 PriorityQueue<int[]> heap = new PriorityQueue<int[]>(l,new Comparator<int[]>(){
 public int compare(int[] a,int[] b){
 return b[0] - a[0];
 }

 });
 heap.offer(courses[0]);
 temp += courses[0][0];
 for(int i=1;i<l;i++){
 int[] curr = heap.poll();

 if(temp+courses[i][0] <= courses[i][1]){

 heap.offer(courses[i]);
 temp += courses[i][0];
 }else if(temp-curr[0]+courses[i][0] <= courses[i][1] && courses[i][0] < curr[0]){
 temp -= curr[0];
 curr = courses[i];
 temp += courses[i][0];
 }

 heap.offer(curr);
 }
 ans = heap.size();

 return ans;
 }
 */
public class CourseSchedule {



     class Node
    {
        Range i;
        int max;
        Node left;
        Node right;
    }

     Node  createNode(Range i)
    {
        Node temp = new Node();
        temp.i = i;
        temp.max = i.max;
        return temp;
    }

     Node insert(Node root, Range i)
    {
        if (root == null)
            return createNode(i);

        int l = root.i.min;

        if (i.min < l)
            root.left = insert(root.left, i);


        else
            root.right = insert(root.right, i);

        if (root.max < i.max)
            root.max = i.max;

        return root;
    }
    class Range implements Comparable<Range>, Comparator<Range>
    {
        int min, max;
        public Range(int l, int h){
            min = l;
            max = h;
        }

        @Override
        public int compareTo(Range o) {
            if( this.max < o.max) return -1;
            else if(this.max == o.max){
                if(this.min < o.min) return -1;
            } else{
                return 1;
            }
            return 0;
        }

        @Override
        public int compare(Range o1, Range o) {
            if( o1.max < o.max) return -1;
            else if(o1.max == o.max){
                if(o1.min < o.min) return -1;
            } else{
                return 1;
            }
            return 0;
        }
    }

     boolean checkconflict(Range i1, Range i2)
    {
        if (i1.min < i2.max && i2.min < i1.max)
            return true;
        return false;
    }

     Range overlapSearch(Node root, Range i)
    {
        if (root == null) return null;

        if (checkconflict(root.i, i))
        return root.i;

        if (root.left != null && root.left.max >= i.min)
            return overlapSearch(root.left, i);

       return overlapSearch(root.right, i);
    }

     int checkConflicts(Range [] appt, int n)
    {
        Arrays.sort(appt);

        Node root = null;
        root = insert(root, appt[0]);
        int count = 0;
        int ocount = 0;
        for (int i=1; i<n; i++)
        {
           Range res = overlapSearch(root, appt[i]);
            if (res != null){
               ocount++;
            }

            root = insert(root, appt[i]);
        }
        return appt.length - ocount;
    }

    public int scheduleCourse(int[][] inp) {
        Range[] in = new Range[inp.length];
        for(int i = 0; i < inp.length; i++){
            in[i] = new Range(inp[i][0],inp[i][1]);
        }
        Arrays.sort(in);
      return checkConflicts(in,inp.length);
    }

    public static void main(String[] args){

        int[][] inp = {{5,5}, {4,6}, {2,6}};

        System.out.println(new CourseSchedule().scheduleCourse(inp));
    }
}
