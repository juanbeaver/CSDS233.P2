
/**
 * An interface for an ADT that represents an ordered sequence of numbers.
 *
 * @author (Juan C Beaver)
 * @version (02/06/2022)
 */
public interface NumList
{
    /**
     * Method size
     *
     * @return The return value
     */
    int size();

    /**
     * Method capacity
     *
     * @return The return value
     */
    int capacity();

    /**
     * Method add
     *
     * @param value A parameter
     */
    void add(double value);

    /**
     * Method insert
     *
     * @param i A parameter
     * @param value A parameter
     */
    void insert(int i, double value);

    /**
     * Method remove
     *
     * @param i A parameter
     */
    void remove(int i);

    /**
     * Method contains
     *
     * @param value A parameter
     * @return The return value
     */
    boolean contains(double value);

    /**
     * Method lookup
     *
     * @param i A parameter
     * @return The return value
     */
    double lookup(int i);

    /**
     * Method equals
     *
     * @param otherList A parameter
     * @return The return value
     */
    boolean equals(NumList otherList);

    /**
     * Method removeDuplicates
     *
     */
    void removeDuplicates();

    /**
     * Method toString
     *
     * @return The return value
     */
    String toString();
}
