package com.example.demo.model.target;

import com.fasterxml.jackson.annotation.JsonSubTypes;


@JsonSubTypes({
        @JsonSubTypes.Type(value = Expression.class, name = "expression"),
        @JsonSubTypes.Type(value = Field.class, name = "column")})
public interface TargetClause {

    String getQueryView();

}