import java.io.*;
import java.util.*;
class Map {
    int inDegree;
    int index;
    ArrayList<Map> arrayList;

    Map(int index) {
        this.index = index;
        arrayList = new ArrayList<Map>();
        inDegree = 0;
    }
}
class Queue{
    int[] arr;
    int pointer = 1;
    Queue(int value){
        arr = new int[value+1];
        for (int i = 1; i <arr.length ; i++) {
            arr[i]=Integer.MAX_VALUE;
        }
    }
    public void enQueue(int value){
        arr[pointer] = value;
        int temp = pointer;
        pointer++;
        while(arr[temp]<arr[temp/2]){

            int tep= arr[temp];
            arr[temp]=arr[temp/2];
            arr[temp/2]=tep;
            temp=temp/2;
        }


    }
    public int deQueue(){
        int answer = arr[1];
        arr[1] = arr[pointer-1];
        arr[pointer-1] = Integer.MAX_VALUE;
        pointer--;
        int temp =1;
        while(arr[temp]>Math.min(arr[temp*2],arr[temp*2+1])){
            if (arr[temp*2]<arr[temp*2+1]){
                int tep= arr[temp];
                arr[temp]=arr[temp*2];
                arr[temp*2]=tep;
                temp=temp*2;
            }else {
                int tep= arr[temp];
                arr[temp]=arr[temp*2+1];
                arr[temp*2+1]=tep;
                temp=temp*2+1;
            }
        }
        return answer;
    }


}
public class Main {

    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);
        int n = in.nextInt();
        int m = in.nextInt();
        Map[] maps = new Map[n];
        for (int i = 1; i <= n; i++) {
            maps[i-1] = new Map(i);
        }
        for (int i = 0; i < m; i++) {
            int inD = in.nextInt();
            int outD = in.nextInt();
            maps[inD-1].arrayList.add(maps[outD-1]);
            maps[outD-1].inDegree++;
        }
        Queue queue = new Queue(1000005);
        for (int i = 0; i < maps.length; i++) {
            if (maps[i].inDegree==0){
                queue.enQueue(i+1);
            }
        }
        int[] anwserList = new int[n];
        int count =0;
        while (count<n){
            int i =  (int)queue.deQueue();
            anwserList[count]=i;
            count++;
            for (Map map : maps[i-1].arrayList) {
                map.inDegree--;
                if (map.inDegree==0){
                    queue.enQueue(map.index);
                }
            }
        }
        for (int i = 0; i < anwserList.length; i++) {
            if (i==n-1){
                out.print(anwserList[i]);
                break;
            }
            out.print(anwserList[i]+" ");
        }
        out.close();
    }

    public void dfs(){

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