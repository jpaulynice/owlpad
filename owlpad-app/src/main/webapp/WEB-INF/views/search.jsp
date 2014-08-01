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
		<style>
			.spinner {
			    display:    none;
			    position:   fixed;
			    z-index:    1000;
			    top:        0;
			    left:       0;
			    height:     100%;
			    width:      100%;
			    background: rgba( 255, 255, 255, .8 ) 
			                url('http://sampsonresume.com/labs/pIkfp.gif') 
			                50% 50% 
			                no-repeat;
			}
			
			body.loading {
			    overflow: hidden;   
			}
			
			body.loading .spinner {
			    display: block;
			}
		</style>
	</head>
	<body>
		<nav class="navbar navbar-default" role="navigation">
		</nav>
		<div class="bs-docs-section">
			<div class="bs-example">
				<div class="panel panel-default">
				  <div class="panel-heading">Documents</div>
				  	<div class="gridRegion">
					</div>
				</div>
			</div>
		</div>
		<div class="spinner"></div>
	</body>
</html>