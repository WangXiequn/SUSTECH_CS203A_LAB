import java.io.*;
import java.math.*;
import java.util.*;

class Main {

    public static final long d = 139;
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        out.close();
    }

    static class Task {

        public void solve(InputReader in, PrintWriter out) {
            char[] txt1 = in.next().toCharArray();
            char[] txt2 = in.next().toCharArray();
            int mid = 0;
            int low = 0;
            int high = Math.min(txt1.length,txt2.length);
            while(low<high){
                mid = (low+high)/2+1;
                if (judge(txt1,txt2,mid)){
                    low = mid;
                }else {
                    high = mid-1;
                }
            }
            out.println(high);
        }

    }
    public static boolean judge(char[] s1, char[] s2, int mid){
        long h = pow(d,mid-1);
        long [] t1 = new long[s1.length-mid+1];
        long [] t2 = new long[s2.length-mid+1];
        for (int i = 0; i <mid ; i++) {
            t1[0] = (d*t1[0]+s1[i]);
            t2[0] = (d*t2[0]+s2[i]);
        }
        for (int i = 1; i < t1.length; i++) {
            t1[i] = (d*(t1[i-1]-s1[i-1]*h)+s1[i+mid-1]);
        }
        for (int i = 1; i < t2.length; i++) {
            t2[i] = (d*(t2[i-1]-s2[i-1]*h)+s2[i+mid-1]);
        }
        long[] temp = new long[t1.length];
        mergeSort(t1,0,t1.length-1,temp);
        for (int i = 0; i < t2.length; i++) {
            if (binarySearch(t2[i],t1)){
                return true;
            }
        }
        return false;
    }

    public static boolean binarySearch(long k,long[]arr){
        int low = 0;
        int high = arr.length - 1;
        while (low<=high){
            int mid = low+(high-low)/2;
            if (k==arr[mid]){

                return true;
            }else if(k<arr[mid]){
                high = mid-1;
            }else {
                low = mid+1;
            }
        }
        return false;
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
    public static long pow(long a, long b) {

        long result = 1;
        long base = a;

        while(b > 0) {
            if((b & 1) != 0) {
                result *= base;
            }
            base *= base;
            b >>= 1;
        }

        return result;

    }








    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public char[] nextCharArray() {
            return next().toCharArray();
        }

        //         public boolean hasNext() {
        //             try {
        //                 return reader.ready();
        //             } catch(IOException e) {
        //                 throw new RuntimeException(e);
        //             }
        //         }
        public boolean hasNext() {
            try {
                String string = reader.readLine();
                if (string == null) {
                    return false;
                }
                tokenizer = new StringTokenizer(string);
                return tokenizer.hasMoreTokens();
            } catch (IOException e) {
                return false;
            }
        }

        public BigInteger nextBigInteger() {
            return new BigInteger(next());
        }

        public BigDecimal nextBigDecinal() {
            return new BigDecimal(next());
        }
    }
}