import java.util.ListIterator;

/**
 * Created by lautaro on 3/28/16.
 */
public class OpenHashSet extends SimpleHashSet {
    /*Fields/Data members inherited from SimpleHashSet:
   capacityMinusOne, INITIAL_CAPACITY*/
    LinkedListString[] linkedListStrings;
    public int sizeCounter = 0;
    private static final int MINIMAL_CAPACITY = 1;
    private static final int SIZE_CHANGE = 2;
    private static final int MINIMAL_CHANGE = 1; //Although it has the same value than MINIMAL_CAPACITY,
    //it has a different meaning, hence I decided to give a different name to it;


    // -------Constructors----------


    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16)
     *
     * @param upperLoadFactor - The upper load factor of the hash table.
     * @param lowerLoadFactor - The lower load factor of the hash table.
     */
    public OpenHashSet(float upperLoadFactor, float lowerLoadFactor) {
        super(upperLoadFactor, lowerLoadFactor);
        linkedListStrings = new LinkedListString[INITIAL_CAPACITY];

    }


    /**
     * A default constructor. Constructs a new, empty table with default initial capacity (16), upper load
     * factor (0.75) and lower load factor (0.25).
     */

    public OpenHashSet() {
        super();
        linkedListStrings = new LinkedListString[INITIAL_CAPACITY];


    }




    /**
     * Data constructor - builds the hash set by adding the elements one by one. Duplicate values should be
     * ignored. The new table has the default values of initial capacity (16), upper load factor (0.75), and
     * lower load factor (0.25).
     *
     * @param data - Values to add to the set.
     */
    public OpenHashSet(String[] data) {
        new OpenHashSet();
        linkedListStrings = new LinkedListString[INITIAL_CAPACITY];


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


        if ((float)(size() + MINIMAL_CHANGE) / (capacity()) > getUpperLoadFactor()) {

            int oldCapacity  = capacity();
            capacity *= SIZE_CHANGE;
            LinkedListString[] newLinked = new LinkedListString[capacity()];
            rehash(newLinked, oldCapacity);

            linkedListStrings = newLinked;

        }

        int index = clamp(newValue);

        if (linkedListStrings[index] == null){
            linkedListStrings[index] = new LinkedListString();
        }

        linkedListStrings[index].add(newValue);

        sizeCounter++;
        return true;

    }

  /**Puts the actual values into the given array newLinked, taking into account the capacity from before
     * resizing
     * @param newLinked - the new array
     * @param oldCapacity - the old capacity
     */
    private void rehash(LinkedListString[] newLinked, int oldCapacity) {
        for (int i=0; i < oldCapacity; i++) {
            LinkedListString myList = linkedListStrings[i];
            if (myList == null)
                continue;

            ListIterator<String> myIterator = myList.listIterator();

            while (myIterator.hasNext()){
                String value = myIterator.next();
                int index = clamp(value);
                if (newLinked[index] == null)
                    newLinked[index] = new LinkedListString();
                newLinked[index].add(value);
      }
        }
    }

    /**Returns the amount of elements currently in the array
     * @return the amount of elements currently in the array */
    public int size(){
        return sizeCounter;
    }

    /**Checks if the array contains the given value
     * @param value - the given String value
     * @return true if the array contains the value, false otherwise*/
    public boolean contains(String value){
        if (value == null)
            return false;
        int index = clamp(value);
        if (linkedListStrings[index] == null)
        return false;
        else
        return linkedListStrings[index].contains(value);


    }
    /***/
    public boolean delete(String value) {
        if (value == null)
            return false;

        int index = clamp(value);

        if (linkedListStrings[index] == null){
            return false;
        }

        if (linkedListStrings[index].contains(value)) {
            linkedListStrings[index].remove(value);

            sizeCounter--;

        if (((float) size() / (capacity()) < getLowerLoadFactor()) && (capacity()>MINIMAL_CAPACITY)) {
            int oldCapacity = capacity();
            capacity /= SIZE_CHANGE;
            LinkedListString[] newLinked = new LinkedListString[capacity()];
            rehash(newLinked, oldCapacity);
            linkedListStrings = newLinked;

        }

        return true;
    }
        return false;
    }
    /**Returns the hash which best fits to the array for the given String
     * @param value - the string to "clamp"
     * @return its fitting hash*/
    public int clamp(String value){
        return (value.hashCode() & capacityMinusOne());
    }
}