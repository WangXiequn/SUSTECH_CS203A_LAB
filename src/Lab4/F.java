import java.io.*;
/**
 * @author 11383
 */

import java.io.*;
import java.math.*;
import java.util.*;

class Main {

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        int times = in.nextInt();
        int lengthOfLinkList=500;
        for(int i =0 ;i<times;i++){
            char[] initialTxt =in.next().toCharArray();

            LinkListForLL manager = new LinkListForLL();
            for (int j = 0; j <initialTxt.length/lengthOfLinkList+1 ; j++) {
                LinkListForC linkList = new LinkListForC();
                if (j==initialTxt.length/lengthOfLinkList){
                    for (int k = 0; k <initialTxt.length-lengthOfLinkList*j ; k++) {
                        linkList.add(initialTxt[j*lengthOfLinkList+k]);
                    }
                }else {
                    for (int k = 0; k < lengthOfLinkList ; k++) {
                        linkList.add(initialTxt[j*lengthOfLinkList+k]);
                    }
                }
                manager.add(linkList);


            }
            int readTimes = in.nextInt();
            for (int j = 0; j <readTimes ; j++) {
                int model = in.nextInt();
                if (model==1){
                    char character = in.nextCharArray()[0];
                    int place = in.nextInt();

                    operation(manager,character,place);
                }else {
                    int place = in.nextInt();
                    System.out.println(operation(manager,place));
                }
            }




        }
        out.close();
    }
    public static void operation(LinkListForLL listLinkList,char character,int place){
        listLinkList.currentNode= listLinkList.head;
        for (int i = 0; i <listLinkList.size ; i++) {
            if (place<=listLinkList.currentNode.value.size){
                boolean flag =  listLinkList.currentNode.value.insert(place,character);
                if (flag){
                    LinkListForC linkListForC = new LinkListForC();
                    LinkListForC.Node node= listLinkList.currentNode.value.cut(501);
                    linkListForC.add(node,500);
                    listLinkList.insert(linkListForC);
                }
                break;
            }else {
                place-=listLinkList.currentNode.value.size;
                listLinkList.currentNode=listLinkList.currentNode.next;
            }
        }
    }
    public static char operation(LinkListForLL listLinkList,int place){
        listLinkList.currentNode = listLinkList.head;
        char answer;
        for (int i = 0; i <listLinkList.size ; i++) {
            if (place<=listLinkList.currentNode.value.size){
                answer = listLinkList.currentNode.value.get(place);
                return answer;
            }else {
                place-=listLinkList.currentNode.value.size;
                listLinkList.currentNode=listLinkList.currentNode.next;
            }

        }
        answer = '!';
        return answer;
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
class LinkListForC {
    Node head;
    int size = 0;
    Node currentNode;
    public void add(char value) {
        Node newNode = new Node(value);

        currentNode.next = newNode;
        currentNode=newNode;
        currentNode.next=null;
        this.size+=1;

    }
    public char get(int place){
        currentNode=head;
        for (int i = 0; i <place ; i++) {
            currentNode= currentNode.next;
        }
        return currentNode.value;
    }

    public void add(Node value,int size) {

        head.next = value;
        currentNode=value;
        this.size+=size;

    }
    public Node cut(int place){
        currentNode =head;
        for (int i = 0; i <place-1 ; i++) {
            currentNode=currentNode.next;
        }
        Node temp = currentNode.next;
        currentNode.next =null;
        size = place-1;
        return temp;
    }
    public boolean insert (int place,char value){
        currentNode=head;
        Node newNode = new Node(value);
        for (int i = 0; i <place-1 ; i++) {
            currentNode=currentNode.next;
        }
        Node tempNode = currentNode.next;
        currentNode.next=newNode;
        newNode.next=tempNode;
        size+=1;
        if (size>=1000){
            return true;
        }
        return false;
    }
    public LinkListForC(){
        Node head = new Node('1');
        this.head = head;
        this.currentNode=this.head;
    }


    class Node{
        Node next = null;
        char value;
        int size;
        public Node(char value,int size){
            this.value=value;
            this.size=size;
        }
        public Node(char value){
            this.value=value;

        }

    }

}
class LinkListForLL {
    Node head;
    int size = 0;
    Node currentNode;
    public void add(LinkListForC value) {
        Node newNode = new Node(value);
        if (size==0){
            head = newNode;
            currentNode = newNode;
            currentNode.next=null;
            this.size+=1;
        }else {
            currentNode.next = newNode;
            currentNode=newNode;
            currentNode.next=null;
            this.size+=1;
        }



    }

    public void insert(LinkListForC value){
        Node node = new Node(value);
        if (currentNode.next==null){
            currentNode.next=node;
            node.next=null;
            size+=1;
        }else {
            Node tempNode = currentNode.next;
            currentNode.next = node;
            node.next = tempNode;
            size+=1;
        }
    }


    class Node{
        Node next = null;
        LinkListForC value;
        int size;
        public Node(LinkListForC value,int size){
            this.value=value;
            this.size=size;
        }
        public Node(LinkListForC value){
            this.value=value;

        }

    }

}
