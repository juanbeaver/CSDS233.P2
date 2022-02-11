# P2 Progress Log

## February 6, 2022, 3:00 PM

* Setup repository for the project on Github.&#x20;
* Setup Progress Log on GitBook to keep the log synchronized with Github.&#x20;
* Copied over the NumList interface.
* Wrote the skeleton for the NumLinkedList class.&#x20;
* Made the skeleton for the NumLinkedListTester class.

Time spent: \~30min

## February 10, 1:00 PM

* Edited the NumLinkedListTester class (Used NumArrayListTester class) to better fit this implementation.
* Created NumDLNode class for the nodes of the doubly linked list.
* Wrote isEmpty() and iterateTo() helper functions.&#x20;
* wrote size() and capacity(). Capacity returns double the current size.
* Wrote the add() method.&#x20;
* Wrote the insert() method.&#x20;
  * For this method I had some minor issues with my iteratoTo() method as it was inserting one after i.
* &#x20;Wrote the toString() method

Time spent: \~40min (10% Mark)

## February 10, 2:00 PM

* Wrote remove() method.&#x20;
  * Had issues with this method, as my original code was removing the element before i.&#x20;
* wrote contains() method.
* Wrote lookup() method.&#x20;
  * Had a little trouble with throwing a NoSuchElementException but realized I didn't need a try-catch block.
* Improved JUnit test cases.

Time spent: \~25 min (50% mark)

## February 10, 3:00 PM&#x20;

* Wrote equals() method.&#x20;
* Wrote removeDuplicates() method.
  * I had a really hard time trying to figure out a way to write this method. I think I was trying to make it too fancy. I needed to get something down that works. This was not finished.&#x20;
* Wrote test cases for reverse() and union() methods
* Implemented isSorted functionality by having a global boolean and checking at every addition and insertion if the surrounding elements will remain in order.

Time spent: \~ 60 min (90% mark)

## February 10, 7:00 PM&#x20;

* Wrote reverse() method
  * I had a lot of trouble getting the logic down for this reverse method. My test case kept looping until memory errors were caused.&#x20;
  * I finally managed to get the reverse method working. I was trying to start off too complicated.&#x20;
  * Writing the reverse method for the NumArrayList class was much simpler.&#x20;
* Wrote union() method.
  * Wrote the case for sorted lists with a single for loop. O(N).
    * union() method for NumLinkedList does not operate correctly because removeDuplicates() is not operational.&#x20;
* Attempted to fix removeDuplicates() method
  * Could not completely fix removeDuplicates(), It was partially working.

TIme spent: \~ 3 hours (100% mark)
