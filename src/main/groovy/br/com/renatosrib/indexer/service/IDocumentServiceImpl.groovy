package br.com.renatosrib.indexer.service

import br.com.renatosrib.indexer.model.Document
import br.com.renatosrib.indexer.repository.DocumentRepository
import br.com.renatosrib.indexer.to.DocumentTo
import org.elasticsearch.action.search.SearchResponse
import org.elasticsearch.common.unit.Fuzziness
import org.elasticsearch.index.query.MultiMatchQueryBuilder
import org.elasticsearch.index.query.QueryBuilders
import org.elasticsearch.search.SearchHit
import org.elasticsearch.search.highlight.HighlightBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate
import org.springframework.data.elasticsearch.core.SearchResultMapper
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder
import org.springframework.data.elasticsearch.core.query.SearchQuery
import org.springframework.stereotype.Service

import static java.lang.Long.parseLong
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery
import static org.elasticsearch.index.query.QueryBuilders.termQuery


@Service
class IDocumentServiceImpl implements IDocumentService {

    @Autowired
    DocumentRepository repository

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    @Override
    Document save(Document document) {
        return repository.save(document)
    }

    @Override
    List<DocumentTo> findByContent(String content, Pageable pageRequest) {

        SearchQuery searchQuery = new NativeSearchQueryBuilder().withIndices("files")
                .withPageable(pageRequest)
                .withQuery(QueryBuilders.multiMatchQuery(
                content.toLowerCase()).field("content").
                fuzziness(Fuzziness.AUTO).analyzer("brazilian"))
                .withQuery(QueryBuilders.multiMatchQuery(
                content.toLowerCase()).field("content").
                fuzziness(Fuzziness.AUTO).analyzer("english"))
                .withHighlightFields(
                new HighlightBuilder.Field("content"))
                .build()

        AggregatedPage<DocumentTo> documents = elasticsearchTemplate.queryForPage(searchQuery, Document.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable = pageRequest) {
                List<DocumentTo> results = new ArrayList<DocumentTo>()
                for (SearchHit searchHit : response.hits) {
                    if (response.hits.hits.length <= 0) {
                        return  new AggregatedPageImpl<T>((List<T>) []);
                    }
                    DocumentTo documentTo = new DocumentTo()
                    documentTo.path = searchHit.id
                    documentTo.fileName = documentTo.content = searchHit.source.get("fileName")
                    documentTo.content = searchHit.source.get("content")
                    documentTo.lastModification = parseLong((String) searchHit.source.get("lastModification"))
                    documentTo.contentHighlithed =  []
                    searchHit.highlightFields.get("content")?.fragments()?.each {highlight ->
                        documentTo.contentHighlithed.add(highlight.toString())
                    }
                    results.add(documentTo)
                }
                if (results.size() > 0) {
                    return new AggregatedPageImpl<T>((List<T>) results)
                }
                return  new AggregatedPageImpl<T>((List<T>) []);
            }
        });

        return documents?.getContent()
    }

    @Override
    List<DocumentTo> findAll() {
        List<DocumentTo> documents = [];
        repository.findAll().each { document ->
            documents.add(DocumentTo.fromDocument(document));
        }
        return documents
    }
}
