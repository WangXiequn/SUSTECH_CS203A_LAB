import java.io.*;
import java.time.OffsetDateTime;
import java.util.*;
class Map {
    int index;
    int times;
    ArrayList<Map> arrayList;
    boolean isVisited;

    Map(int index) {
        this.index = index;
        arrayList = new ArrayList<Map>();
        times= 0;
        isVisited=false;
    }
}
class Queue {
    static final int MAX_SIZE = 2000002;
    int[] queue;
    int front;
    int rear;


    public Queue() {
        queue = new int[MAX_SIZE];
        front = rear = 0;
    }

    public void enQueue(int num) {
        queue[rear] = num;
        rear++;
    }

    public int deQueue() {
        int answer = queue[front];
        if (front < rear) {
            front++;
        }
        return answer;
    }

    public int head() {
        return queue[front];
    }

}
public class Main {

    public static void main(String[] args) throws IOException {
        Reader in = new Reader();
        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt();
        int m = in.nextInt();
        Map[] maps = new Map[n+m+2];
        for (int i = 1; i <= maps.length; i++) {
            maps[i - 1] = new Map(i);
        }
        int count = n + 1;
        for (int i = 0; i < m; i++) {
            int k1 = in.nextInt();
            int k2 = in.nextInt();
            int c = in.nextInt();
            if (c == 1) {
                maps[k1 - 1].arrayList.add(maps[k2 - 1]);
            } else {
                maps[k1 - 1].arrayList.add(maps[count - 1]);
                maps[count - 1].arrayList.add(maps[k2 - 1]);
                count++;
            }
        }
        Queue queue = new Queue();
        queue.enQueue(1);

        while (queue.front!= queue.rear){
            int s =queue.deQueue();
            maps[s-1].isVisited=true;
            int time = maps[s-1].times;
            for (Map map:maps[s-1].arrayList) {
                if (!map.isVisited){
                    queue.enQueue(map.index);
                    map.times=time+1;
                    map.isVisited=true;
                }
            }
            if (maps[n-1].times!=0){
                out.print(maps[n-1].times);
                break;
            }
        }
        if (maps[n-1].times==0){
            out.print(-1);
        }


        out.close();
    }

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
