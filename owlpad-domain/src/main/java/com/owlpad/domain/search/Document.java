package com.owlpad.domain.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.elasticsearch.search.SearchHit;

/**
 * A document is simply a file that can be in different formats.
 *
 * @author Jay Paulynice
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "document")
public class Document {
    @XmlElement(name = "fields")
    private List<Field> fields;

    public Document() {

    }

    public Document(final SearchHit hit, final int pos) {
        this.setFields(getDocFieldsFromSearchHit(hit, pos));
    }

    public Document(final org.apache.lucene.document.Document doc, final int id) {
        this.setFields(getDocumentFromLuceneDoc(doc, id));
    }

    public List<Field> getDocumentFromLuceneDoc(final org.apache.lucene.document.Document doc, final int id) {
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

    private List<Field> getDocFieldsFromSearchHit(final SearchHit searchHit, final int id) {
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

    /**
     * @return the fields
     */
    public List<Field> getFields() {
        return fields;
    }

    /**
     * @param fields
     *            the fields to set
     */
    public void setFields(final List<Field> fields) {
        this.fields = fields;
    }
}
