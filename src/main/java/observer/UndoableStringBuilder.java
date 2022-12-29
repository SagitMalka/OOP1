package observer;

import java.util.Stack;
import java.lang.StringBuilder;

/*
Use the class you've implemented in previous assignment
 */
public class UndoableStringBuilder {
    /**
     * this class delegate all methods of StringBuilder class to support undo() method.
     * in order to do that, this class save current string in a stack, and every version
     * of it at time. undo method would pop the last version of string after making a change.
     * the changes undo supports are append, delete, insert, replace and reverse.
     *
     * @author Sagit Malka, Stav Avitan
     * @version 2
     */


    Stack<String> stack = new Stack<>();
    StringBuilder sb = new StringBuilder();

    /**
     * Appends the specified string to this character sequence
     *
     * @param str a string to append
     * @return a reference to this object
     * @throws RuntimeException if string is null
     */
    public UndoableStringBuilder append(String str) {
        if (str == null) {
            throw new RuntimeException("string is null");
        }
        stack.push(sb.toString());
        sb.append(str);
        return this;
    }

    /**
     * Removes the characters in a substring of this sequence.
     *
     * @param start The beginning index, inclusive
     * @param end   The ending index, exclusive.
     * @return This object
     * @throws StringIndexOutOfBoundsException if start is negative, greater than length(), or greater than end
     */
    public UndoableStringBuilder delete(int start, int end) {
        checkRange(start, end, sb);
        stack.push(sb.toString());
        sb.delete(start, end);
        return this;
    }

    /**
     * Inserts the string into this character sequence.
     *
     * @param offset the offset
     * @param str    string to inset
     * @return a reference to this object.
     * @throws RuntimeException argument must be greater than or equal to 0, and less than or equal to the length of this sequence
     */
    public UndoableStringBuilder insert(int offset, String str) {
        checkOffset(offset, sb);
        stack.push(sb.toString());
        sb.insert(offset, str);
        return this;
    }

    /**
     * replace substring in range [start, end]
     *
     * @param start first index
     * @param end   last index
     * @param str   new value to replace
     * @return this object
     */
    public UndoableStringBuilder replace(int start, int end, String str) {
        checkRange(start, end, sb);
        stack.push(sb.toString());
        sb.replace(start, end, str);
        return this;
    }

    /**
     * revers the string
     *
     * @return this object
     * @throws RuntimeException if no available string to revers
     */
    public UndoableStringBuilder reverse() {
        if (stack.isEmpty()) {
            throw new RuntimeException("no string to reverse");
        }
        stack.push(sb.toString());
        sb.reverse();
        return this;
    }

    /**
     * @return a string consisting of exactly this sequence of characters
     */
    @Override
    public String toString() {
        return sb.toString();
    }

    /**
     * this method canceling the last change that has been made
     *
     * @return the previous string
     */
    public void undo() {
        if (stack.isEmpty()) {
            throw new RuntimeException("you have used all available undo functions");
        }
        String tmp = stack.pop();
        sb = new StringBuilder(tmp);
    }

    /**
     * check if start is negative, greater than length(), or greater than end.
     *
     * @param start first index
     * @param end   last index
     * @param s     string value
     */


    private static void checkRange(int start, int end, StringBuilder s) {
        int len = s.length();
        if (start < 0 || start > end || end > len) {
            throw new StringIndexOutOfBoundsException(
                    "start " + start + ", end " + end + ", length " + len);
        }
    }

    /**
     * check if offset is negative or greater than the length of current string builder
     *
     * @param offset the offset
     * @param sb     current string
     * @throws StringIndexOutOfBoundsException if offset invalid
     */
    private static void checkOffset(int offset, StringBuilder sb) {
        int length = sb.length();
        if (offset < 0 || offset > length) {
            throw new StringIndexOutOfBoundsException("offset " + offset +
                    ", length " + length);
        }
    }
}

