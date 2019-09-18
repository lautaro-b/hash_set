/**
 *  Analyzer that has a main method that measures the requested runtimes for the performances.
 */
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

public class SimpleSetPerformanceAnalyzer {

    private static final int NANOSEC_TO_MILISEC = 1000000;
    private static final int WARM_UP = 70000;
    private static final int LINKED_TIME = 7000;
    private static final int ARRAY_SIZE = 5;

    private static final String[] sets = {"HashSet", "TreeSet", "ClosedHasSet", "OpenHashSet",
            "LinkedList"};

    private static SimpleSet[] set_1 = new SimpleSet[5];
    private static SimpleSet[] set_2 = new SimpleSet[5];
    private static String[] data_1 = Ex3Utils.file2array("src/data1.txt");
    private static String[] data_2 = Ex3Utils.file2array("src/data2.txt");

    public static void addDataToSet (SimpleSet[] setArray, String[] data, int file) {
        long time;
        long difference;

        for (int i = 0; i<ARRAY_SIZE; i++) {
            time = System.nanoTime();
            for (String str : data) {
                setArray[i].add(str);
            }
            difference = (System.nanoTime() - time) / NANOSEC_TO_MILISEC;
            System.out.println(("The time required to add data from data" + file + " to " + sets[i] + " is" +
                    " " + difference + " nanoseconds."));

        }
    }

    public static void contains(SimpleSet[] setArray, String value, int file) {
        int sizeMinusOne = ARRAY_SIZE - 1;
        long time;
        long difference;
        time = System.nanoTime();
        for (int i = 0; i < sizeMinusOne; i++) {
            for (int j = 0; j < WARM_UP; j++) {
                setArray[i].contains(value);
            }

            difference = (System.nanoTime() - time) / WARM_UP;
            System.out.println("Time to check if " + sets[i] + " contains " + value + " from data" + file +
                    "" +
                    " is " +
                    difference + " nanoseconds");

        }
        time = System.nanoTime();
         for (int j = 0; j < LINKED_TIME; j++) {
             setArray[ARRAY_SIZE-1].contains(value);
             }
            difference = (System.nanoTime() - time) / LINKED_TIME;
            System.out.println("Time to check if " + sets[sizeMinusOne] + " contains " + value +
                    " from data" +
                    file +
                    " is " + difference + " nanoseconds");
    }

    public static void main (String[] args) {
        OpenHashSet openHashSet = new OpenHashSet();
        ClosedHashSet closedHashSet = new ClosedHashSet();

        CollectionFacadeSet hashSet = new CollectionFacadeSet(new HashSet<>());
        CollectionFacadeSet linkedList = new CollectionFacadeSet(new LinkedList<>());
        CollectionFacadeSet treeSet = new CollectionFacadeSet(new TreeSet<>());


        set_1[0] = hashSet;
        set_1[1] = treeSet;
        set_1[2] = closedHashSet;
        set_1[3] = openHashSet;
        set_1[4] = linkedList;


        set_2[0] = hashSet;
        set_2[1] = treeSet;
        set_2[2] = closedHashSet;
        set_2[3] = openHashSet;
        set_2[4] = linkedList;



        addDataToSet(set_1, data_1, 1);
        addDataToSet(set_2, data_2, 2);
        contains(set_1, "hi", 1);
        contains(set_1, "-13170890158", 1);
        contains(set_2, "23", 2);
        contains(set_2, "hi", 2);


    }

}
