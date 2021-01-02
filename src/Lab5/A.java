import java.io.*;
import java.math.*;
import java.util.*;
class Stack{
    char[] chars;
    int top;
    public Stack(int size){
        chars = new char[size];
        this.top = -1;
    }
    public void push(char character){
        top++;
        chars[top] = character;
    }
    public char pop(){
        if (top==-1){
            return '!';
        }
        char answer = chars[top];
        top--;
        return answer;
    }


}

class Main {

    public static void main(String[] args) {
        InputStream inputStream = System.in;// new FileInputStream("C:\\Users\\wavator\\Downloads\\test.in");
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        int times = in.nextInt();
        for (int i = 0; i < times; i++) {
            int arrLen = in.nextInt();
            char[] brackets = in.next().toCharArray();
            if (arrLen%2==1){
                out.println("NO");
                continue;
            }
            Stack stack = new Stack(arrLen);
            boolean flag = true;
            for (int j = 0; j < brackets.length; j++) {
                if (brackets[j]=='{'||brackets[j]=='('||brackets[j]=='['){
                    stack.push(brackets[j]);
                }else{
                    char temp = stack.pop();
                    if (temp=='!'){
                        flag=false;
                        break;
                    }
                    if (temp=='{'&&brackets[j]!='}'){
                        flag=false;
                        break;
                    }
                    if (temp=='('&&brackets[j]!=')'){
                        flag=false;
                        break;
                    }
                    if (temp=='['&&brackets[j]!=']'){
                        flag=false;
                        break;
                    }
                }
            }
            if (flag&&stack.top==-1){
                out.println("YES");
            }else {
                out.println("NO");
            }
        }
        out.close();
    }


    static class Task {

        public void solve(InputReader in, PrintWriter out) {




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