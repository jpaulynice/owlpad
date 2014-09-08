<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
	<head>
		<script data-main="./resources/js/search" src="resources/js/vendor/require.js"></script>
		<!--<script src="./resources/js/script.min.js"></script>-->
		<link href="./resources/css/bootstrap.min.css" rel="stylesheet" type="text/css">
		<link href="./resources/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
		<link href="./resources/css/styles.css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="./resources/css/syntaxHighlighter/shCoreDefault.css">
		<link rel="shortcut icon" href="./resources/img/favicon.ico" type="image/x-icon">
		<link rel="icon" href="./resources/img/favicon.ico" type="image/x-icon">
		<title>Owlpad Search</title>
	</head>
	<body>
		<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
		</nav>
		<div class="bs-docs-section" style="float:left;width:50%;padding:10px;margin-top:60px;">
			<div class="bs-example">
				<div class="panel panel-default">
				  <div class="panel-heading">Preview</div>
				  	<div class="gridRegion">
					</div>
				</div>
			</div>
		</div>
		<div class="bs-docs-section" style="float:right;width:50%;padding:10px;margin-top:60px;">
			<div class="bs-example">
				<div class="panel panel-default">
				  <div class="panel-heading">Documents</div>
				  	<div class="previewRegion"></div>
				</div>
			</div>
		</div>	
		<div class="spinner"></div>	
	</body>
</html>