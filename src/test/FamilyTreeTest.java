package test;

import net.htlgrieskirchen.aud3.familytree.FamilyTree;
import net.htlgrieskirchen.aud3.familytree.Member;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class FamilyTreeTest {

    private static FamilyTree familyTree;

    @BeforeAll
    static void setUp() {
        familyTree = new FamilyTree();

        Member emz = new Member("Emz");
        emz.setFemale();
        familyTree.add(emz);
        Member frank = new Member("Frank");
        frank.setMale();
        familyTree.add(frank);
        Member tara = new Member("Tara");
        tara.setFemale();
        familyTree.add(tara);
        Member mortis = new Member("Mortis");
        mortis.setMale();
        familyTree.add(mortis);
        Member sandy = new Member("Sandy");
        sandy.setMale();
        familyTree.add(sandy);
        Member poco = new Member("Poco");
        poco.setOther();
        familyTree.add(poco);
        Member gene = new Member("Gene");
        gene.setMale();
        familyTree.add(gene);

        emz.addPartner(frank);
        emz.addChild(tara);

        frank.addPartner(emz);
        frank.addChild(tara);

        mortis.addPartner(tara);
        mortis.addChild(sandy);
        mortis.addChild(poco);
        mortis.addChild(gene);

        tara.addParent(emz);
        tara.addParent(frank);
        tara.addPartner(mortis);
        tara.addChild(sandy);
        tara.addChild(poco);
        tara.addChild(gene);

        sandy.addParent(mortis);
        sandy.addParent(tara);

        poco.addParent(mortis);
        poco.addParent(tara);

        gene.addParent(mortis);
        gene.addParent(tara);

    }

    @Test
    void add() {
        System.out.println("add");
        FamilyTree ft  = new FamilyTree();
        ft.add(new Member("Andreas"));
        assert ft.getAllMembers().size() != 0;
    }

    @Test
    void add2() {
        System.out.println("add2");
        FamilyTree ft  = new FamilyTree();
        ft.add(null);
        ArrayList<Member> ar = new ArrayList<>();
        assert ft.getAllMembers().equals(ar);
    }

    @Test
    void getAllMembers() {
        System.out.println("getAllMembers");
        assert familyTree.getAllMembers().size() > 1;
    }

    @Test
    void getAllMembers2() {
        System.out.println("getAllMembers2");
        FamilyTree familyTree = new FamilyTree();
        assert familyTree.getAllMembers().equals(new ArrayList<Member>());
    }

    @Test
    void getMemberByName() {
        System.out.println("getMemberByName");
        assert familyTree.getMemberByName("Emz").getName().equals("Emz");
    }

    @Test
    void getAllChildren() {
        System.out.println("getAllChildren");
        assert familyTree.getAllChildren(familyTree.getMemberByName("Emz")).size() != 0;
    }

    @Test
    void getAllChildren2() {
        System.out.println("getAllChildren2");
        try {
            familyTree.getAllChildren(familyTree.getMemberByName("Andreas"));
        } catch (NullPointerException e) {
            assert true;
        }
    }

    @Test
    void getAllGrandparents() {
        System.out.println("getAllGrandparents");
        assert familyTree.getAllGrandparents().size() == 2;
    }

    @Test
    void getAllGrandparents2() {
        System.out.println("getAllGrandparents2");
        FamilyTree familyTree = new FamilyTree();
        try {
            familyTree.getAllGrandparents().get(0);
        } catch (IndexOutOfBoundsException e ) {
            assert true;
        }
    }

    @Test
    void getAllGrandchildren() {
        System.out.println("getAllGrandchildren");
        assert familyTree.getAllChildren(familyTree.getMemberByName("Emz")).size() == 4;
    }

    @Test
    void getAllGrandchildren2() {
        System.out.println("getAllGrandchildren2");
        try {
            assert familyTree.getAllChildren(familyTree.getMemberByName("Andreas")).size() == 0;
        } catch (NullPointerException e) {
            assert true;
        }
    }

    @Test
    void getAllSiblings() {
        System.out.println("getAllSiblings");
        assert familyTree.getAllSiblings().size() == 3;
    }

    @Test
    void getAllSiblings2() {
        System.out.println("getAllSiblings2");
        FamilyTree familyTree  = new FamilyTree();
        try {
            familyTree.getAllSiblings().get(0);
        } catch (NullPointerException e ) {
            assert true;
        }
    }

    @Test
    void getAllGrandmas() {
        System.out.println("getAllGrandmas");
        assert familyTree.getAllGrandmas().size() == 1;
    }

    @Test
    void getAllGrandmas2() {
        System.out.println("getAllGrandmas2");
        assert familyTree.getAllGrandmas().get(0).getName().equals("Emz");
    }

    @Test
    void isParentOf() {
        System.out.println("isParentOf");
        assert familyTree.isParentOf("Emz", "Tara");
    }

    @Test
    void isParentOf2() {
        System.out.println("isParentOf2");
        assert !familyTree.isParentOf("Emz", "Andreas");
    }

    @Test
    void isGrandparentOf() {
        System.out.println("isGrandparentOf");
        assert familyTree.isGrandparentOf("Emz", "Sandy");
    }

    @Test
    void isGrandparentOf2() {
        System.out.println("isGrandparentOf2");
        assert familyTree.isGrandparentOf("Emz", "Poco");
    }
}