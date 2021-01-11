import java.io.*;
import java.util.*;
class Edge {
    Edge(int begin,int end,int length){
        this.begin= begin;
        this.end=end;
        this.length = length;
    }
    int begin;
    int end;
    int length;

}
public class Main {


    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] matrix = new int[n][m];
        ArrayList<Edge> arrayList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = in.nextInt();
            }
        }
        int[] parent = new int[n*m+1];

        for (int i = 0; i < n*m+1; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i==n-1){
                    if (j==m-1){

                    }else {
                        arrayList.add(new Edge((i)*m+j+1,(i)*m+j+2,matrix[i][j]*matrix[i][j+1]));
                    }
                }else if (j==m-1){
                    if (i==n-1){

                    }else {
                        arrayList.add(new Edge((i)*m+j+1,(i)*m+j+1+m,matrix[i][j]*matrix[i+1][j]));
                    }
                }else {
                    arrayList.add(new Edge((i)*m+j+1,(i)*m+j+2,matrix[i][j]*matrix[i][j+1]));
                    arrayList.add(new Edge((i)*m+j+1,(i)*m+j+1+m,matrix[i][j]*matrix[i+1][j]));
                }


            }
        }
        arrayList.sort(Comparator.comparingInt(o -> -o.length));
        long answer = 0;
        for (Edge edge:arrayList) {
            if (findAndCompress(edge.begin,parent)!=findAndCompress(edge.end,parent)){
                answer+=edge.length;
                union(edge.begin,edge.end,parent);
            }
        }
        out.print(answer);
        out.close();
    }
    public static int findAndCompress(int a,int[]parent){
        int root = a;
        while (root!=parent[root]){
            root = parent[root];
        }
        while (a != root){
            int pa = parent[a];
            parent[a] = root;
            a =pa;
        }
        return root;
    }
    public static void union(int a ,int b,int[] parent){
        a = findAndCompress(a,parent);
        parent[a] = findAndCompress(b,parent);
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