package net.htlgrieskirchen.aud3.familytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author Klaus Scheiböck, Simon Schabetsberger
 */
public class FamilyTree {

    private Member root;
    private int size = 0;

    public FamilyTree() {

        root = null;

    }

    /**
     * Adds a family member to the family tree.
     *
     * @param member Family member to add.
     */
    public void add(Member member) {
        if(root == null) {
            root = member;
        } else {
            if(member.getParents()[0] != null) {
                member.getParents()[0].addChild(member);    //add child to parent 1
            }
            if(member.getParents()[1] != null) {
                member.getParents()[1].addChild(member);    //add child to parent 2
            }

        }
        size++;
    }

    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();

        members.add(root);
        members.addAll(getAllChildren(root));

        return members;
    }

    public List<Member> getAllChildren(Member member) {
        List<Member> children = new ArrayList<>();

        children.addAll(member.getChildren());

        for(Member m: member.getChildren()) {
            children.addAll(getAllChildren(m));
        }
        return children;
    }

    /**
     * Searches the family tree for all members that are grandparents and returns them in a list.
     * 
     * @return A list of all family members that are grandparents.
     */
    public List<Member> getAllGrandparents() {
        return getAllMembers().stream().filter(Member::isGrandParent).collect(Collectors.toList());
    }

    /**
     * Searches the family tree for all members that are grandchildren and returns them in a list.
     * 
     * @return A list of all family members that are grandchildren.
     */
    public List<Member> getAllGrandchildren() {

        // TODO : start programming here
        
        return null; // TODO : remove
    }

    /**
     * Searches the family tree for all members that have siblings and returns them and their siblings in a map.
     * 
     * @return A map of all family members that have siblings (key) and a list of their siblings (value).
     */
    public Map<Member, List<Member>> getAllSiblings() {

        // TODO : start programming here
        
        return null; // TODO : remove
    }

    /**
     * Searches the family tree for all members that are grandmas and returns them in a list.
     * 
     * @return A list of all family members that are grandmas.
     */
    public List<Member> getAllGrandmas() {
        return getAllGrandparents().stream().filter(Member::isFemale).collect(Collectors.toList());
    }

    /**
     * Searches for the family members with the given names and checks if they are parent and child.
     *
     * @param parentName Name of the parentName to search for.
     * @param childName Name of the childName to search for.
     * @return <code>True</code> if the family member that corresponds to <code>parentName</code> is a parent of the family member that corresponds to <code>childName</code>. Otherwise, <code>false</code>.
     */
    public boolean isParentOf(String parentName, String childName) {

        // TODO : start programming here
        
        return false; // TODO : remove
    }
    
    /**
     * Searches for the family members with the given names and checks if they are grandparent and grandchild.
     *
     * @param grandparentName Name of the grandparent to search for.
     * @param grandchildName Name of the grandchild to search for.
     * @return <code>True</code> if the family member that corresponds to <code>grandparentName</code> is a grandparent of the family member that corresponds to <code>grandchildName</code>. Otherwise, <code>false</code>.
     */
    public boolean isGrandparentOf(String grandparentName, String grandchildName) {

        // TODO : start programming here
        
        return false; // TODO : remove
    }
    
}