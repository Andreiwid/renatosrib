package br.com.renatosrib.indexer.services

import br.com.renatosrib.indexer.to.IndexedFileTO

interface IFileService {
    IndexedFileTO save(IndexedFileTO indexedFileTO);

}