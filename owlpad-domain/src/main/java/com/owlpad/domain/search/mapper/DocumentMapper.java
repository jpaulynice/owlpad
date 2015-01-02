package com.owlpad.domain.search.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.search.SearchHit;

import com.owlpad.domain.search.Document;
import com.owlpad.domain.search.Field;

/**
 * @author Jay Paulynice
 *
 */
public class DocumentMapper {
    /**
     * @param hit
     *            elastic search hit
     * @param pos
     *            according to relevance
     * @return {@link Document} object
     */
    public static Document map(final SearchHit hit, final int pos) {
        return new Document(getDocFieldsFromSearchHit(hit, pos));
    }

    /**
     * @param doc
     *            lucene document
     * @param id
     *            order of relevance
     * @return {@link Document} object
     */
    public static Document map(final org.apache.lucene.document.Document doc,
            final int id) {
        return new Document(getDocumentFromLuceneDoc(doc, id));
    }

    /**
     * @param doc
     * @param id
     * @return
     */
    private static List<Field> getDocumentFromLuceneDoc(
            final org.apache.lucene.document.Document doc, final int id) {
        final List<Field> fields = new ArrayList<Field>();
        final Field docIdField = new Field();
        docIdField.setFieldId("doc_id");
        docIdField.setName("#");
        docIdField.setI18nKey("searchapp.docId");
        docIdField.setFieldType("string");
        docIdField.setValue(String.valueOf(id));
        docIdField.setVisible(true);

        final Field docNameField = new Field();
        docNameField.setFieldId("doc_title");
        docNameField.setName("Title");
        docNameField.setI18nKey("searchapp.docTitle");
        docNameField.setFieldType("string");
        docNameField.setValue(doc.get("filename"));
        docNameField.setVisible(true);

        final Field authorField = new Field();
        authorField.setFieldId("doc_author");
        authorField.setName("Author");
        authorField.setI18nKey("searchapp.docAuthor");
        authorField.setFieldType("string");
        authorField.setValue(doc.get("author"));
        authorField.setVisible(true);

        final Field dateField = new Field();
        dateField.setFieldId("doc_date");
        dateField.setName("Last Modified");
        dateField.setI18nKey("searchapp.docDate");
        dateField.setFieldType("string");
        dateField.setValue(doc.get("lastModified"));
        dateField.setVisible(true);

        final Field created = new Field();
        created.setFieldId("doc_created");
        created.setName("Created");
        created.setI18nKey("searchapp.docCreated");
        created.setFieldType("string");
        created.setValue(doc.get("created"));
        created.setVisible(true);

        final Field sizeField = new Field();
        sizeField.setFieldId("doc_size");
        sizeField.setName("Size (Bytes)");
        sizeField.setI18nKey("searchapp.size");
        sizeField.setFieldType("string");
        sizeField.setValue(doc.get("size"));
        sizeField.setVisible(true);

        fields.add(docIdField);
        fields.add(docNameField);
        fields.add(authorField);
        fields.add(dateField);
        fields.add(created);
        fields.add(sizeField);

        return fields;
    }

    private static List<Field> getDocFieldsFromSearchHit(
            final SearchHit searchHit, final int id) {
        final Map<String, Object> fieldsMap = searchHit.getSource();
        final List<Field> fields = new ArrayList<Field>();
        final Field docIdField = new Field();
        docIdField.setFieldId("doc_id");
        docIdField.setName("#");
        docIdField.setI18nKey("searchapp.docId");
        docIdField.setFieldType("string");
        docIdField.setValue(String.valueOf(id));
        docIdField.setVisible(true);

        final Field docIdStringField = new Field();
        docIdStringField.setFieldId("doc_string_id");
        docIdStringField.setName("dId");
        docIdStringField.setI18nKey("searchapp.dId");
        docIdStringField.setFieldType("string");
        docIdStringField.setValue(searchHit.getId());
        docIdStringField.setVisible(false);

        final Field docNameField = new Field();
        docNameField.setFieldId("doc_title");
        docNameField.setName("Title");
        docNameField.setI18nKey("searchapp.docTitle");
        docNameField.setFieldType("string");
        docNameField.setValue(fieldsMap.get("filename").toString());
        docNameField.setVisible(true);

        final Field authorField = new Field();
        authorField.setFieldId("doc_author");
        authorField.setName("Author");
        authorField.setI18nKey("searchapp.docAuthor");
        authorField.setFieldType("string");
        authorField.setValue(fieldsMap.get("author").toString());
        authorField.setVisible(true);

        final Field dateField = new Field();
        dateField.setFieldId("doc_date");
        dateField.setName("Last Modified");
        dateField.setI18nKey("searchapp.docDate");
        dateField.setFieldType("string");
        dateField.setValue(fieldsMap.get("lastModified").toString());
        dateField.setVisible(true);

        final Field created = new Field();
        created.setFieldId("doc_created");
        created.setName("Created");
        created.setI18nKey("searchapp.docCreated");
        created.setFieldType("string");
        created.setValue(fieldsMap.get("created").toString());
        created.setVisible(true);

        final Field sizeField = new Field();
        sizeField.setFieldId("doc_size");
        sizeField.setName("Size (Bytes)");
        sizeField.setI18nKey("searchapp.size");
        sizeField.setFieldType("string");
        sizeField.setValue(fieldsMap.get("size").toString());
        sizeField.setVisible(true);

        fields.add(docIdField);
        fields.add(docIdStringField);
        fields.add(docNameField);
        fields.add(authorField);
        fields.add(dateField);
        fields.add(created);
        fields.add(sizeField);

        return fields;
    }
}
