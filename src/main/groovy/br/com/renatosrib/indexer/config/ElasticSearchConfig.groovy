package br.com.renatosrib.indexer.config

import org.elasticsearch.client.Client
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress
import org.elasticsearch.node.NodeBuilder
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories

@Configuration
@EnableElasticsearchRepositories(basePackages = "br.com.renatosrib.indexer.repository")
public class ElasticSearchConfig {



    private ElasticsearchTemplate template;

    @Autowired
    public elasticSearchTemplateBean(ElasticsearchTemplate template) {
        this.template = template;
    }

}
