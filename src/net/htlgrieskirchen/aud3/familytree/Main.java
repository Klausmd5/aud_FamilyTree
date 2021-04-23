package net.htlgrieskirchen.aud3.familytree;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Klaus Scheiböck, Einböck Ranz
 */
public class Main {
    
    public static void main(String[] args) {

        // TODO : start programming here
        FamilyTree familyTree = new FamilyTree();

        Member root = new Member("Root");
        familyTree.add(root);

        Member emz = new Member("Emz");
        emz.addParent(root);
        emz.setGender(Member.Gender.FEMALE);

        Member frank = new Member("Frank", emz, new ArrayList<>(), new ArrayList<>(), Member.Gender.MALE);
        frank.addParent(root);
        emz.addPartner(frank);


        Member tara = new Member("Tara", null, new ArrayList<>(), new ArrayList<>(), Member.Gender.FEMALE);
        tara.addParent(emz);
        tara.addParent(frank);

        Member mortis = new Member("Mortis", tara, new ArrayList<>(), new ArrayList<>(), Member.Gender.MALE);
        mortis.addParent(root);
        tara.addPartner(mortis);

        ArrayList<Member> taraMortis = new ArrayList<>();
        taraMortis.add(tara);
        taraMortis.add(mortis);

        Member poco = new Member("Poco", null, taraMortis, new ArrayList<>(), Member.Gender.OTHER);

        Member gene = new Member("Gene", null, taraMortis, new ArrayList<>(), Member.Gender.MALE);

        Member sandy = new Member("Sandy", null, taraMortis, new ArrayList<>(), Member.Gender.FEMALE);



        Member bea = new Member("Bea", null, new ArrayList<>(), new ArrayList<>(), Member.Gender.FEMALE);
        bea.addParent(root);

        Member bo = new Member("Bo", bea, new ArrayList<>(), new ArrayList<>(), Member.Gender.MALE);
        bo.addParent(root);
        bea.addPartner(bo);

        ArrayList<Member> beaBo = new ArrayList<>();
        beaBo.add(bea);
        beaBo.add(bo);

        Member nita = new Member("Nita", poco, beaBo, new ArrayList<>(), Member.Gender.FEMALE);
        poco.addPartner(nita);

        Member leon = new Member("Leon", sandy, beaBo, new ArrayList<>(), Member.Gender.MALE);
        sandy.addPartner(leon);

        Member amber = new Member("Amber", null, beaBo, new ArrayList<>(), Member.Gender.FEMALE);


        ArrayList<Member> sandyLeon = new ArrayList<>();
        sandyLeon.add(sandy);
        sandyLeon.add(leon);

        Member rosa = new Member("Rosa", null, sandyLeon, new ArrayList<>(), Member.Gender.FEMALE);

        Member jassie = new Member("Jassie", null, sandyLeon, new ArrayList<>(), Member.Gender.FEMALE);


        ArrayList<Member> nitaPoco = new ArrayList<>();
        nitaPoco.add(nita);
        nitaPoco.add(poco);

        Member penny = new Member("Penny", jassie, nitaPoco, new ArrayList<>(), Member.Gender.FEMALE);
        jassie.addPartner(penny);

        Member crow = new Member("Crow", null, nitaPoco, new ArrayList<>(), Member.Gender.OTHER);


        ArrayList<Member> jassiePenny = new ArrayList<>();
        jassiePenny.add(jassie);
        jassiePenny.add(penny);

        Member darryl = new Member("Darryl", null, jassiePenny, new ArrayList<>(), Member.Gender.OTHER);

        Member surge = new Member("Surge", null, jassiePenny, new ArrayList<>(), Member.Gender.OTHER);

        Member barley = new Member("Barley", null, jassiePenny, new ArrayList<>(), Member.Gender.OTHER);


        familyTree.add(emz);
        familyTree.add(frank);

        familyTree.add(tara);
        familyTree.add(mortis);


        familyTree.add(bea);
        familyTree.add(bo);

        familyTree.add(amber);
        familyTree.add(leon);
        familyTree.add(sandy);

        familyTree.add(nita);
        familyTree.add(poco);
        familyTree.add(gene);


        familyTree.add(rosa);
        familyTree.add(jassie);

        familyTree.add(penny);
        familyTree.add(crow);


        familyTree.add(darryl);
        familyTree.add(surge);
        familyTree.add(barley);


        //familyTree.getAllMembers().forEach(member -> System.out.println(member.getName() + " - "+(member.getChildren().size() >0 ? member.getChildren().get(0).getName() : "")));
        familyTree.getAllMembers().forEach(System.out::println);
    }
    
}
