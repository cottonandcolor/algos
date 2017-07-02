package hrdynamic;

import hrrookie.CultureConf2;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by predave on 6/27/17.
 * for(child : currentNode)
 {
 a0 = dfs(child,0,cur);
 a1 = dfs(child,1,cur);

 val0 = val0 * a0 % mod;
 val1 = val1 * a1 % mod;

 total = total * ( a0 + a1 ) % mod;
 }

 Let dp[node][cur][par] be the state of a dp. (where node = nodeNumber, cur = 0 or 1 (color of the node), par = 0 or 1 (color of parent)).
 Now, at each call of dfs(node, cur, par), I'll calculate three things:
 val0, val1, total.(All 3 initialised to 1).
 val0 is the total number of cases where ALL the children are of color 0.
 val1 is the total number of cases where ALL the children are of color 1.
 total is all the possible arrangements of the children nodes.
 This is the main part of the code:
 for(child : currentNode)
 {
 a0 = dfs(child,0,cur);
 a1 = dfs(child,1,cur);

 val0 = val0 * a0 % mod;
 val1 = val1 * a1 % mod;

 total = total * ( a0 + a1 ) % mod;
 }
 here, a0 is the number of ways of arranging the current child such that the current child has the color 0. And a1, obviously color 1.
 The sum of these will give the total ways of arranging the subtree rooted at that particular child.
 Keep multiplying all these values with 'total' (modulo mod).
 That gives us the total arrangements of the subtrees.
 Now I'll tell you the reason to use val0 and val1.
 After iterating through all the children nodes, I'll check for a certain condition.
 If cur==par (i.e. the current color is the same as the parent color) I will just cache the total value and return it
 return dp[node][cur][par] = total;
 If cur != par , then I'll check the value of cur.
 If cur = 0, then I will change my total as total = (total - val1 + mod) % mod;
 What that essentially means is that if cur is 0, then par = 1 (since we reached this part of the code because cur != par), I'm removing the cases where all the children nodes have the color 1.
 I'm doing it because the node must be of the same color as AT LEAST one of its children.
 That is the reason why I calculated val1.
 If cur = 1, then my total will become total = (total - val0 + mod) % mod; (Because I'm removing the case where ALL the children have the value of 0)
 And after reducing this value, I return that value of total (after caching it of course).
 Now how should I call the function to obtain the final answer??
 We need to call the function at the root node obviously. (Let the root node be 0).
 So we finally end up calling
 dfs(0,0,1) + dfs(0,1,0);
 Since the parent will HAVE to always be the same as atleast one of the children, we can consider it as being connected to a parent of the opposite color.
 12  |


 in the code same[u][0] stores the value of how many different ways it is possible to have a configuration such that "u" has a color 0 and all its children have the color opposite to it that is "1".
 Similarly you can understand same[u][1]. diff[u][0] means that "u" has the color 0 and its children can have any configuration except for one configuration that is 0-1-0 configuration.
 That's why we exclude same[v][1](v is u's child) in the calculation of diff[u][0]. Because same[v][1] means that all its children are 0. So diff will hold all the required configuration plus 1 set of undesirable configurations(we multiply diff[v][1] with diff[u][0]) so we subtract same[u][0] from diff[u][0] to get the desirable result.
 This is how your traversal code will look like in java:-

 */
public class KingdomDivision {
    private final static int MODULO = 1000000007;

    private static int[][][] state;

    private static ArrayList<ArrayList<Integer>> graph;

    private static int evaluate(int node, int color, int parent, int parentColor) {

        if (state[node][color][parentColor] != -1) {
            return state[node][color][parentColor];
        }

        if (graph.get(node).size() == 1 && node != 0) {
            if (color == parentColor) {
                state[node][color][parentColor] = 1;
            } else {
                state[node][color][parentColor] = 0;
            }
            return state[node][color][parentColor];
        }

        state[node][color][parentColor] = 1;
        for (int i = 0; i < graph.get(node).size(); i++) {
            int child = graph.get(node).get(i);
            if (child == parent) {
                continue;
            }
            int childValue = 0;
            for (int childColor = 0; childColor < 2; childColor++)
                childValue = (childValue +  evaluate(child, childColor, node, color)) % MODULO;
            state[node][color][parentColor] = (int)((state[node][color][parentColor] * 1L * childValue) % MODULO);
        }
        if (parentColor != color) {
            int diff = 1;
            for (int i = 0; i < graph.get(node).size(); i++) {
                int child = graph.get(node).get(i);
                if (child == parent) {
                    continue;
                }
                diff = (int)((diff * 1L * evaluate(child, 1 - color, node, color)) % MODULO);
            }
            state[node][color][parentColor] -= diff;
            while (state[node][color][parentColor] < 0) {
                state[node][color][parentColor] += MODULO;
            }
        }
        return state[node][color][parentColor];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<Integer>());
        }

        state = new int[n][2][2];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < 2; j++)
                for (int k = 0; k < 2; k++)
                    state[i][j][k] = -1;

        for(int a0 = 0; a0 < n-1; a0++) {
            int u = in.nextInt();
            int v = in.nextInt();
            graph.get(u - 1).add(v - 1);
            graph.get(v - 1).add(u - 1);
        }

        int result = (evaluate(0, 1, -1, 0) + evaluate(0, 0, -1, 1)) % MODULO;
        System.out.println(result);
    }
}
