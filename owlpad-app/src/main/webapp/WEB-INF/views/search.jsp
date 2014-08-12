<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
	<head>
		<script data-main="./resources/js/search" src="resources/js/vendor/require.js"></script>
		<!--<script src="./resources/js/script.min.js"></script>-->
		<link href="./resources/css/bootstrap.min.css" rel="stylesheet" type="text/css">
		<link href="./resources/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
		<link href="./resources/css/styles.css" rel="stylesheet">
		<!--<script src="./resources/js/syntaxHighlighter/shCore.js"></script>
		<script src="./resources/js/syntaxHighlighter/shBrushJScript.js"></script>
		<script src="./resources/js/syntaxHighlighter/shBrushJava.js"></script>
		<script>SyntaxHighlighter.all();</script>-->
		<link rel="stylesheet" type="text/css" href="./resources/css/syntaxHighlighter/shCoreDefault.css">
		<title>Owlpad Search</title>
	</head>
	<body>
		<nav class="navbar navbar-default" role="navigation">
		</nav>
		<div class="bs-docs-section" style="float:left;width:60%;padding:10px;">
			<div class="bs-example">
				<div class="panel panel-default">
				  <div class="panel-heading">Documents</div>
				  	<div class="gridRegion">
					</div>
				</div>
			</div>
		</div>
		<div class="bs-docs-section" style="float:right;width:40%;padding:10px;">
			<div class="bs-example">
				<div class="panel panel-default">
				  <div class="panel-heading">Preview</div>
				  	<div class="previewRegion"></div>
				</div>
			</div>
		</div>		
	</body>
</html>