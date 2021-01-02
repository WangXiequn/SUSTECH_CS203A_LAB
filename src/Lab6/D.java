import java.io.*;
import java.math.*;
import java.util.*;

class Main {

    public static void main(String[] args) {
        InputStream inputStream = System.in;// new FileInputStream("C:\\Users\\wavator\\Downloads\\test.in");
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        out.close();
    }

    static class Task {

        public void solve(InputReader in, PrintWriter out) {
            int times = in.nextInt();
            for (int i = 0; i < times; i++) {
                char[] chars = in.next().toCharArray();
                int[] pi = findNext(chars);
                int k = pi[pi.length-1];
                int currentLength = chars.length;
                while (2*k>currentLength){
                    currentLength = k;
                    k = pi[k-1];
                }
                out.println(currentLength-2*k);
            }
        }
    }
    public static int[] findNext(char[] chars){
        int[] pi = new int[chars.length];
        int k =0;
        pi[0]=0;
        for (int i = 1; i <pi.length ; i++) {
            while (k>0&&chars[i]!=chars[k]){
                k= pi[k-1];
            }
            if (chars[i]==chars[k]){
                k++;
            }
            pi[i]=k;
        }
        return pi;
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