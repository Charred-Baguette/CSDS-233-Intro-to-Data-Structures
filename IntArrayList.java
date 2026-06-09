public class IntArrayList {
    // private data members
    private int [] data;
    private int numItems; //How many items are being stored in the list
                        // note this is NOT the same as the size of the array
    private int currentPos;// Which position in the list is considered the current one

    //constructor
    public IntArrayList() {
        data = new int [10]; // Initially allow for up to 10 items to be stored in our list
        currentPos = 0; // Set our "current" location to the beginning of the list
        numItems = 0; // Our list is initially empty
    }

    // add Methods
    // For all add methods you should consider what to do if the array is full before addition

    // adds item to the end of the list

    //helper function to resize the array when it is full
    private void resize(){
        int newSize = data.length * 2;
        int[] newData = new int[newSize];
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

   public boolean add(int item){
        if (numItems < data.length) {
            data[numItems] = item;
            numItems++;
            return true;
        }
        else{
            //list is full, need to resize
            resize();
            data[numItems] = item;
            numItems++;
            return true;
        }
    }
    // inserts item into the list at position pos.
    // currentPos is set to this position
    public void add(int pos, int item){
        if (pos < 0 || pos > numItems) {
            throw new IndexOutOfBoundsException("Position out of bounds");
        }
        if (numItems >= data.length) {
            resize();
        }
        for (int i = numItems; i > pos; i--) {
            data[i] = data[i - 1];
        }
        data[pos] = item;
        numItems++;
        currentPos = pos;
    }

    // For these next two (addAfter and addBefore)
    // place an int before or after the data[currentPos] int
    // this added item becomes the new currentPos.
    
    // If no valid currentPs, addAfter places item at the end of the list and
    // addBefore places item at beginning of list;
    // returns true if list is changed

    public boolean addAfter(int item){
        if (numItems == 0) {
            add(0, item);
            return true;
        } else if (currentPos >= 0 && currentPos < numItems) {
            add(currentPos + 1, item);
            return true;
        } else {
            add(numItems, item);
            return true;
        }
    }

    public boolean addBefore(int item){
        if (numItems == 0) {
            add(0, item);
            return true;
        } else if (currentPos >= 0 && currentPos < numItems) {
            add(currentPos, item);
            return true;
        } else {
            add(0, item);
            return true;
        }
    }

    // Appends items to the end of this list.
    // Returns true if the list changed as a result.
    // currentPos doesn’t change
    boolean addAll(IntArrayList items){
        int currentPos_prior = currentPos;
        if (items == null || items.numItems == 0) {
            return false;
        }
        for (int i = 0; i < items.numItems; i++) {
            add(items.data[i]);
        }
        currentPos = currentPos_prior; // restore currentPos so it doesn't change
        return true;
    }
}
