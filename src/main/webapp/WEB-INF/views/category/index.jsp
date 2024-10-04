<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Category Management</title>
    <!-- Bootstrap 5 CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container">
    <h3 class="mt-4 mb-4 text-center">CATEGORY MANAGEMENT</h3>
	    
		    
    <!-- Include form -->
    <jsp:include page="_Form.jsp"/>

    <hr class="my-4">

    <!-- Include table -->
    <jsp:include page="_Table.jsp"/>

    <!-- Bootstrap 5 JS and Popper.js -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
