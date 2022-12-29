package observer;

public class ConcreteMember implements Member {
    private Member member;
    private UndoableStringBuilder usb;

    public ConcreteMember(Member member) {
        this.member = member;

    }


    public void update(UndoableStringBuilder usb) {

    }
}
