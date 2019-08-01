package com.test.demo.rule;

public class CaseRelationship {

    private String processInstanceId;

    private String caseCategory;

    private String relavatedProcInstId;

    private String relationship;

    private String defaultRelationShip;

    private String batchNo;

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getCaseCategory() {
        return caseCategory;
    }

    public void setCaseCategory(String caseCategory) {
        this.caseCategory = caseCategory;
    }

    public String getRelavatedProcInstId() {
        return relavatedProcInstId;
    }

    public void setRelavatedProcInstId(String relavatedProcInstId) {
        this.relavatedProcInstId = relavatedProcInstId;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getDefaultRelationShip() {
        return defaultRelationShip;
    }

    public void setDefaultRelationShip(String defaultRelationShip) {
        this.defaultRelationShip = defaultRelationShip;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }
}
