package com.owlpad.service.impl.search;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import static org.elasticsearch.node.NodeBuilder.*;

import com.owlpad.domain.search.Document;
import com.owlpad.domain.search.Field;
import com.owlpad.domain.search.SearchRequest;
import com.owlpad.domain.search.SearchResponse;
import com.owlpad.service.search.SearchService;

/**
 * 
 * @author Jay Paulynice
 *
 */
public class ESSearchServiceImpl implements SearchService{
	
	@Override
	public SearchResponse search(SearchRequest searchRequest) {
		SearchResponse internalResponse = new SearchResponse();
		
		Client client = nodeBuilder().clusterName("elasticsearch").node().client();
		
		org.elasticsearch.action.search.SearchResponse response = client.prepareSearch("owlpad-index")
		        .setTypes("docs")
		        .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
		        .setQuery(QueryBuilders.matchQuery("contents",searchRequest.getKeyWord()))
		        .setFrom(0)
		        .setSize(searchRequest.getMaxHits())
		        .execute()
		        .actionGet();
		SearchHits hits = response.getHits();
		
		List<Document> docs = new ArrayList<>();
		int num = 1;
		for(SearchHit hit: hits){
			Document doc = getDocument(hit,num);
			docs.add(doc);
			num++;
		}
		internalResponse.setDocuments(docs);
		client.close();
		
		return internalResponse;
	}
	
	private Document getDocument(SearchHit searchHit, int docPosition){
		List<Field> fields = new ArrayList<Field>();
		Document docResult = new Document();
		Field docIdField = new Field();
		docIdField.setFieldId("doc_id");
		docIdField.setName("ID");
		docIdField.setI18nKey("searchapp.docId");
		docIdField.setFieldType("string");
		docIdField.setValue(String.valueOf(docPosition));

		Field docNameField = new Field();
		docNameField.setFieldId("doc_title");
		docNameField.setName("Title");
		docNameField.setI18nKey("searchapp.docTitle");
		docNameField.setFieldType("string");
		docNameField.setValue(searchHit.getFields().get("filename").getValue().toString());

		Field docPathField = new Field();
		docPathField.setFieldId("doc_path");
		docPathField.setName("URI");
		docPathField.setI18nKey("searchapp.docURI");
		docPathField.setFieldType("string");
		docPathField.setValue(searchHit.getFields().get("filepath").getValue().toString());
		
		Field authorField = new Field();
		authorField.setFieldId("doc_author");
		authorField.setName("Author");
		authorField.setI18nKey("searchapp.docAuthor");
		authorField.setFieldType("string");
		authorField.setValue(searchHit.getFields().get("author").getValue().toString());
		
		Field dateField = new Field();
		dateField.setFieldId("doc_date");
		dateField.setName("Last Modified");
		dateField.setI18nKey("searchapp.docDate");
		dateField.setFieldType("string");
		dateField.setValue(searchHit.getFields().get("lastModified").getValue().toString());
		
		Field created = new Field();
		created.setFieldId("doc_created");
		created.setName("Created");
		created.setI18nKey("searchapp.docCreated");
		created.setFieldType("string");
		created.setValue(searchHit.getFields().get("created").getValue().toString());
		
		Field sizeField = new Field();
		sizeField.setFieldId("doc_size");
		sizeField.setName("Size (Bytes)");
		sizeField.setI18nKey("searchapp.size");
		sizeField.setFieldType("string");
		sizeField.setValue(searchHit.getFields().get("size").getValue().toString());

		fields.add(docIdField);
		fields.add(docNameField);
		fields.add(docPathField);
		fields.add(authorField);
		fields.add(dateField);
		fields.add(created);
		fields.add(sizeField);

		docResult.setFields(fields);

		return docResult;
	}
}
