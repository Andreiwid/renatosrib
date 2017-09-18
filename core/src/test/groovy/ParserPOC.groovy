import jdk.internal.org.xml.sax.SAXException
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

    static String parseToPlainText() throws IOException, SAXException, TikaException {
        BodyContentHandler handler = new BodyContentHandler()
        ParseContext parseContext = new ParseContext()

        AutoDetectParser parser = new AutoDetectParser()
        EmbeddedFilesExtractor extractor = new EmbeddedFilesExtractor(new File("/tmp/lucene6idx/").toPath(), parseContext)
        parseContext.set(extractor.class,  extractor)

        Metadata metadata = new Metadata()
        InputStream inputStream = new FileInputStream('/home/renato/Documentos/pasta-monitorada/teste/certificado nivel 3 - melt.pdf')
        parser.parse(inputStream, handler, metadata, parseContext)
        return handler.toString()

    }

    @Test
    public void testParse() {
        parseToPlainText()
    }

    static String parseToPlainText2() {
        InputStream stream = new FileInputStream('/home/renato/Documentos/pasta-monitorada/teste/certificado nivel 3 - melt.pdf');

        Parser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler(
                Integer.MAX_VALUE);

        TesseractOCRConfig config = new TesseractOCRConfig();
        PDFParserConfig pdfConfig = new PDFParserConfig();
        ParseContext parseContext = new ParseContext();

        parseContext.set(TesseractOCRConfig.class, config);
        parseContext.set(PDFParserConfig.class, pdfConfig);
        parseContext.set(Parser.class, parser); // need to add this to make
        // sure recursive parsing
        // happens!
        Metadata metadata = new Metadata();
        parser.parse(stream, handler, metadata, parseContext);
        return handler.toString().trim();
    }

    @Test
    public void testParse2() {
        parseToPlainText2()
    }
}
