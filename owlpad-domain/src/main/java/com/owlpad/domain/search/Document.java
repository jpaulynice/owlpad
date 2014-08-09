package com.owlpad.domain.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.search.SearchHit;

/**
 * A document is simply a file that can be in different formats.
 * 
 * @author Jay Paulynice
 *
 */
public class Document {
	private List<Field> fields;
	
	public Document(){
		
	}
	
	public Document(SearchHit hit,int pos){
		this.setFields(getDocFieldsFromSearchHit(hit,pos));
	}
	
	public Document(org.apache.lucene.document.Document doc, int id){
		this.setFields(getDocumentFromLuceneDoc(doc,id));
	}
	
	public List<Field> getDocumentFromLuceneDoc(org.apache.lucene.document.Document doc, int id){
		List<Field> fields = new ArrayList<Field>();
		Field docIdField = new Field();
		docIdField.setFieldId("doc_id");
		docIdField.setName("ID");
		docIdField.setI18nKey("searchapp.docId");
		docIdField.setFieldType("string");
		docIdField.setValue(String.valueOf(id));
		docIdField.setVisible(true);

		Field docNameField = new Field();
		docNameField.setFieldId("doc_title");
		docNameField.setName("Title");
		docNameField.setI18nKey("searchapp.docTitle");
		docNameField.setFieldType("string");
		docNameField.setValue(doc.get("filename"));
		docNameField.setVisible(true);

		/*
		Field docPathField = new Field();
		docPathField.setFieldId("doc_path");
		docPathField.setName("URI");
		docPathField.setI18nKey("searchapp.docURI");
		docPathField.setFieldType("string");
		docPathField.setValue(doc.get("filepath"));
		*/
		
		Field authorField = new Field();
		authorField.setFieldId("doc_author");
		authorField.setName("Author");
		authorField.setI18nKey("searchapp.docAuthor");
		authorField.setFieldType("string");
		authorField.setValue(doc.get("author"));
		authorField.setVisible(true);
		
		Field dateField = new Field();
		dateField.setFieldId("doc_date");
		dateField.setName("Last Modified");
		dateField.setI18nKey("searchapp.docDate");
		dateField.setFieldType("string");
		dateField.setValue(doc.get("lastModified"));
		dateField.setVisible(true);
		
		Field created = new Field();
		created.setFieldId("doc_created");
		created.setName("Created");
		created.setI18nKey("searchapp.docCreated");
		created.setFieldType("string");
		created.setValue(doc.get("created"));
		created.setVisible(true);
		
		Field sizeField = new Field();
		sizeField.setFieldId("doc_size");
		sizeField.setName("Size (Bytes)");
		sizeField.setI18nKey("searchapp.size");
		sizeField.setFieldType("string");
		sizeField.setValue(doc.get("size"));
		sizeField.setVisible(true);

		fields.add(docIdField);
		fields.add(docNameField);
		//sfields.add(docPathField);
		fields.add(authorField);
		fields.add(dateField);
		fields.add(created);
		fields.add(sizeField);

		return fields;
	}
	
	private List<Field> getDocFieldsFromSearchHit(SearchHit searchHit,int id){
		Map<String,Object> fieldsMap = searchHit.getSource();
		List<Field> fields = new ArrayList<Field>();
		Field docIdField = new Field();
		docIdField.setFieldId("doc_id");
		docIdField.setName("ID");
		docIdField.setI18nKey("searchapp.docId");
		docIdField.setFieldType("string");
		docIdField.setValue(String.valueOf(id));
		docIdField.setVisible(true);
		
		Field docIdStringField = new Field();
		docIdStringField.setFieldId("doc_string_id");
		docIdStringField.setName("dId");
		docIdStringField.setI18nKey("searchapp.dId");
		docIdStringField.setFieldType("string");
		docIdStringField.setValue(searchHit.getId());
		docIdStringField.setVisible(false);

		Field docNameField = new Field();
		docNameField.setFieldId("doc_title");
		docNameField.setName("Title");
		docNameField.setI18nKey("searchapp.docTitle");
		docNameField.setFieldType("string");
		docNameField.setValue(fieldsMap.get("filename").toString());
		docNameField.setVisible(true);
		
		Field sourceField = new Field();
		sourceField.setFieldId("doc_source");
		sourceField.setName("Source");
		sourceField.setI18nKey("searchapp.docSource");
		sourceField.setFieldType("string");
		sourceField.setValue(fieldsMap.get("contents").toString());
		sourceField.setVisible(false);

		/*
		Field docPathField = new Field();
		docPathField.setFieldId("doc_path");
		docPathField.setName("URI");
		docPathField.setI18nKey("searchapp.docURI");
		docPathField.setFieldType("string");
		docPathField.setValue(fieldsMap.get("filepath").toString());
		*/
		
		Field authorField = new Field();
		authorField.setFieldId("doc_author");
		authorField.setName("Author");
		authorField.setI18nKey("searchapp.docAuthor");
		authorField.setFieldType("string");
		authorField.setValue(fieldsMap.get("author").toString());
		authorField.setVisible(true);
		
		Field dateField = new Field();
		dateField.setFieldId("doc_date");
		dateField.setName("Last Modified");
		dateField.setI18nKey("searchapp.docDate");
		dateField.setFieldType("string");
		dateField.setValue(fieldsMap.get("lastModified").toString());
		dateField.setVisible(true);
		
		Field created = new Field();
		created.setFieldId("doc_created");
		created.setName("Created");
		created.setI18nKey("searchapp.docCreated");
		created.setFieldType("string");
		created.setValue(fieldsMap.get("created").toString());
		created.setVisible(true);
		
		Field sizeField = new Field();
		sizeField.setFieldId("doc_size");
		sizeField.setName("Size (Bytes)");
		sizeField.setI18nKey("searchapp.size");
		sizeField.setFieldType("string");
		sizeField.setValue(fieldsMap.get("size").toString());
		sizeField.setVisible(true);
		

		fields.add(docIdField);
		fields.add(docIdStringField);

		fields.add(docNameField);
		//fields.add(docPathField);
		fields.add(authorField);
		fields.add(dateField);
		fields.add(created);
		fields.add(sizeField);
		fields.add(sourceField);

		return fields;
	}
	
	/**
	 * @return the fields
	 */
	public List<Field> getFields() {
		return fields;
	}

	/**
	 * @param fields the fields to set
	 */
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
}
