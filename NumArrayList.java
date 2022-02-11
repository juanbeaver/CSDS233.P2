import java.util.concurrent.TimeUnit;

/**
 * Write a description of class NumArrayList here.
 *
 * @author (Juan C Beaver)
 * @version (01/19/2022)
 */
public class NumArrayList implements NumList
{
    // instance variables
    private double[] list;
    private int elementCount = 0;
    private boolean isSorted = true;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("This is a demonstration of an ADT implementation from scratch");
        System.out.println("Here is a list in which I will add the numbers 1-10");
        NumArrayList list = new NumArrayList(10);
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

    /**
     * Constructor for objects of class NumArrayList
     */
    public NumArrayList()
    {
        // initialise instance variables
        list = new double[0];
    }

    public NumArrayList(int size){
        // initialize instance variables
        list = new double[size];
    }

    /*
     * Helper method to check if the array currently has the capacity to add n elements
     */
    public boolean canAdd(int amount){
        return list.length - elementCount > amount;
    }

    /*Method to double the capacity of an already initiated array or set the initial
     * array size to 10 */
    public void increaseCapacity(){
        if(list.length > 0) {
            double[] newList = new double[list.length * 2];
            for (int i = 0; i <= list.length - 1; i++) {
                newList[i] = list[i];
            }
            list = newList;
        }
        else{
            list = new double[10];
        }
    }



    //Returns the size of the list
    public int size(){
        return elementCount;
    }

    //Returns the capacity of the list
    public int capacity(){
        return list.length;
    }

    // adds a new element to the end of the current list
    public void add(double value){
        if (!canAdd(1)) {
            increaseCapacity();
        }
        list[elementCount] = value;
        elementCount++;
        if(size()<2){
            isSorted = true;
        }
        else{
            isSorted = list[elementCount-1] > list[elementCount - 2];
        }
    }

    /*inserts a new element before the i-th element of the list
    (using 0 for the index of the first element). */
    public void insert(int i, double value){
        if(elementCount <= i){
            if(!canAdd(1)){
                increaseCapacity();
            }
            list[elementCount] = value;
            elementCount++;
            isSorted = list[elementCount-1] > list[elementCount - 2];
        }else{
            if(!canAdd(1)) {
                increaseCapacity();
            }
            for (int j = elementCount; j > i; j--) {
                list[j] = list[j - 1];
            }
            list[i] = value;
            isSorted = list[i-1] < list[i] && list[i+1] > list[i];
            elementCount++;
        }
    }

    //Removes the i-th element of the list.
    public void remove(int i){
        if(list.length > 0) {
            if (i < elementCount - 1) {
                for (int j = i; j < elementCount; j++) {
                    list[j] = list[j + 1];
                }
                elementCount--;
            }
            else if(i == elementCount - 1){
                list[i] = 0.0;
                elementCount--;
            }
        }
    }

    //Returns true if the list contains value.
    public boolean contains(double value){
        for (int i = 0; i < elementCount; i++) {
            if(list[i] == value){
                return true;
            }
        }
        return false;
    }

    //Returns the element at the i-th index.
    public double lookup(int i){
        try{
            return list[i];
        }
        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("This list does not have an element at" + i + "index");
            throw e;
        }
    }

    //Two lists are equal if they have the same amount and sequence of doubles.
    public boolean equals(NumList otherList){
        if(size() == otherList.size()){
            for (int i = 0; i < size(); i++) {
                if(list[i] != otherList.lookup(i)){
                    return false;
                }
            }
            return true;
        }
        else{
            return false;
        }

    }

    //Removes any duplicate doubles while keeping its original order.
    public void removeDuplicates(){
        //copy all numbers to new list and if newList contains i, skip.
        NumArrayList newList = new NumArrayList(size());
        int listSize = size();
        for (int i = 0; i < listSize; i++) {
            if(!newList.contains(list[i])){
                newList.add(list[i]);
            }
            else{
                elementCount--;
            }
        }
        double[] newListArray = new double[newList.size()];
        for (int i = 0; i < newList.size(); i++) {
            newListArray[i] = newList.lookup(i);
        }
        list = newListArray;
        elementCount = newList.size();
    }

    public void reverse(){
        double temp = 0;
        for (int i = 0; i < elementCount; i++) {
            while(i < elementCount/2) {
                temp = list[i];
                list[i] = list[elementCount - 1 - i];
                list[elementCount - 1 - i] = temp;
                i++;
            }
        }
    }

    public NumList union(NumList list1, NumList list2){
        NumArrayList aList1 = (NumArrayList) list1;
        NumArrayList aList2 = (NumArrayList) list2;
        NumArrayList newList = new NumArrayList(aList2.size() + aList1.size());

        int a1 = 0;
        int a2 = 0;
        if(aList1.isSorted && aList2.isSorted){
            for(int i = 0; i < newList.capacity() - 1; i++){
                if(!newList.contains(aList1.lookup(a1)) ||
                   !newList.contains(aList2.lookup(a2))) {
                    if(aList1.size() > a1 && aList2.size() > a2) {
                        if (aList1.lookup(a1) > aList2.lookup(a2)) {
                            newList.add(aList2.lookup(a2));
                            a2++;
                        } else if (aList1.lookup(a1) < aList2.lookup(a2)) {
                            newList.add(aList1.lookup(a1));
                            a1++;
                        } else {
                            newList.add(aList1.lookup(a1));
                            a1++;
                            a2++;
                        }
                    }
                    else{
                        if(aList1.size() <= a1){
                            for(int j = a2; i < aList2.size(); j++){
                                newList.add(aList2.lookup(j));
                            }
                        }
                        else{
                            for(int k = a1; k < aList1.size(); k++){
                                newList.add(aList1.lookup(k));
                            }
                        }
                    }
                }
                else{
                    a1++;
                    a2++;
                }
            }
        }
        else{

        }
        return newList;
    }

    // Returns a string representation of the list
    public String toString(){
        StringBuilder s2 = new StringBuilder();
        for(int i = 0; i < elementCount; i++){
            s2.append(list[i]);
            if(i < elementCount - 1){
                s2.append(" ");
            }
        }
        return s2.toString();
    }
}