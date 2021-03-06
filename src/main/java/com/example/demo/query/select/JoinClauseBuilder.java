package com.example.demo.query.select;

import com.example.demo.model.join.Join;
import com.example.demo.query.ClauseBuilder;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;


@RequiredArgsConstructor
public class JoinClauseBuilder implements ClauseBuilder {

    private final List<Join> joins;

    @Override
    public String build() {
        if (Objects.isNull(joins) || joins.isEmpty()) {
            return "";
        }
        final StringBuilder result = new StringBuilder();

        for (Join join : joins) {
            result.append(join.getJoinType().getQueryView());

            if (Objects.isNull(join.getSchemaName()) || join.getSchemaName().isEmpty()) {
                result.append(join.getTable());
            } else {
                result.append(join.getSchemaName())
                        .append(".")
                        .append(join.getTable());
            }
            result.append(" ON ")
                    .append(join.getOn().getField())
                    .append(" = ")
                    .append(join.getOn().getValue());

        }
        return result.toString();
    }

}
