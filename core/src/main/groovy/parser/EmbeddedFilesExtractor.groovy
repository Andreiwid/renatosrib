package parser

import org.apache.commons.io.FilenameUtils
import org.apache.tika.config.TikaConfig
import org.apache.tika.extractor.ParsingEmbeddedDocumentExtractor
import org.apache.tika.metadata.Metadata
import org.apache.tika.mime.MediaType
import org.apache.tika.mime.MimeTypeException
import org.apache.tika.parser.ParseContext
import org.xml.sax.ContentHandler
import org.xml.sax.SAXException

import java.nio.file.Files
import java.nio.file.Path

class EmbeddedFilesExtractor extends ParsingEmbeddedDocumentExtractor {
    private final Path outputDir;
    private int fileCount = 0;
    private TikaConfig config = TikaConfig.getDefaultConfig();


    EmbeddedFilesExtractor(Path outputDir, ParseContext context) {
        super(context);
        this.outputDir = outputDir;
    }

    @Override
    public boolean shouldParseEmbedded(Metadata metadata) {
        return true;
    }

    @Override
    public void parseEmbedded(InputStream stream, ContentHandler handler, Metadata metadata, boolean outputHtml)
            throws SAXException, IOException {

        //try to get the name of the embedded file from the metadata
        String name = metadata.get(Metadata.RESOURCE_NAME_KEY);

        if (name == null) {
            name = "file_" + fileCount++;
        } else {
            //make sure to select only the file name (not any directory paths
            //that might be included in the name) and make sure
            //to normalize the name
            name = FilenameUtils.normalize(FilenameUtils.getName(name));
        }

        //now try to figure out the right extension for the embedded file
        MediaType contentType = detector.detect(stream, metadata);

        if (name.indexOf('.') == -1 && contentType != null) {
            try {
                name += config.getMimeRepository().forName(
                        contentType.toString()).getExtension();
            } catch (MimeTypeException e) {
                e.printStackTrace();
            }
        }
        //should add check to make sure that you aren't overwriting a file
        Path outputFile = outputDir.resolve(name);
        //do a better job than this of checking
        Files.createDirectories(outputFile.getParent());
        Files.copy(stream, outputFile);
    }

}
