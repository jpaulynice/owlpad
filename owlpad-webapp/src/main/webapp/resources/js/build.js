({
    "baseUrl" : "./",
    "mainConfigFile" : "main.js",
    "dir" : "dist",
    "optimize" : "uglify2",
    "removeCombined" : true,
    "findNestedDependencies" : true,
    modules : [{
        name : 'main'
    }, {
        name : 'search',
        exclude : ['main']
    }, {
        name : 'index',
        exclude : ['main']
    }]
})