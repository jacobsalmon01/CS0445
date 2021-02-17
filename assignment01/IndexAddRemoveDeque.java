/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author jakesalmon
 */
public class IndexAddRemoveDeque<T> extends IndexDeque<T> implements IndexableAddRemove<T>{

    public IndexAddRemoveDeque(int initsize) {
        super(initsize);
    }
    public void addToFront(int i, T item){
        if (isEmpty()) {
            data[0] = item;
            front = 0;
            back = 1;
        }
        else if ( i + 1 > size() ) {
            throw new IndexOutOfBoundsException("Illegal index" + i);
        }
        else if (size() >= data.length-1){
            data = resize();
            
        }
        if (i + front >= data.length) {
            int newCount = i - (data.length-front);
            for (int b = back; b >= newCount; b--){
                data[b+1]=data[b];
            }
            data[newCount] = item;
            if (back == (data.length - 1)) {
                back = 0;
            }
            else {
                back++;
            }
        }
        else if (i == 0){
            data[front] = item;
        }
        else {
            for (int b = back; b>=front+i; b--){                
                data[b+1] = data[b];
            }
            data[front+i] = item;
            if (back == (data.length-1)) {
                back = 0;
            }
            else {
                back++;
            }
        }
    }
    public void addToBack(int i, T item){
        if (isEmpty()) {
            data[0] = item;
            front = 0;
            back = 1;
        }
        else if ( i + 1 > size() ) {
            throw new IndexOutOfBoundsException("Illegal index" + i);
        }
        else if (size() >= data.length-1){
            data = resize();
        }
        else if (back - i < 0) {
            int newCount = data.length+(back-i);
            for (int b = front; b <= newCount; b++){
                data[b-1] = data[b];
            }
            
            data[newCount] = item;
            if (front == 0) {
                front = data.length-1;
            }
            else {
                front--;
            }
        
        } 
        else if (i == 0){
            data[back] = item;
        }
        else {
            for (int b = back; b>= back-i; b--){
                data[b+1] = data[b];
            }
            data[back] = item;
            if (back == data.length-1){
                front = 0;
            }
            else {
                back++;
            }
        }
    }
    
    public T removeFront(int i) throws IndexOutOfBoundsException {
        T temp;
        if ( i + 1 > size() ) {
            throw new IndexOutOfBoundsException("Illegal index" + i);
        }
        if (i + front >= data.length) {
            int newCount = i - (data.length-front);          
            temp = data[newCount];
            data[newCount] = null;
            for (int b = newCount; b < back; b++){
                data[b] = data[b+1];
            }
            if (back == 0) {
                back = data.length - 1;
            } else {
                back--;
            }
            return temp;
        } else {
            
            temp = data[front + i];
            data[front+i] = null;
            T temp1 = data[0];
            for (int b = (front+i); b<back; b++){
                data[b] = data[b+1];          
            }
            data[back] = null;
            if (back == 0) {
                back = data.length - 1;
            } else {
                back--;
            }
            return temp;
        }
        
    }
    public T removeBack(int i){
        T temp;
        if ( i + 1 > size() ) {
            throw new IndexOutOfBoundsException("Illegal index" + i);
        }
        if (back - i < 0) {
            int newCount = data.length+(back-i);
            temp = data[newCount];
            data[newCount] = null;           
            for (int b = newCount; b>front; b--){
                data[b] = data[b-1];
            }
            if (front == (data.length-1)){
                front = 0;
            }
            else {
                front++;
            }
            return temp;
        }
        else {
            temp = data[back-i];
            data[back-i] = null;
            for (int b = (back-i); b > front; b--){
              
                data[b] = data[b-1];
            }
            data[front] = null;           
            if (front == (data.length-1)){
                front = 0;
            }
            else {
                front++;
            }
            return temp;
        }
    }
    
}