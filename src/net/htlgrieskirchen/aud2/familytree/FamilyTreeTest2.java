package net.htlgrieskirchen.aud2.familytree;

import net.htlgrieskirchen.aud3.familytree.FamilyTree;
import net.htlgrieskirchen.aud3.familytree.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class FamilyTreeTest {

    FamilyTree ft;

    @BeforeEach
    void setUp() {

        ft = new FamilyTree();

        Member emz = new Member("Emz");
        emz.setFemale();
        ft.add(emz);
        Member frank = new Member("Frank");
        frank.setMale();
        ft.add(frank);
        Member tara = new Member("Tara");
        tara.setFemale();
        ft.add(tara);
        Member mortis = new Member("Mortis");
        mortis.setMale();
        ft.add(mortis);
        Member sandy = new Member("Sandy");
        sandy.setMale();
        ft.add(sandy);
        Member poco = new Member("Poco");
        poco.setOther();
        ft.add(poco);
        Member gene = new Member("Gene");
        gene.setMale();
        ft.add(gene);

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
    void getAllGrandparents() {

        List<Member> result = ft.getAllGrandparents();

        assertTrue(result.size() == 2);

        assertTrue(findOnce(new String[]{"Emz", "Frank"}, result));

    }

    @Test
    void getAllGrandchildren() {

        List<Member> result = ft.getAllGrandchildren();

        assertTrue(result.size() == 3);

        assertTrue(findOnce(new String[]{"Sandy", "Poco", "Gene"}, result));

    }

    @Test
    void getAllSiblings() {

        Map<Member, List<Member>> result = ft.getAllSiblings();
        System.out.println(result.size());

        result.forEach((member, members) -> {
            System.out.println(member.getName());
            System.out.println("sibs:");
            members.forEach(member1 -> System.out.println("sib "+member1.getName()));
        });

        assertTrue(result.keySet().size() == 3);

        assertTrue(findOnce(new String[]{"Sandy", "Poco", "Gene"}, result.keySet()));

        for (Map.Entry<Member, List<Member>> entry : result.entrySet()) {
            if (entry.getKey().getName().equals("Sandy")) {

                List<Member> siblings = entry.getValue();

                assertTrue(siblings.size() == 2);

                assertTrue(findOnce(new String[]{"Poco", "Gene"}, siblings));
            }
            if (entry.getKey().getName().equals("Poco")) {

                List<Member> siblings = entry.getValue();

                assertTrue(siblings.size() == 2);

                assertTrue(findOnce(new String[]{"Sandy", "Gene"}, siblings));
            }
            if (entry.getKey().getName().equals("Gene")) {

                List<Member> siblings = entry.getValue();

                assertTrue(siblings.size() == 2);

                assertTrue(findOnce(new String[]{"Sandy", "Poco"}, siblings));
            }
        }

    }

    @Test
    void getAllGrandmas() {

        List<Member> result = ft.getAllGrandmas();

        assertTrue(result.size() == 1);

        assertTrue(findOnce("Emz", result));

    }

    @Test
    void isParentOf() {

        Map<String, List<String>> parents = new HashMap<>();
        parents.put("Emz", Arrays.asList("Tara"));
        parents.put("Frank", Arrays.asList("Tara"));

        parents.put("Tara", Arrays.asList("Sandy", "Poco", "Gene"));
        parents.put("Mortis", Arrays.asList("Sandy", "Poco", "Gene"));

        for (Map.Entry<String, List<String>> entry : parents.entrySet()) {
            for (String child : entry.getValue()) {
                assertTrue(ft.isParentOf(entry.getKey(), child));
            }
        }

        Map<String, List<String>> nonParents = new HashMap<>();
        nonParents.put("Emz", Arrays.asList("Emz", "Frank", "Mortis", "Sandy", "Poco", "Gene"));
        nonParents.put("Frank", Arrays.asList("Emz", "Frank", "Mortis", "Sandy", "Poco", "Gene"));

        nonParents.put("Tara", Arrays.asList("Emz", "Frank", "Tara", "Mortis"));
        nonParents.put("Mortis", Arrays.asList("Emz", "Frank", "Tara", "Mortis"));

        nonParents.put("Sandy", Arrays.asList("Sandy", "Emz", "Frank", "Tara", "Mortis", "Sandy", "Poco", "Gene"));
        nonParents.put("Poco", Arrays.asList("Sandy", "Emz", "Frank", "Tara", "Mortis", "Sandy", "Poco", "Gene"));
        nonParents.put("Gene", Arrays.asList("Sandy", "Emz", "Frank", "Tara", "Mortis", "Sandy", "Poco", "Gene"));

        for (Map.Entry<String, List<String>> entry : nonParents.entrySet()) {
            for (String nonchild : entry.getValue()) {
                assertFalse(ft.isParentOf(entry.getKey(), nonchild));
            }
        }
    }

    @Test
    void isGrandparentOf() {

        Map<String, List<String>> grandparents = new HashMap<>();
        grandparents.put("Emz", Arrays.asList("Sandy", "Poco", "Gene"));
        grandparents.put("Frank", Arrays.asList("Sandy", "Poco", "Gene"));

        for (Map.Entry<String, List<String>> entry : grandparents.entrySet()) {
            for (String grandchild : entry.getValue()) {
                assertTrue(ft.isGrandparentOf(entry.getKey(), grandchild));
            }
        }

        Map<String, List<String>> nonGrandparents = new HashMap<>();
        nonGrandparents.put("Emz", Arrays.asList("Emz", "Frank", "Tara", "Mortis"));
        nonGrandparents.put("Frank", Arrays.asList("Emz", "Frank", "Tara", "Mortis"));

        nonGrandparents.put("Tara", Arrays.asList("Emz", "Frank", "Tara", "Mortis", "Sandy", "Poco", "Gene"));
        nonGrandparents.put("Mortis", Arrays.asList("Emz", "Frank", "Tara", "Mortis", "Sandy", "Poco", "Gene"));

        nonGrandparents.put("Sandy", Arrays.asList("Emz", "Frank", "Tara", "Mortis", "Sandy", "Poco", "Gene"));
        nonGrandparents.put("Poco", Arrays.asList("Emz", "Frank", "Tara", "Mortis", "Sandy", "Poco", "Gene"));
        nonGrandparents.put("Gene", Arrays.asList("Emz", "Frank", "Tara", "Mortis", "Sandy", "Poco", "Gene"));

        for (Map.Entry<String, List<String>> entry : nonGrandparents.entrySet()) {
            for (String nonchild : entry.getValue()) {
                assertFalse(ft.isGrandparentOf(entry.getKey(), nonchild));
            }
        }
    }

    boolean findOnce(String[] names, Collection<Member> collection) {
        boolean result = true;

        for (String name : names) {
            result = result && findOnce(name, collection);
        }

        return result;
    }

    boolean findOnce(String name, Collection<Member> collection) {
        int found = 0;

        for (Member member : collection) {
            if (member.getName().equals(name)) {
                found++;
            }
        }

        return found == 1;
    }
}