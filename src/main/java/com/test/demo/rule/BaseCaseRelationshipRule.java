package com.test.demo.rule;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class BaseCaseRelationshipRule implements Rule {

    protected Map<String, CaseRelationship> getCaseRelationshipMap(List<CaseRelationship> caseRelationships){
        return caseRelationships == null?Collections.EMPTY_MAP
                :caseRelationships.stream().collect(Collectors.toMap(CaseRelationship::getProcessInstanceId, caseRelationship -> caseRelationship));
    }

    public String getPriorityRelationship(String originRelationship, RelationshipEnum relationshipEnum){
        RelationshipEnum originRelationshipEnum = RelationshipEnum.getRelationEnumByName(originRelationship);
        return getPriorityRelationship(originRelationshipEnum, relationshipEnum);
    }

    public String getPriorityRelationship(RelationshipEnum originRelationshipEnum, RelationshipEnum relationshipEnum){
        if(originRelationshipEnum == null || relationshipEnum == null){
            return null;
        }
        if(originRelationshipEnum == null){
            return relationshipEnum.getName();
        }
        if(relationshipEnum == null){
            return originRelationshipEnum.getName();
        }
        if(relationshipEnum.getPriority() > originRelationshipEnum.getPriority()){
            return relationshipEnum.getName();
        }
        return originRelationshipEnum.getName();
    }
}
