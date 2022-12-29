package observer;

import java.util.ArrayList;
import java.util.List;

public class GroupAdmin implements Sender {
    private List<Member> clients = new ArrayList<>();
    private UndoableStringBuilder usb = new UndoableStringBuilder();

    public void register(Member member) {
        clients.add(member);
    }

    public void unregister(Member member) {
        clients.remove(member);
    }

    //Inserts the string into this character sequence.
    public void insert(int offset, String str) {
        this.usb.insert(offset, str);
        updateAll();
    }

    // Appends the specified string to this character sequence.
    public void append(String str) {
        this.usb.append(str);
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
