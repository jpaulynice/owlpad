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

		Field docNameField = new Field();
		docNameField.setFieldId("doc_title");
		docNameField.setName("Title");
		docNameField.setI18nKey("searchapp.docTitle");
		docNameField.setFieldType("string");
		docNameField.setValue(doc.get("filename"));

		Field docPathField = new Field();
		docPathField.setFieldId("doc_path");
		docPathField.setName("URI");
		docPathField.setI18nKey("searchapp.docURI");
		docPathField.setFieldType("string");
		docPathField.setValue(doc.get("filepath"));
		
		Field authorField = new Field();
		authorField.setFieldId("doc_author");
		authorField.setName("Author");
		authorField.setI18nKey("searchapp.docAuthor");
		authorField.setFieldType("string");
		authorField.setValue(doc.get("author"));
		
		Field dateField = new Field();
		dateField.setFieldId("doc_date");
		dateField.setName("Last Modified");
		dateField.setI18nKey("searchapp.docDate");
		dateField.setFieldType("string");
		dateField.setValue(doc.get("lastModified"));
		
		Field created = new Field();
		created.setFieldId("doc_created");
		created.setName("Created");
		created.setI18nKey("searchapp.docCreated");
		created.setFieldType("string");
		created.setValue(doc.get("created"));
		
		Field sizeField = new Field();
		sizeField.setFieldId("doc_size");
		sizeField.setName("Size (Bytes)");
		sizeField.setI18nKey("searchapp.size");
		sizeField.setFieldType("string");
		sizeField.setValue(doc.get("size"));

		fields.add(docIdField);
		fields.add(docNameField);
		fields.add(docPathField);
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

		Field docNameField = new Field();
		docNameField.setFieldId("doc_title");
		docNameField.setName("Title");
		docNameField.setI18nKey("searchapp.docTitle");
		docNameField.setFieldType("string");
		docNameField.setValue(fieldsMap.get("filename").toString());

		Field docPathField = new Field();
		docPathField.setFieldId("doc_path");
		docPathField.setName("URI");
		docPathField.setI18nKey("searchapp.docURI");
		docPathField.setFieldType("string");
		docPathField.setValue(fieldsMap.get("filepath").toString());
		
		Field authorField = new Field();
		authorField.setFieldId("doc_author");
		authorField.setName("Author");
		authorField.setI18nKey("searchapp.docAuthor");
		authorField.setFieldType("string");
		authorField.setValue(fieldsMap.get("author").toString());
		
		Field dateField = new Field();
		dateField.setFieldId("doc_date");
		dateField.setName("Last Modified");
		dateField.setI18nKey("searchapp.docDate");
		dateField.setFieldType("string");
		dateField.setValue(fieldsMap.get("lastModified").toString());
		
		Field created = new Field();
		created.setFieldId("doc_created");
		created.setName("Created");
		created.setI18nKey("searchapp.docCreated");
		created.setFieldType("string");
		created.setValue(fieldsMap.get("created").toString());
		
		Field sizeField = new Field();
		sizeField.setFieldId("doc_size");
		sizeField.setName("Size (Bytes)");
		sizeField.setI18nKey("searchapp.size");
		sizeField.setFieldType("string");
		sizeField.setValue(fieldsMap.get("size").toString());
		

		fields.add(docIdField);
		fields.add(docNameField);
		fields.add(docPathField);
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
	 * @param fields the fields to set
	 */
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
}
