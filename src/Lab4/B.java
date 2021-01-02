import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int times = scanner.nextInt();
        for (int i = 0; i < times; i++) {
            int length = scanner.nextInt();
            String strs = scanner.next();
            char[] chars = strs.toCharArray();
            DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
            doubleLinkedList.add(Integer.MAX_VALUE);


            for (int j = 0; j < length; j++) {
                switch (chars[j]) {
                    case 'I':
                        doubleLinkedList.currentNode = doubleLinkedList.head;
                        break;
                    case 'H':
                        if (doubleLinkedList.currentNode.prev != null) {
                            doubleLinkedList.currentNode = doubleLinkedList.currentNode.prev;
                        }
                        break;
                    case 'L':
                        if (doubleLinkedList.currentNode.next != null) {
                            doubleLinkedList.currentNode = doubleLinkedList.currentNode.next;
                        }
                        break;
                    case 'x':
                        if (doubleLinkedList.currentNode.next != null && doubleLinkedList.currentNode.value != Integer.MAX_VALUE) {
                            doubleLinkedList.remove();

                        }
                        break;
                    case 'r':
                        break;

                    default:
                        if (j == 0 || chars[j - 1] != 'r') {
                            doubleLinkedList.addFront(chars[j] - 48);
                        } else {
                            if (doubleLinkedList.currentNode.value == Integer.MAX_VALUE) {
                                doubleLinkedList.addFront(chars[j] - 48);
                                doubleLinkedList.currentNode = doubleLinkedList.currentNode.prev;
                            } else {
                                doubleLinkedList.currentNode.value = chars[j] - 48;
                            }
                        }
                        break;
                }
            }
            doubleLinkedList.currentNode = doubleLinkedList.head;
            while (doubleLinkedList.currentNode.value != Integer.MAX_VALUE) {
                System.out.print(doubleLinkedList.currentNode.value);
                doubleLinkedList.currentNode = doubleLinkedList.currentNode.next;
            }
            System.out.println();
        }
    }
}
class DoubleLinkedList  {
    Node head=null;
    Node currentNode;
    int size=0;


    public int size() {
        return size;
    }
    public void addFront(int value){
        Node newNode = new Node(value);
        if (currentNode.prev == null){
            head = newNode;
            newNode.next=currentNode;
            currentNode.prev = newNode;
            return;
        }
        Node tmpNode=currentNode.prev;

        currentNode.prev=newNode;
        tmpNode.next=newNode;
        newNode.next=currentNode;
        newNode.prev=tmpNode;
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
            head.next=null;
            head.prev=null;
            currentNode = head;
            size++;
        }else{
            Node tmpNode=currentNode;
            currentNode.next = node;
            size++;
            currentNode = node;
            currentNode.prev=tmpNode;
            node.next = null;
        }
    }
    public void remove() {
        Node tmpNode = currentNode;
        currentNode=currentNode.next;
        if (tmpNode.prev==null){
            currentNode.prev=null;
            head=currentNode;

        }else {
            tmpNode.prev.next=currentNode;
            currentNode.prev=tmpNode.prev;
        }
    }

    class Node{
        Node next = null;
        Node prev = null;
        int value;
        public Node(int value){
            this.value = value;
        }

    }
}
