package observer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static observer.JvmUtilities.objectTotalSize;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


class GroupAdminTest {
    GroupAdmin adminous;
    List<Member> group1, group2;
    String msg = "hello to the queen";

    @BeforeEach
    void setUp() {
        adminous = new GroupAdmin();
        group1 = new ArrayList<>();
        group2 = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            group1.add(new ConcreteMember());
            if (i % 2 == 0)
                group2.add(new ConcreteMember());
        }
    }

    @Test
    void getMembers() {
        for (Member member : group1) {
            adminous.register(member);
        }
        Set<Member> clients = adminous.getClients();
        objectTotalSize(adminous);

        /** Check clients equal group1 */
        assertTrue(clients.containsAll(group1) && clients.size() == group1.size());

    }

    @Test
    void register() {
        ConcreteMember member = (ConcreteMember) group1.get(0);
        adminous.register(member);
        assertSame(member, adminous.getClients().iterator().next());

        adminous.append(msg);
        UndoableStringBuilder usb = member.getUsb();
        assertEquals(usb.toString(), adminous.getUsb().toString());

    }

    @Test
    void unregister() {
        for (Member member : group1) {
            adminous.register(member);
        }
        adminous.append(msg);
        adminous.unregister(group1.get(2));
        adminous.undo();

    }
    @Test
    void unregisterUnknown(){
        ConcreteMember member2 = (ConcreteMember) group2.get(0);
        adminous.unregister(member2);
        assertNull(member2.getUsb());
    }

    @Test
    void insert() {
        fillUSB();
        adminous.insert(2, "!@");

        StringBuilder sb = new StringBuilder(msg);
        sb.insert(2, "!@");

        assertEquals(sb.toString(), adminous.getUsb().toString());
    }

    @Test
    void append() {
        fillUSB();
        adminous.append("1");

        StringBuilder sb = new StringBuilder(msg);
        sb.append("1");

        assertEquals(sb.toString(), adminous.getUsb().toString());
    }

    @Test
    void delete() {
        fillUSB();
        adminous.delete(0,2);

        StringBuilder sb = new StringBuilder(msg);
        sb.delete(0,2);
        assertEquals(sb.toString() , adminous.getUsb().toString());
    }

    @Test
    void undo() {
        fillUSB();
        adminous.append("1");
        adminous.undo();
        assertEquals(adminous.getUsb().toString(), msg);
    }

    void fillUSB(){
        adminous.append(msg);
    }

}