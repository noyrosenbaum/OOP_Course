import observer.ConcreteMember;
import observer.JvmUtilities;
import observer.GroupAdmin;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;


public class Tests {
    public static final Logger logger = LoggerFactory.getLogger(Tests.class);
    // stub method to check external dependencies compatibility

    @Test
    public void GroupAdmintest() {
        GroupAdmin admin = new GroupAdmin();
        ConcreteMember member1 = new ConcreteMember("Noy");
        admin.register(member1);
        //Insert test
        System.out.println("Insert test");
        admin.insert(0, "test1");
        assertEquals("Member are [Name: Noy, String: test1]", admin.toString());
        System.out.println("Passed");
        //Append test
        System.out.println("Append test");
        admin.append("addition");
        assertEquals("Member are [Name: Noy, String: test1addition]", admin.toString());
        System.out.println("Passed");
        //Undo test
        System.out.println("Undo test");
        admin.undo();
        assertEquals("Member are [Name: Noy, String: test1]", admin.toString());
        System.out.println("Passed");
        //Delete test
        System.out.println("Delete test");
        admin.delete(0, 5);
        assertEquals("Member are [Name: Noy, String: ]", admin.toString());
        System.out.println("Passed");
    }

    @Test
    public void JvmUtiltest(){
        GroupAdmin admin = new GroupAdmin();
        ConcreteMember member1 = new ConcreteMember("Noy");
        ConcreteMember member2 = new ConcreteMember("Maya");
        System.out.println("Created 2 members and one admin");
        System.out.println("Get footprint of initialized objects");
        //get the size of entire references
        System.out.println("admin:");
        logger.info(()->JvmUtilities.objectFootprint(admin));
        System.out.println("\n");
        System.out.println("member1:");
        logger.info(()->JvmUtilities.objectFootprint(member1));
        System.out.println("\n");
        System.out.println("member2:");
        logger.info(()->JvmUtilities.objectFootprint(member2));
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("Get totalsize of initialized objects");
        //get the size of the whole object
        System.out.println("admin:");
        logger.info(()->JvmUtilities.objectTotalSize(admin));
        System.out.println("\n");
        System.out.println("member1:");
        logger.info(()->JvmUtilities.objectTotalSize(member1));
        System.out.println("\n");
        System.out.println("member2:");
        logger.info(()->JvmUtilities.objectTotalSize(member2));
        System.out.println("\n");

        System.out.println("Register some members");
        //register members
        admin.register(member1);
        admin.register(member2);

        System.out.println("Get admin memory data's changes after apply members");
        //get changes in admin object after we apply 2 members
        logger.info(()->JvmUtilities.objectFootprint(admin));
        logger.info(()->JvmUtilities.objectTotalSize(admin));
        System.out.println("\n");

        System.out.println("Unregister members");
        //unregister members from admin
        admin.unregister(member1);
        admin.unregister(member2);

        System.out.println("Get admin memory data's changes after remove members");
        //get changes in admin object after remove members
        logger.info(()->JvmUtilities.objectFootprint(admin));
        logger.info(()->JvmUtilities.objectTotalSize(admin));
        System.out.println("\n");

        System.out.println("Get JVM process");
        //present JVM process
        logger.info(()->JvmUtilities.jvmInfo());

    }
}
