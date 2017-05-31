package hrrookie;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
/*

int main() {

int n;
        cin>>n;
        int a, burntOut;
        vector<int> burntList(n);
        vector<int> supervisor(n);
        vector<set<int> > subordinates(n);
        for(int i = 1; i < n; i++){
        cin>>a>>burntOut;
        supervisor[i] = a;
        subordinates[a].insert(i);

        if(burntOut == 0) burntList[i] = 0;
        else burntList[i] = 1;
        }
        int minCount = 99999;
        int currentSupervisor = supervisor[n-1];
        int count = 0;
        bool checkFlag;

        for(int i = subordinates.size() - 1; i >= 0; i--){

        // loop over all subordinates
        checkFlag = false;
        for(set<int> :: iterator it = subordinates[i].begin(); it != subordinates[i].end(); ++it) {
        // check if there is a burnt out subordinate
        //cout<<"subordinate of "<<i<<" is "<<*it<<"\n";
        if (burntList[*it] == 0){
        // if yes, make this green and all subordinates and superior green
        checkFlag = true;
        //cout<<i<<" is sent for conference\n";
        burntList[i] = 1;
        burntList[*it] = 1;
        burntList[supervisor[i]] = 1;
        }
        }
        if(checkFlag) count++;
        }
        cout<<count;
        return 0;
        }
 */
public class CultureConf2 {


        static private boolean[] marked;
        static private int n;
        static int[][] e;
        static int count = 0;
        static Graph graph;

        public static class Graph {
            int[] nodes;
            Map<Integer, List> adjMap = new HashMap<Integer, List>();

            public Graph(int n) {
                nodes = new int[n];
                nodes[0] = 1;
            }

            public void addChild(int root, int child, int b) {
                nodes[child] = b;
                if (adjMap.get(root) == null) {
                    List<Integer> adj = new LinkedList<Integer>();
                    adj.add(child);
                    adjMap.put(root, adj);
                } else {
                    adjMap.get(root).add(child);
                }
            }

//if any of child in adj list is 0 , mark children,parent , this as 1 increase count
            public int getMinCount() {
                int size = nodes.length - 1;
                while (size >= 0 ) {
                    Integer node = size;
                    List<Integer> adjlist = adjMap.get(node);
                    marked[node] = true;

                    if (adjlist != null) {
                        boolean burntsubfound = false;
                        for (Integer n : adjlist) {
                            if (nodes[n] == 0) {
                                burntsubfound = true;
                                break;
                            }
                        }
                        if(burntsubfound) {
                            count++;
                            nodes[node] = 1;
                            if(node != 0) {
                                nodes[e[node - 1][0]] = 1;
                            }
                            for (Integer n : adjlist) {
                                nodes[n] = 1;
                            }
                        }
                    }
                  size --;
                }
                return count;
            }

        }



        static int getMinimumEmployees(int[][] e){
            // Complete this function


            return graph.getMinCount();
        }

        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            n = in.nextInt();

            marked = new boolean[n];
            graph = new Graph(n);

            // Information for employees 1 through n - 1:
            // The first value is the employee's supervisor ID
            // The second value is the employee's burnout status (0 is burned out, 1 is not)
            e = new int[n-1][2];
            for(int e_i=0; e_i < n-1; e_i++){
                for(int e_j=0; e_j < 2; e_j++){
                    e[e_i][e_j] = in.nextInt();
                }
                graph.addChild(e[e_i][0], e_i + 1, e[e_i][1]);
            }
            int minimumEmployees = getMinimumEmployees(e);
            System.out.println(minimumEmployees);
        }


}
