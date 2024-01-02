package com.kanna.elastic.util;

import co.elastic.clients.elasticsearch._types.query_dsl.FuzzyQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import lombok.val;

import java.util.function.Supplier;

public class ESUtil {


    public static MatchAllQuery matchAllQuery(){
        val matchAllQuery = (val) new MatchAllQuery.Builder();
        return null;
    }
    public static Supplier<Query> getSupplier(String fuzzyWord){
            Supplier<Query> querySupplier = () ->Query.of(q-> q.fuzzy(fuzzyQuery(fuzzyWord)));
            return querySupplier;
    }

    public static FuzzyQuery fuzzyQuery(String fuzzyWord){
        return new FuzzyQuery.Builder().field("title").value(fuzzyWord).build();
    }


}
