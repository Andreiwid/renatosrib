//package br.com.renatosrib.indexer
//
//import org.apache.lucene.analysis.standard.StandardAnalyzer
//import org.apache.lucene.document.Document
//import org.apache.lucene.document.Field
//import org.apache.lucene.document.FieldType
//import org.apache.lucene.index.*
//import org.apache.lucene.queryparser.classic.MultiFieldQueryParser
//import org.apache.lucene.queryparser.classic.QueryParser
//import org.apache.lucene.search.IndexSearcher
//import org.apache.lucene.search.Query
//import org.apache.lucene.search.ScoreDoc
//import org.apache.lucene.search.TopDocs
//import org.apache.lucene.store.Directory
//import org.apache.lucene.store.FSDirectory
//import org.apache.tika.Tika
//
//import java.nio.file.Paths
//import java.text.ParseException
//
///**
// * Created by renato on 31/07/17.
// */
//
//
//public class App {
//    private static final String INDEX_DIR = "/tmp/lucene6idx"
//    private static FieldType fileNameType
//    private static FieldType fieldContent
//    private static FieldType fieldPath
//
//
//
//    private static IndexWriter createWriter() throws IOException {
//        FSDirectory dir = FSDirectory.open(Paths.get(INDEX_DIR))
//        IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer())
//        IndexWriter writer = new IndexWriter(dir, config)
//        return writer
//    }
//
//    private static List<Document> createDocs(String path, List<Document> docs = new ArrayList<>()) throws IOException {
//
//        File file = new File(path)
//        println ("buscando arquivos para a pasta ${path}. É diretório? ${file.isDirectory()}")
//
//        if(file.isDirectory()) {
//            for(File currentFile :file.listFiles()) {
//                Tika tika  = new Tika()
//                Document documentFile = new Document()
//                if(currentFile.isFile()) {
//                    String fileContent = tika.parse(currentFile).getText();
//                    documentFile.add(new Field("fileName", currentFile.getName(), fileNameType))
//                    documentFile.add(new Field("fileContent",fileContent, fieldContent))
//                    documentFile.add(new Field("fieldPath", currentFile.path, fieldPath))
//                    docs.add(documentFile)
//                } else {
//                    createDocs(currentFile.path, docs)
//                }
//
//            }
//        }
//
//
//
//        return docs
//    }
//
//    private static IndexSearcher createSearcher() throws IOException {
//        Directory dir = FSDirectory.open(Paths.get(INDEX_DIR))
//        IndexReader reader = DirectoryReader.open(dir)
//        IndexSearcher searcher = new IndexSearcher(reader)
//        return searcher
//    }
//
//    public static void main(String[] args) throws IOException, ParseException, org.apache.lucene.queryparser.classic.ParseException {
//        //Inicializa tipos
//        fileNameType = new FieldType()
//        fileNameType.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS)
//        fileNameType.setStored(true)
//        fileNameType.setOmitNorms(false)
//        fileNameType.setTokenized(true)
//
//        fieldContent = new FieldType()
//        fieldContent.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS)
//        fieldContent.setStored(true)
//        fieldContent.setOmitNorms(true)
//        fieldContent.setTokenized(true)
//
//        fieldPath = new FieldType()
//        fieldPath.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS)
//        fieldPath.setStored(true)
//        fieldPath.setOmitNorms(false)
//        fieldPath.setTokenized(false)
//
//        // cria writer e indexa
//        IndexWriter writer = createWriter()
//        writer.deleteAll()
//        List<Document> docs = createDocs("/home/renato/Documentos/pasta-monitorada")
//        writer.addDocuments(docs)
//        writer.commit()
//        writer.close()
//
//        // busca
//        IndexSearcher searcher = createSearcher()
//        String[] fields = ["fileName", "fileContent"]
//        MultiFieldQueryParser qp = new MultiFieldQueryParser(fields, new StandardAnalyzer())
//
//        Query q1 = qp.parse("inter*")
//        TopDocs hits = searcher.search(q1, 5)
//        System.out.println(hits.totalHits + " arquivos encontrados para a busca por \"" + q1.toString() + "\"")
//        int num = 0
//        for (ScoreDoc sd : hits.scoreDocs) {
//            Document d = searcher.doc(sd.doc)
//            System.out.println(String.format("#%d: %s ", ++num, d.get("fileName")))
//        }
//
//    }
//}
