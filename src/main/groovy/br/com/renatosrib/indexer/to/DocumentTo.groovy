package br.com.renatosrib.indexer.to

import br.com.renatosrib.indexer.model.Document

class DocumentTo {
    String path

    String fileName

    String content

    Long lastModification

    List<String> contentHighlithed

    public static DocumentTo fromDocument(Document document) {
        DocumentTo documentTo = new DocumentTo();
        documentTo.path = document.id;
        documentTo.fileName = document.fileName;
        documentTo.content = document.content;
        documentTo.lastModification = document.lastModification;
        return documentTo
    }

}
