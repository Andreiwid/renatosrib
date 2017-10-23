package br.com.renatosrib.indexer.to

import br.com.renatosrib.indexer.domain.IndexedFileEntity


class IndexedFileTO {
    Long id
    String fileName
    String path
    String content

    IndexedFileEntity toIndexedFileEntity() {
        IndexedFileEntity indexedFileEntity = new IndexedFileEntity()
        indexedFileEntity.id = this.id
        indexedFileEntity.content = this.content
        indexedFileEntity.path = this.path
        indexedFileEntity.fileName = this.fileName
        return indexedFileEntity
    }

    public static IndexedFileTO toIndexedFileTO(IndexedFileEntity fileEntity) {
        IndexedFileTO fileTO = new IndexedFileTO()
        fileTO.id = fileEntity.id
        fileTO.content = fileEntity.content
        fileTO.path = fileEntity.path
        fileTO.fileName = fileEntity.fileName
        return  fileTO
    }
}
