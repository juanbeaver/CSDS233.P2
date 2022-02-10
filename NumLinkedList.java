public class NumLinkedList implements NumList{

    private int elementCount = 0;

    private NumDLNode head;
    private NumDLNode tail;


    public NumLinkedList(){
        head = null;
        tail = null;
    }

    public static void main(String[] args) {
        NumLinkedList testList = new NumLinkedList();
        testList.add(1);
        testList.add(4);
        testList.add(5);
        testList.insert(1, 2);
        testList.insert(2, 3);
        System.out.println("The size of the list is: " + testList.size());
        System.out.println(testList);

    }

    public boolean isEmpty(){
        return (head == null);
    }

    public NumDLNode iterateTo(int i){
        NumDLNode nodePtr = null;
        try {
            nodePtr = head;
            for (int j = 0; j < i - 1; j++) {
                nodePtr = nodePtr.getNext();
            }
        }catch(NullPointerException e){
            System.out.println("THere exists no element at the given index");
        }
        return nodePtr;
    }

    public int size(){
        return elementCount;
    }

    public int capacity(){
        return elementCount*2;
    }

    public void add(double value){
        if(isEmpty()){
            head = new NumDLNode(value, null, null);
            tail = head;
        }
        else{
            tail.setNext(new NumDLNode(value, tail, null));
            tail = tail.getNext();
        }
        elementCount++;
    }

    public void insert(int i, double value){
        NumDLNode nodePtr = iterateTo(i);
        if(nodePtr == null){
            add(value);
        }
        else {
            NumDLNode newNode = new NumDLNode(value, nodePtr, nodePtr.getNext());
        }
        elementCount++;
    }

    public void remove(int i){
        NumDLNode nodePtr = iterateTo(i);
        if(nodePtr != null) {
            if (nodePtr != head || nodePtr != tail) {
                nodePtr.getPrevious().setNext(nodePtr.getNext());
                nodePtr.getNext().setPrevious(nodePtr.getPrevious());
                elementCount--;
            } else if (nodePtr == head) {
                nodePtr.getNext().setPrevious(null);
                elementCount--;
                head = nodePtr.getNext();
            }
            else if(nodePtr == tail) {
                nodePtr.getPrevious().setNext(null);
                elementCount--;
                tail = nodePtr.getPrevious();
            }
        }

    }

    public boolean contains(double value){
        return false;
    }

    public double lookup(int i){
        return 0;
    }

    public boolean equals(NumList otherList){
        return false;
    }

    public void removeDuplicates(){

    }

    public String toString(){
        NumDLNode nodePtr = head;
        StringBuilder s2 = new StringBuilder();

        while(nodePtr != null){
            if(nodePtr.getNext() != null) {
                s2.append(nodePtr.getElement() + " ");
            }
            else{
                s2.append(nodePtr.getElement());
            }
            nodePtr = nodePtr.getNext();
        }
        return s2.toString();

    }
}
