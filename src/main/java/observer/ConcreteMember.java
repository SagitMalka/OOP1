package observer;

public class ConcreteMember implements Member {
    private UndoableStringBuilder usb;

    public void subscribe(){

    }
    public void update(UndoableStringBuilder usb) {
        this.usb = usb;
    }
}