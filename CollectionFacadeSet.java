import java.util.Collection;

/**
 * Created by lautaro on 3/28/16.
 */
public class CollectionFacadeSet implements SimpleSet {

    protected java.util.Collection<java.lang.String> collection;

    public CollectionFacadeSet(java.util.Collection<java.lang.String> collection) {
        this.collection = collection;
    }


    protected Collection myColection;

    public boolean add(java.lang.String newValue) {

       if (!collection.contains(newValue)) {
            collection.add(newValue);
            return true;

        }
        return false; //todo esto funciona como else????
    }


    public boolean contains(java.lang.String searchVal){
        return collection.contains(searchVal);
    }

    public boolean delete(java.lang.String toDelete){
        if (collection.contains(toDelete)) {
            collection.remove(toDelete);
            return true;
        }
        return false;
    }



    public int size() {
        return collection.size();
    }
}

