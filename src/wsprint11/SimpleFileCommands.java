package wsprint11;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;


public class SimpleFileCommands {
    PriorityQueue<Integer> fileQueue=new PriorityQueue<Integer>(1,new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1-o2;
        }
    });
    static Map<String , PriorityQueue<Integer>> fileMap = new HashMap<String , PriorityQueue<Integer>>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        // Process each command
        for(int a0 = 0; a0 < q; a0++){
            // Read the first string denoting the command type
            String command = in.next();
            if("crt".equals(command)){
                String filename = in.next();
                System.out.println(createFile(filename, false));
            } else if("del".equals(command)) {
                String filename = in.next();
                System.out.println(deleteFile(filename, false));
            } else if("rnm".equals(command)) {
                String fromFile = in.next();
                String toFile = in.next();
                System.out.println(renameFile(fromFile, toFile));
            }
            // Write additional code to read the command's file name(s) and process the command
            // Print the output string appropriate to the command
        }
    }

    private static String renameFile(String fromFile, String toFile) {
        String str = deleteFile(fromFile, true);
        str += createFile(toFile, true);
        return str;
    }

    private static String deleteFile(String filename, boolean rename) {
        int i = filename.indexOf('(');
        String fn = i == -1 ? filename : filename.substring(0,i);
        PriorityQueue<Integer> pq = fileMap.get(fn);
        if(pq != null) {
            if (i == -1) {
                pq.add(0);
            } else {
                int j = filename.indexOf(')');
                pq.add(Integer.parseInt(filename.substring(i+1,j)));
            }
        }
        if( !rename) {
            return "- " + filename;
        } else {
            return "r " + filename + " -> ";
        }
    }

    private static String createFile(String filename, boolean rename) {
        PriorityQueue<Integer> pq = fileMap.get(filename);
        int nextIndex = 0;
        if(pq == null) {
            pq = new PriorityQueue<Integer>();
            fileMap.put(filename,pq);
            pq.add(nextIndex+1);
        } else {
            nextIndex = pq.poll();
        }
        if(pq.size() == 0){
            pq.add(nextIndex+1);
        }
        if(nextIndex == 0) {
            if(!rename) {
                return "+ " + filename;
            } else {
                return filename;
            }
        } else {
            if(!rename) {
                return "+ " + filename + "(" + nextIndex + ")";
            } else {
                return filename + "(" + nextIndex + ")";
            }
        }

    }
}
