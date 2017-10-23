package br.com.renatosrib.indexer.repository

import br.com.renatosrib.indexer.domain.IndexedFileEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.data.solr.repository.SolrCrudRepository

interface FileRepository extends CrudRepository<IndexedFileEntity, Long> {

}