package com.kanna.elastic.config;

import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

import javax.net.ssl.SSLContext;

@Configuration
public class ElasticConfig extends ElasticsearchConfiguration {
    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedToLocalhost()
                .usingSsl(buildSSLContext())
                .withBasicAuth("elastic","4mY=76Z*J5sIz+tEuV0e")
                .build();
    }

    private static SSLContext buildSSLContext() {
        try{
            return new SSLContextBuilder().loadTrustMaterial(null, TrustAllStrategy.INSTANCE).build();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
