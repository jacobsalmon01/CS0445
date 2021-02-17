/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author jakesalmon
 */
public class MyDeque<T> implements DequeInterface<T> {

    protected T[] data;
    protected int back;  // index of logical back item in deque
    protected int front;
    protected int size;

    public MyDeque(int initsize) {
        // Note how array is created and cast to T.
        data = (T[]) new Object[initsize];
        // Back is initialized to -1 for special case when deqeue
        // is empty.  Be careful about this special case for both
        // adding and removing.
        back = -1;
        front = -1;

    }

    public MyDeque(MyDeque<T> old) {
        back = 1;
        data = (T[]) new Object[old.data.length];
        for (int i = 0; i < old.size(); i++) {
            this.addToBack(old.data[i]);
            
        }
    }

    public String toString() {
        if (size() > 0) {
            StringBuilder returnString = new StringBuilder();
            returnString.append("Contents: ");
            if (back > front) {
                for (int i = front; i <= back; i++) {
                    returnString.append(data[i] + " ");
                }
            }
            if (front > back) {
                for (int i = front; i < data.length; i++) {
                    returnString.append(data[i] + " ");
                }
                for (int i = 0; i <= back; i++) {
                    returnString.append(data[i] + " ");
                }
            }
            return returnString.toString();

        } else {
            return new String("Contents: ");
        }
    }

    public boolean equals(MyDeque<T> rhs) {
        T[] array;
        array = (T[]) new Object[data.length];
        T[] rhsArray;
        rhsArray = (T[]) new Object[rhs.data.length];
        if (back > front) {
            for (int i = front; i <= back; i++) {
                array[i] = data[i];
            }
        }
        if (rhs.back > rhs.front) {
            for (int i = rhs.front; i <= rhs.back; i++) {
                rhsArray[i] = rhs.data[i];
            }
        }
        if (front > back) {

            array = (T[]) new Object[size];
            for (int i = front; i < data.length; i++) {
                array[i - front] = data[i];
            }
            for (int i = 0; i <= back; i++) {
                array[(i - front) + data.length] = data[i];
            }
        }
        if (rhs.front > rhs.back) {
            for (int i = rhs.front; i < rhs.data.length; i++) {
                rhsArray[i - rhs.front] = data[i];
            }
            for (int i = 0; i <= rhs.back; i++) {
                rhsArray[(i - rhs.front) + rhs.data.length] = data[i];
            }
        }
        boolean isTrue = true;
        if (this.size() != rhs.size()) {
            isTrue = false;
        } else {
            for (int i = 0; i < array.length; i++) {
                if (array[i] != rhsArray[i]) {
                    isTrue = false;
                    break;
                }

            }
        }

        return isTrue;
    }

    public void addToFront(T newEntry) {
        // Add to front, NOT shifting data to make room.
        // If array is full, print out a message and do not
        // add the entry.
        if (isEmpty()) {
            data[0] = newEntry;
            front = 0;
            back = 0;
            return;
        }
        if (size() == data.length - 1) {              // if array is full, don't add
            data = resize();
        }
        if (front == 0) {
            front = data.length - 1;
        } else {
            front--;
        }
        data[front] = newEntry;

    }

    public void addToBack(T newEntry) {
        if (isEmpty()) {
            data[0] = newEntry;
            front = 0;
            back = 0;
            return;
        }
        if (size() == data.length - 1) {
            data = resize();
        }
        if (back == data.length - 1) {
            back = 0;

        } else {
            back++;

        }
        
        data[back] = newEntry;
    }

    public T removeBack() {
        if (isEmpty()) {
            return null;
        } else {
            if ( back == 0){
               T returnItem = data[back];
                data[back] = null;
                back = data.length-1;
                
                return returnItem;
            }
            else {              
                T returnItem = data[back];
                data[back] = null;
                back--;
                
                return returnItem;                      
            }
        }
    }

    public T removeFront() {
        if (isEmpty()) {
            return null;
        } else {
            T returnItem = data[front];
            data[front] = null;
            if (front == data.length - 1) {
                front = 0;
            } else {
                front++;
            }

            return returnItem;
        }
    }

    public T getBack() {
        if (isEmpty()) {
            return null;
        } else {
            return data[back];
        }
    }

    public T getFront() {
        if (isEmpty()) {
            return null;
        } else {
            return data[front];
        }
    }

    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        } else {
            return false;
        }

    }

    public void clear() {
        for (int i = 0; i < data.length; i++) {
            data[i] = null;

        }
        size = 0;
        back = -1;
        front = -1;
    }

    public int size() { //logical size of array
        size = 0;
        for (int i = 0; i <= data.length - 1; i++) {
            if (data[i] != null) {
                size++;
            }
        }
        return size; //updates size whenever method is called to avoid having to update instance data
    }

    public int capacity() {
        return data.length;
    }

    public T[] resize() {
        T[] data1 = (T[]) new Object[2 * (data.length)];
        if (front < back) {
            for (int i = 0; i < data.length; i++) {
                data1[i] = data[i];
            }

        } else if (front > back) {
            for (int i = 0; i < back; i++) {
                data1[i] = data[i];
            }
            for (int i = (data.length - 1); i > front; i--) {
                data1[(data.length - 1 - i)] = data[i];

            }
            front = front + (data.length);

        }
        return data1;
    }

}
