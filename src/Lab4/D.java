import java.io.*;
/**
 * @author 11383
 */
class Main {

    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);
        int times = in.nextInt();
        for (int i = 0; i <times ; i++) {
            boolean flag =false;
            int arrLen = in.nextInt();
            if (arrLen%2==0){
                arrLen-=1;
                flag=true;
            }
            int[] initialArr = new int[arrLen];
            int[] natural = new int[arrLen];
            int[] tempArr1 = new int[arrLen];
            int[] tempArr2 = new int[arrLen];
            for(int j = 0;j<arrLen;j++){
                initialArr[j]=in.nextInt();
                natural[j]=j;
                if (flag&&j==arrLen-1){
                    in.nextInt();
                }
            }
            mergeSort(initialArr,0,arrLen-1,tempArr1,natural,tempArr2);
            Node[] nodes = new Node[arrLen];
            Node[] nodes1 = new Node[arrLen];
            Node[] tempNodes = new Node[arrLen];
            LinkList linkList = new LinkList();
            for(int j = 0;j<arrLen;j++){
                nodes[j] = linkList.add(initialArr[j]);
                nodes1[j]= nodes[j];
            }
            mergeSort(natural,0,arrLen-1,tempArr1,nodes,tempNodes);
            int[] answerList = new int[(arrLen+1)/2];
            judge(linkList,nodes,answerList,arrLen,nodes1);
            printList(answerList);

        }



        out.close();
    }
    public static void judge(LinkList linkList,Node[] nodes,int[] answerList,int arrLen,Node[] nodes1){
        linkList.midNode = nodes1[arrLen/2];
        answerList[0] = nodes1[arrLen/2].value;
        for (int i = 1; i <answerList.length ; i++) {
            Node tempNode1 = nodes[arrLen-1-(i-1)*2];
            Node tempNode2 = nodes[arrLen-2-(i-1)*2];
            int flag = 0;
            if (tempNode1.number>linkList.midNode.number){
                flag+=1;
            }
            if (tempNode2.number>linkList.midNode.number){
                flag+=1;
            }
            if (tempNode1.number==linkList.midNode.number){
                if (tempNode2.number>linkList.midNode.number){
                    linkList.midNode=linkList.midNode.prev;
                    linkList.remove(tempNode1);
                    linkList.remove(tempNode2);
                }else {
                    linkList.midNode=linkList.midNode.next;
                    linkList.remove(tempNode1);
                    linkList.remove(tempNode2);
                }
                answerList[i]=linkList.midNode.value;
                continue;
            }
            if (tempNode2.number==linkList.midNode.number){
                if (tempNode1.number>linkList.midNode.number){
                    linkList.midNode=linkList.midNode.prev;
                    linkList.remove(tempNode1);
                    linkList.remove(tempNode2);
                }else {
                    linkList.midNode=linkList.midNode.next;
                    linkList.remove(tempNode1);
                    linkList.remove(tempNode2);
                }
                answerList[i]=linkList.midNode.value;
                continue;
            }
            linkList.remove(tempNode1);
            linkList.remove(tempNode2);
            switch (flag){
                case 2  : linkList.midNode=linkList.midNode.prev;
                    break;
                case 0 : linkList.midNode=linkList.midNode.next;
                    break;
                default:

            }
            answerList[i]=linkList.midNode.value;
        }
    }

    public static void printList(int[] arr){
        for (int i=arr.length-1;i >=0 ; i--) {
            if (i==0){
                System.out.println(arr[0]);
                continue;
            }
            System.out.print(arr[i]+" ");
        }
    }

    public static void mergeSort(int[] arr,int low,int high,int[] tmp,int[]arr1,int[]tmp2) {
        if (low >= high) {
            return;
        }
        mergeSort(arr,low,(high+low)/2,tmp,arr1,tmp2);
        mergeSort(arr,(high+low)/2+1,high,tmp,arr1,tmp2);
        merge(arr,low,high,tmp,arr1,tmp2);

    }
    public static void merge(int[] arr,int low,int high,int[]tep,int[]arr1,int[]tmp2){
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
    public static void mergeSort(int[] arr,int low,int high,int[] tmp,Node[]arr1,Node[]tmp2) {
        if (low >= high) {
            return;
        }
        mergeSort(arr,low,(high+low)/2,tmp,arr1,tmp2);
        mergeSort(arr,(high+low)/2+1,high,tmp,arr1,tmp2);
        merge(arr,low,high,tmp,arr1,tmp2);

    }
    public static void merge(int[] arr,int low,int high,int[]tep,Node[]arr1,Node[]tmp2){
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
class LinkList  {
    Node head;
    Node midNode;
    Node currentNode;
    Node tail;
    int size;
    public Node add(int value) {
        size++;
        Node newNode = new Node(value,size);
        Node tempNode = currentNode.next;
        newNode.prev=currentNode;
        currentNode.next = newNode;
        newNode.next = tempNode;
        tempNode.prev = newNode;
        currentNode = newNode;
        return newNode;

    }
    public void remove(Node node) {
        Node tempNode = node.next;
        node.prev.next = tempNode;
        tempNode.prev = node.prev;
        node.next =null;
        node.prev = null;
    }
    public LinkList(){
        Node head = new Node(-1);
        this.head = head;
        Node tail = new Node(-2);
        this.tail = tail;
        head.next=tail;
        head.prev=null;
        tail.prev=head;
        tail.next=null;
        currentNode = head;

    }



}
class Node{
    Node next = null;
    Node prev = null;
    int value;
    int number;
    public Node(int value,int number){
        this.value=value;
        this.number = number;
    }
    public Node(int value){
        this.value=value;

    }

}
