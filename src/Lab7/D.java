import java.io.*;
import java.util.*;
class Tree{
    Tree l;
    Tree r;
    int value;
    Tree(int value){
        this.value = value;
    }

}

public class Main {
    public static int count = 0;
    public static int count1 = 0;
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int times = in.nextInt();
        for (int i = 0; i < times; i++) {
            count = 0;
            count1=0;
            int l = in.nextInt();
            int[] pre = new int[l];
            int[] iO = new int[l];
            for (int j = 0; j < l; j++) {
                pre[j] = in.nextInt();
            }
            for (int j = 0; j < l; j++) {
                iO[j] = in.nextInt();
            }
            Tree root = process(pre, iO, 0, l);
            int[] post = new int[l];
            post(root,post);
            for (int j = 0; j < l; j++) {
                if(j==l-1){
                    System.out.print(post[j]);
                    break;
                }
                System.out.print(post[j]+" ");
            }
            System.out.println();
        }
    }

    public static Tree process(int[] pre, int[] mid, int l, int r) {
        int m = 0;
        if (count >= pre.length) {
            return null;
        }
        for (int i = l; i < r + 1; i++) {
            if (pre[count] == mid[i]) {
                m = i;
                break;
            }
        }
        Tree tree = new Tree(pre[count]);
        count++;
        if (m > l) {
            tree.l = process(pre, mid, l, m - 1);
        }
        if (m < r) {
            tree.r = process(pre, mid, m + 1, r);
        }
        return tree;
    }

    public static void post(Tree tree,int[]post) {
        if (tree.l != null) {
            post(tree.l,post);
        }
        if (tree.r != null) {
            post(tree.r,post);
        }
        post[count1++]=tree.value;

    }


}
