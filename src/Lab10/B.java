
import java.io.*;
import java.util.*;
class Map {
    ArrayList<Integer>[] map;
    ArrayList<Integer>[] maps;
    boolean[]visits;
    boolean[]visits1;
    ArrayList<Integer> dfs;
    ArrayList<Integer> dfs1;
    Map(int size){
        map = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            map[i]=new ArrayList<>();
        }
        maps = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            maps[i]=new ArrayList<>();
        }
        visits = new boolean[size];
        visits1 = new boolean[size];
        dfs = new ArrayList<>();
        dfs1 = new ArrayList<>();
    }
    public void addEdge(int begin,int end){
        map[begin-1].add(end);
        maps[end-1].add(begin);
    }
    public void dfs(int i){
        visits[i] = true;
        dfs.add(i+1);
        for (int j = 0; j < map[i].size(); j++) {
            if (!visits[map[i].get(j)-1]){
                this.dfs(map[i].get(j)-1);
            }
        }
    }
    public void dfs1(int i){
        visits1[i] = true;
        dfs1.add(i+1);
        for (int j = 0; j < maps[i].size(); j++) {
            if (!visits1[maps[i].get(j)-1]){
                this.dfs1(maps[i].get(j)-1);
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
        Map map = new Map(n);
        for (int i = 0; i < m; i++) {
            int begin = in.nextInt();
            int end = in.nextInt();
            map.addEdge(begin,end);
        }
        map.dfs(0);
        if (map.dfs.size()<n){
            out.println("wawawa");
        }else {
            int i = map.dfs.get(0)-1;
            map.dfs1(i);
            if (map.dfs1.size()!=n){
                out.println("wawawa");
            }else {
                out.println("Bravo");
            }
        }
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