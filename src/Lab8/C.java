import java.io.*;
import java.util.*;

class AVLTree {
    public static int delete = 0;
    AVLTree left;
    AVLTree right;
    AVLTree father;
    int key;
    int[] arr;

    AVLTree(int key) {
        this.key = key;
        arr = new int[3];
    }

    public static int insertion(AVLTree tree, int value) {
        tree.arr[0]++;
        if (tree.key >= value) {
            if (tree.left == null) {
                AVLTree newTree = new AVLTree(value);
                tree.left = newTree;
                newTree.father = tree;
                tree.arr[1]++;
                return 1;
            } else {
                tree.arr[1] = insertion(tree.left, value) + 1;
                if (Math.abs(tree.arr[1] - tree.arr[2]) >= 2) {
                    reBalance(tree);
                }
                return Math.max(tree.arr[1], tree.arr[2]);
            }
        } else {
            if (tree.right == null) {
                AVLTree newTree = new AVLTree(value);
                newTree.father = tree;
                tree.right = newTree;
                tree.arr[2]++;
                return 1;
            } else {
                tree.arr[2] = insertion(tree.right, value) + 1;
                if (Math.abs(tree.arr[1] - tree.arr[2]) >= 2) {
                    reBalance(tree);
                }
                return Math.max(tree.arr[1], tree.arr[2]);
            }
        }
    }

    public static int deletion(AVLTree tree, int value) {

        if (tree.key == value) {
            return tureDeletion(tree);
        } else if (tree.key > value) {
            tree.arr[0]--;
            tree.arr[1] = deletion(tree.left, value) + 1;
            if (Math.abs(tree.arr[1] - tree.arr[2]) >= 2) {
                reBalance(tree);
            }
            return Math.max(tree.arr[1], tree.arr[2]);
        } else {
            tree.arr[0]--;
            tree.arr[2] = deletion(tree.right, value) + 1;
            if (Math.abs(tree.arr[1] - tree.arr[2]) >= 2) {
                reBalance(tree);
            }
            return Math.max(tree.arr[1], tree.arr[2]);
        }

    }

    public static int tureDeletion(AVLTree tree) {
        if (tree.left == null && tree.right == null) {
            AVLTree temp = tree.father;
            if (temp.left == tree) {
                temp.left = null;
            } else {
                temp.right = null;
            }
            tree.father = null;
            return -1;
        } else if (tree.right == null) {
            if(tree.father==null){
                tree.key=tree.left.key;
                tree.arr[0]--;
                tree.arr[1]--;
                tree.left.father=null;
                tree.left=null;
                return 0;
            }
            AVLTree temp = tree.father;
            AVLTree sub = tree.left;
            sub.father = temp;
            if (temp.left == tree) {
                temp.left = sub;
            } else {
                temp.right = sub;
            }
            tree.father = null;
            return Math.max(sub.arr[1], sub.arr[2]);
        } else {
            tree.arr[2] = find(tree.right) + 1;
            tree.arr[0]--;
            tree.key = delete;
            if (Math.abs(tree.arr[1] - tree.arr[2]) >= 2) {
                reBalance(tree);
            }
            return Math.max(tree.arr[1], tree.arr[2]);
        }
    }

    public static int find(AVLTree tree) {
        if (tree.left != null) {
            tree.arr[0]--;
            tree.arr[1] = find(tree.left) + 1;
            if (Math.abs(tree.arr[1] - tree.arr[2]) >= 2) {
                reBalance(tree);
            }
            return Math.max(tree.arr[1], tree.arr[2]);
        } else {
            delete = tree.key;
            if (tree.right == null) {
                AVLTree temp = tree.father;
                if (temp.left == tree) {
                    temp.left = null;
                } else {
                    temp.right = null;
                }
                tree.father = null;
                return -1;
            } else {
                AVLTree temp = tree.father;
                AVLTree sub = tree.right;
                sub.father = temp;
                if (temp.left == tree) {
                    temp.left = sub;
                } else {
                    temp.right = sub;
                }
                tree.father = null;
                return Math.max(sub.arr[1], sub.arr[2]);
            }
        }
    }
    public static void reBalance(AVLTree tree) {
        if (tree.arr[1] > tree.arr[2]) {
            int h = tree.arr[2];
            if (tree.left.arr[1]>=tree.left.arr[2]){
                AVLTree A =tree.left.left;
                AVLTree B = tree.left.right;
                AVLTree C = tree.right;
                AVLTree b = tree.left;
                int temp = b.key;
                b.key=tree.key;
                tree.key=temp;
                tree.left=A;
                A.father=tree;
                tree.arr[1]=h+1;
                tree.right=b;
                b.father=tree;
                b.arr[0]=0;
                if (B==null){
                    b.left=null;
                    b.arr[1]=0;
                }else {
                    b.left=B;
                    b.arr[1]=Math.max(B.arr[1],B.arr[2])+1;
                    b.arr[0]=b.arr[0]+B.arr[0]+1;
                    B.father=b;
                }
                if (C==null){
                    b.right=null;
                    b.arr[2]=0;
                }else {
                    b.right=C;
                    b.arr[2]=Math.max(C.arr[1],C.arr[2])+1;
                    b.arr[0]+=C.arr[0]+1;
                    C.father=b;
                }
                tree.arr[2] = Math.max(tree.right.arr[2],tree.right.arr[1])+1;
            }else {
                AVLTree A = tree.left.left;
                AVLTree B = tree.left.right.left;
                AVLTree C = tree.left.right.right;
                AVLTree D = tree.right;
                int temp = tree.key;
                AVLTree c = tree.left.right;
                AVLTree b = tree.left;
                tree.key=c.key;
                c.key=temp;
                b.arr[0]=0;
                tree.right=c;
                c.father=tree;
                if (A==null){
                    b.left=null;
                    b.arr[1]=0;
                }else {
                    b.left=A;
                    b.arr[1] = Math.max(A.arr[1],A.arr[2])+1;
                    b.arr[0]+=A.arr[0]+1;
                    A.father=b;
                }
                if (B==null){
                    b.right=null;
                    b.arr[2]=0;
                }else {
                    b.right=B;
                    b.arr[2] = Math.max(B.arr[1],B.arr[2])+1;
                    b.arr[0]+=B.arr[0]+1;
                    B.father=b;
                }
                c.arr[0]=0;
                if (C==null){
                    c.left=null;
                    c.arr[1]=0;
                }else {
                    c.left=C;
                    c.arr[1] = Math.max(C.arr[1],C.arr[2])+1;
                    c.arr[0]+=C.arr[0]+1;
                    C.father=c;
                }
                if (D==null){
                    c.right=null;
                    c.arr[2]=0;
                }else {
                    c.right=D;
                    c.arr[2] = Math.max(D.arr[1],D.arr[2])+1;
                    c.arr[0]+=D.arr[0]+1;
                    D.father=c;
                }
                tree.arr[1]=tree.arr[2]=h+1;
            }
        }else {
            int h = tree.arr[1];
            if (tree.right.arr[2]>=tree.right.arr[1]){
                AVLTree A =tree.right.right;
                AVLTree B = tree.right.left;
                AVLTree C = tree.left;
                AVLTree b = tree.right;
                int temp = b.key;
                b.key=tree.key;
                tree.key=temp;
                tree.right=A;
                A.father=tree;
                tree.arr[2]=h+1;
                tree.left=b;
                b.father=tree;
                b.arr[0]=0;
                if (B==null){
                    b.right=null;
                    b.arr[2]=0;
                }else {
                    b.right=B;
                    b.arr[2]=Math.max(B.arr[1],B.arr[2])+1;
                    b.arr[0]+=B.arr[0]+1;
                    B.father=b;
                }
                if (C==null){
                    b.left=null;
                    b.arr[1]=0;
                }else {
                    b.left=C;
                    b.arr[1]=Math.max(C.arr[1],C.arr[2])+1;
                    b.arr[0]+=C.arr[0]+1;
                    C.father=b;
                }
                tree.arr[1] = Math.max(tree.left.arr[2],tree.left.arr[1])+1;
            }else {
                AVLTree A = tree.right.right;
                AVLTree B = tree.right.left.right;
                AVLTree C = tree.right.left.left;
                AVLTree D = tree.left;
                int temp = tree.key;
                AVLTree c = tree.right.left;
                AVLTree b = tree.right;
                tree.key=c.key;
                c.key=temp;
                b.arr[0]=0;
                c.arr[0]=0;
                tree.left=c;
                c.father=tree;
                if (A==null){
                    b.right=null;
                    b.arr[2]=0;
                }else {
                    b.right=A;
                    b.arr[2] = Math.max(A.arr[1],A.arr[2])+1;
                    b.arr[0]+=A.arr[0]+1;
                    A.father=b;
                }
                if (B==null){
                    b.left=null;
                    b.arr[1]=0;
                }else {
                    b.left=B;
                    b.arr[1] = Math.max(B.arr[1],B.arr[2])+1;
                    b.arr[0]+=B.arr[0]+1;
                    B.father=b;
                }
                if (C==null){
                    c.right=null;
                    c.arr[2]=0;
                }else {
                    c.right=C;
                    c.arr[2] = Math.max(C.arr[1],C.arr[2])+1;
                    c.arr[0]+=C.arr[0]+1;
                    C.father=c;
                }
                if (D==null){
                    c.left=null;
                    c.arr[1]=0;
                }else {
                    c.left=D;
                    c.arr[1] = Math.max(D.arr[1],D.arr[2])+1;
                    c.arr[0]+=D.arr[0]+1;
                    D.father=c;
                }
                tree.arr[1]=tree.arr[2]=h+1;
            }
        }
    }

    public static int findKth(AVLTree tree,int k) {
        int s;
        if (tree.left == null) {
            if(k==1){
                return tree.key;
            }else {
                return findKth(tree.right,k-1);
            }
        } else {
            s = tree.left.arr[0]+1;

            if (s >= k) {
                return findKth(tree.left, k);
            } else if (s + 1 == k) {
                return tree.key;
            } else {
                return findKth(tree.right, k - s - 1);
            }
        }
    }

}
public class Main {
    public static int predecessorQuery(AVLTree tree,int q){
        int p = Integer.MIN_VALUE;
        AVLTree u = tree;
        while (u!=null){
            if (u.key==q){
                p=q;
                return p;
            }
            if (u.key>q){
                u=u.left;
            }else {
                p=u.key;
                u=u.right;
            }
        }
        return p;
    }
    public static int SuccessorQuery(AVLTree tree,int q){
        int p = Integer.MIN_VALUE;
        AVLTree u = tree;
        while (u!=null){
            if (u.key==q) {
                p = q;
                return p;
            }
            if (u.key<q){
                u=u.right;
            }else {
                p=u.key;
                u=u.left;
            }
        }
        return p;
    }
    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);
//        int n =in.nextInt();
//        AVLTree pet = new AVLTree(Integer.MIN_VALUE);
//        AVLTree adopter = new AVLTree(Integer.MIN_VALUE);
//        long answer =0;
//        int[] arr = new int[]
//        for (int i = 0; i < n; i++) {
//            int cases = in.nextInt();
//            int value = in.nextInt();
//            if (cases==0){
//                if (adopter.arr[0]==0){
//                    AVLTree.insertion(pet,value);
//                }else {
//                    int predecessor = predecessorQuery(adopter,value);
//                    int successor = SuccessorQuery(adopter,value);
//                    if (Math.abs(predecessor-value)<Math.abs(successor-value)){
//                        answer+=Math.abs(predecessor-value);
//                        AVLTree.deletion(adopter,predecessor);
//                    }else {
//                        answer+=Math.abs(successor-value);
//                        AVLTree.deletion(adopter,successor);
//                    }
//                }
//            }else {
//                if (pet.arr[0]==0){
//                    AVLTree.insertion(adopter,value);
//                }else {
//                    int predecessor = predecessorQuery(pet,value);
//                    int successor = SuccessorQuery(pet,value);
//                    if (Math.abs(predecessor-value)<Math.abs(successor-value)){
//                        answer+=Math.abs(predecessor-value);
//                        AVLTree.deletion(pet,predecessor);
//                    }else {
//                        answer+=Math.abs(successor-value);
//                        AVLTree.deletion(pet,successor);
//                    }
//                }
//            }
//
//        }
//        out.print(answer);
        int times = in.nextInt();
        for (int i = 0; i < times; i++) {
            int arrLen = in.nextInt();
            long[] arr = new long[arrLen];
            long[] time = new long[arrLen];
            long answer=0;
            for (int j = 0; j < arrLen; j++) {
                arr[j] = in.nextLong();
            }
            long max = Long.MIN_VALUE;
            for (int j = 0; j < arrLen; j++) {
                time[j] = in.nextLong();
                if (time[j]>max){
                    max=time[j];
                }
            }
            AVLTree root=null;
            for (int j = 1; j <=(int)max+1 ; j++) {
                if (j==1){
                    root=new AVLTree(j);
                }else {
                    AVLTree.insertion(root,j);
                }
            }
            long[] tep1 =new long[arrLen];
            long[] tep2 =new long[arrLen];
            mergeSort(arr,0,arrLen-1,tep1,time,tep2);
            for (int j = arrLen-1; j >=0 ; j--) {
                int when = predecessorQuery(root,(int)time[j]);
                if (when!=Integer.MIN_VALUE){
                    AVLTree.deletion(root,when);
                    answer+=arr[j];
                }
            }
            out.println(answer);
        }
        out.close();
    }
    public static void mergeSort(long[] arr,int low,int high,long[] tmp,long[]arr1,long[]tmp2) {
        if (low >= high) {
            return;
        }
        mergeSort(arr,low,(high+low)/2,tmp,arr1,tmp2);
        mergeSort(arr,(high+low)/2+1,high,tmp,arr1,tmp2);
        merge(arr,low,high,tmp,arr1,tmp2);

    }
    public static void merge(long[] arr,int low,int high,long[]tep,long[]arr1,long[]tmp2){
        int mid = (low+high)/2;
        int leftArr = low;
        int rightArr = mid+1;

        for (int i = 0; i <high-low+1 ; i++) {
            if (leftArr>mid){
                tep[low+i]=arr[rightArr];
                tmp2[low+i] = arr1[rightArr];
                rightArr++;
                continue;
            }
            if (rightArr>high){
                tep[low+i]=arr[leftArr];
                tmp2[low+i]=arr1[leftArr];
                leftArr++;
                continue;
            }
            if (arr[rightArr]<arr[leftArr]){
                tep[low+i]=arr[rightArr];
                tmp2[low+i]=arr1[rightArr];
                rightArr++;
                continue;
            }
            if (arr[rightArr]>=arr[leftArr]){
                tep[low+i]=arr[leftArr];
                tmp2[low+i]=arr1[leftArr];
                leftArr++;
            }
        }
        for (int i = 0; i <high-low+1 ; i++) {
            arr[low+i]=tep[low+i];
            arr1[low+i] = tmp2[low+i];
        }

    }



    static class Reader
    {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            }  while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.')
            {
                while ((c = read()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }

}