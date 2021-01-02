import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);
        int n = in.nextInt();
        long x1 = in.nextLong();
        long x2 = in.nextLong();
        long[] x1s = new long[n];
        long[] x2s = new long[n];
        for (int i = 0; i < n ; i++) {
            long ki= in.nextLong();
            long bi = in.nextLong();
            x1s[i] = ki*x1+bi;
            x2s[i] = ki*x2+bi;
        }

        long[] tep1 = new long[n];
        long[] tep2 = new long[n];
        mergeSort(x1s,0,n-1,tep1,x2s,tep2);
        boolean flag = true;
        boolean flag1 = false;
        int left = 0;
        int right = 0;
        int min = 0;
        int max = 0;
        for (int i = 0; i <n-1 ; i++) {
            if (flag1 ==false &&x1s[i]==x1s[i+1]){
                flag1 = true;
                left = i;
                if (x2s[i]>x2s[i+1]){
                    max = i;
                    min = i+1;
                }else {
                    max = i+1;
                    min = i;
                }
                continue;
            }
            if (flag1==true&&x1s[i]==x1s[i+1]){
                if (x2s[i+1]>x2s[max]){
                    max = i+1;
                }
                if (x2s[i+1]<x2s[min]){
                    min = i+1;
                }
                continue;
            }
            if (flag1==true&&x1s[i]!=x1s[i+1]){
                flag1=false;
                right =i;
                long minimum = x2s[min];
                long maximum = x2s[max];
                x2s[left]= minimum;
                x2s[right] = maximum;
            }
        }

        for (int i = 0; i <n-1 ; i++) {
            if (x2s[i]>x2s[i+1]&&x1s[i]!=x1s[i+1]){
                flag = false;
                break;
            }
        }
        if (flag){
            out.print("NO");
        }else {
            out.print("YES");
        }




        out.close();
    }

    public static void mergeSort(long[] arr,int low,int high,long[] tmp,long[]arr1,long[]tmp2) {
        if (low >= high) {
            return;
        }
        mergeSort(arr,low,(high+low)/2,tmp,arr1,tmp2);
        mergeSort(arr,(high+low)/2+1,high,tmp,arr1,tmp2);
        merge(arr,low,high,tmp,arr1,tmp2);

    }
    public static void merge(long[] arr,int low,int high,long[]tep,long[]arr1,long[]tmp2){
        int mid = (low+high)/2;
        int leftArr = low;
        int rightArr = mid+1;

        for (int i = 0; i <high-low+1 ; i++) {
            if (leftArr>mid){
                tep[low+i]=arr[rightArr];
                tmp2[low+i] = arr1[rightArr];
                rightArr++;
                continue;
            }
            if (rightArr>high){
                tep[low+i]=arr[leftArr];
                tmp2[low+i]=arr1[leftArr];
                leftArr++;
                continue;
            }
            if (arr[rightArr]<arr[leftArr]){
                tep[low+i]=arr[rightArr];
                tmp2[low+i]=arr1[rightArr];
                rightArr++;
                continue;
            }
            if (arr[rightArr]>=arr[leftArr]){
                tep[low+i]=arr[leftArr];
                tmp2[low+i]=arr1[leftArr];
                leftArr++;
            }
        }
        for (int i = 0; i <high-low+1 ; i++) {
            arr[low+i]=tep[low+i];
            arr1[low+i] = tmp2[low+i];
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