package net.htlgrieskirchen.aud3.familytree;

import java.util.Arrays;

/**
 *
 * @author Add your names here.
 */
public class Main {
    
    public static void main(String[] args) {

        // TODO : start programming here
        FamilyTree familyTree = new FamilyTree();

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

        Member florian = new Member("Florian");
        florian.addParent(fabian);
        familyTree.add(florian);

        //familyTree.getAllGrandmas().stream().forEach(System.out::println);

        familyTree.getAllGrandchildren().forEach(System.out::println);
        //manuel.getGrandChildren().stream().forEach(System.out::println);
        //System.out.println(familyTree.getgr("Anel", "Fabian"));
    }
    
}
