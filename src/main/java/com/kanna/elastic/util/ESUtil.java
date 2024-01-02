package com.kanna.elastic.util;

import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import lombok.val;

import java.util.function.Supplier;

public class ESUtil {


    public static MatchAllQuery matchAllQuery(){
        val matchAllQuery = (val) new MatchAllQuery.Builder();
        return null;
    }


}
