import java.io.*;
import java.util.*;
class LinkedList {
    Node head;
    Node tail;
    Node currentNode;
    public void add(int value) {
        Node newNode = new Node(value);
        Node tempNode = tail.prev;
        newNode.next=tail;
        tail.prev=newNode;
        tempNode.next=newNode;
        newNode.prev=tempNode;
        currentNode=newNode;
    }
    public void remove(Node node){
        Node tempNode = node.next;
        Node tempNode1 = node.prev;
        tempNode.prev=tempNode1;
        tempNode1.next=tempNode;
        node.next=null;
        node.prev=null;
    }
    public void printList(){
        currentNode = head;
        while(currentNode.next.value!=-2){
            System.out.println(currentNode.next.value);
            currentNode=currentNode.next;
        }
    }


    public LinkedList(){
        Node head = new Node(-1);
        this.head = head;
        Node tail = new Node(100003);
        this.tail = tail;
        this.head.next = tail;
        this.head.prev= null;
        this.tail.prev=head;
        this.tail.next=null;
        this.currentNode=this.head;
    }




}
class Node{
    Node next = null;
    Node prev = null;
    int value;
    public Node(int value){
        this.value=value;

    }

}
class MyList {
    Node[] nodes;
    int length=0;
    public MyList(){
        Node[] nodes = new Node[10003];
        this.nodes = nodes;
    }
    public void add(Node node){
        nodes[length] = node;
        length++;
    }
    public void delete(int place){
        nodes[place]=null;
        length--;
    }
    public void add(Node node,int place){
        nodes[place] = node;
        length++;
    }
    public Node get(int place){
        return nodes[place];
    }





}


class Main {

    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);
        int times = in.nextInt();
        for (int i = 0; i <times ; i++) {
            int arrLen = in.nextInt();
            LinkedList linkedList = new LinkedList();
            MyList myList = new MyList();
            boolean flag = false;
            for (int j = 0; j < arrLen; j++) {
                int number = in.nextInt();
                if (flag){

                    if (number>=linkedList.currentNode.value){
                        linkedList.add(number);
                        linkedList.remove(linkedList.currentNode.prev);
                        flag = false;
                    }else {
                        if (j==arrLen-1){
                            linkedList.remove(linkedList.currentNode);
                        }else {
                            linkedList.add(number);
                            linkedList.remove(linkedList.currentNode.prev);
                        }
                    }
                }else {
                    if (number>=linkedList.currentNode.value){
                        linkedList.add(number);
                    }else {
                        if (myList.length==0||linkedList.currentNode.prev!=myList.get(myList.length-1)){
                            myList.add(linkedList.currentNode.prev);
                        }
                        if (j==arrLen-1){
                            linkedList.remove(linkedList.currentNode);
                        }else {
                            linkedList.add(number);
                            linkedList.remove(linkedList.currentNode.prev);
                        }

                        flag = true;
                    }
                }
            }
            while (myList.length>0){
                int position = 0;
                int length1 = myList.length;
                for (int j = 0; j < length1 ; j++) {

                    if (myList.get(j).value<=myList.get(j).next.value||myList.get(j).next.value==100003){
                        myList.delete(j);
                    }else {
                        if(myList.get(j).next!=myList.get(j+1)){
                            boolean flag1 = false;
                            Node tempNode=null;
                            if (myList.get(j).prev.value!=-1){
                                tempNode =myList.get(j).prev;
                                flag1=true;
                            }
                            linkedList.remove(myList.get(j).next);
                            linkedList.remove(myList.get(j));
                            myList.delete(j);
                            if (flag1){
                                if (position==0||myList.get(position-1)!=tempNode) {
                                    myList.add(tempNode, position);
                                    position++;
                                }
                            }

                        }else {
                            boolean flag1 = false;
                            Node tempNode=null;
                            if (myList.get(j).prev.value!=-1){
                                tempNode =myList.get(j).prev;
                                flag1=true;
                            }
                            int length = 0;
                            while (myList.get(j+length).next==myList.get(j+1+length)){
                                length++;
                            }
                            int k;
                            for ( k = 0; k <length ; k++) {

                                if (myList.get(j+k).value>myList.get(j+k+1).value){
                                    linkedList.remove(myList.get(j+k));
                                    myList.delete(j+k);
                                }else {
                                    linkedList.remove(myList.get(j+k));
                                    myList.delete(j+k);
                                    break;
                                }
                            }
                            if (k==length){
                                if (myList.get(j+length).value>myList.get(j+length).next.value){
                                    linkedList.remove(myList.get(j+length).next);
                                    linkedList.remove(myList.get(j+length));
                                    myList.delete(j+length);
                                }else {
                                    linkedList.remove(myList.get(j+length));
                                    myList.delete(j+length);
                                }
                            }
                            j= j+k;

                            if (flag1){
                                if (position==0||myList.get(position-1)!=tempNode) {
                                    myList.add(tempNode, position);
                                    position++;
                                }
                            }

                        }
                    }
                }
            }
            linkedList.currentNode = linkedList.head;
            while (linkedList.currentNode.next.value!=100003){
                out.print(linkedList.currentNode.next.value);
                if (linkedList.currentNode.next.next.value!=100003){
                    out.print(" ");
                }
                linkedList.currentNode=linkedList.currentNode.next;
            }
            out.println();
        }
        out.close();
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