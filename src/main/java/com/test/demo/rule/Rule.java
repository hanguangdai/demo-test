package com.test.demo.rule;

import java.util.List;

public interface Rule {

    List<CaseRelationship> excute(TaskDetail taskDetail, List<CaseRelationship> caseRelationships);

}
