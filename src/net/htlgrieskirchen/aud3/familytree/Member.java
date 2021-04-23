package net.htlgrieskirchen.aud3.familytree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Klaus Scheiböck, Franz Einboeck
 */
public class Member {

    private final String name;
    private Member partner;
    private List<Member> parents;
    private List<Member> children;
    private Gender gender;


    public Member(String name) {
        this.name = name;
        partner = null;
        parents = new ArrayList<>();
        gender = Gender.OTHER;
        children = new ArrayList<>();
    }

    public Member(String name, Member partner, List<Member> parents, List<Member> children, Gender gender) {
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
        if(parents.size() <= 2) {
            parents.add(parent);
        }
    }

    public void setMale() {
        this.setGender(Gender.MALE);
    }

    public void setFemale() {
        this.setGender(Gender.FEMALE);
    }

    public void setOther() {
        this.setGender(Gender.OTHER);
    }

    public List<Member> getChildren() {
        return children;
    }

    public List<Member> getParents() {
        return parents;
    }

    public Member getPartner() {
        return partner;
    }

    public boolean hasPartner() {
        return getPartner() != null;
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

    public List<Member> getSiblings() {
        List<Member> siblings = new ArrayList<>();
        getParents().stream().filter(Member::hasChildren).filter(member -> member.getChildren().size() > 1)
                .forEach(member -> siblings.addAll(member.getChildren().stream().filter(member1 -> !member1.getName().equals(getName())).collect(Collectors.toList())));
        return siblings.stream().distinct().collect(Collectors.toList());
    }

    public List<Member> getGrandChildren() {
        List<Member> grandChildren = new ArrayList<>();
        children.stream().forEach(member -> grandChildren.addAll(member.getChildren()));
        return grandChildren;
    }

    public String getParentNamesAsString() {
        if(getChildren().size() < 1) return " - ";
        return String.join(", ", parents.stream().map(member -> member.getName()).collect(Collectors.toList()));
    }

    public String getChildrenAsString() {
        if(getChildren().size() < 1) return " - ";
        return String.join(", ", getChildren().stream().map(member -> member.getName()).collect(Collectors.toList()));
    }

    public String getPartnerAsString() {
        if(partner == null) return " - ";
        return partner.getName();
    }

    public Gender getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", partner=" + getPartnerAsString() +
                ", parents=" + getParentNamesAsString() +
                ", children=" + getChildrenAsString() +
                ", gender=" + gender +
                '}';
    }

    public enum Gender {
        MALE, FEMALE, OTHER;
    }

}
