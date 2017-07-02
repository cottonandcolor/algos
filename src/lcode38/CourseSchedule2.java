package lcode38;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class CourseSchedule2 {
public int scheduleCourse(int[][] courses) {
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


 PriorityQueue<Integer> heap = new PriorityQueue<Integer>(l,new Comparator<Integer>(){
 public int compare(Integer a,Integer b){
 return b - a;
 }

 });
 heap.offer(courses[0][0]);
 temp += courses[0][0];
 for(int i=1;i<l;i++){
 Integer curr = heap.poll();

 if(temp+courses[i][0] <= courses[i][1]){

 heap.offer(courses[i][0]);
 temp += courses[i][0];
 }else if(temp-curr+courses[i][0] <= courses[i][1] && courses[i][0] < curr){
 temp -= curr;
 curr = courses[i][0];
 temp += courses[i][0];
 }

 heap.offer(curr);
 }
 ans = heap.size();

 return ans;
 }






    public static void main(String[] args){

        int[][] inp = {{5,5}, {4,6}, {2,6}};

        System.out.println(new CourseSchedule2().scheduleCourse(inp));
    }
}
