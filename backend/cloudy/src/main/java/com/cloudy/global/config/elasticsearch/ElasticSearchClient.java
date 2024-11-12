package com.cloudy.global.config.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

import java.util.Base64;

import static org.apache.commons.codec.binary.Base64.encodeBase64;

@Configuration
public class ElasticSearchClient extends ElasticsearchConfiguration {

    @Value("${spring.elasticsearch.uris}")
    private String elasticsearchUri;
    @Value("${spring.elasticsearch.username}")
    private String username;
    @Value("${spring.elasticsearch.password}")
    private String password;

    @Override
    public ClientConfiguration clientConfiguration() {

        return ClientConfiguration.builder().connectedTo(elasticsearchUri)
                .usingSsl(false).withBasicAuth(username,password)
                .build();
    }

    @Bean
    public ElasticsearchClient elasticsearchClient() {
        // RestClient를 사용하여 ElasticsearchClient 생성
        RestClient restClient = RestClient.builder(
                        HttpHost.create(elasticsearchUri)
                ).setDefaultHeaders(new Header[]{
                        new BasicHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes())) })
                .build();
        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());
        return new ElasticsearchClient(transport);
    }


}
