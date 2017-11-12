package br.com.renatosrib.indexer.service

import br.com.renatosrib.indexer.model.Document
import br.com.renatosrib.indexer.repository.DocumentRepository
import org.apache.tika.metadata.Metadata
import org.apache.tika.parser.AutoDetectParser
import org.apache.tika.parser.ParseContext
import org.apache.tika.parser.Parser
import org.apache.tika.parser.ocr.TesseractOCRConfig
import org.apache.tika.parser.pdf.PDFParserConfig
import org.apache.tika.sax.BodyContentHandler
import org.elasticsearch.action.search.SearchPhaseExecutionException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import javax.annotation.PostConstruct

@Service
class DocumentExtractorServiceImpl implements IDocumentExtractorService {

    private ParseContext parseContext

    @Autowired
    DocumentRepository repository

    @Value('${spring.monitored-folder}')
    private String monitoredFolder

    @PostConstruct
    private  void getParseContextInstance() {
        TesseractOCRConfig config = new TesseractOCRConfig()
        config.setLanguage("por")
        PDFParserConfig pdfConfig = new PDFParserConfig()
        pdfConfig.setExtractInlineImages(true)
        pdfConfig.setExtractUniqueInlineImagesOnly(false)
        ParseContext parseContext = new ParseContext()
        parseContext.set(TesseractOCRConfig.class, config)
        parseContext.set(PDFParserConfig.class, pdfConfig)

        this.parseContext = parseContext
    }

    @Transactional
    private Document fromFile(File file) {
        Parser parser = new AutoDetectParser()
        //Evita loop infinito
        this.parseContext.set(Parser.class, parser)

        Document possiblyExistentFile
        try{
            possiblyExistentFile = repository.findByPath(file.absolutePath)
        } catch (SearchPhaseExecutionException e ){
            println "Não foi possível buscar pelo path "+ file.absolutePath
        }
        if(possiblyExistentFile == null || possiblyExistentFile.lastModification != file.lastModified()) {
            Document document = new Document()

            FileInputStream fileInputStream = new FileInputStream((file))
            BodyContentHandler handler = new BodyContentHandler(Integer.MAX_VALUE)
            Metadata metadata = new Metadata()
            parser.parse(fileInputStream, handler, metadata, parseContext)
            document.content = handler.toString()
            document.id = file.absolutePath
            document.fileName = file.name
            document.lastModification = file.lastModified()
            println document.fileName + " indexado com sucesso."
            return repository.save(document)
        }
        return null
    }

    private List<Document> indexFolder(String folder) {
        File file = new File(folder)
        List<Document> documents = new ArrayList<Document>()

        for(File currentFile :file.listFiles()) {
            if(currentFile.isFile()) {
                Document document = fromFile(currentFile)
                if(document) {
                    documents.add(document)
                }
            } else {
                indexFolder(currentFile.path)
            }

        }
        return documents
    }

    @Override
    List<Document> synchronizeFolder() {
        return indexFolder(monitoredFolder)
    }
}
