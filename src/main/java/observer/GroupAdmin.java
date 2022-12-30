package observer;

import java.util.HashSet;
import java.util.Set;

/**
 *  Observer class implements Sender Interface.
 *  Contains the UndoableStringBuilder states and client list that gets updates of any changes has been made
 */
public class GroupAdmin implements Sender {

    private Set<Member> clients = new HashSet<>();
    private UndoableStringBuilder usb = new UndoableStringBuilder();

    public Set<Member> getClients() {
        return new HashSet<>(clients);
    }

    /**
     * adding a member to get notifications
     * @param member
     */
    public void register(Member member) {
        clients.add(member);
    }

    /**
     * cancel notifications updates
     * @param member
     */
    public void unregister(Member member) {
        clients.remove(member);
    }

    /**
     * Inserts a string into this character sequence by call insert() method of UndoableStringBuilder class
     * notify all members registers
     * @param offset start point at current sequence
     * @param str substring to insert
     */
    public void insert(int offset, String str) {
        usb.insert(offset, str);
        updateAll();
    }

    /**
     * Appends the specified string to this character sequence by call append() method of UndoableStringBuilder class
     * notify all members registers
     * @param str the specified string
     */
    public void append(String str) {
        usb.append(str);
        updateAll();
    }

    /**
     * Removes the characters in a substring of this sequence by call delete() method of UndoableStringBuilder class
     * notify all members registers
     * @param start starting point
     * @param end end point
     */
    public void delete(int start, int end) {
        usb.delete(start, end);
        updateAll();
    }

    /**
     * Erases the last change done to the document, similar to Ctrl + z. made call undo() method of UndoableStringBuilder class
     * notify all members registers
     */
    public void undo() {
        usb.undo();
        updateAll();
    }

    /**
     * Updates all registered members about change has made
     */
    public void updateAll() {
        for (Member member : clients) {
            member.update(this.usb);
        }
    }

    /**
     * get current usb
     * @return usb
     */
    public UndoableStringBuilder getUsb() {
        return usb;
    }

}
