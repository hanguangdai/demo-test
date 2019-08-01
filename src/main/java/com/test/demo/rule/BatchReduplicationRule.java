package com.test.demo.rule;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class BatchReduplicationRule extends BaseCaseRelationshipRule{

    @Override
    public List<CaseRelationship> excute(TaskDetail taskDetail, List<CaseRelationship> caseRelationships) {
        if(caseRelationships != null){
            List<CaseRelationship> tempCaseRelationships = new ArrayList<>(caseRelationships);
            Collection<CaseRelationship> batchCaseRelationships = tempCaseRelationships.stream().filter(caseRelationship -> {
                boolean isFilter =  caseRelationship.getBatchNo()!=null
                        &&RelationshipEnum.BATCH.getName().equals(caseRelationship.getDefaultRelationShip())
                        &&caseRelationship.getRelationship().indexOf(RelationshipEnum.SAME_INCIDENT.getName())!=-1;
                if(isFilter){
                    caseRelationships.remove(caseRelationship);
                }
                return isFilter;
            }).collect(Collectors.toMap(CaseRelationship::getBatchNo, caseRelationship -> caseRelationship, (value1, value2) -> value1)).values();
            caseRelationships.addAll(batchCaseRelationships);
        }
        return caseRelationships;
    }
}
