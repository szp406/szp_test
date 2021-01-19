package com.jdyx.es.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.client.ClientConfiguration;
//import org.springframework.data.elasticsearch.client.RestClients;
//import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
//import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class ElasticConfig {
//public class ElasticConfig extends AbstractElasticsearchConfiguration {

    /** 协议 */
    @Value("${elasticsearch.schema:http}")
    private String schema;

    /** 集群地址，如果有多个用“,”隔开 */
    @Value("${elasticsearch.address}")
    private String address;

    /** 连接超时时间 */
    @Value("${elasticsearch.connectTimeout}")
    private int connectTimeout;

    /** Socket 连接超时时间 */
    @Value("${elasticsearch.socketTimeout}")
    private int socketTimeout;

    /** 获取连接的超时时间 */
    @Value("${elasticsearch.connectionRequestTimeout}")
    private int connectionRequestTimeout;

    /** 最大连接数 */
    @Value("${elasticsearch.maxConnectNum}")
    private int maxConnectNum;

    /** 最大路由连接数 */
    @Value("${elasticsearch.maxConnectPerRoute}")
    private int maxConnectPerRoute;

//    @Value("${elasticsearch.user}")
//    private String user;
//
//    @Value("${elasticsearch.password}")
//    private String password;


    @Bean
    public RestHighLevelClient elasticsearchClient() {
        // 拆分地址
        List<HttpHost> hostLists = new ArrayList<HttpHost>();
        String[] hostList = address.split(",");
        for (String addr : hostList) {
            String host = addr.split(":")[0];
            String port = addr.split(":")[1];
            hostLists.add(new HttpHost(host, Integer.parseInt(port), schema));
        }
        // 转换成 HttpHost 数组
        HttpHost[] httpHost = hostLists.toArray(new HttpHost[]{});
//        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(user, password));
        // 构建连接对象
        RestClientBuilder builder = RestClient.builder(httpHost);
        // 异步连接延时配置
        builder.setRequestConfigCallback(requestConfigBuilder -> {
            requestConfigBuilder.setConnectTimeout(connectTimeout);
            requestConfigBuilder.setSocketTimeout(socketTimeout);
            requestConfigBuilder.setConnectionRequestTimeout(connectionRequestTimeout);
            return requestConfigBuilder;
        });
        // 异步连接数配置
        builder.setHttpClientConfigCallback(httpClientBuilder -> {
            httpClientBuilder.setMaxConnTotal(maxConnectNum);
            httpClientBuilder.setMaxConnPerRoute(maxConnectPerRoute);
//            httpClientBuilder.disableAuthCaching();
//            httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
            return httpClientBuilder;
        });
        return new RestHighLevelClient(builder);
    }


//    @Bean
//    public ElasticsearchRestTemplate restTemplate() throws Exception {
//        return new ElasticsearchRestTemplate(elasticsearchClient());
//    }


}
