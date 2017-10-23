package br.com.renatosrib.indexer.impl

import br.com.renatosrib.indexer.repository.FileRepository
import br.com.renatosrib.indexer.services.IFileService
import br.com.renatosrib.indexer.to.IndexedFileTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FileService implements IFileService {

    @Autowired
    FileRepository fileRepository

    @Override
    IndexedFileTO save(IndexedFileTO indexedFileTO) {
        println("Chegou ao service!");
        return IndexedFileTO.toIndexedFileTO(fileRepository.save(indexedFileTO.toIndexedFileEntity()))
    }
}
