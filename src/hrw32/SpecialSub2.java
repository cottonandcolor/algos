package hrw32;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by predave on 5/20/17.
 * SpecialSub
 * First let's consider the situation that we only need to get the size of . Assumed that all distinct palindromic substrings of  have been found and now we are asked to calculate how many prefixes of them are distinct. Let the palindromic substrings be , of course we cannot use k strings to save them, but we could use their positions in  to indicate the string itself, for example, let  be , and one palindromic substring is , we can use  to indicate aba.
 Then we sort the palindromic substrings in lexicographical order and |G(s)| is equal to . We now need to sort  and in order to do so we need a fast method to compare two strings. Hashing can be used here, with which we can first get the lcp of a and b, which are the two strings we need to compare, by a binary search. With the lcp of a and b, we only need to compare the next bit of lcp(a,b), which are for certain different. So we can now compare two strings in , and it's well-known that the sort algorithm based on comparison can be do no better than  comparisons, which means that the complexity of our overall algorithm is , which can not pass this problem.
 The aforesaid algorithm is slow because we have to do  comparisons and for each comparison we need to do a binary search, which is a waste of time. To accelerate it, let's first sort the strings by their lengths. We then build a binary search tree for the strings and insert them to the trees one by one. Here I choose Treap to be the bst. (for more details of Treap, you can look up here Treap) Now let's focus on the comparison, for example, for two string  and , assumed that , how we can compare them in a faster way.
 image
 In order to compare A and B, first we need to compare the first letters of both strings. If they are different, we have done, otherwise, we need to compare two strings marked by arrows. We should note that the orange strings are palindromes also and they are both shorter than  which means they have been inserted into the BST already. We can first use hashing to check if the two orange strings are equal, if they are we only need to check the last letter of B and we're done or else it's clear that whether the string marked by arrow of A is smaller than the counterpart of B is equivalent to whether the orange string of A is smaller than that of B. So if we can check compare two strings that are already inserted in the BST in , we can do the comparison in  and our overall complexity is .
 Ok now we need to find an approach to compare two strings in the BST fast. We set a label to each string in the BST and for each node  it has an interval  where labels of all nodes in the subtree of  are in.For convenience, we usually set  as 's label and the interval of its left child is  and that of its right child is  and we set the interval of the root. When we want to compare two strings in the BST, we only need to compare their label, which gives us an  approach.
 However, labels will change when we insert a string in the BST since the structure has changed. But it's quite easy to handle this case and when we make a rotation on treap we just simply dfs the whole subtree and change their labels! It's why we choose treap to be the BST that the expected size of subtree that we will dfs would be  since treap is one kind of weight-balanced tree. For the detail of exertion you can refer to my code.
 By far, we have discussed a way of calculating the size of  and it's fairly similar to solve the problem which focusses on every prefix of . Supposed that we have done for the prefix , now we are about to process prefix . We have sorted the palindromic substrings of prefix  and save them in a treap. It's easy to note that we will at most add one more distinct palindromic substring to the bst when we affix the char , and it has to be the longest palindromic suffix of prefix . If we figure out a way to get this string, we only need to insert it into the bst and update the answer. For example, assumed that we insert string  into the bst, and its adjacent strings are , the new answer should be . Thanks to bst, we could easily find one's adjacent strings and to get the lcp we just do a binary search. The last thing is to find the longest palindromic suffix of prefix , which could be done by eertree. There is already a nice description and I'm not going to explain it here.
 So after a long and tough analysis, we come to a solution whose complexity is .
 Set by philipsweng

 Problem Setter's code :
 \
 #include <bits/stdc++.h>

 using namespace std;

 const int maxn = 300005,mo[2] = {int(1e9) + 7,998244353};

 typedef unsigned long long ull;

 struct treap_node
 {
 ull rk;
 int l,r,v,siz;
 }T[maxn];

 struct palin_node
 {
 int go[26],fail,len,fa;
 }Pt[maxn];

 char s[maxn];
 ull ans;
 int sav[maxn][2];
 int has[2][maxn],pw[2][maxn],cnt,root,n;

 void palin_prepare()
 {
 cnt = 1;
 Pt[1].len = -1;
 Pt[1].fail = Pt[0].fail = 1;
 T[0].rk = 0,T[1].rk = -1;
 }

 int get(int p,int x)
 {
 for(;s[n - Pt[p].len - 1] - 'a' != x;p = Pt[p].fail);
 return p;
 }

 bool append(int &lst,int x)
 {
 int p = lst;
 ++ n;
 p = get(p,x);
 if (!Pt[p].go[x])
 {
 int cur = ++ cnt;
 Pt[cur].fa = p;
 Pt[cur].len = Pt[p].len + 2;
 Pt[cur].fail = Pt[get(Pt[p].fail,x)].go[x];
 Pt[p].go[x] = cur;
 lst = cur;
 return 1;
 }
 lst = Pt[p].go[x];
 return 0;
 }

 void relabel(int p,ull l,ull r)
 {
 if (!p) return;
 ull mid = (l + r) >> 1;
 T[p].rk = mid;
 relabel(T[p].l,l,mid),relabel(T[p].r,mid,r);
 }

 inline bool same(int l,int r,int x,int y)
 {
 if (r - l != y - x) return 0;
 if (l > r) return 1;
 for(int i = 0;i < 2;i ++)
 {
 int a = (has[i][r] - has[i][l - 1] * 1ll * pw[i][r - l + 1]) % mo[i];
 int b = (has[i][y] - has[i][x - 1] * 1ll * pw[i][y - x + 1]) % mo[i];
 if ((a < 0 ? a + mo[i] : a) != (b < 0 ? b + mo[i] : b)) return 0;
 }
 return 1;
 }

 bool small(int a,int b)
 {
 if (Pt[a].len < Pt[b].len) return !small(b,a);
 int la = sav[a][0],ra = sav[a][1],lb = sav[b][0],rb = sav[b][1];
 if (s[la] != s[lb]) return s[la] < s[lb];
 if (same(la + 1,la + rb - lb - 1,lb + 1,rb - 1)) return s[la + rb - lb] < s[rb];
 return T[Pt[a].fa].rk < T[Pt[b].fa].rk;
 }

 void update(int x)
 {
 if (!x) return;
 T[x].siz = T[T[x].l].siz + T[T[x].r].siz + 1;
 }

 int insert(int &u,ull l,ull r)
 {
 int tmp = 0;
 ull mid = (l + r) >> 1;
 if (!u) u = cnt,T[cnt].rk = mid; else
 {
 if (small(cnt,u))
 {
 tmp = insert(T[u].l,l,mid);
 if (T[T[u].l].v > T[u].v)
 {
 int p = T[u].l;
 T[u].l = T[p].r,T[p].r = u;
 update(u);
 u = p;
 relabel(p,l,r);
 }
 } else
 {
 tmp = T[T[u].l].siz + 1 + insert(T[u].r,mid,r);
 if (T[T[u].r].v > T[u].v)
 {
 int p = T[u].r;
 T[u].r = T[p].l,T[p].l = u;
 update(u);
 u = p;
 relabel(p,l,r);
 }
 }
 }
 update(u);
 return tmp;
 }

 int get_od(int s,int p)
 {
 if (!s || s > T[p].siz) return 0;
 if (s <= T[T[p].l].siz) return get_od(s,T[p].l);
 if (s == T[T[p].l].siz + 1) return p;
 return get_od(s - T[T[p].l].siz - 1,T[p].r);
 }

 int get_lcp(int l,int r,int x,int y)
 {
 int ll = 1,rr = min(r - l + 1,y - x + 1),tmp = 0;
 for(int mid;ll <= rr;)
 {
 mid = (ll + rr) >> 1;
 if (same(l,l + mid - 1,x,x + mid - 1)) tmp = mid,ll = mid + 1; else rr = mid - 1;
 }
 return tmp;
 }

 int get_lcp(int a,int b)
 {
 return get_lcp(sav[a][0],sav[a][1],sav[b][0],sav[b][1]);
 }

 void push(int l,int r)
 {
 ans += r - l + 1;
 sav[cnt][0] = l,sav[cnt][1] = r;
 T[cnt].v = rand();
 int s = insert(root,0,1ll << 60) + 1;
 int lx = get_od(s - 1,root),rx = get_od(s + 1,root);
 if (lx && rx) ans += get_lcp(lx,rx);
 if (lx) ans -= get_lcp(lx,cnt);
 if (rx) ans -= get_lcp(cnt,rx);
 }

 int main()
 {
 int type = 0;
 scanf("%d", &type);
 scanf("%d", &n);
 scanf("%s", s + 1);
 for(int i = 0;i < 2;i ++) pw[i][0] = 1;
 palin_prepare();
 n = 0;
 for(int i = 1,lst = 0,l = strlen(s + 1);i <= l;i ++)
 {
 for(int j = 0;j < 2;j ++)
 {
 pw[j][i] = pw[j][i - 1] * 37ll % mo[j];
 has[j][i] = (has[j][i - 1] * 37ll + s[i] - 'a') % mo[j];
 }
 if (append(lst,s[i] - 'a')) push(i - Pt[lst].len + 1,i);
 printf("%llu\n", ans);
 }
 return 0;
 }
 */
public class SpecialSub2 {

    static List<Integer> countList = new ArrayList<Integer>() ;
    static Set<String> t = new HashSet<String>();
    static Set<String> G = new HashSet<String>();


    static Integer[] propertyOfString(String s){
        // Complete this function

        int index = 0;

        char c = s.charAt(0);
        t.add(String.valueOf(c));
        G.add(String.valueOf(c));
        countList.add(new Integer(1));

        for(int i = 1 ; i < s.length(); i++){
            Set<String> newStrings = new HashSet<String>();
            char ci = s.charAt(i);
            if(!t.contains(String.valueOf(ci))) {
                t.add(String.valueOf(ci));
                newStrings.add(String.valueOf(ci));
            }
            int prev = i - 1;
            //while(prev >= 0) {
                if (ci == s.charAt(prev)) {
                    String p = "" + ci + ci;
                    if (!t.contains(p)) {
                        t.add(p);
                        newStrings.add(p);
                    }
                }
                for(int j = 0 ; j < i -1 ; j++) {
                    String palin = getPalindrome(s.substring(j, i + 1));
                    if (palin != null && !palin.isEmpty()) {
                        if (!t.contains(palin)) {
                            t.add(palin);
                            newStrings.add(palin);
                        }
                    }
                }

                //prev--;
            //}

            addCountList(countList.get(countList.size() - 1) , newStrings);
        }

        Integer[] ret = new Integer[countList.size()];
        countList.toArray(ret);
        return ret;
    }

    private static String getPalindrome(String s) {
        StringBuilder ret = new StringBuilder("") ;
        int len = s.length();
        int start = 0;
        int end = len - 1;
        while (start < end){
                if(s.charAt(start) != s.charAt(end)){
                   return null;
                }
                start++;
                end--;
        }
        return s;
    }

    private static void addCountList(Integer prevCount, Set<String> newStrings) {
        for(String s: newStrings){
            String o = "";
            for(int i = 0; i < s.length() ; i++){
                o = o + s.charAt(i);
                if(!G.contains(o)) {
                    G.add(o);
                    prevCount++;
                }
            }
        }
        countList.add(prevCount);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String s = in.next();
        Integer[] result = propertyOfString(s);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + (i != result.length - 1 ? "\n" : ""));
        }
        System.out.println("");


    }
}
