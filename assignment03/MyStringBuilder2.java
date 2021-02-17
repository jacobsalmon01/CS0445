// CS 0445 Fall 2020
// Read this class and its comments very carefully to make sure you implement
// the class properly.  The data and public methods in this class are identical
// to those in MyStringBuilder, with the exception of the two additional methods
// shown at the end.  You cannot change the data or add any instance
// variables.  However, you may (and will need to) add some private methods.
// No iteration (i.e. no loops) is allowed in this implementation. 
// For more details on the general functionality of most of these methods, 
// see the specifications of the similar method in the StringBuilder class.  


import java.util.*;

public class MyStringBuilder2 {
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
    // Create a new MyStringBuilder2 initialized with the chars in String s
    // Constructor to make a new MyStringBuilder2 from a String. The constructor
// itself is NOT recursive – however, it calls a recursive method to do the
// actual set up work. This should be your general approach for all of the
// methods, since the recursive methods typically need extra parameters that
// are not given in the specification.
    public MyStringBuilder2(String s) {
        if (s != null && s.length() > 0) {
            makeBuilder(s, 0);
        } else // no String so initialize empty MyStringBuilder2
        {
            length = 0;
            firstC = null;
            lastC = null;
        }
    }

    // Create a new MyStringBuilder2 initialized with the chars in array s
    public MyStringBuilder2(char[] s) {
        if (s != null && s.length > 0) {
            makeBuilder(s, 0);
        } else {
            length = 0;
            firstC = null;
            lastC = null;
        }
    }

    // Recursive method to set up a new MyStringBuilder2 from a String
    private void makeBuilder(String s, int pos) {
        // Recursive case – we have not finished going through the String
        if (pos < s.length() - 1) {
            // Note how this is done – we make the recursive call FIRST, then
            // add the node before it. In this way the LAST node we add is
            // the front node, and it enables us to avoid having to make a
            // special test for the front node. However, many of your
            // methods will proceed in the normal front to back way.
            makeBuilder(s, pos + 1);
            firstC = new CNode(s.charAt(pos), firstC);
            length++;
        } else if (pos == s.length() - 1) // Special case for last char in String
        {                                 // This is needed since lastC must be
            // set to point to this node
            firstC = new CNode(s.charAt(pos));
            lastC = firstC;
            length = 1;
        } else // This case should never be reached, due to the way the
        // constructor is set up. However, I included it as a
        { // safeguard (in case some other method calls this one)
            length = 0;
            firstC = null;
            lastC = null;
        }
    }

    private void makeBuilder(char[] s, int pos) {
        if (pos < s.length - 1) {
            makeBuilder(s, pos + 1);
            firstC = new CNode(s[pos], firstC);
            length++;
        } else if (pos == s.length - 1) {
            firstC = new CNode(s[pos]);
            lastC = firstC;
            length = 1;
        } else {
            length = 0;
            firstC = null;
            lastC = null;
        }
    }

    private void makeBuilder(MyStringBuilder2 s, int pos) {
        if (pos < s.length) {
            makeBuilder(s, pos + 1);
            firstC = new CNode(s.toString().charAt(pos), firstC);
            length++;
        } else if (pos == s.length - 1) {
            firstC = new CNode(s.toString().charAt(pos));
            lastC = firstC;
            length = 1;
        } else {
            length = 0;
            firstC = null;
            lastC = null;
        }
    }

    // Copy constructor -- make a new MyStringBuilder2 from an old one.  Be sure
    // that you make new nodes for the copy.
    public MyStringBuilder2(MyStringBuilder2 old) {
        if (old != null && old.length() > 0) {
            makeBuilder(old, 0);
        } else {
            length = 0;
            firstC = null;
            lastC = null;
        }
    }

    // Create a new empty MyStringBuilder2
    public MyStringBuilder2() {

    }

    private void appendBuilder(MyStringBuilder2 s, int pos, CNode lastC) {
        CNode newNode;
        if (pos < s.length - 1) { //recursively loop through MSB appending
            newNode = new CNode(s.charAt(pos));
            newNode.next = null;
            lastC.next = newNode;
            lastC = newNode;
            length++;
            appendBuilder(s, pos + 1, lastC);
        } else if (pos == s.length - 1) { // final case
            newNode = new CNode(s.charAt(pos));
            newNode.next = null;
            lastC.next = newNode;
            this.lastC = newNode;
            length++;
        }

    }

    // Append MyStringBuilder2 b to the end of the current MyStringBuilder2, and
    // return the current MyStringBuilder2.  Be careful for special cases!
    public MyStringBuilder2 append(MyStringBuilder2 b) {
        if (length == 0 || firstC == null) { //special cases
            return b;
        }
        if (b.length == 0 || b.firstC == null) {
            return null;
        }
        appendBuilder(b, 0, lastC);

        return this;

    }

    private void appendBuilder(String s, int pos, CNode lastC) {
        CNode newNode;

        if (pos < s.length() - 1) {
            //currNode = newNode;
            newNode = new CNode(s.charAt(pos));
            newNode.next = null;
            lastC.next = newNode;
            lastC = newNode;
            length++;
            appendBuilder(s, pos + 1, lastC);

        } else if (pos == s.length() - 1) {
            newNode = new CNode(s.charAt(pos));
            newNode.next = null;
            lastC.next = newNode;
            this.lastC = newNode;
            length++;
        }

    }

    // Append String s to the end of the current MyStringBuilder2, and return
    // the current MyStringBuilder2.  Be careful for special cases!
    public MyStringBuilder2 append(String s) {
        if (length == 0 || firstC == null) {
            makeBuilder(s, 0);
            return this;
        }
        if (s.length() == 0) {
            return this;
        }

        appendBuilder(s, 0, lastC);
        return this;

    }

    private void appendBuilder(char[] c, int pos, CNode lastC) {
        CNode newNode;

        if (pos < c.length - 1) {
            //currNode = newNode;
            newNode = new CNode(c[pos]);
            newNode.next = null;
            lastC.next = newNode;
            lastC = newNode;
            length++;
            appendBuilder(c, pos + 1, lastC);
        } else if (pos == c.length - 1) {
            newNode = new CNode(c[pos]);
            newNode.next = null;
            lastC.next = newNode;
            this.lastC = newNode;
            length++;
        }

    }

    // Append char array c to the end of the current MyStringBuilder2, and
    // return the current MyStringBuilder2.  Be careful for special cases!
    public MyStringBuilder2 append(char[] c) {
        //if (length == 0 || firstC == null) {
        //    return s;
        //}
        if (c.length == 0) {
            return this;
        }
        CNode currNode;
        CNode newNode;
        appendBuilder(c, 0, lastC);
        return this;
    }

    // Append char c to the end of the current MyStringBuilder2, and
    // return the current MyStringBuilder2.  Be careful for special cases!
    public MyStringBuilder2 append(char c) {
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

    // Return the character at location "index" in the current MyStringBuilder2.
    // If index is invalid, throw an IndexOutOfBoundsException.
    public char charAt(int index) {
        if (index > this.length) {
            throw new IndexOutOfBoundsException("Illegal Index " + index);
        }
        String word = this.toString();
        char c = word.charAt(index);
        return c;
    }

    private void deleteBuilder(int start, int end, int pos, int status, CNode currNode, CNode startNode, CNode endNode) {
        if (status == 2) { //both startNode and endNode have been found
            if (start == 0) {
                firstC = endNode;
                return;
            }
            startNode.next = endNode; // link startNode to endNode, essentially deleting everything in between
        }
        if (status == 1) { // startNode has been located
            currNode = currNode.next; // loop through to find correct Node
            if (pos == end - 1) {
                endNode = currNode; //parameter Node for removing
                status = 2;
            }
            deleteBuilder(start, end, pos + 1, status, currNode, startNode, endNode);
        }
        if (status == 0) { // if neither startnode nor endnode have been located
            if (start == 0) { // if startNode = firstC, it's been found already
                status = 1;
            }
            currNode = currNode.next; // loop through to find correct Node
            if (pos == start - 2) {
                startNode = currNode; // parameter Node for removing
                status = 1;
            }
            deleteBuilder(start, end, pos + 1, status, currNode, startNode, endNode);
        }
    }

    // Delete the characters from index "start" to index "end" - 1 in the
    // current MyStringBuilder2, and return the current MyStringBuilder2.
    // If "start" is invalid or "end" <= "start" do nothing (just return the
    // MyStringBuilder2 as is).  If "end" is past the end of the MyStringBuilder2, 
    // only remove up until the end of the MyStringBuilder2. Be careful for 
    // special cases!
    public MyStringBuilder2 delete(int start, int end) {
        if (length == 0 || firstC == null) {
            return null;
        }
        if (start < 0 || end <= start) {
            return this;
        }
        if (end > length) {
            end = length;
        }
        CNode startNode = null;
        CNode endNode = null;
        CNode currNode = firstC;
        deleteBuilder(start, end, 0, 0, currNode, startNode, endNode);
        length = length - (end - start);
        return this;
    }

    private void deleteCharBuilder(int pos, int status, int index, CNode preRemove, CNode postRemove, CNode currNode) {
        if (status == 2) {
            if (index == 0) {
                firstC = postRemove;
                return;
            }
            preRemove.next = postRemove;
        }
        if (status == 1) {
            currNode = currNode.next;
            if (pos == index) {
                postRemove = currNode;
                status = 2;
            }
            deleteCharBuilder(pos + 1, status, index, preRemove, postRemove, currNode);
        }
        if (status == 0) {
            currNode = currNode.next;
            if (index == 0) {
                firstC = currNode;
                return;
            }
            if (pos == index - 2) {
                preRemove = currNode;
                status = 1;
            }
            deleteCharBuilder(pos + 1, status, index, preRemove, postRemove, currNode);
        }
    }

    // Delete the character at location "index" from the current
    // MyStringBuilder2 and return the current MyStringBuilder2.  If "index" is
    // invalid, do nothing (just return the MyStringBuilder2 as is).
    // Be careful for special cases!
    public MyStringBuilder2 deleteCharAt(int index) {
        if (length == 0 || firstC == null) {
            return null;
        }
        if (index < 0 || index > length) {
            return this;
        }
        CNode preRemove = null;
        CNode postRemove = null;
        CNode currNode = firstC;
        deleteCharBuilder(0, 0, index, preRemove, postRemove, currNode);
        length--;
        return this;
    }

    private int indexOfBuilder(int msbPos, int strPos, String str, CNode currNode, CNode tempNode) {
        if (currNode.data == str.charAt(strPos)) { // if the first char of str = found
            if (strPos == str.length() - 1) {// if we have reached the end of str, we are done; return index
                return msbPos - strPos;
            } else if (currNode.next != null) { //check the next node as long as we don't pass the end of str
                return indexOfBuilder(msbPos + 1, strPos + 1, str, currNode.next, tempNode);
            } else {  //if next node is null but we haven't finished the string, not found
                return -1;
            }
        } else if (tempNode.next != null) { // first character of str not found, check next node recursively   
            return indexOfBuilder(msbPos - strPos + 1, 0, str, tempNode.next, tempNode.next);
        } else { // reached end of MSB 
            return -1;
        }
    }

    public int indexOf(String str) {
        CNode currNode = firstC;
        CNode tempNode = firstC;
        if (str.length() != 0 && length() != 0) {
            return indexOfBuilder(0, 0, str, currNode, tempNode);
        } else {
            return -1;
        }
    }

    private void insertBuilder(int pos, int offset, int status, String str, CNode preInsertNode, CNode insertNode, CNode currNode) {
        if (status == 2) { // we know where both preInsertNode and insertNode are
            if (pos < str.length()) { //time to recursively insert each char
                if (offset == 0) { //special case:
                    CNode newNode = new CNode(str.charAt(pos));
                    firstC = newNode;
                    currNode = newNode;
                    offset++; // offset no longer matters, change so the if statement doesnt repeat
                    insertBuilder(pos + 1, offset, status, str, preInsertNode, insertNode, currNode);
                } else {
                    CNode newNode = new CNode(str.charAt(pos));
                    currNode.next = newNode;
                    currNode = newNode;
                    insertBuilder(pos + 1, offset, status, str, preInsertNode, insertNode, currNode);
                }
                length++;
            }
            if (pos == str.length()) {
                currNode.next = insertNode;
            }
        }
        if (status == 1) { // if preInsertNode has been found but not insertNode 
            currNode = currNode.next; //loop through
            if (pos == offset - 1) {
                insertNode = currNode;
                status = 2;
                insertBuilder(0, offset, status, str, preInsertNode, insertNode, preInsertNode);
            }
            insertBuilder(pos + 1, offset, status, str, preInsertNode, insertNode, preInsertNode);
        }
        if (status == 0) { // if neither preInsertNode nor insertNode has been found     
            currNode = currNode.next; // loop through to find preInsertNode
            if (offset == 0) {
                status = 2; // special case: if offset == 0, we know where both are
                pos = -1;
                insertNode = firstC;
                currNode = firstC;
            }
            if (pos == offset - 2) { // if offset !=0, proceed normally
                preInsertNode = currNode;
                status = 1;
            }
            insertBuilder(pos + 1, offset, status, str, preInsertNode, insertNode, currNode);
        }
    }

    // Insert String str into the current MyStringBuilder2 starting at index
    // "offset" and return the current MyStringBuilder2.  if "offset" == 
    // length, this is the same as append.  If "offset" is invalid
    // do nothing.
    public MyStringBuilder2 insert(int offset, String str) {
        if (offset == length) {
            this.append(str);
            return this;
        }
        CNode preRemoveNode = null;
        CNode removeNode = null;
        insertBuilder(0, offset, 0, str, preRemoveNode, removeNode, firstC);
        return this;
    }

    private void insertBuilder(int pos, int offset, int status, char c, CNode preInsertNode, CNode insertNode, CNode currNode) {
        if (status == 2) { // we know where both preInsertNode and insertNode are
            currNode = new CNode(c, insertNode);
            preInsertNode.next = currNode;
        }
        if (status == 1) { // if preInsertNode has been found but not insertNode 
            currNode = currNode.next; //loop through
            if (pos == offset - 1) {
                insertNode = currNode;
                status = 2;
                insertBuilder(0, offset, status, c, preInsertNode, insertNode, preInsertNode);
            }
            insertBuilder(pos + 1, offset, status, c, preInsertNode, insertNode, preInsertNode);
        }
        if (status == 0) { // if neither preInsertNode nor insertNode has been found     
            currNode = currNode.next; // loop through to find preInsertNode
            if (offset == 0) {
                status = 2; // special case: if offset == 0, we know where both are
                pos = -1;
                insertNode = firstC;
                currNode = firstC;
            }
            if (pos == offset - 2) { // if offset !=0, proceed normally
                preInsertNode = currNode;
                status = 1;
            }
            insertBuilder(pos + 1, offset, status, c, preInsertNode, insertNode, currNode);
        }
    }

    // Insert character c into the current MyStringBuilder2 at index
    // "offset" and return the current MyStringBuilder2.  If "offset" ==
    // length, this is the same as append.  If "offset" is invalid, 
    // do nothing.
    public MyStringBuilder2 insert(int offset, char c) {
        if (offset == length) {
            this.append(c);
            return this;
        }
        CNode preRemoveNode = null;
        CNode removeNode = null;
        insertBuilder(0, offset, 0, c, preRemoveNode, removeNode, firstC);
        return this;
    }

    private void insertBuilder(int pos, int offset, int status, char[] c, CNode preInsertNode, CNode insertNode, CNode currNode) {
        if (status == 2) { // we know where both preInsertNode and insertNode are
            if (pos < c.length) { //time to recursively insert each char
                if (offset == 0) { //special case:
                    CNode newNode = new CNode(c[pos]);
                    firstC = newNode;
                    currNode = newNode;
                    offset++; // offset no longer matters, change so the if statement doesnt repeat
                    insertBuilder(pos + 1, offset, status, c, preInsertNode, insertNode, currNode);
                } else {
                    CNode newNode = new CNode(c[pos]);
                    currNode.next = newNode;
                    currNode = newNode;
                    insertBuilder(pos + 1, offset, status, c, preInsertNode, insertNode, currNode);
                }
                length++;
            }
            if (pos == c.length) {
                currNode.next = insertNode;
            }
        }
        if (status == 1) { // if preInsertNode has been found but not insertNode 
            currNode = currNode.next; //loop through
            if (pos == offset - 1) {
                insertNode = currNode;
                status = 2;
                insertBuilder(0, offset, status, c, preInsertNode, insertNode, preInsertNode);
            }
            insertBuilder(pos + 1, offset, status, c, preInsertNode, insertNode, preInsertNode);
        }
        if (status == 0) { // if neither preInsertNode nor insertNode has been found     
            currNode = currNode.next; // loop through to find preInsertNode
            if (offset == 0) {
                status = 2; // special case: if offset == 0, we know where both are
                pos = -1;
                insertNode = firstC;
                currNode = firstC;
            }
            if (pos == offset - 2) { // if offset !=0, proceed normally
                preInsertNode = currNode;
                status = 1;
            }
            insertBuilder(pos + 1, offset, status, c, preInsertNode, insertNode, currNode);
        }
    }

    // Insert char array c into the current MyStringBuilder2 starting at index
    // index "offset" and return the current MyStringBuilder2.  If "offset" is
    // invalid, do nothing.
    public MyStringBuilder2 insert(int offset, char[] c) {
        if (offset == length) {
            this.append(c);
            return this;
        }
        CNode preRemoveNode = null;
        CNode removeNode = null;
        insertBuilder(0, offset, 0, c, preRemoveNode, removeNode, firstC);
        return this;
    }

    // Return the length of the current MyStringBuilder2
    public int length() {
        return length;
    }

    private void replaceBuilder(int pos, int status, int start, int end, String str, CNode currNode, CNode startNode, CNode endNode) {
        if (status == 2) { // if both have been found
            if (str.length() >= end - start) { // if the string length is > end-start, that means we can replace
                if (pos < (end - start)) { //Node data with one action UNTIL length must increase, in which
                    currNode = currNode.next; //case we create new Nodes
                    currNode.data = str.charAt(pos);
                    replaceBuilder(pos + 1, 2, start, end, str, currNode, startNode, endNode);
                } else if (pos < str.length()) { //begin adding new Nodes
                    CNode newNode = new CNode(str.charAt(pos));
                    currNode.next = newNode;
                    currNode = newNode;
                    length++;
                    replaceBuilder(pos + 1, 2, start, end, str, currNode, startNode, endNode);
                } else if (pos == str.length()) { //link last Node inserted with where it belongs in MSB
                    currNode.next = endNode;
                }
            }
        }
        if (status == 1) { // if startNode has been found but not endNode
            currNode = currNode.next; // loop through nodes
            if (pos == end - 1) { // endNode found
                endNode = currNode;
                status = 2; //recursive call won't return to this if statement
                replaceBuilder(0, status, start, end, str, startNode, startNode, endNode);
            } else { //else statement b/c if endnode has been found the call requires different arguments
                replaceBuilder(pos + 1, status, start, end, str, currNode, startNode, endNode);
            }
        }
        if (status == 0) { // if neither startNode nor endNode has been found
            currNode = currNode.next; //loop through Nodes
            if (pos == start - 2) { //startNode found
                startNode = currNode;
                status = 1; //recursive call won't return to this if statement
            }
            replaceBuilder(pos + 1, status, start, end, str, currNode, startNode, endNode);
        }
    }

    // Delete the substring from "start" to "end" - 1 in the current
    // MyStringBuilder2, then insert String "str" into the current
    // MyStringBuilder2 starting at index "start", then return the current
    // MyStringBuilder2.  If "start" is invalid or "end" <= "start", do nothing.
    // If "end" is past the end of the MyStringBuilder2, only delete until the
    // end of the MyStringBuilder2, then insert.  This method should be done
    // as efficiently as possible.  In particular, you may NOT simply call
    // the delete() method followed by the insert() method, since that will
    // require an extra traversal of the linked list.
    public MyStringBuilder2 replace(int start, int end, String str) {
        if (start > length || start < 0 || start >= end) {
            return this;
        }
        if (end > length) {
            end = length;
        }
        replaceBuilder(0, 0, start, end, str, firstC, null, null);
        return this;
    }

    private void reverseBuilder(int pos, MyStringBuilder2 clone, CNode prevNode, CNode nextNode, CNode currNode) {
        if (pos < length) {
            nextNode = currNode.next;
            currNode.next = prevNode;
            prevNode = currNode;
            currNode = nextNode;
            reverseBuilder(pos + 1, clone, prevNode, nextNode, currNode);
        } else if (pos == length) {
            this.lastC = clone.firstC;
            this.firstC = prevNode;
        }
    }

    // Reverse the characters in the current MyStringBuilder2 and then
    // return the current MyStringBuilder2.
    public MyStringBuilder2 reverse() {
        MyStringBuilder2 clone = this;
        reverseBuilder(0, clone, null, null, firstC);
        return this;
    }

    private void substringBuilder(int pos, int status, char[] charArray, int start, int end, CNode startNode, CNode currNode) {

        if (status == 1) {

            if (pos < end - start) {
                charArray[pos] = currNode.data;
                currNode = currNode.next;
                substringBuilder(pos + 1, status, charArray, start, end, startNode, currNode);
            }
        } else if (status == 0) {
            if (pos < length) {

                currNode = currNode.next;
                if (pos == start - 1) {
                    pos = -1;
                    startNode = currNode;
                    status = 1;
                }
                if (start == 0) {
                    startNode = firstC;
                    currNode = firstC;
                    pos = -1;
                    status = 1;
                }
                substringBuilder(pos + 1, status, charArray, start, end, startNode, currNode);
            }
        }

    }

    // Return as a String the substring of characters from index "start" to
    // index "end" - 1 within the current MyStringBuilder2
    public String substring(int start, int end) {
        char[] charArray = new char[end - start];
        substringBuilder(0, 0, charArray, start, end, firstC, firstC);
        return new String(charArray);

    }

    // Return the entire contents of the current MyStringBuilder2 as a String
    // Again note that the specified method is not actually recursive – rather it
    // calls a recursive method to do the work. Note that in this case we also
    // create a char array before the recursive call, then pass it as a
    // parameter, then construct and return a new string from the char array.
    // Carefully think about the parameters you will be passing to your recursive
    // methods. Through them you must be able to move through the list and
    // reduce the "problem size" with each call.
    public String toString() {
        char[] c = new char[length];
        getString(c, 0, firstC);
        return (new String(c));
    }

    // Here we need the char array to store the characters, the pos value to
// indicate the current index in the array and the curr node to access
// the data in the actual MyStringBuilder2. Note that these rec methods
// are private – the user of the class should not be able to call them.
    private void getString(char[] c, int pos, CNode curr) {
        if (curr != null) // Not at end of the list
        {
            c[pos] = curr.data; // put next char into array
            getString(c, pos + 1, curr.next); // recurse to next node and
        } // next pos in array
    }

    private int lastIndexOfBuilder(String str, CNode currNode, CNode tempNode, int index, int strPos, int msbPos, boolean status) {
        if (status == false) {
            if (currNode.next != null) {
                currNode = currNode.next;
                index = lastIndexOfBuilder(str, currNode, currNode, index, 0, msbPos + 1, status);
            }
        }
        if (tempNode.data == str.charAt(strPos) && index == -1) {
            if (tempNode.next != null) {
                if (lastC.data == (str.charAt(str.length() - 1)) && strPos == str.length() - 2) {
                    index = msbPos - strPos;
                    return index;
                } else if (strPos == str.length() - 1) {
                    index = msbPos - strPos;
                    return index;
                } else {
                    index = lastIndexOfBuilder(str, currNode, tempNode.next, -1, strPos + 1, msbPos + 1, true);
                }
            }
        }
        return index;
    }

    // Find and return the indexmatches a sequence of characters within the current
    // MyStringBuilder2.  If str  within the current MyStringBuilder2 where
    // String str LAST does not match any sequence of characters
    // within the current MyStringBuilder2, return -1.  Think carefully about
    // what you need to do for this method before implementing it.  For some
    // help with this see the Assignment 3 specifications.
    public int lastIndexOf(String str) {

        if (str.length() != 0 && length() != 0) {
            //    return lastIndexOfBuilder( -1, 0, 0, 0, str, firstC, firstC);
            return lastIndexOfBuilder(str, firstC, firstC, -1, 0, 0, false);
        } else {
            return -1;
        }
    }

    private boolean regMatchBuilder(String[] pats, MyStringBuilder2[] msbPats, int i, CNode currNode, CNode tempNode, int state) {
        //System.out.println(pats[i]);
        if (state == 1){
            if (currNode.next == null) { //1a
                return false;
            } else if (!pats[i].contains(String.valueOf(currNode.data))) { //1b (else if pats[i] does not contain currNode.data)              
                //System.out.println(currNode.data + " not found in " + pats[i]);
                regMatchBuilder(pats, msbPats, i, currNode.next, currNode.next, 0);
            } else { //1c, character at currNode found in pattern
               //System.out.println(currNode.data + " matches with " + pats[i]);
                regMatchBuilder(pats, msbPats, i, currNode.next, currNode.next, 1);
            }
        }
        if (state == 2){
            if (pats[i].contains(String.valueOf(currNode.data))){ //2a, after finding first char, another is found in msb
                regMatchBuilder(pats, msbPats, i, currNode, tempNode.next, 2);
            } else if (!pats[i].contains(String.valueOf(currNode.data)) && i == pats.length-1){ //2b, we're done
                return true;
            } else { //2c, next char didn't work but we're not at the end of pats yet
                regMatchBuilder(pats, msbPats, i + 1, tempNode, tempNode, 3);
            }
        }
        if (state == 3){
            if (pats[i].contains(String.valueOf(currNode.data))){  
                regMatchBuilder(pats, msbPats, i, currNode.next, currNode.next, 2);
            } else {
                return false;
            }
        } 
        return false;
    }

// Find and return an array of MyStringBuilder2, where each MyStringBuilder2
// in the return array corresponds to a part of the match of the array of
// patterns in the argument.  If the overall match does not succeed, return
// null.  For much more detail on the requirements of this method, see the
// Assignment 3 specifications.
    public MyStringBuilder2[] regMatch(String[] pats) {
        MyStringBuilder2[] msbPats = new MyStringBuilder2[pats.length];
        regMatchBuilder(pats, msbPats, 0, firstC, firstC, 1);
        return msbPats;

    }

    // You must use this inner class exactly as specified below.  Note that
    // since it is an inner class, the MyStringBuilder2 class MAY access the
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
