/**
 * Created by lautaro on 3/28/16.
 */
public abstract class SimpleHashSet implements SimpleSet {

    public final static int INITIAL_CAPACITY = 16;
    protected final static float UPPER_LOAD_FACTOR_DEFAULT = 0.75f;
    protected final static float LOWER_LOAD_FACTOR = 0.25f;


    protected final float upperLoad;
    protected final float lowerLoad;
    protected int capacity;
    protected int capacityMinusOne = capacity-1;






    /**Constructs a new, empty table with the specified load factors, and the default initial capacity (16)
     * @param upperLoadFactor - The upper load factor of the hash table.
     * @param lowerLoadFactor - The lower load factor of the hash table.*/
    public SimpleHashSet(float upperLoadFactor, float lowerLoadFactor) {

        this.capacity = INITIAL_CAPACITY;
        this.upperLoad = upperLoadFactor;
        this.lowerLoad = lowerLoadFactor;
    }




    public SimpleHashSet() {
        this.capacity = INITIAL_CAPACITY;
        this.upperLoad = UPPER_LOAD_FACTOR_DEFAULT;
        this.lowerLoad = LOWER_LOAD_FACTOR;

    }


    public float getUpperLoadFactor() {
        return this.upperLoad;
    }

    public float getLowerLoadFactor() {
        return this.lowerLoad;
    }



    public int capacity(){
        return this.capacity;


    }
    public int capacityMinusOne(){
        return (capacity() - 1); //TODO necesitamos this o no???
    }









}

