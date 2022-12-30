package observer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupAdmin implements Sender {

    private Set<Member> clients = new HashSet<>();
    private UndoableStringBuilder usb = new UndoableStringBuilder();

    public Set<Member> getClients() {
        return clients;
    }

    public void register(Member member) {
        clients.add(member);
    }

    public void unregister(Member member) {
        clients.remove(member);
    }

    //Inserts the string into this character sequence.
    public void insert(int offset, String str) {
        usb.insert(offset, str);
        updateAll();
    }

    // Appends the specified string to this character sequence.
    public void append(String str) {
        usb.append(str);
        updateAll();
    }

    // Removes the characters in a substring of this sequence.
    public void delete(int start, int end) {
        usb.delete(start, end);
        updateAll();
    }

    // Erases the last change done to the document, reverting
    // it to an older state.
    public void undo() {
        usb.undo();
        updateAll();
    }

    // Updates all registered members
    public void updateAll() {
        for (Member member : clients) {
            member.update(this.usb);
        }
    }

    public UndoableStringBuilder getUsb() {
        return usb;
    }
}
