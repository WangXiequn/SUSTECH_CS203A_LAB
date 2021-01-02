import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);
        int times = in.nextInt();

        for (int i = 0; i <times ; i++) {

            int n = in.nextInt();
            int m = in.nextInt();
            int[] arrN = new int[n];
            int[] arrM = new int[m];
            for (int j = 0; j <n ; j++) {
                arrN[j]= in.nextInt();
            }
            for (int j = 0; j <m ; j++) {
                arrM[j] = in.nextInt();
            }
            int []arr = merge(arrN,arrM);
            for (int j = 0; j <arr.length ; j++) {
                if (j==arr.length-1){
                    out.print(arr[j]);
                    break;
                }
                out.print(arr[j]+" ");

            }
            out.println();

        }


        out.close();
    }
    public static int[] merge(int[]arrN,int[]arrM){
        int[] mergedArr = new int[arrN.length+arrM.length];
        int pointerN = 0;
        int pointerM = 0;
        for (int i = 0; i <mergedArr.length ; i++) {
            if (pointerN==arrN.length){
                mergedArr[i]=arrM[pointerM];
                pointerM++;
                continue;
            }
            if (pointerM==arrM.length){
                mergedArr[i]=arrN[pointerN];
                pointerN++;
                continue;
            }
            if (arrN[pointerN]<arrM[pointerM]){
                mergedArr[i]=arrN[pointerN];
                pointerN+=1;
                continue;
            }
            if (arrN[pointerN]>=arrM[pointerM]){
                mergedArr[i]=arrM[pointerM];
                pointerM+=1;

            }
        }

        return mergedArr;
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