/**
 * Created by lautaro on 3/28/16.
 */
public class ClosedHashSet extends SimpleHashSet {


    /*Fields/Data members inherited from SimpleHashSet:
    capacityMinusOne, INITIAL_CAPACITY*/
    String[] setArray;


    final static String wasDeleted = "wasDeleted";
    protected int sizeCounter = 0; //cantidad todo private???
    private static final int MINIMAL_CAPACITY = 1;
    private static final int SIZE_CHANGE = 2;


    // -------Constructors----------


    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16)
     *
     * @param upperLoadFactor - The upper load factor of the hash table.
     * @param lowerLoadFactor - The lower load factor of the hash table.
     */
    public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor) {
        super(upperLoadFactor, lowerLoadFactor); //TODO ponemos esto_
        setArray = new String[INITIAL_CAPACITY];
    }


    /**
     * A default constructor. Constructs a new, empty table with default initial capacity (16), upper load
     * factor (0.75) and lower load factor (0.25).
     */

    public ClosedHashSet() {
        super();
        setArray = new String[INITIAL_CAPACITY];

    }


    /**
     * Data constructor - builds the hash set by adding the elements one by one. Duplicate values should be
     * ignored. The new table has the default values of initial capacity (16), upper load factor (0.75), and
     * lower load factor (0.25).
     *
     * @param data - Values to add to the set.
     */
    public ClosedHashSet(String[] data) {
        new ClosedHashSet();
        setArray = new String[INITIAL_CAPACITY];


        for (int i = 0; i < data.length; i++) {
            add(data[i]);


        }
    }


    // ---------Methods-------------



      /* Methods inherited:
    clamp, getLowerLoadFactor, getUpperLoadFactor*/

    /**
     * Add a specified element to the set if it's not already in it.
     *
     * @param newValue - New value to add to the set
     * @return False if newValue already exists in the set
     */

    public boolean add(String newValue) {
        if (newValue == null)
            return false;

        if (contains(newValue))
            return false;

        else if ((float) (size()+1) / (capacity()) > getUpperLoadFactor() || size() / capacity() ==
                MINIMAL_CAPACITY) {

            int oldCapacity = capacity();
            capacity *= SIZE_CHANGE;
            String[] myStringArray = new String[capacity()];
            rehash(myStringArray, oldCapacity);
            setArray = myStringArray;


        }
        insert(newValue, capacity());
        sizeCounter++;
        return true;
    }


    /**
     * Look for a specified value in the set.
     *
     * @param searchVal - Value to search for
     * @return True if searchVal is found in the set
     */

    public boolean contains(String searchVal) {

        for (int i = 0; i < capacity(); i++) {
            int index = (searchVal.hashCode() + (i * i + i) / SIZE_CHANGE) & (capacityMinusOne());
            if (setArray[index] == null)
                return false;
            else if (setArray[index].equals(searchVal))
                return true;


        }
        return false;
    }

    /**
     * Remove the input element from the set.
     *
     * @param toDelete - Value to delete
     * @return True if toDelete is found and deleted
     */
    public boolean delete(String toDelete) {


        for (int i = 0; i < capacity(); i++) {
            int index = (toDelete.hashCode() + (i * i + i) / SIZE_CHANGE) & (capacityMinusOne());

            if (setArray[index] == null)
                return false;
            else if (setArray[index].equals(toDelete)) {
                setArray[index] = wasDeleted;
                sizeCounter--;
                if ((float) size() / (capacity()) < getLowerLoadFactor() && capacity() > MINIMAL_CAPACITY) {
                    int oldCapacity = capacity();
                    capacity /= SIZE_CHANGE;
                    String[] myStringArray = new String[capacity()];
                    rehash(myStringArray, oldCapacity);
                    setArray = myStringArray;
                }
                return true;


            }

        }
        return false;
    }

    /**
     * @return The number of elements currently in the set
     */
    @Override
    public int size() {
        return sizeCounter;
    }


    /*TODO TAMBIEN SE HEREDA CAPACITY?*/

    /**Inserts the given value into the array
     * @param newValue - the string to insert
     * @param relevantCapacity - the relevant capacity to take into account*/
    private void insert(String newValue, int relevantCapacity) {

        for (int i = 0; i < relevantCapacity; i++) {

            int index = (newValue.hashCode() + (i * i + i) / SIZE_CHANGE) & (capacityMinusOne());
            if ((setArray[index] == wasDeleted) || (setArray[index] == null))
            {setArray[index] = newValue; break;}


        }

    }
    /**Puts the actual values into the given array newStringArray, taking into account the capacity from
     * before
     * resizing
     * @param newStringArray - the new array
     * @param oldCapacity - the old capacity
     */
    private void rehash(String[] newStringArray, int oldCapacity) {

        for (int i =0; i<oldCapacity; i++) {
            String myString = setArray[i];

            if (myString != null) {
                for (int j = 0; j < newStringArray.length; j++) {
                    int index = (myString.hashCode() + (j * j + j) /SIZE_CHANGE) & (capacityMinusOne());
                    if (newStringArray[index] == null)
                    newStringArray[index] = myString; break;


                }
            }
        }

    }
}










