

public class IndexDeque<T> extends MyDeque<T> implements Indexable<T> {

    public IndexDeque(int initsize) {
        super(initsize);
    }
    

    public T getFront(int i) throws IndexOutOfBoundsException {
        if (size() < i+1 ) {
            throw new IndexOutOfBoundsException("Illegal index " + i);
        }
        if (i + front >= data.length) {
            int newCount = i - (data.length-front);
            return data[newCount];
        } else {
            return data[front + i];
        }
        //THROW INDEXOUTOFBOUNDS EXCEPTION

    }
    public T getBack(int i)  throws IndexOutOfBoundsException {        //count backwards or forwards from back?
        if (size() < i+1 ) {
            throw new IndexOutOfBoundsException("Illegal index" + i);
        }
        if (back - i < 0) {
            int newCount = data.length+(back-i);
            return data[newCount];
        } else {
            return data[back - i];
        }
        //THROW INDEXOUTOFBOUNDS EXCEPTION
    }
    public void setFront(int i, T item)  throws IndexOutOfBoundsException {
        if (size() < i+1 ) {
            throw new IndexOutOfBoundsException("Illegal index" + i);
        }
        if (i + front >= data.length) {
            int newCount = i - (data.length-front);
            data[newCount] = item;
        } else {
            data[front + i] = item;
        }
        //THROW INDEXOUTOFBOUNDS EXCEPTION
    }
    public void setBack(int i, T item)  throws IndexOutOfBoundsException {
        if (size() < i+1 ) {
            throw new IndexOutOfBoundsException("Illegal index" + i);
        }
        if (back - i < 0){
            int newCount = data.length+(back-i);
            data[newCount] = item;
        }
        else {
            data[back-i] = item;
        }
//        if (i + back >= data.length) {
//            int newCount = i - (data.length-back);
//            data[newCount] = item;         
//        } else {
//            data[back + i] = item;
//        } //THROW INDEXOUTOFBOUNDS EXCEPTION
//        System.out.println();
//        for (int b = 0; b<data.length; b++){
//            System.out.print(data[b] + " ");
//        }
        
    }
    public int size() { //logical size of array
        int size=0;
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null) {
                size++;
            }
            
        }
        return size; //updates size whenever method is called to avoid having to update instance data
    }
    
	
}
