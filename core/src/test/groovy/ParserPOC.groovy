import jdk.internal.org.xml.sax.SAXException
import org.apache.lucene.document.Document
import org.apache.lucene.document.Field
import org.apache.tika.Tika
import org.apache.tika.parser.Parser;
import org.apache.tika.exception.TikaException
import org.apache.tika.extractor.ParsingEmbeddedDocumentExtractor
import org.apache.tika.io.TikaInputStream
import org.apache.tika.metadata.Metadata
import org.apache.tika.parser.AutoDetectParser
import org.apache.tika.parser.ParseContext
import org.apache.tika.parser.ocr.TesseractOCRConfig
import org.apache.tika.parser.pdf.PDFParserConfig
import org.apache.tika.sax.BodyContentHandler
import org.junit.Test
import parser.EmbeddedFilesExtractor


class ParserPOC {

    @Test
    public void parseToPlainText() throws IOException, SAXException, TikaException {
        Parser parser = new AutoDetectParser();
        TesseractOCRConfig config = new TesseractOCRConfig();
        config.setLanguage("por")
        PDFParserConfig pdfConfig = new PDFParserConfig();
        pdfConfig.setExtractInlineImages(true);
        pdfConfig.setExtractUniqueInlineImagesOnly(false); // set to false if pdf contains multiple images.
        ParseContext parseContext = new ParseContext();
        parseContext.set(TesseractOCRConfig.class, config);
        parseContext.set(PDFParserConfig.class, pdfConfig);
        //need to add this to make sure recursive parsing happens!
        parseContext.set(Parser.class, parser);
        createDocs("/home/renato/Documentos/pasta-monitorada", parser, parseContext)
        parser.parse()

    }

    static  void createDocs(String path, Parser parser, ParseContext parseContext ) {
        File file = new File(path)

        for(File currentFile :file.listFiles()) {
            if(currentFile.isFile()) {
                FileInputStream fileInputStream = new FileInputStream((currentFile))
                BodyContentHandler handler = new BodyContentHandler(Integer.MAX_VALUE);

                parser.parse(fileInputStream, handler, new Metadata(), parseContext)
                println handler.toString()
//                Tika tika = new Tika(new AutoDetectParser().getDetector(), parser)
//                FileInputStream fileInputStream = new FileInputStream(currentFile)
//                String fileContent = parser.parse(fileInputStream, handler, new Metadata(), parseContext )
//                tika.parse(currentFile).getText()
            } else {
                createDocs(currentFile.path, parser,  parseContext)
            }

        }
    }
}
