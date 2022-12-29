package observer;


import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

public class ConcreteMember implements Member, Cloneable {

    private String name;
    private UndoableStringBuilder str;

    final static Logger logger =
            LoggerFactory.getLogger(ConcreteMember.class);

    /**
     * Constructor
     */
    public ConcreteMember(String name){
        this.name=name;
        this.str = null;
    }

    /** Update a member
     * @param usb - UndoableStringBuilder member(observer)
     */
    @Override
    public void update(UndoableStringBuilder usb) {
        this.str = usb;
    }

    /**
     * Shallow copy
     */
    @Override
    public UndoableStringBuilder clone() throws CloneNotSupportedException {
        return (UndoableStringBuilder) super.clone();
    }

    /**
     * toString function
     * @return Data of a member
     */
    public String toString() {
        return "Name: " + this.name + ", " + "String: " + this.str;
    }
}
