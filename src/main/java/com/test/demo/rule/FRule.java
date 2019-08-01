package com.test.demo.rule;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FRule extends BaseCaseRelationshipRule {


    @Override
    public List<CaseRelationship> excute(TaskDetail taskDetail, List<CaseRelationship> caseRelationships) {
        List<BatchProcess> batchProcesses = null;
        Map<String, CaseRelationship> caseRelationshipMap = this.getCaseRelationshipMap(caseRelationships);
        List<CaseRelationship> newCaseRelationships = batchProcesses.stream().map(batchProcess -> {
            CaseRelationship caseRelationship = new CaseRelationship();
            caseRelationship.setBatchNo(batchProcess.getBatchNo());
            caseRelationship.setProcessInstanceId(batchProcess.getProcessInstanceId());
            return caseRelationship;
        }).collect(Collectors.toList());
        return newCaseRelationships;
    }



}
