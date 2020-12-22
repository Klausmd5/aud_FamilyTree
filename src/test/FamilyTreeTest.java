package test;

import net.htlgrieskirchen.aud3.familytree.FamilyTree;
import net.htlgrieskirchen.aud3.familytree.Member;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class FamilyTreeTest {

    public static FamilyTree familyTree;

    @BeforeAll
    static void setUp() {
        familyTree = new FamilyTree();

        Member root = new Member("Anel");
        root.setGender(Member.Gender.MALE);
        familyTree.add(root);

        Member lukas = new Member("Lukas");
        lukas.addParent(root);
        familyTree.add(lukas);

        Member manuel = new Member("Manuel");
        manuel.addParent(root);
        manuel.setGender(Member.Gender.FEMALE);
        familyTree.add(manuel);

        Member fabian = new Member("Fabian");
        fabian.addParent(manuel);
        familyTree.add(fabian);

        Member markus = new Member("Markus");
        markus.addParent(manuel);
        markus.addParent(lukas);
        markus.setGender(Member.Gender.MALE);
        familyTree.add(markus);

        Member florian = new Member("Florian");
        florian.addParent(fabian);
        familyTree.add(florian);

        Member andreas = new Member("Andreas");
        andreas.addParent(fabian);
        familyTree.add(andreas);
    }

    @Test
    void add() {
        System.out.println("add");
        FamilyTree ft  = new FamilyTree();
        ft.add(new Member("Andreas"));
        assert ft.getAllMembers().size() == 1;
    }

    @Test
    void add2() {
        System.out.println("add2");
        FamilyTree ft  = new FamilyTree();
        ft.add(null);
        assert ft.getAllMembers() == null;
    }

    @Test
    void getAllMembers() {
        System.out.println("getAllMembers");
        assert familyTree.getAllMembers().size() > 1;
    }

    @Test
    void getAllMembers2() {
        System.out.println("getAllMembers2");
        FamilyTree ft  = new FamilyTree();
        assert ft.getAllMembers() == null;
    }

    @Test
    void getMemberByName() {
        System.out.println("getMemberByName");
        assert familyTree.getMemberByName("Andreas").getName().equals("Andreas");
    }

    @Test
    void getAllChildren() {
        System.out.println("getAllChildren");
        assert familyTree.getAllChildren(familyTree.getMemberByName("Fabian")).size() == 2;
    }

    @Test
    void getAllChildren2() {
        System.out.println("getAllChildren2");
        assert familyTree.getAllChildren(familyTree.getMemberByName("Andreas")).size() == 0;
    }

    @Test
    void getAllChildren3() {
        System.out.println("getAllChildren3");
        FamilyTree ft  = new FamilyTree();
        assert ft.getAllChildren(familyTree.getMemberByName("Andreas")).size() == 0;
    }

    @Test
    void getAllGrandparents() {
        System.out.println("getAllGrandparents");
        assert familyTree.getAllGrandparents().size() == 2;
    }

    @Test
    void getAllGrandparents2() {
        System.out.println("getAllGrandparents2");
        FamilyTree ft  = new FamilyTree();
        try {
            ft.getAllGrandparents().get(0);
        } catch (NullPointerException e ) {
            assert true;
            return;
        }
        assert false;
    }

    @Test
    void getAllGrandchildren() {
        System.out.println("getAllGrandchildren");
        assert familyTree.getAllChildren(familyTree.getMemberByName("Manuel")).size() == 4;
    }

    @Test
    void getAllGrandchildren2() {
        System.out.println("getAllGrandchildren2");
        assert familyTree.getAllChildren(familyTree.getMemberByName("Andreas")).size() == 0;
    }

    @Test
    void getAllGrandchildren3() {
        System.out.println("getAllGrandchildren3");
        FamilyTree ft  = new FamilyTree();
        assert ft.getAllChildren(familyTree.getMemberByName("Andreas")).size() == 0;
    }

    @Test
    void getAllSiblings() {
        System.out.println("getAllSiblings");
        assert familyTree.getAllSiblings().size() == 6;
    }

    @Test
    void getAllSiblings2() {
        System.out.println("getAllSiblings2");
        FamilyTree ft  = new FamilyTree();
        try {
            ft.getAllSiblings().get(0);
        } catch (NullPointerException e ) {
            assert true;
            return;
        }
        assert false;
    }

    @Test
    void getAllGrandmas() {
        System.out.println("getAllGrandmas");
        assert familyTree.getAllGrandmas().size() == 1;
    }

    @Test
    void getAllGrandmas2() {
        System.out.println("getAllGrandmas2");
        assert familyTree.getAllGrandmas().get(0).getName().equals("Manuel");
    }

    @Test
    void isParentOf() {
        System.out.println("isParentOf");
        assert familyTree.isParentOf("Anel", "Lukas") == true;
    }

    @Test
    void isParentOf2() {
        System.out.println("isParentOf2");
        assert familyTree.isParentOf("Anel", "Andreas") == false;
    }

    @Test
    void isGrandparentOf() {
        System.out.println("isGrandparentOf");
        assert familyTree.isGrandparentOf("Anel", "Fabian") == true;
    }

    @Test
    void isGrandparentOf2() {
        System.out.println("isGrandparentOf2");
        assert familyTree.isGrandparentOf("Anel", "Lukas") == false;
    }
}