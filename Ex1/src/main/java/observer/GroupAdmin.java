package observer;

import java.util.ArrayList;
import java.util.List;

public class GroupAdmin implements Sender {

    private List<Member> memberList=new ArrayList<>();
    private UndoableStringBuilder usb= new UndoableStringBuilder();


    /** Register a member
     * @param obj - member(observer)
     */
    @Override
    public void register(Member obj) {
        this.memberList.add(obj);
    }

    /** Unregister a member
     * @param obj - member(observer)
     */
    @Override
    public void unregister(Member obj) {
        if(memberList.contains(obj)) memberList.remove(obj);
    }

    /**
     * Notify members (observers) a change was made
     */
    public void notifyMembers(){
        if(!memberList.isEmpty()) {
            for (Member obj : memberList) {
                obj.update(this.usb);
            }
        }
    }

    /** Inserts the string into this character sequence and then notify members a change was made.
     * @param offset - integer we'll start inserting at
     * @param obj- string we'll be adding
     */
    @Override
    public void insert(int offset, String obj) {
        this.usb.insert(offset, obj);
        notifyMembers();
    }

    /** Appends the specified string to this character sequence and then notify members a change was made.
     * @param obj - string
     */
    @Override
    public void append(String obj) {
        this.usb.append(obj);
        notifyMembers();
    }

    /** Removes the characters in a substring of this sequence and then notify members a change was made.
     * @param start - int (index of first character that will be removed)
     * @param end - int (index of last character that will be removed)
     */
    @Override
    public void delete(int start, int end) {
        this.usb.delete(start,end);
        notifyMembers();
    }

    /**
     * Erases the last change done to the document, reverting it to an older state and then notify members.
     */
    @Override
    public void undo() {
        this.usb.undo();
        notifyMembers();
    }

    /**
     * toString function
     * @return Data of a member
     */
    public String toString() {
        return "Member are " + memberList.toString();
    }


}
