import java.util.ArrayList;
import java.util.List;

public class GroupAdmin implements Sender {
    private List<Member> clients = new ArrayList<>();
    private Member member;
    private UndoableStringBuilder usb = new UndoableStringBuilder();


    public void register(Member obj) {
        clients.add(obj);
    }

    public void unregister(Member obj) {
        clients.remove(obj);
    }

    //Inserts the string into this character sequence.
    public void insert(int offset, String obj) {
        this.usb.insert(offset, obj);
        updateAll();
    }

    // Appends the specified string to this character sequence.
    public void append(String obj) {
        this.usb.append(obj);
        updateAll();
    }

    // Removes the characters in a substring of this sequence.
    public void delete(int start, int end) {
        this.usb.delete(start,end);
        updateAll();
    }

    // Erases the last change done to the document, reverting
    // it to an older state.
    public void undo() {
        this.usb.undo();
        updateAll();
    }

    // Updates all registered members
    public void updateAll(){
        for (Member member: clients) {
            member.update(this.usb);
        }
    }
}
