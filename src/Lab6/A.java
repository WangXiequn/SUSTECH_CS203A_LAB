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
            char[][] string = new char[in.nextInt()][];
            int minLength = Integer.MAX_VALUE;
            for (int i = 0; i < string.length; i++) {
                string[i] = in.next().toCharArray();
                if (string[i].length<minLength){
                    minLength = string[i].length;
                }
            }
            int prefix = 0;
            int suffix = 0;
            boolean flag = true;
            boolean flag1 =true;
            for (int i = 0; i < minLength; i++) {
                if (string.length==1){
                    prefix=string[0].length;
                    suffix = string[0].length;
                    break;
                }
                if (flag){
                    for (int j = 0; j <string.length-1 ; j++) {
                        if (string[j][i] != string[j+1][i]) {
                            flag=false;
                            break;

                        }
                        if (j==string.length-2){
                            prefix++;
                        }
                    }
                }
                if (flag1){
                    for (int j = string.length-1; j >0 ; j--) {
                        if (string[j][string[j].length-i-1] != string[j-1][string[j-1].length-i-1]) {
                            flag1 = false;
                            break;
                        }
                        if (j==1){
                            suffix++;
                        }
                    }
                }
            }
            out.println(prefix*suffix);
        }
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