package observer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConcreteMemberTest {
    GroupAdmin adminous;
    List<Member> group1, group2;
    String msg = "hello to the queen";

    @BeforeEach
    void setUp() {
        System.out.println("pre-test create");
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
    void checkUpdates() {
        for (Member m : group1) {
            adminous.register(m);
        }
        adminous.append("!");
        for (Member m : adminous.getClients()) {
            assertEquals(((ConcreteMember) m).getUsb(), adminous.getUsb());
        }
    }
}