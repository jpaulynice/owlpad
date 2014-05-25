({
  "baseUrl": "./",
  "mainConfigFile": "${basedir}/src/main/webapp/resources/js/main.js",
  "name": "main", 
  "include": ["vendor/require"], 
  "exclude": [], 
  "optimize": "uglify2", 
  "out": "${basedir}/src/main/webapp/resources/js/script.min.js", 
  "insertRequire": ["main"]
})