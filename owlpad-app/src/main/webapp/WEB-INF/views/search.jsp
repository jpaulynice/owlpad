<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
	<head>
		<script data-main="resources/js/main" src="resources/js/vendor/require.js"></script>
		<!--<script src="./resources/js/script.min.js"></script>-->
		<link href="./resources/css/bootstrap.min.css" rel="stylesheet" type="text/css">
		<link href="./resources/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
		<link href="./resources/css/styles.css" rel="stylesheet">
		
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
				  	<div class="preview">
				  		<div class="empty-view" style="margin-right:auto;margin-left:auto;width:50%;">Click a row to see more details</div>
					</div>
				</div>
			</div>
		</div>
		<div class="spinner"></div>
	</body>
</html>