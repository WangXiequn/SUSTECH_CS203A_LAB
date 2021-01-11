import java.io.*;
import java.util.*;
class Map {
    ArrayList<Edge>[] edgeList;
    int size;
    int k;
    long[][] minimalDistance;
    PriorityQueue<Triple> triplePriorityQueue;
    ArrayList<Integer>[] portal;
    boolean[][] visited;
    Map(int size,int k){
        this.size=size;
        edgeList =new ArrayList[size];
        for (int i = 0; i < size; i++) {
            edgeList[i] = new ArrayList<>();
        }
        portal =new ArrayList[size];
        for (int i = 0; i < size; i++) {
            portal[i] = new ArrayList<>();
        }
        this.k = k;
        minimalDistance = new long[size][k+1];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < k+1; j++) {
                minimalDistance[i][j] = Long.MAX_VALUE;
            }
        }
        visited = new boolean[size][k+1];
    }
    public void addEdge(int from,int to,int weight){
        edgeList[from].add(new Edge(to,weight));
    }
    public void addPortal(int from,int to){
        portal[from].add(to);
    }
    public void Dijkstra(int begin){
        minimalDistance[begin][k]=0;
        triplePriorityQueue = new PriorityQueue<>(Comparator.comparingLong(o -> o.weight));
        triplePriorityQueue.add(new Triple(begin,0,k));
        while (!triplePriorityQueue.isEmpty()){
            Triple triple = triplePriorityQueue.poll();
            visited[triple.node][triple.restOfK]=true;
            edgeRelax(triple);
        }
    }
    public void edgeRelax(Triple triple){
        int node = triple.node;
        int restOfk = triple.restOfK;
        for (Edge e: edgeList[node]) {
            if (!visited[e.to][restOfk]&&minimalDistance[e.to][restOfk]>minimalDistance[node][restOfk]+e.weight){
                minimalDistance[e.to][restOfk]=minimalDistance[node][restOfk]+e.weight;
                triplePriorityQueue.add(new Triple(e.to,minimalDistance[e.to][restOfk],restOfk));
            }
        }
        if (restOfk>=1){
            for (int e: portal[node]) {
                if (!visited[e][restOfk-1]&&minimalDistance[e][restOfk-1]>minimalDistance[node][restOfk]){
                    minimalDistance[e][restOfk-1]=minimalDistance[node][restOfk];
                    triplePriorityQueue.add(new Triple(e,minimalDistance[e][restOfk-1],restOfk-1));
                }
            }
        }
    }
}
class Triple{
    int node;
    long weight;
    int restOfK;
    Triple(int node,long weight,int restOfK){
        this.node=node;
        this.weight=weight;
        this.restOfK=restOfK;
    }
}
class Edge{
    int to;
    long weight;
    Edge(int to,long weight){
        this.weight=weight;
        this.to=to;
    }
}
public class Main {


    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);
        int n = in.nextInt();
        int m = in.nextInt();
        int p = in.nextInt();
        int k = in.nextInt();
        Map map = new Map(n,k);
        for (int i = 0; i < m; i++) {
            int from = in.nextInt()-1;
            int to = in.nextInt()-1;
            int weight = in.nextInt();
            map.addEdge(from,to,weight);
        }
        for (int i = 0; i < p; i++) {
            int from = in.nextInt()-1;
            int to = in.nextInt()-1;
            map.addPortal(from,to);
        }
        int begin = in.nextInt()-1;
        int end = in.nextInt()-1;
        map.Dijkstra(begin);
        long min = Long.MAX_VALUE;
        for (int i = 0; i < k+1; i++) {
            if (map.minimalDistance[end][i]<min){
                min=map.minimalDistance[end][i];
            }
        }
        out.print(min);
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