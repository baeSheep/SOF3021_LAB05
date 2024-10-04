<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Sorting</title>
    <!-- Bootstrap 5 CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container">
    <h3 class="mt-4 mb-4 text-center">SORTING BY ${field}</h3>
    
    <table class="table table-hover table-striped">
        <thead class="table-dark">
            <tr>
                <th><a href="/product/sort?field=id" class="text-light">Id</a></th>
                <th><a href="/product/sort?field=name" class="text-light">Name</a></th>
                <th><a href="/product/sort?field=price" class="text-light">Price</a></th>
                <th><a href="/product/sort?field=createDate" class="text-light">Date</a></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="item" items="${items}">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.name}</td>
                    <td>${item.price}</td>
                    <td>${item.createDate}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- Bootstrap 5 JS and Popper.js -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
