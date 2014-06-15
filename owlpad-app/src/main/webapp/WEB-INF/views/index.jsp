<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
	<head>
		<script data-main="resources/js/main" src="resources/js/vendor/require.js"></script>
		<!--<script src="./resources/js/script.min.js"></script>-->
		<link href="./resources/css/bootstrap.min.css" rel="stylesheet" type="text/css">
		<link href="./resources/css/docs.min.css" rel="stylesheet">
		<link href="./resources/css/styles.css" rel="stylesheet">
		<link href="./resources/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
		
		<title>Owlpad Indexing</title>
	</head>
	<body>
		<nav class="navbar navbar-default indexPage" role="navigation">
		</nav>
		<div class="row">
			<div class="col-lg-6 col-sm-6 col-12">
				<h4>Select files for indexing...</h4>
				<div class="input-group">
					<span class="input-group-btn">
						<span class="btn btn-primary btn-file">
							Browse<input type="file" multiple="">
						</span>
					</span>
					<input type="text" class="form-control" readonly="">
				</div>
			</div>
		</div>
		<script src="./resources/js/script.js"></script>
	</body>
</html>