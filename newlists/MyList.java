package newlists;

import java.util.Arrays;

public class MyList<E>{
    E[] theData;
    int capacity;
    int size;

    public MyList(){
        capacity = 10;
        // we cannot have an array if we don't know of what
        // so make a basic Object array and then tell it to make it
        // E, so whatever type this object is given
        theData = (E[])(new Object[capacity]);
        size = 0;
    }
    public MyList(int capacity){
        this.capacity = capacity;
        capacity = 10;
        theData = (E[])(new Object[capacity]);
        size = 0;
    }

    public boolean add(E anEntry){
        if (size == capacity){
            reallocate();
        }
        theData[size] = anEntry;
        size++;
        return true;
    }
    // get new array with double the size and same items in same index
    public void reallocate(){
        capacity = capacity*2;
        theData = Arrays.copyOf(theData, capacity);
    }
}
