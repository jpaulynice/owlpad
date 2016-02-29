OwlPad
==========
[![Build Status](https://travis-ci.org/julesbond007/owlpad.svg)](https://travis-ci.org/julesbond007/owlpad)
Try it: [Search App](http://julesjaypaulynice.com:8080/owlpad/search)
UI Screenshot
==========
![Settings Window](https://raw.github.com/julesbond007/owlpad/master/owlpad-webapp/src/main/webapp/resources/img/screenshot.png)

A very simple search application built on top of apache lucene and elasticsearch engine.  This is a work in progress.  I will try to document this as much as possible as well.

The original idea was to build an internal tool to quickly search and analyze source code repositories such as git, mercurial, svn, cvs, etc.

Projects in this repository: 

<ul>
<li><b>owlpad-webapp</b>: web application</li>
<li><b>owlpad-service</b>: public api</li>
<li><b>owlpad-domain</b>: domain objects shared by the api, service and the client</li>
<li><b>owlpad-service-impl</b>: service implementation</li>
</ul>

Technology stack
================
<ul>
<li><b>Front-end:</b> Backbone, Marionette, Requirejs, Handlebars, require-handlebars-plugin (hbs), r.js and Node to minify and combine js files, Underscore, JQuery, and Bootstrap</li>
<li><b>Middle and Service tiers:</b> Java, Spring Framework, Apache CXF, Hibernate, MySQL, Gradle, Apache lucene and Elasticsearch</li>
</ul>

Dependencies:
 
1. Gradle
2. Elasticsearch
3. MySQL database
