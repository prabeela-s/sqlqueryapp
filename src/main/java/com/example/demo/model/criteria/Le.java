package com.example.demo.model.criteria;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@JsonTypeName("le")
@NoArgsConstructor
public class Le extends SimpleCriteria {

    public Le(String fieldName, String value) {
        super(fieldName, value);
    }

    @Override
    public SimpleConditionalOperator getSimpleConditionalOperator() {
        return SimpleConditionalOperator.LESS_THAN_EQUALS;
    }

    @Override
    public CriteriaType getCriteriaType() {
        return CriteriaType.SIMPLE;
    }

}
