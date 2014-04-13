search-app
==========

A very simple keyword search application built on top of apache lucene.

<p>Example using curl if your service is deployed under tomcat using 8080 port:</p>

<pre>curl -X POST -H 'Content-Type: application/json' -d 
'{
  "keyWord":"java"
}' 
'http://localhost:8080/api/v1/search'
</pre>

Service log: 

<pre>
----------------------------
ID: 1
Address: http://localhost:8080/api/v1/search
Encoding: ISO-8859-1
Http-Method: POST
Content-Type: application/json
Headers: {Accept=[*/*], Content-Length=[18], content-type=[application/json], Host=[localhost:8080], 
User-Agent=[curl/7.30.0]}
Payload: {"keyWord":"java"}
--------------------------------------
Apr 12, 2014 9:25:52 PM org.apache.cxf.interceptor.AbstractLoggingInterceptor log
INFO: Outbound Message
---------------------------
ID: 1
Response-Code: 200
Content-Type: application/json
Headers: {Date=[Sun, 13 Apr 2014 01:25:52 GMT]}
Payload: {"status":null,"documents":null}
--------------------------------------
</pre>
