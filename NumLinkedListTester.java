import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * The test class NumLinkedListTester.
 *
 * @author  (Juan Beaver)
 * @version (01/26/22)
 */
public class NumLinkedListTester {
    /**
     * Default constructor for test class numArrayListTest
     */

    @Test
    public void testSizeAndCapacity(){
        NumLinkedList testList = new NumLinkedList();
        testList.add(1);
        testList.add(2);
        assertEquals(2, testList.size());
        assertEquals(4, testList.capacity());

        NumLinkedList testList2 = new NumLinkedList();
        assertEquals(0,testList2.size());
        assertEquals(0, testList2.capacity());
    }

    @Test
    public void testAddAndToString(){
        NumLinkedList testList = new NumLinkedList();
        testList.add(1);    testList.add(2);    testList.add(3);    testList.add(4);    testList.add(5);
        assertEquals("1.0 2.0 3.0 4.0 5.0", testList.toString());

        //test adding to a list with insufficient capacity for the operation
        NumLinkedList testList2 = new NumLinkedList();
        testList2.add(1);
        //This next line should automatically increase the capacity of the list.
        testList2.add(2);
        assertEquals("1.0 2.0", testList2.toString());

        //Test adding to a list that was initiated with a capacity of zero
        NumLinkedList testList3 = new NumLinkedList();
        testList3.add(1);   testList3.add(2);   testList3.add(3);

        assertEquals("1.0 2.0 3.0", testList3.toString());
    }

    @Test
    public void testInsert(){
        //Test inserting when there is sufficient capacity
        NumLinkedList testList = new NumLinkedList();
        testList.add(1);    testList.add(2);    testList.add(4);    testList.add(5);

        testList.insert(2, 3);
        assertEquals("1.0 2.0 3.0 4.0 5.0", testList.toString());

        //Tests inserting when there is insufficient capacity
        NumLinkedList testList2 = new NumLinkedList();
        testList2.add(1);   testList2.add(2);   testList2.add(4);   testList2.add(5);

        testList2.insert(2, 3);
        assertEquals("1.0 2.0 3.0 4.0 5.0", testList2.toString());

        //Tests inserting to the end or beginning of the list
        NumLinkedList testList3 = new NumLinkedList();
        NumLinkedList testList4 = new NumLinkedList();
        testList3.add(2);   testList3.add(3);   testList3.add(4);   testList3.add(5);

        testList3.insert(0,1);
        assertEquals("1.0 2.0 3.0 4.0 5.0", testList3.toString());

        testList4.add(1);   testList4.add(2);   testList4.add(3);   testList4.add(4);

        testList4.insert(4,5);
        assertEquals("1.0 2.0 3.0 4.0 5.0", testList4.toString());
    }

    @Test
    public void testRemove(){
        //Tests removing from the beginning of the list
        NumLinkedList testList = new NumLinkedList();
        testList.add(1);    testList.add(2);    testList.add(3);    testList.add(4);

        testList.remove(0);
        assertEquals("2.0 3.0 4.0", testList.toString() );

        //Tests removing from the end of the list
        NumLinkedList testList2 = new NumLinkedList();
        testList2.add(1);   testList2.add(2);   testList2.add(3);   testList2.add(4);

        testList2.remove(3);
        assertEquals("1.0 2.0 3.0", testList2.toString());

        //Tests removing from an empty list
        NumLinkedList testList3 = new NumLinkedList();
        testList3.remove(0);
        assertEquals("", testList3.toString());

        //Tests removing from the middle of the list
        NumLinkedList testList4 = new NumLinkedList();
        testList4.add(1);   testList4.add(2);   testList4.add(3);   testList4.add(4);

        testList4.remove(2);
        assertEquals("1.0 2.0 4.0", testList4.toString());

        //Tests attempting to remove an element that doesn't exist
        NumLinkedList testList5 = new NumLinkedList();
        testList5.add(1);   testList5.add(2);   testList5.add(3);   testList5.add(4);

        testList5.remove(4);
        assertEquals("1.0 2.0 3.0 4.0", testList5.toString());

        testList5.remove(5);
        assertEquals("1.0 2.0 3.0 4.0", testList5.toString());

    }

    @Test
    public void testContains(){
        //Tests if the given parameter is found when at the beginning of the list
        NumLinkedList testList = new NumLinkedList();
        testList.add(1);    testList.add(2);    testList.add(3);    testList.add(4);

        assertTrue(testList.contains(1.0));

        //Tests if the given parameter os found when at the end of the list
        assertTrue(testList.contains(4.0));

        //Tests if the given parameter is found when in the middle of the list
        assertTrue(testList.contains(2.0));

        //Tests if the method handles not finding the given value in the list
        assertFalse(testList.contains(5.0));

    }

    @Test
    public void testLookup(){
        //Test the lookup of an element in the beginning of the list
        NumLinkedList testList = new NumLinkedList();
        testList.add(1);    testList.add(2);    testList.add(3);    testList.add(4);    testList.add(5);

        assertEquals(1.0, testList.lookup(0), 0);

        //Test the lookup of an element in the end of the list
        assertEquals( 5.0, testList.lookup(4), 0);

        //Test the lookup of an element in the middle of the array
        assertEquals( 3.0, testList.lookup(2), 0);

        //Test that an exception is raised correctly
        NumLinkedList testList2 = new NumLinkedList();
        testList2.add(1);
        testList2.add(2);

        assertThrows(NoSuchElementException.class, () ->{
            testList2.lookup(7);
        });
    }

    @Test
    public void testEquals(){
        //list containing 1,2,3,4,5 with a capacity of 5
        NumLinkedList testList = new NumLinkedList();
        testList.add(1);    testList.add(2);    testList.add(3);    testList.add(4);    testList.add(5);

        //list containing 1,2,3,4,5 with a capacity of 5
        NumLinkedList testList2 = new NumLinkedList();
        testList2.add(1);   testList2.add(2);   testList2.add(3);   testList2.add(4);   testList2.add(5);

        //list containing 1,2,3,4,5 with a capacity of 10
        NumLinkedList testList3 = new NumLinkedList();
        testList3.add(1);   testList3.add(2);   testList3.add(3);   testList3.add(4);   testList3.add(5);

        //list containing 1,2,3,4,5,6 with a capacity of 6
        NumLinkedList testList4 = new NumLinkedList();
        testList4.add(1);   testList4.add(2);   testList4.add(3);   testList4.add(4);   testList4.add(5);   testList4.add(6);

        //Tests that two lists with the same elements and size returns true
        assertTrue(testList.equals(testList2));

        //Tests that a list with the same elements as this but different capacity are still equal
        assertTrue(testList.equals(testList3));

        //Tests that a list that contains the same sequence of numbers within this list but has more numbers doesn't return true
        assertFalse(testList.equals(testList4));
    }

    @Test
    public void testRemoveDuplicates(){
        //Test the removal of many of the same number
        NumLinkedList testList = new NumLinkedList();
        testList.add(1);    testList.add(1);    testList.add(1);    testList.add(1);    testList.add(1);

        testList.removeDuplicates();
        assertEquals("1.0", testList.toString());

        //Test the removal of many unique numbers
        NumLinkedList testList2 = new NumLinkedList();
        testList2.add(1);   testList2.add(2);   testList2.add(1);   testList2.add(2);   testList2.add(3);   testList2.add(4);   testList2.add(3);

        testList2.removeDuplicates();
        assertEquals("1.0 2.0 3.0 4.0", testList2.toString());

        NumLinkedList testList3 = new NumLinkedList();
        testList3.add(1);   testList3.add(65);  testList3.add(13);  testList3.add(1);   testList3.add(43);  testList3.add(13);  testList3.add(121);

        assertEquals("1.0 65.0 13.0 43.0 121.0", testList3.toString());
    }

    @Test
    public void testToString(){
        //Test a list with many numbers
        NumLinkedList testList = new NumLinkedList();
        testList.add(1);    testList.add(2);    testList.add(3);    testList.add(4);    testList.add(5);

        assertEquals("1.0 2.0 3.0 4.0 5.0", testList.toString());

        //Test a list with no numbers added
        NumLinkedList testList2 = new NumLinkedList();

        assertEquals("", testList2.toString());

        //Test a list with only one number added
        NumLinkedList testList3 = new NumLinkedList();
        testList3.add(1);

        assertEquals("1.0", testList3.toString());

        //Tests a list with a capacity of zero
        NumLinkedList testList4 = new NumLinkedList();

        assertEquals("", testList4.toString());
    }

    @Test
    public void testReverse(){
        NumLinkedList testList1 = new NumLinkedList();
        testList1.add(1);   testList1.add(2);   testList1.add(3);   testList1.add(4);   testList1.add(5);
        testList1.reverse();

        assertEquals("5.0 4.0 3.0 2.0 1.0", testList1.toString());

        NumLinkedList testList2 = new NumLinkedList();
        testList2.reverse();

        assertEquals("", testList2.toString());

        NumLinkedList testList3 =  new NumLinkedList();
        testList3.add(1);   testList3.reverse();

        assertEquals("1.0", testList3.toString());



        NumArrayList aList1 = new NumArrayList();
        aList1.add(1);   aList1.add(2);   aList1.add(3);   aList1.add(4);   aList1.add(5);
        aList1.reverse();

        assertEquals("5.0 4.0 3.0 2.0 1.0", aList1.toString());

        NumArrayList aList2 = new NumArrayList();
        aList2.reverse();

        assertEquals("", aList2.toString());

        NumArrayList aList3 =  new NumArrayList();
        aList3.add(1);   aList3.reverse();

        assertEquals("1.0", aList3.toString());
    }

    @Test
    public void testUnion(){
        NumArrayList aList1 = new NumArrayList();
        NumArrayList aList2 = new NumArrayList();

        aList1.add(1.0); aList1.add(2.0); aList1.add(3.0); aList1.add(4.0);
        aList2.add(1.0); aList2.add(4.0); aList2.add(5.0); aList2.add(6.0);

        assertEquals("1.0 2.0 3.0 4.0 5.0 6.0", aList2.union(aList1, aList2).toString());

        NumArrayList aList3 = new NumArrayList();
        NumArrayList aList4 = new NumArrayList();

        aList3.add(0); aList3.add(5); aList3.add(1); aList3.add(24); aList3.add(99);
        aList4.add(45); aList4.add(6); aList4.add(87); aList4.add(0); aList4.add(35);

        assertEquals("5.0 1.0 24.0 99.0 45.0 6.0 87.0 35.0", aList3.union(aList3, aList4).toString());

        NumArrayList aList5 = new NumArrayList();
        aList5.add(4);   aList5.add(86);   aList5.add(53);   aList5.add(890);   aList5.add(3);

        //assertEquals("1.0 ");

        //NumLinkedList Tests

        NumLinkedList testList1 = new NumLinkedList();
        NumLinkedList testList2 = new NumLinkedList();

        testList1.add(1.0); testList1.add(2.0); testList1.add(3.0); testList1.add(4.0);
        testList2.add(1.0); testList2.add(4.0); testList2.add(5.0); testList2.add(6.0);

        assertEquals("1.0 2.0 3.0 4.0 5.0 6.0", testList2.union(testList1, testList2).toString());

        NumLinkedList testList3 = new NumLinkedList();
        NumLinkedList testList4 = new NumLinkedList();

        testList3.add(0); testList3.add(5); testList3.add(1); testList3.add(24); testList3.add(99);
        testList4.add(45); testList4.add(6); testList4.add(87); testList4.add(0); testList4.add(35);

        assertEquals("5.0 1.0 24.0 99.0 45.0 6.0 87.0 35.0", testList3.union(testList3, testList4).toString());

        NumLinkedList testList5 = new NumLinkedList();
        testList5.add(4);   testList5.add(86);   testList5.add(53);   testList5.add(890);   testList5.add(3);

        //assertEquals("1.0 ");
    }
}