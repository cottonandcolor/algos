package lcode38;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class CourseSchedule1 {
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






    public static void main(String[] args){

        int[][] inp = {{5,5}, {4,6}, {2,6}};

        System.out.println(new CourseSchedule1().scheduleCourse(inp));
    }
}
