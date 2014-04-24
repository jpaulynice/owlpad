<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
	<head>
		<script data-main="resources/js/main" src="resources/js/vendor/require.min.js"></script>
		<link href="./resources/css/styles.css" rel="stylesheet">
		<title>Search App</title>
	</head>
	<body>
		<div class="main">
			<div class="content">
			</div>
		</div>
		<script type="text/x-handlebars-template" id="gridView">
	<thead>        
    	<tr>
			{{#each headers}}
      			<th>{{this}}</th>
			{{/each}}
      	</tr>
   	 </thead>
    <tbody></tbody>
	<tfooter></tfooter>
	</script>
	
	<script type="text/x-handlebars-template" id="rowView">
	{{#each tableRow}}
		<td>
			{{this}}
        </td>
	{{/each}}
</script>
</body>
</html>