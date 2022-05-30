package com.example.demo.query.select;


import com.example.demo.exception.JsonSQL4JParseException;
import com.example.demo.model.Select;
import com.example.demo.model.target.TargetClause;
import com.example.demo.model.target.TargetClauseDeserializer;
import com.example.demo.query.ClauseBuilder;
import com.example.demo.query.Query;
import com.example.demo.query.WhereClauseBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;


public class SelectQuery implements Query {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final ClauseBuilder targetBuilder;
    private final Select select;
    private final ClauseBuilder joinBuilder;


    static {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(TargetClause.class, new TargetClauseDeserializer(TargetClause.class));
        MAPPER.registerModule(module);
    }
    private final ClauseBuilder whereBuilder;

    public SelectQuery(String jsonQuery) {
        //validate by schema
        try {
            this.select = MAPPER.readValue(jsonQuery, Select.class);
        } catch (JsonProcessingException e) {
            throw new JsonSQL4JParseException("Can not parse json query: [" + jsonQuery + "]", e);
        }

        targetBuilder = new TargetClauseBuilder(select.getFields());
        whereBuilder = new WhereClauseBuilder(select.getCriteria());
        joinBuilder = new JoinClauseBuilder(select.getJoins());
    }

    @Override
    public String getQuery() {
        return "SELECT " +
                targetBuilder.build() +
                " FROM " + select.getTables().get(0).getSchemaName() + "." + select.getTables().get(0).getTableName()
                + whereBuilder.build()
                + joinBuilder.build();

    }
}
