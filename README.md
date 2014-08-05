OwlPad
==========
[![Build Status](https://travis-ci.org/julesbond007/owlpad.svg)](https://travis-ci.org/julesbond007/owlpad)

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
<li><b>Middle and Service tiers:</b> Java, Spring Framework, Apache CXF, Gradle, Apache lucene for search.</li>
</ul>

Steps to work:

1. clone repo: 
  <code>git clone https://github.com/julesbond007/owlpad.git</code>
2. import projects in eclipse:
  <code>file-->import-->gradle project-->specify directory-->build model-->finish</code>

<p>To test the service as an app deployed under tomcat in 'service' using port 8080:</p>

<b>index service:</b>
<pre>
curl -X POST -H 'Content-Type: application/json' -d 
'{
  "indexRequest":
  {
    "directoryToIndex":"/Users/julespaulynice/Documents/ws",
    "suffix":".java"
  }
}' 
'http://localhost:8080/api/v1/index'
</pre>

<b>search service:</b>
<pre>curl -X POST -H 'Content-Type: application/json' -d 
'{
  "keyWord": "java",
  "maxHits": 10
}' 
'http://localhost:8080/service/api/v1/search'
</pre>

Screenshot
==========
![Settings Window](https://raw.github.com/julesbond007/owlpad/master/owlpad-app/src/main/webapp/resources/img/screenshot.png)
