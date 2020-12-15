package net.htlgrieskirchen.aud3.familytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Add your names here.
 */
public class Member {

    private final String name;
    private Member partner;
    private Member[] parents;
    private List<Member> children;
    private Gender gender;


    public Member(String name) {
        this.name = name;
        partner = null;
        parents = new Member[2];
        gender = Gender.OTHER;
        children = new ArrayList<>();
    }

    public Member(String name, Member partner, Member[] parents, List<Member> children, Gender gender) {
        this.name = name;
        this.partner = partner;
        this.parents = parents;
        this.children = children;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }
    
    public void addParent(Member parent) {
        // should be fine.. normally parents aren't changed
        if(parents[0] == null) {
           parents[0] = parent;
        } else {
            parents[1] = parent;
        }
    }

    public List<Member> getChildren() {
        return children;
    }

    public Member[] getParents() {
        return parents;
    }

    public void addPartner(Member partner) {
        this.partner = partner;
    }

    public void addChild(Member child) {
        children.add(child);
    }

    boolean isFemale() {
        return gender.equals(Gender.FEMALE);
    }

    boolean isMale() {
        return gender.equals(Gender.MALE);
    }

    boolean isOther() {
        return gender.equals(Gender.OTHER);
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public boolean hasChildren() {
        return children.size() >= 1;
    }

    public boolean isGrandParent() {
        return children.stream().filter(Member::hasChildren).count() >= 1;
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", partner=" + partner +
                ", parents=" +
                ", children=" + children +
                ", gender=" + gender +
                '}';
    }

    public enum Gender {
        MALE, FEMALE, OTHER;
    }

}
