import java.io.*;
import java.math.*;
import java.util.*;
class Queue{
    static final int MAX_SIZE =2000002;
    int[] queue;
    int front;
    int rear;
    public Queue(){
        queue = new int[MAX_SIZE];
        front=rear=0;
    }
    public void enQueue(int num){
        queue[rear] = num;
        rear++;
    }
    public void deQueue(){
        if(front<rear){
            front++;
        }
    }
    public int head(){
        return queue[front];
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
        Queue queue = new Queue();
        for (int i = 0; i < times; i++) {
            char operator = in.nextCharArray()[0];
            switch (operator){
                case 'E':
                    int num = in.nextInt();
                    queue.enQueue(num);
                    break;
                case 'A':
                    out.println(queue.head());
                    break;
                case 'D':
                    queue.deQueue();
                    break;
                default:
                    break;
            }
        }
        int length =queue.rear-queue.front;
        for (int i = 0; i < length; i++) {
            if (i==length-1){
                out.print(queue.head());
                break;
            }
            out.print(queue.head()+" ");
            queue.deQueue();
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