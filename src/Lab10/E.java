import java.io.*;
import java.util.*;
class Map{
    ArrayList<Integer>[] arrayList;
    ArrayList<Integer>[] arrayListRS;
    Stack stack;
    boolean[] visited;
    boolean[] visitedRS;
    int size;
    ArrayList<TreeSet<Integer>> answerList;
    Map(int size){
        this.size=size;
        arrayList = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            arrayList[i] =new ArrayList<>();
        }
        arrayListRS = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            arrayListRS[i] =new ArrayList<>();
        }
        visited = new boolean[size];
        visitedRS = new boolean[size];
        answerList = new ArrayList<>();
    }
    public void addEdge(int begin,int end){
        arrayList[begin].add(end);
        arrayListRS[end].add(begin);
    }
    public int getAnswer(){
        stack = new Stack();
        for (int i = 0; i < size; i++) {
            if (!visited[i]){
                forwardDFS(i);
            }
        }
        while (!stack.empty()){
            int v = (int) stack.pop();
            if (!visitedRS[v]){
                answerList.add(new TreeSet<>());
                backDFS(v,answerList.size()-1);
            }
        }
        int min = Integer.MAX_VALUE;
        for (TreeSet treeset:answerList) {
            Iterator iterator = treeset.iterator();
            boolean flag = true;
            while (iterator.hasNext()){
                int index = (int) iterator.next();
                for (int s:arrayList[index]) {
                    if (!treeset.contains(s)){
                        flag = false;
                        break;
                    }

                }
                if (!flag){
                    break;
                }
            }
            if (flag&&treeset.size()<min){
                min = treeset.size();
            }
        }
        return min;
    }
    public void forwardDFS(int i){
        visited[i]=true;
        for (int s:arrayList[i]) {
            if (!visited[s]){
                forwardDFS(s);
            }
        }
        stack.push(i);
    }
    public void backDFS(int i,int index){
        visitedRS[i] = true;
        answerList.get(index).add(i);
        for (int s: arrayListRS[i]) {
            if (!visitedRS[s]){
                backDFS(s,index);
            }
        }
    }
}

public class Main {


    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);
        int n = in.nextInt();
        int m = in.nextInt();
        int t = in.nextInt();
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = in.nextInt();
            arr[i][1] = in.nextInt();
        }
        int[] arrs = new int[m];
        for (int i = 0; i < m; i++) {
            arrs[i] = in.nextInt();
        }
        Map map = new Map(m);
        for (int i = 0; i < n; i++) {
            int a =arr[i][0]-1;
            int b =arr[i][1]-1;
            if ((arrs[a]+1)%t==arrs[b]){
                map.addEdge(a,b);
            }
            if ((arrs[b]+1)%t==arrs[a]){
                map.addEdge(b,a);
            }
        }
        out.println(map.getAnswer());


        out.close();
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