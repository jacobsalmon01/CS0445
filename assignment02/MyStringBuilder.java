// CS 0445 Fall 2020
// Read this class and its comments very carefully to make sure you implement
// the class properly.  Note the items that are required and that cannot be
// altered!  Generally speaking you will implement your MyStringBuilder using
// a singly linked list of nodes.  See more comments below on the specific
// requirements for the class.
// For more details on the general functionality of most of these methods, 
// see the specifications of the similar method in the StringBuilder class. 


public class MyStringBuilder {
    // These are the only three instance variables you are allowed to have.
    // See details of CNode class below.  In other words, you MAY NOT add
    // any additional instance variables to this class.  However, you may
    // use any method variables that you need within individual methods.
    // But remember that you may NOT use any variables of any other
    // linked list class or of the predefined StringBuilder or 
    // or StringBuffer class in any place in your code.  You may only use the
    // String class where it is an argument or return type in a method.

    private CNode firstC;	// reference to front of list.  This reference is necessary
    // to keep track of the list
    private CNode lastC; 	// reference to last node of list.  This reference is
    // necessary to improve the efficiency of the append()
    // method
    private int length;  	// number of characters in the list

    // You may also add any additional private methods that you need to
    // help with your implementation of the public methods.
    // Create a new MyStringBuilder initialized with the chars in String s
    // Note: This method is implemented for you.  See A2Help.java
    // Create a new MyStringBuilder which contains the contents of the
// String argument.
    public MyStringBuilder(String s) {
        if (s == null || s.length() == 0) // Special case for empty String
        {					 			  // or null reference
            firstC = null;
            lastC = null;
            length = 0;
        } else {
            // Create front node to get started
            firstC = new CNode(s.charAt(0));
            length = 1;
            CNode currNode = firstC;
            // Create remaining nodes, copying from String.  Note
            // how each new node is simply added to the end of the
            // previous one.  Trace this to see what is going on.
            for (int i = 1; i < s.length(); i++) {
                CNode newNode = new CNode(s.charAt(i));
                currNode.next = newNode;
                currNode = newNode;
                length++;
            }
            lastC = currNode;
        }
    }

    // Create a new MyStringBuilder initialized with the chars in array s
    public MyStringBuilder(char[] s) {
        if (s.length == 0) {
            firstC = null;
            lastC = null;
            length = 0;
        } else {
            firstC = new CNode(s[0]);
            length = 1;
            CNode currNode = firstC;
            for (int i = 1; i < s.length; i++) {
                CNode newNode = new CNode(s[i]);
                currNode.next = newNode;
                currNode = newNode;
                length++;
            }
            lastC = currNode;
        }

    }

    // Copy constructor -- make a new MyStringBuilder from an old one.  Be sure
    // that you make new nodes for the copy.
    public MyStringBuilder(MyStringBuilder old) {
        if (old == null) {
            firstC = null;
            lastC = null;
            length = 0;
        } else {
            firstC = new CNode(old.firstC.data, old.firstC.next);
            lastC = new CNode(old.lastC.data);
            length = old.length;
            CNode currNode = firstC;

            for (int i = 1; i < length; i++) {
                CNode newNode = new CNode(old.charAt(i));
                currNode.next = newNode;
                currNode = newNode;
            }

        }
    }

    // Create a new empty MyStringBuilder
    public MyStringBuilder() {
    }

    // Return the entire contents of the current MyStringBuilder as a String
    // For this method you should do the following:
    // 1) Create a character array of the appropriate length
    // 2) Fill the array with the proper characters from your MyStringBuilder
    // 3) Return a new String using the array as an argument, or
    //    return new String(charArray);
    // Note: This method is implemented for you.  See A2Help.java
    // Create and return a String from the characters in the
// MyStringBuilder.  To do this efficiently, we first make
// an array of the appropriate size, fill it with the
// characters, and then construct and return a String from
// that array.  Note that generally you CANNOT use arrays to
// implement your methods but for this method (and substring()
// also) we are using an array just for the purposes of
// storing the characters in order to return a new String.
    public String toString() {
        char[] c = new char[length];  // Make array of correct size 
        int i = 0;
        CNode currNode = firstC;
        while (currNode != null) // Iterate through the MyStringBuilder
        {							// putting the characters into the

            c[i] = currNode.data;	// correct positions in the array
            i++;
            currNode = currNode.next;
        }
        return new String(c);	// return a new String from the array
    }

    // Append MyStringBuilder b to the end of the current MyStringBuilder, and
    // return the current MyStringBuilder.  Be careful for special cases!
    public MyStringBuilder append(MyStringBuilder b) {
        if (length == 0 || firstC == null) {

            return b;
        }
        if (b.length == 0 || b.firstC == null) {
            return null;
        }
        CNode currNode = lastC;
        for (int i = 0; i < b.length; i++) {
            CNode newNode = new CNode(b.charAt(i));
            currNode.next = newNode;
            currNode = newNode;
            length++;
        }
        lastC = currNode;
        return this;

    }

    // Append String s to the end of the current MyStringBuilder, and return
    // the current MyStringBuilder.  Be careful for special cases!
    public MyStringBuilder append(String s) {
        if (firstC == null) {
            firstC = new CNode(s.charAt(0));
            CNode currNode = firstC;
            for (int i = 1; i < s.length(); i++) {

                CNode newNode = new CNode(s.charAt(i));
                currNode.next = newNode;
                currNode = newNode;
            }
            length = s.length();
            return this;
        }
        if (s.length() == 0 || s == null) {
            return this;
        }
        CNode currNode = lastC;
        for (int i = 0; i < s.length(); i++) {
            CNode newNode = new CNode(s.charAt(i));
            currNode.next = newNode;
            currNode = newNode;
            length++;
        }
        lastC = currNode;
        return this;
    }

    // Append char array c to the end of the current MyStringBuilder, and
    // return the current MyStringBuilder.  Be careful for special cases!
    public MyStringBuilder append(char[] c) {
        if (firstC == null) {
            firstC = new CNode(c[0]);
            CNode currNode = firstC;
            for (int i = 1; i < c.length; i++) {

                CNode newNode = new CNode(c[i]);
                currNode.next = newNode;
                currNode = newNode;
            }
            length = c.length;
            return this;
        }
        if (c.length == 0 || c == null) {
            return this;
        }
        CNode currNode = lastC;
        for (int i = 0; i < c.length; i++) {
            CNode newNode = new CNode(c[i]);
            currNode.next = newNode;
            currNode = newNode;
            length++;
        }
        lastC = currNode;
        return this;
    }

    // Append char c to the end of the current MyStringBuilder, and
    // return the current MyStringBuilder.  Be careful for special cases!
    public MyStringBuilder append(char c) {
        if (firstC == null) {
            firstC = new CNode(c);
            return this;
        }
        CNode currNode = lastC;
        CNode newNode = new CNode(c);
        currNode.next = newNode;
        currNode = newNode;
        length++;
        lastC = currNode;
        return this;
    }

    // Return the character at location "index" in the current MyStringBuilder.
    // If index is invalid, throw an IndexOutOfBoundsException.
    public char charAt(int index) throws IndexOutOfBoundsException {
        if (index > this.length) {
            throw new IndexOutOfBoundsException("Illegal Index " + index);
        }
        String word = this.toString();

        char c = word.charAt(index);

        return c;
    }

    // Delete the characters from index "start" to index "end" - 1 in the
    // current MyStringBuilder, and return the current MyStringBuilder.
    // If "start" is invalid or "end" <= "start" do nothing (just return the
    // MyStringBuilder as is).  If "end" is past the end of the MyStringBuilder, 
    // only remove up until the end of the MyStringBuilder. Be careful for 
    // special cases!
    public MyStringBuilder delete(int start, int end) {
        CNode startNode = new CNode('c');
        CNode endNode = new CNode('c');
        if (start < 0 || end <= start) {
            return this;
        }
        if (end > length) {
            end = length;
        }
        if (start == 0) {
            startNode = firstC;
        }
        CNode currNode = firstC;
        for (int i = 1; i <= end; i++) {
            currNode = currNode.next;
            if (i == start - 1) {
                startNode = currNode;
            }
            if (i == end) {
                endNode = currNode;
            }
        }
        if (start == 0) {
            firstC = endNode;
            length = ((length) - ((end) - (start)));
            return this;
        }
        //lastC = currNode;
        length = ((length) - ((end) - (start)));
        startNode.next = endNode;
        return this;
    }

    // Delete the character at location "index" from the current
    // MyStringBuilder and return the current MyStringBuilder.  If "index" is
    // invalid, do nothing (just return the MyStringBuilder as is).
    // Be careful for special cases!
    public MyStringBuilder deleteCharAt(int index) {
        CNode preRemoveNode = new CNode('c');
        CNode removeNode = new CNode('c');
        if (index > length) {
            return this;
        }
        if (index == 0) {
            firstC = firstC.next;
            length--;
            return this;
        }
        CNode currNode = firstC;
        for (int i = 1; i <= index; i++) {
            currNode = currNode.next;
            if (i == index - 1) {
                preRemoveNode = currNode;
            }
            if (i == index) {
                removeNode = currNode;
            }
        }
        preRemoveNode.next = removeNode.next;
        length = length - 1;
        return this;
    }

    // Find and return the index within the current MyStringBuilder where
    // String str first matches a sequence of characters within the current
    // MyStringBuilder.  If str does not match any sequence of characters
    // within the current MyStringBuilder, return -1.  Think carefully about
    // what you need to do for this method before implementing it.
    public int indexOf(String str) {
        char[] c = new char[length];
        int i = 0;
        CNode currNode = firstC;
        int index = -1;
        while (currNode != null) {
            c[i] = currNode.data;
            i++;
            currNode = currNode.next;
        }
        int count = 0;
        while (count < length) {    //loop through char array/MSB
            int checkIndex = 0;
            if (this.toString().charAt(0) == str.charAt(0)) {    //find index of first letter of str in MSB, call it checkIndex
                checkIndex = count;
            }
            count++;
            for (int a = 0; a < str.length(); a++) {    // check if checkIndex subsequent letters match str
                if ((a + checkIndex) >= c.length) { //if not/length of string is too big from given index, index=-1(not found)
                    index = -1;
                    break;
                }
                if (this.toString().charAt(a + checkIndex) != str.charAt(a)) {
                    index = -1;
                    break;
                } else {    // if the letters are always equal until the end of str, return checkIndex
                    index = checkIndex;
                }
            }
            if (index != -1) {
                break;
            }
        }
        return index;
    }

    // Insert String str into the current MyStringBuilder starting at index
    // "offset" and return the current MyStringBuilder.  if "offset" == 
    // length, this is the same as append.  If "offset" is invalid
    // do nothing.
    public MyStringBuilder insert(int offset, String str) {
        if (offset == length) {
            this.append(str);
            return this;
        }
        CNode insertNode = new CNode('b');
        CNode preInsertNode = new CNode('b');
        CNode currNode = firstC;
        if (offset == 0) {
            CNode newNode = new CNode(str.charAt(0));
            currNode = newNode;
            CNode newFirstC = newNode;
            length++;
            for (int i = 1; i < str.length(); i++) {
                newNode = new CNode(str.charAt(i));
                currNode.next = newNode;
                currNode = newNode;
                length++;
            }
            currNode.next = firstC;
            firstC = newFirstC;
        } else {
            for (int i = 0; i < offset; i++) {
                currNode = currNode.next;
                if (i == offset - 2) {
                    preInsertNode = currNode;
                }
                if (i == offset - 1) {
                    insertNode = currNode;

                }
            }
            CNode newNode = new CNode(str.charAt(0));
            preInsertNode.next = newNode;
            currNode = newNode;
            length++;
            for (int b = 1; b < str.length(); b++) {
                newNode = new CNode(str.charAt(b));
                currNode.next = newNode;
                currNode = newNode;
                length++;
            }
            currNode.next = insertNode;
        }

        return this;
    }

    // Insert character c into the current MyStringBuilder at index
    // "offset" and return the current MyStringBuilder.  If "offset" ==
    // length, this is the same as append.  If "offset" is invalid, 
    // do nothing.
    public MyStringBuilder insert(int offset, char c) {
        CNode insertNode = new CNode('b');
        CNode currNode = firstC;
        CNode insertNode2 = new CNode('b');
        if (offset == length) {
            this.append(c);
            return this;
        }
        if (offset == 0) {
            CNode newNode = new CNode(c);
            CNode newFirstC = firstC;
            newNode.next = newFirstC;
            firstC = newNode;
            length++;
            return this;
        }
        for (int i = 0; i < length; i++) {
            currNode = currNode.next;
            if (i == offset - 2) {
                insertNode = currNode;
            }
            if (i == offset - 1) {
                insertNode2 = currNode;
            }
        }
        CNode newNode = new CNode(c);
        insertNode.next = newNode;
        newNode.next = insertNode2;

        length++;
        return this;
    }

    // Insert char array c into the current MyStringBuilder starting at index
    // index "offset" and return the current MyStringBuilder.  If "offset" is
    // invalid, do nothing.
    public MyStringBuilder insert(int offset, char[] c) {
        if (offset == length) {
            this.append(c);
            return this;
        }
        CNode insertNode = new CNode('b');
        CNode preInsertNode = new CNode('b');
        CNode currNode = firstC;
        if (offset == 0) {
            CNode newNode = new CNode(c[0]);
            currNode = newNode;
            CNode newFirstC = newNode;
            length++;
            for (int i = 1; i < c.length; i++) {
                newNode = new CNode(c[i]);
                currNode.next = newNode;
                currNode = newNode;
                length++;
            }
            currNode.next = firstC;
            firstC = newFirstC;
            return this;
        }
        for (int i = 0; i < offset; i++) {
            currNode = currNode.next;
            if (i == offset - 2) {
                preInsertNode = currNode;
            }
            if (i == offset - 1) {
                insertNode = currNode;

            }
        }
        CNode newNode = new CNode(c[0]);
        preInsertNode.next = newNode;
        currNode = newNode;
        length++;
        for (int b = 1; b < c.length; b++) {
            newNode = new CNode(c[b]);
            currNode.next = newNode;
            currNode = newNode;
            length++;
        }
        currNode.next = insertNode;
        return this;

    }

    // Return the length of the current MyStringBuilder
    public int length() {
        return length;
    }

    // Delete the substring from "start" to "end" - 1 in the current
    // MyStringBuilder, then insert String "str" into the current
    // MyStringBuilder starting at index "start", then return the current
    // MyStringBuilder.  If "start" is invalid or "end" <= "start", do nothing.
    // If "end" is past the end of the MyStringBuilder, only delete until the
    // end of the MyStringBuilder, then insert.  This method should be done
    // as efficiently as possible.  In particular, you may NOT simply call
    // the delete() method followed by the insert() method, since that will
    // require an extra traversal of the linked list.
    public MyStringBuilder replace(int start, int end, String str) {
        if (start > length || start < 0 || start >= end) {
            return this;
        }
        CNode startNode = null;
        CNode endNode = null;
        if (end > length) {
            end = length;
        }
        if (start == 0) {
            startNode = firstC;
        }
        CNode currNode = firstC;
        for (int i = 1; i <= end; i++) {
            currNode = currNode.next;
            if (i == start - 1) {
                startNode = currNode;
            }
            if (i == end) {
                endNode = currNode;
            }
        }
        if (start == 0) {
            firstC = endNode;
            length = ((length) - ((end) - (start)));
            return this;
        }
        //lastC = currNode;
        length = ((length) - ((end) - (start)));
        startNode.next = endNode;
        CNode newNode = new CNode(str.charAt(0));
        startNode.next = newNode;
        currNode = newNode;
        length++;
        for ( int i = 1; i < str.length(); i++){
            newNode = new CNode(str.charAt(i));
            currNode.next = newNode;
            currNode = newNode;
            length++;
        }
        currNode.next = endNode;
        return this;
    }

// Reverse the characters in the current MyStringBuilder and then
// return the current MyStringBuilder.
    public MyStringBuilder reverse() {
        MyStringBuilder clone = this;
        CNode prevNode = null;
        CNode nextNode = null;
        CNode currNode = firstC;
        for (int i = 0; i < length; i++){;
            nextNode = currNode.next;
            currNode.next = prevNode;
            prevNode = currNode;
            currNode = nextNode;
        }
        lastC = clone.firstC;
        firstC =prevNode;
        return this;
        
        
    }

    // Return as a String the substring of characters from index "start" to
    // index "end" - 1 within the current MyStringBuilder.  For this method
    // you should do the following:
    // 1) Create a character array of the appropriate length
    // 2) Fill the array with the proper characters from your MyStringBuilder
    // 3) Return a new String using the array as an argument, or
    //    return new String(charArray);
    public String substring(int start, int end) {
        char[] charArray = new char[end-start];
        CNode startNode = firstC;
        CNode currNode = startNode;
        for (int i = 0; i < start; i++){
            currNode = currNode.next;
        }
        startNode = currNode;
        for (int i = 0; i < end-start; i++){
            charArray[i] = currNode.data;
            currNode = currNode.next;
            
        }
        return new String(charArray);
    }

    // You must use this inner class exactly as specified below.  Note that
    // since it is an inner class, the MyStringBuilder class MAY access the
    // data and next fields directly.
    private class CNode {

        private char data;
        private CNode next;

        public CNode(char c) {
            data = c;
            next = null;
        }

        public CNode(char c, CNode n) {
            data = c;
            next = n;
        }
    }
}
