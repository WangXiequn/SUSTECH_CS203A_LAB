import java.io.*;
import java.util.*;
class Queue{
    Tree[] trees;
    Queue(){
        trees = new Tree[100099];
        rail=front=0;
    }
    int rail;
    int front;
    public void enQueue(Tree tree){
        trees[rail++] = tree;
    }
    public boolean isEmpty(){
        return front==rail;
    }
    public Tree deQueue(){
        return trees[front++];
    }
}
class Tree{
    int value;
    boolean visited = false;
    ArrayList<Tree> subTrees;
    boolean committed = false;
    boolean isCut = false;
    Tree(int value){
        this.value = value;
        subTrees = new ArrayList<Tree>();
    }
    public void addSubTree(Tree tree){
        subTrees.add(tree);
    }
}
public class Main {

    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);
        int times = in.nextInt();
        for (int i = 0; i < times; i++) {
            int n = in.nextInt();
            Tree[] trees= new Tree[n];
            int k = in.nextInt();
            for (int j = 0; j < n; j++) {
                trees[j] = new Tree(j+1);
            }
            for (int j = 0; j < n-1; j++) {
                int k1 = in.nextInt();
                int k2 = in.nextInt();
                trees[k1-1].addSubTree(trees[k2-1]);
                trees[k2-1].addSubTree(trees[k1-1]);
            }
            int root = 0;

            for (int j = 0; j < k; j++) {
                root = in.nextInt();
                trees[root-1].committed = true;
            }
            cut(trees[root-1]);
            Tree ft=bfs(trees[root-1]);
            out.println(dfs(ft)/2);
        }
        out.close();
    }
    public static boolean cut(Tree tree){
        boolean cut = true;
        tree.visited=true;
        for (int i = 0; i < tree.subTrees.size(); i++) {
            if (!tree.subTrees.get(i).visited){
                boolean temp = cut(tree.subTrees.get(i));
                if(!temp){
                    cut=false;
                }
            }
        }
        if (cut&&!tree.committed){
            tree.isCut=true;
            return true;
        }
        tree.committed=true;
        return false;
    }
    public static int dfs(Tree tree){
        int max = 0;
        tree.visited = true;
        for (int i = 0; i < tree.subTrees.size(); i++) {
            if (tree.subTrees.get(i).committed&&!tree.subTrees.get(i).visited){
                int tep = dfs(tree.subTrees.get(i));
                if(tep>max){
                    max=tep;
                }
            }
        }
        return max+1;
    }
    public static Tree bfs(Tree tree){
        Queue queue = new Queue();
        queue.enQueue(tree);
        Tree tep = tree;
        while (!queue.isEmpty()){
            tep = queue.deQueue();
            tep.visited=false;
            for (int i = 0; i < tep.subTrees.size(); i++) {
                if(tep.subTrees.get(i).committed&&tep.subTrees.get(i).visited){
                    queue.enQueue(tep.subTrees.get(i));
                }
            }
        }
        return tep;
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