import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int times = scanner.nextInt();
        for (int i = 0; i < times; i++) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            CycleLinkedList cycleLinkedList = new CycleLinkedList();

            for (int j = 0; j < n; j++) {
                cycleLinkedList.add(j+1);
            }
            cycleLinkedList.currentNode = cycleLinkedList.head;
            if (k==1){
                for (int j = 1; j <=n ; j++) {
                    if (j ==n){
                        System.out.print(j);
                        continue;
                    }
                    System.out.print(j+" ");
                }
                System.out.println();
                continue;
            }
            for (int j = 0; j <n ; j++) {

                for (int l = 0; l <k-2 ; l++) {

                    cycleLinkedList.currentNode=cycleLinkedList.currentNode.next;
                }
                if (j==n-1){
                    System.out.print(cycleLinkedList.currentNode.next.value);
                    break;
                }
                System.out.print(cycleLinkedList.currentNode.next.value+" ");
                cycleLinkedList.remove();
                cycleLinkedList.currentNode=cycleLinkedList.currentNode.next;
            }
            System.out.println();
        }
    }
}
class CycleLinkedList  {
    Node head=null;
    Node currentNode;
    int size=0;


    public int size() {
        return size;
    }


    public boolean isEmpty() {
        return this.size() == 0;
    }

    public boolean contains(Object o) {
        return false;
    }
    public void add(int value) {
        Node node = new Node(value);
        if (head==null){
            head = node;
            head.next=head;
            currentNode = head;
            size++;
        }else{
            currentNode.next = node;
            size++;
            currentNode = node;
            node.next = head;
        }
    }
    public void remove() {
        if (currentNode.next==head){
            head=currentNode.next.next;
        }
        Node tep  = currentNode.next.next;

        currentNode.next=null;
        currentNode.next=tep;
    }

    class Node{
        Node next = null;
        int value;
        public Node(int value){
            this.value = value;
        }

    }
}

