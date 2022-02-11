import java.util.NoSuchElementException;

public class NumLinkedList implements NumList{

    private int elementCount = 0;

    private boolean isSorted = true;
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
        testList.remove(1);
        System.out.println("Removing element at index 1");
        System.out.println(testList);
        testList.insert(1, 2.5);
        System.out.println(testList);


    }

    public boolean isEmpty(){
        return (head == null);
    }

    public NumDLNode iterateTo(int i){
        NumDLNode nodePtr = null;
        try {
            nodePtr = head;
            for (int j = 0; j < i; j++) {
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
            isSorted = true;
        }
        else{
            tail.setNext(new NumDLNode(value, tail, null));
            tail = tail.getNext();
            isSorted = tail.getPrevious().getElement() <= tail.getElement();
        }
        elementCount++;
    }

    public void insert(int i, double value){
        NumDLNode nodePtr = iterateTo(i);
        NumDLNode newNode = null;
        if(nodePtr == null){
            add(value);
        }
        else{
            if(nodePtr != tail && nodePtr != head) {
                newNode = new NumDLNode(value, nodePtr.getPrevious(), nodePtr);
                isSorted = value >= nodePtr.getPrevious().getElement() && value <= nodePtr.getElement();
            }
            else if(nodePtr == head){
                newNode = new NumDLNode(value, null, nodePtr);
                head = newNode;
                isSorted = head.getElement() <= head.getNext().getElement();
            }
            else{
                newNode = new NumDLNode(value, nodePtr.getPrevious(), null);
                tail = newNode;
                isSorted = tail.getElement() >= tail.getPrevious().getElement();
            }
        }
        elementCount++;
    }

    public void remove(int i){
        NumDLNode nodePtr = iterateTo(i);
        if(nodePtr != null) {
            if (nodePtr != head && nodePtr != tail) {
                nodePtr.getPrevious().setNext(nodePtr.getNext());
                nodePtr.getNext().setPrevious(nodePtr.getPrevious());
                elementCount--;
            } else if (nodePtr == head) {
                nodePtr.getNext().setPrevious(null);
                elementCount--;
                head = nodePtr.getNext();
            }
            else{
                nodePtr.getPrevious().setNext(null);
                elementCount--;
                tail = nodePtr.getPrevious();
            }
        }

    }

    public boolean contains(double value){
        NumDLNode nodePtr = head;
        while(nodePtr != null){
            if(nodePtr.getElement() == value){
                return true;
            }
            nodePtr = nodePtr.getNext();
        }
        return false;
    }

    public double lookup(int i){
        if (i <= elementCount - 1) {
            return iterateTo(i).getElement();
        }else{
            throw new NoSuchElementException();
        }
    }

    //Twp lists are equal if they have all the same numbers in the same sequence
    public boolean equals(NumList otherList){
        if(size() == otherList.size()){
            for(int i = 0; i < size(); i++){
                if(lookup(i) != otherList.lookup(i)){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public void removeDuplicates(){

    }

    public void reverse() {
        NumDLNode nodePtr = tail;
        head = nodePtr;
        NumDLNode temp = null;
        while(nodePtr.getPrevious() != null) {
            if(nodePtr != null) {
                temp = nodePtr.getNext();
                nodePtr.setNext(nodePtr.getPrevious());
                nodePtr.setPrevious(temp);
                nodePtr = nodePtr.getNext();
            }
        }
        tail = nodePtr;
    }

    public static NumList union(NumList list1, NumList list2){
        return null;
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
