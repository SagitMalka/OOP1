package observer;

public class ConcreteMember implements Member {
    private UndoableStringBuilder usb;

    public void update(UndoableStringBuilder usb) {
        this.usb = usb;
    }
}