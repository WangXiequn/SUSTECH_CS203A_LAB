import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);
        int amountOfMonster = in.nextInt();
        int amountOfDS = in.nextInt();
        long amoutOfIF = in.nextLong();
        long[] hp = new long[amountOfMonster];
        long[] ak = new long[amountOfMonster];
        long answer = 0;
        for (int i = 0; i <amountOfMonster ; i++) {
            hp[i]=in.nextInt();
            ak[i] = in.nextInt();
            answer+=ak[i];
        }
        long[] values = new long[amountOfMonster];
        for (int i = 0; i <amountOfMonster ; i++) {
            values[i]= hp[i]-ak[i];
        }
        long[] valuesOfDS = new long[amountOfMonster];
        long max = 0;
        long max1 = 0;
        int where = 0;
        for (int i = 0; i <amountOfMonster ; i++) {
            valuesOfDS[i] = power(hp[i],amountOfDS)-ak[i];
            if (amoutOfIF>=2){
                long temp = valuesOfDS[i];
                if (values[i]>=0){
                    temp-=values[i];
                }
                if (temp>max1){
                    max1 =temp;
                    max = valuesOfDS[i];
                    where = i;
                }
                if(temp==max1&&values[i]>0){
                    max=valuesOfDS[i];
                    where = i;
                }
                continue;
            }
            if (valuesOfDS[i]==max){
                if (values[i]<values[where]) {
                    max = valuesOfDS[i];
                    where = i;
                }
            }
            if (valuesOfDS[i]>max){
                max = valuesOfDS[i];
                where = i;
            }

        }
        if (max==0||amoutOfIF==0){
            out.println(answer);
        }else {
            answer+=max;
            long sp = values[where];
            long[] tmp = new long[amountOfMonster];
            mergeSort(values,0, values.length-1, tmp);
            int j = 0;
            for (int i = amountOfMonster-1; i >=0 ; i--) {
                if (values[i]==sp){
                    sp= Long.MAX_VALUE;
                    continue;
                }
                if (values[i]<=0||j>=amoutOfIF-1){
                    break;
                }
                answer+=values[i];
                j++;

            }
            out.println(answer);
        }





        out.close();
    }
    public static long power(long value,int times){
        for (int i = 0; i <times ; i++) {
            value*=2;
        }
        return value;
    }
    public static void mergeSort(long[] arr,int low,int high,long[] tmp) {
        if (low >= high) {
            return;
        }
        mergeSort(arr,low,(high+low)/2,tmp);
        mergeSort(arr,(high+low)/2+1,high,tmp);
        merge(arr,low,high,tmp);

    }
    public static void merge(long[] arr,int low,int high,long[]tep){
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