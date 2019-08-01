package com.test.demo.rule;

import java.util.stream.Stream;

public enum RelationshipEnum {

    BATCH("a",1,""),SAME_INCIDENT("b",2,"");
    private String name;
    private int priority;
    private String page;

    RelationshipEnum(String name, int priority, String page) {
        this.name = name;
        this.priority = priority;
        this.page = page;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public static RelationshipEnum getRelationEnumByName(String name){
        return name == null ? null : Stream.of(RelationshipEnum.values()).filter(relationshipEnum -> relationshipEnum.name.equals(name)).findFirst().get();
    }

}
