package com.example.demo.query;

import com.example.demo.query.select.SelectQuery;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.example.demo.TestUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SelectTest {

    @Test
    @DisplayName("Should get select")
    void shouldGetSelect() {
        String json = TestUtil.readFileByPath("select/Select.json");
        System.out.println("Input: \n"+json);
        Query selectQuery = new SelectQuery(json);
        System.out.println("Output: \n"+selectQuery.getQuery());
        assertEquals("SELECT field1, field2 AS test FROM schema.table1", selectQuery.getQuery());
    }

    @Test
    @DisplayName("Should get select with simple criteria")
    void shouldGetSelectWithSimpleCriteria() {
        String json = TestUtil.readFileByPath("select/SelectWithSimpleCriteria.json");
        System.out.println("Input: \n"+json);
        Query selectQuery = new SelectQuery(json);
        System.out.println("Output: \n"+selectQuery.getQuery());
        assertEquals("SELECT field1, field2 AS test FROM schema.table1 WHERE field3 = 5",
                selectQuery.getQuery());
    }

    @Test
    @DisplayName("Should get simple select with in operator (strings) in criteria")
    void shouldGetSelectWithInOperatorStringsInCriteria() {
        String json = TestUtil.readFileByPath("select/SelectWithInOperatorStringsInCriteria.json");
        System.out.println("Input: \n"+json);
        Query selectQuery = new SelectQuery(json);
        System.out.println("Output: \n"+selectQuery.getQuery());
        assertEquals("SELECT field1, field2 AS test FROM schema.table1 " +
                "WHERE (field3 IN ('US', 'UK', 'IN', 'NZ') OR field4 = 3)", selectQuery.getQuery());
    }

    @Test
    @DisplayName("Should get select with join")
    void shouldGetSelectWithJoin() {
        String json = TestUtil.readFileByPath("select/SelectWithJoin.json");
        Query selectQuery = new SelectQuery(json);
        System.out.println("Input: \n"+json);
        System.out.println("Output: \n"+selectQuery.getQuery());
        assertEquals("SELECT field1, field2 AS test FROM schema.table1 INNER JOIN schema2.table2 ON field2 = field3",
                selectQuery.getQuery());
    }

}
