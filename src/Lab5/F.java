import java.io.*;
import java.math.*;
import java.util.*;
class LinkedList{
    Node head;
    Node tail;
    int size;
    Node tempNode;
    LinkedList(){
        head = new Node(-1);
        tail = new Node(-2);
        head.next = tail;
        tail.prev = head;
        head.prev = null;
        tail.next = null;

    }
    public void push(int value){
        Node newNode = new Node(value);
        tempNode = tail.prev;
        newNode.prev=tempNode;
        newNode.next = tail;
        tail.prev = newNode;
        tempNode.next=newNode;
        size++;
    }
    public int pop(){
        tempNode = tail.prev.prev;
        Node tempNode1 = tail.prev;
        int answer = tempNode1.value;
        tempNode1.next = null;
        tail.prev = tempNode;
        tempNode.next = tail;
        tempNode1.prev = null;
        size--;
        return answer;
    }

    class Node{
        Node next;
        Node prev;
        int value;
        Node(int value){
            this.value = value;
        }

    }
}
class MyList{
    int[] sizeOfNum;
    LinkedList[] linkedLists;
    int pointer=-1;
    MyList() {
        sizeOfNum = new int[1000001];
        linkedLists =new LinkedList[300001];
    }
    public void push(int value){
        int size = sizeOfNum[value];
        sizeOfNum[value]++;
        if (size>pointer){
            linkedLists[size] = new LinkedList();
            linkedLists[size].push(value);
            pointer++;
        }else {
            linkedLists[size].push(value);
        }
    }
    public int pop(){
        if (pointer==-1){
            return -1;
        }
        int answer = linkedLists[pointer].pop();
        sizeOfNum[answer]--;
        if (linkedLists[pointer].size==0){
            linkedLists[pointer] =null;
            pointer--;
        }
        return answer;
    }


}


public class Main {

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
        MyList myList = new MyList();
        public void solve(InputReader in, PrintWriter out) {
            String line;
            while (!(line=in.next()).equals("nsdd")){
                if (line.equals("eat")){
                    int value =  myList.pop();
                    if (value!=-1){
                        out.println(value);
                    }else {
                        out.println("pa");
                    }
                }else {
                    int integer = in.nextInt();
                    myList.push(integer);
                }
            }
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