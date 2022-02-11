import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class NumLinkedList implements NumList{

    private int elementCount = 0;

    private boolean isSorted = true;
    private NumDLNode head;
    private NumDLNode tail;


    public NumLinkedList(){
        head = null;
        tail = null;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("This is a demonstration of an ADT implementation from scratch");
        System.out.println("Here is a list in which I will add the numbers 1-10");
        NumLinkedList list = new NumLinkedList();
        for (int i = 1; i <= 10; i++) {
            list.add(i);
            TimeUnit.MILLISECONDS.sleep(200);
            System.out.println(list);
        }
        System.out.println("Now we will insert 4.5 in its respective spot");
        list.insert(4, 4.5);
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println(list);

        TimeUnit.MILLISECONDS.sleep(200);
        System.out.println("And now we will remove the numbers 4.5");
        list.remove(4);
        TimeUnit.MILLISECONDS.sleep(200);
        System.out.println(list);
        list.remove(9);
        System.out.println("and 10");
        TimeUnit.MILLISECONDS.sleep(200);
        System.out.println(list);

        System.out.println("Now duplicates will be inserted and then removed using the removeDuplicates() method");
        list.insert(3, 3.0);
        list.insert(4, 2.0);
        TimeUnit.MILLISECONDS.sleep(200);
        System.out.println(list);

        System.out.println("And now they'll be removed by removeDuplicates()");
        list.removeDuplicates();
        TimeUnit.MILLISECONDS.sleep(200);
        System.out.println(list);


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

    public NumDLNode nodeAt(int i){
        if (i <= elementCount - 1) {
            NumDLNode node = head;
            for (int j = 0; j < i + 1; j++) {
                node = node.getNext();
            }
            return node;
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
        NumDLNode nodePtr = head;
        NumDLNode lastRemoved = null;
        if(isSorted){
            while(nodePtr.getNext() != null){
                if(nodePtr.getElement() == nodePtr.getNext().getElement()){
                    lastRemoved = nodePtr.getNext();
                    nodePtr.setNext(nodePtr.getNext().getNext());
                    if(nodePtr.getNext() != null) {
                        nodePtr.getNext().setPrevious(nodePtr);
                        elementCount--;
                        nodePtr = head;
                    }
                }
                else{
                    nodePtr = nodePtr.getNext();
                }
            }
            if(size() > 1) {
                if (nodePtr.getElement() == lastRemoved.getElement()) {
                    lastRemoved.setNext(null);
                }
            }
        }
        else{
            for (int i = 0; i < elementCount; i++) {
                for (int j = i + 1; j < elementCount; j++) {
                    if(nodePtr.getElement() == lookup(j)){
                        nodeAt(j).getPrevious().setNext(nodeAt(j).getNext());
                        nodeAt(j).getNext().setPrevious(nodeAt(j).getPrevious());
                        elementCount--;
                    }
                }
                nodePtr = nodePtr.getNext();
            }
        }
    }

    public void reverse() {
        NumDLNode nodePtr = head;
        NumDLNode temp = null;
        while(nodePtr != null) {
            if(nodePtr.getPrevious() == null){
                tail = nodePtr;
            }
            temp = nodePtr.getPrevious();
            nodePtr.setPrevious(nodePtr.getNext());
            nodePtr.setNext(temp);
            nodePtr = nodePtr.getPrevious();
        }
        if(temp != null){
            head = temp.getPrevious();
        }


    }

    public NumList union(NumList list1, NumList list2){
        NumLinkedList newList = new NumLinkedList();

        NumLinkedList lList1 = (NumLinkedList) list1;
        NumLinkedList lList2 = (NumLinkedList) list2;

        NumDLNode nodePtr1 = lList1.iterateTo(0);
        NumDLNode nodePtr2 = lList2.iterateTo(0);

        if(isSorted){
            while(nodePtr1 != null && nodePtr2 != null){
                if(nodePtr1.getElement() != nodePtr2.getElement()){
                    if(nodePtr2.getElement() > nodePtr1.getElement()){
                        newList.add(nodePtr1.getElement());
                        nodePtr1 = nodePtr1.getNext();
                    }
                    else{
                        newList.add(nodePtr2.getElement());
                        nodePtr2 = nodePtr2.getNext();
                    }
                }
                else{
                    newList.add(nodePtr1.getElement());
                    nodePtr1 = nodePtr1.getNext();
                    nodePtr2 = nodePtr2.getNext();

                }
            }
            if(nodePtr1 == null){
                while(nodePtr2 != null){
                    newList.add(nodePtr2.getElement());
                    nodePtr2 = nodePtr2.getNext();
                }
            }
            else{
                while(nodePtr1 != null){
                    newList.add(nodePtr1.getElement());
                    nodePtr1 = nodePtr1.getNext();
                }
            }
            return newList;
        }
        else{
            for(int i = 0; i < lList2.elementCount; i++){
                lList1.add(nodePtr2.getElement());
                nodePtr2 = nodePtr2.getNext();
            }
            lList1.removeDuplicates();
            newList = lList1;
        }
        return newList;
    }

    public String toString(){
        NumDLNode nodePtr = head;
        StringBuilder s2 = new StringBuilder();

        while(nodePtr != null){
            if(nodePtr.getNext() != null) {
                s2.append(nodePtr.getElement()).append(" ");
            }
            else{
                s2.append(nodePtr.getElement());
            }
            nodePtr = nodePtr.getNext();
        }
        return s2.toString();

    }
}
