package observer;

/**
 * ConcreteMember class implements Member Interface. This class describes the client - an object gets notification about changes
 * The class contains shallow copy of UndoableStringBuilder.
 * if a registered client decides to unregister - it no longer gets notifications but still able to get the up-to-date usb
 */
public class ConcreteMember implements Member {
    private UndoableStringBuilder usb;

    public void update(UndoableStringBuilder usb) {
        this.usb = usb;
    }

    public UndoableStringBuilder getUsb() {
        return usb;
    }
}