import java.io.*;
import java.time.OffsetDateTime;
import java.util.*;
class Map {
    int index;

    ArrayList<Map> arrayList;
    boolean isVisitedA;
    boolean isVisitedB;
    Map(int index) {
        this.index = index;
        arrayList = new ArrayList<Map>();
        isVisitedA=isVisitedB=false;
    }
}
class Queue {
    static final int MAX_SIZE = 2000002;
    int[] queue;
    int front;
    int rear;
    public boolean isEmpty(){
        return front==rear;
    }

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
        int a = in.nextInt();
        int b = in.nextInt();
        Map[] maps = new Map[n];
        for (int i = 0; i < n; i++) {
            maps[i] = new Map(i+1);
        }
        for (int i = 0; i < m; i++) {
            int k1 = in.nextInt();
            int k2 =in.nextInt();
            maps[k1-1].arrayList.add(maps[k2-1]);
            maps[k2-1].arrayList.add(maps[k1-1]);
        }
        Queue aq = new Queue();
        Queue bq = new Queue();
        aq.enQueue(a);
        bq.enQueue(b);
        TreeSet<Integer> arrayListA = new TreeSet<>();
        TreeSet<Integer> arrayListB = new TreeSet<>();
        while (!aq.isEmpty()){
            int s = aq.deQueue();
            maps[s-1].isVisitedA=true;
            for (Map map: maps[s-1].arrayList) {
                if (!map.isVisitedA&&map.index!=b){
                    aq.enQueue(map.index);
                    map.isVisitedA=true;
                    arrayListA.add(map.index);
                }
            }
        }
        while (!bq.isEmpty()){
            int s = bq.deQueue();
            maps[s-1].isVisitedB=true;
            for (Map map: maps[s-1].arrayList) {
                if (!map.isVisitedB&&map.index!=a){
                    bq.enQueue(map.index);
                    map.isVisitedB=true;
                    arrayListB.add(map.index);
                }
            }
        }
        TreeSet<Integer> arrayListC = new TreeSet<>();
        arrayListC.addAll(arrayListA);
        arrayListA.removeAll(arrayListB);
        arrayListB.removeAll(arrayListC);
        out.print(arrayListA.size()*arrayListB.size());
        out.close();
    }
    public static void mergeSort(int[] arr,int low,int high,int[] tmp) {
        if (low >= high) {
            return;
        }
        mergeSort(arr,low,(high+low)/2,tmp);
        mergeSort(arr,(high+low)/2+1,high,tmp);
        merge(arr,low,high,tmp);

    }
    public static void merge(int[] arr,int low,int high,int[]tep){
        int mid = (low+high)/2;
        int leftArr = low;
        int rightArr = mid+1;

        for (int i = 0; i <high-low+1 ; i++) {
            if (leftArr>mid){
                tep[low+i]=arr[rightArr];
                rightArr++;
                continue;
            }
            if (rightArr>high){
                tep[low+i]=arr[leftArr];
                leftArr++;
                continue;
            }
            if (arr[rightArr]<arr[leftArr]){
                tep[low+i]=arr[rightArr];
                rightArr++;
                continue;
            }
            if (arr[rightArr]>=arr[leftArr]){
                tep[low+i]=arr[leftArr];
                leftArr++;
            }
        }
        for (int i = 0; i <high-low+1 ; i++) {
            arr[low+i]=tep[low+i];
        }

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