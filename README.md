OwlPad
==========
[![Build Status](https://travis-ci.org/julesbond007/owlpad.svg)](https://travis-ci.org/julesbond007/owlpad)

UI Screenshot
==========
![Settings Window](https://raw.github.com/julesbond007/owlpad/master/owlpad-app/src/main/webapp/resources/img/screenshot.png)

A very simple search application built on top of apache lucene and elasticsearch engine.  This is a work in progress.  I will try to document this as much as possible as well.

The original idea was to build an internal tool to quickly search and analyze source code repositories such as git, mercurial, svn, cvs, etc.

Projects in this repository: 

<ul>
<li><b>owlpad-app</b>: the web application</li>
<li><b>owlpad-service</b>: the api (set of interfaces)</li>
<li><b>owlpad-domain</b>: domain objects shared by the api, service and the client (web-app)</li>
<li><b>owlpad-service-impl</b>: service implementation</li>
</ul>

Technology stack
================
<ul>
<li><b>Front-end:</b> Backbone, Marionette, Requirejs, Handlebars, require-handlebars-plugin (hbs), r.js and Node to minify and combine js files, Underscore, JQuery, and Bootstrap</li>
<li><b>Middle and Service tiers:</b> Java, Spring Framework, Apache CXF, Gradle, Apache lucene and Elasticsearch</li>
</ul>

Dependencies:
 
1. Install Gradle or Maven
2. Install Elasticsearch

Steps to work:

1. clone repo: 
  <code>git clone https://github.com/julesbond007/owlpad.git</code>
2. using gradle: import projects in eclipse:
  <code>file-->import-->gradle project-->specify directory-->build model-->finish</code> 
or using maven 
<code>cd owlpad</code>
<code>mvn eclipse:eclipse</code>
<code>import as existing projects</code>
3. Using Jetty: debug as or run as:
<pre>
  a. owlpad-app:
     port: 8080
     context: /owlpad
     webappcontext: src/main/webapp

 web-app is now running: 
 localhost:8080/owlpad/search

  b. owlpad-service-impl:
     port: 9000
     context: /
     webappcontext: src/main/webapp

 service is now running: 
 localhost:9000/api/v1/search
</pre>

<p>To start index some documents by specifying a 'directoryToIndex'</p>

<b>index api:</b>
<pre>
curl -X POST -H 'Content-Type: application/json' -d 
'{
  "indexRequest":
  {
    "directoryToIndex":"/Users/julespaulynice/Documents/ws",
    "suffix":".java"
  }
}' 
'http://localhost:9000/api/v1/index'
</pre>

<b>search api:</b>
<pre>curl -X POST -H 'Content-Type: application/json' -d 
'{
  "searchRequest":
  {
    "keyWord":"lucene",
    "maxHits":"10"
  }
}' 
'http://localhost:9000/api/v1/search'
</pre>
