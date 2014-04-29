search-app
==========
Travis CI Build Status
==========
[![Build Status](https://travis-ci.org/julesbond007/search-app.svg)](https://travis-ci.org/julesbond007/search-app)
Description
==============
A very simple search application built on top of apache lucene.  This is a work in progress.  I expect to complete all code in a few weeks.  I will try to document this as much as possible as well.  The goal is then to use ElasticSearch's REST search engine.

Projects in this repository: 

<ul>
<li><b>search-webapp</b>: the web application</li>
<li><b>search-service</b>: the api (set of interfaces)</li>
<li><b>search-domain</b>: domain objects shared by the api, service and the client (web-app)</li>
<li><b>search-service-impl</b>: search service implementation</li>
</ul>

Technology stack
================
<ul>
<li><b>Front-end:</b> Backbone, Marionette, Requirejs, Handlebars, require-handlebars-plugin (hbs), r.js and Node to minify and combine js files, Underscore, JQuery, and Bootstrap</li>
<li><b>Middle and Service tiers:</b> Java, Spring Framework, Apache CXF, Apache lucene for search.</li>
</ul>

Steps to work:

1. clone repo (git clone https://github.com/julesbond007/search-app.git)
2. import projects in eclipse
3. run "mvn clean install"
4. locate target folders and deploy war file for search-app and search-service-impl in tomcat

<p>To test the service as an app deployed under tomcat in 'searchService' using port 8080:</p>

<pre>curl -X POST -H 'Content-Type: application/json' -d 
'{
  "keyWord": "java",
  "maxHits": 10
}' 
'http://localhost:8080/searchService/api/v1/search'
</pre>


![alt tag](https://github.com/julesbond007/search-app/blob/master/search-webapp/src/main/webapp/resources/img/screenshot.png)
