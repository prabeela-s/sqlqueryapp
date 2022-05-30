package com.example.demo.model.criteria;


import com.example.demo.exception.JsonSQL4JParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.List;

public class GroupCriteriaDeserializer extends StdDeserializer<GroupCriteria> {

    private static final TypeReference<List<Criteria>> TYPE_REFERENCE =
            new TypeReference<List<Criteria>>() {
            };

    public GroupCriteriaDeserializer() {
        this(null);
    }

    private GroupCriteriaDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public GroupCriteria deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        String currentKey = p.getParsingContext().getCurrentName();
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        List<Criteria> criteriaList = mapper.convertValue(node, TYPE_REFERENCE);

        if (currentKey.equals(GroupConditionalOperator.AND.getJsonView())) {
            return new And(criteriaList);
        } else if (currentKey.equals(GroupConditionalOperator.OR.getJsonView())) {
            return new Or(criteriaList);
        }
        throw new JsonSQL4JParseException("Can not deserialize json. Key [" + currentKey + "] is not correct");
    }

}
