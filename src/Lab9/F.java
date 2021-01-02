import java.io.*;
import java.util.*;
class Map {
    int index;

    ArrayList<Pair> arrayList;

    Map(int index) {
        this.index = index;
        arrayList = new ArrayList<Pair>();
    }
    public void add(int to,int length){
        arrayList.add(new Pair(to,length));
    }
    public void sort(){
        Collections.sort(arrayList, Comparator.comparingInt(o -> o.length));
    }
}
class Pair{
    int to;
    int length;
    Pair(int to,int length){
        this.to = to;
        this.length = length;
    }
}
class Node{
    int from;
    int to;
    int index;
    long length;
    Node(int from,int to,int index,long length){
        this.from=from;
        this.length=length;
        this.index=index;
        this.to=to;
    }
}
public class Main {

    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int times = in.nextInt();
        for (int i = 0; i < times; i++) {
            int n = in.nextInt();
            int m = in.nextInt();
            int q = in.nextInt();
            Map[] maps = new Map[n];
            for (int j = 0; j < n; j++) {
                maps[j] = new Map(j+1);
            }
            for (int j = 0; j < m; j++) {
                int u = in.nextInt();
                int v = in.nextInt();
                int w = in.nextInt();
                maps[u-1].add(v,w);
            }
            for (int j = 0; j < maps.length; j++) {
                maps[j].sort();
            }
            Queue<Node> queue = new PriorityQueue<>(cLNode);
            ArrayList<Long> answerList = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (maps[j].arrayList.size()!=0){
                    queue.add(new Node(j+1,maps[j].arrayList.get(0).to,0,maps[j].arrayList.get(0).length));
                }
            }
            int[] qs = new int[q];
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < q; j++) {
                qs[j]= in.nextInt();
                if (max<qs[j]){
                    max=qs[j];
                }
            }
            while (answerList.size()<max&&!queue.isEmpty()){
                Node node = queue.poll();
                long length = node.length-maps[node.from-1].arrayList.get(node.index).length;
                answerList.add(node.length);
                if (maps[node.from-1].arrayList.size()-1!=node.index){
                    queue.add(new Node(node.from,maps[node.from-1].arrayList.get(node.index+1).to,node.index+1,length+maps[node.from-1].arrayList.get(node.index+1).length));
                }
                if (maps[node.to-1].arrayList.size()!=0){
                    queue.add(new Node(node.to,maps[node.to-1].arrayList.get(0).to,0,maps[node.to-1].arrayList.get(0).length+node.length));
                }

            }
            for (int j = 0; j < qs.length; j++) {
                out.println(answerList.get(qs[j]-1));
            }

        }
        out.close();
    }
    static Comparator<Node> cLNode = (o1, o2) -> (int) (o1.length-o2.length);

    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
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

        public double nextDouble() throws IOException {
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

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }

}
