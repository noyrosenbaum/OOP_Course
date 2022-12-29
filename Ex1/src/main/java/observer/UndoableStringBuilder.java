package observer;

import java.util.Stack;

/*
Use the class you've implemented in previous assignment
 */
public class UndoableStringBuilder {
    private StringBuilder stringBuilder;
    private final Stack<StringBuilder> undoActions;


    public UndoableStringBuilder() {
        this.stringBuilder = new StringBuilder();
        this.undoActions = new Stack<>();
    }

    public UndoableStringBuilder(int capacity) {
        this.stringBuilder = new StringBuilder(capacity);
        this.undoActions = new Stack<>();
    }

    public UndoableStringBuilder(String str) {
        this.stringBuilder = new StringBuilder(str);
        this.undoActions = new Stack<>();
    }

    public UndoableStringBuilder(CharSequence seq) {
        this.stringBuilder = new StringBuilder(seq);
        this.undoActions = new Stack<>();
    }

    /**
     *
     * @param str - Appended String
     * @return Character sequence with given specified string
     */
    public UndoableStringBuilder append(String str) {
        StringBuilder prev = new StringBuilder(this.stringBuilder);
        undoActions.push(prev);
        this.stringBuilder.append(str);
        return this;
    }

    /**
     *
     * @param start - start of substring
     * @param end - end of substring
     * @return A sequence after removing the characters of a substring
     *
     */
    public UndoableStringBuilder delete(int start, int end) {
        this.stringBuilder.delete(start, end);
        this.undoActions.push(this.stringBuilder);

        return this;
    }

    /**
     *
     * @param offset - Number of character sequence
     * @param str - Inserted String
     * @return Character sequence with a String inserted
     */
    public UndoableStringBuilder insert(int offset, String str) {
        this.stringBuilder.insert(offset, str);
        this.undoActions.push(this.stringBuilder);
        return this;
    }

    /**
     *
     * @param start - start of replacement
     * @param end - end of replacement
     * @param str - Replaces String
     * @return Replaced String
     */
    public UndoableStringBuilder replace(int start, int end, String str) {
        StringBuilder prev = new StringBuilder(this.stringBuilder);
        try {
            this.stringBuilder.replace(start, end, str);
            undoActions.push(prev);
        } catch (IndexOutOfBoundsException err) {
            System.err.println("the index can't a negative number!!");
        }catch(NullPointerException err){
            System.err.println("Null can't replace content!");
            err.printStackTrace();
        }
        return this;
    }

    /**
     *
     * @return Reversed sequence
     */

    public UndoableStringBuilder reverse() {
        StringBuilder rev = new StringBuilder(this.stringBuilder);
        this.stringBuilder.reverse();
        undoActions.push(rev);

        return this;
    }

    /**
     * "Cancels" an action that wad made on a StringBuilder object.
     */

    public void undo() {
        if (!undoActions.isEmpty()) {
            this.stringBuilder = this.undoActions.pop();
        }
    }

    /**
     *
     * @return String of StringBuilder object.
     */
    @Override
    public String toString(){

        return this.stringBuilder.toString();

    }
}
