	<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Pagination</title>
    <!-- Bootstrap 5 CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-5">
    <h3 class="text-center">PRODUCT PAGINATION</h3>
    
    <!-- Bảng hiển thị sản phẩm -->
    <table class="table table-bordered table-hover">
        <thead class="table-dark">
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Price</th>
                <th>Date</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="item" items="${page.content}">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.name}</td>
                    <td>${item.price}</td>
                    <td>${item.createDate}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- Phân trang -->
    <nav aria-label="Page navigation">
        <ul class="pagination justify-content-center">
            <li class="page-item ${page.number == 0 ? 'disabled' : ''}">
                <a class="page-link" href="/product/page?p=0">First</a>
            </li>
            <li class="page-item ${page.number == 0 ? 'disabled' : ''}">
                <a class="page-link" href="/product/page?p=${page.number-1}">Previous</a>
            </li>
            <li class="page-item ${page.number == page.totalPages-1 ? 'disabled' : ''}">
                <a class="page-link" href="/product/page?p=${page.number+1}">Next</a>
            </li>
            <li class="page-item ${page.number == page.totalPages-1 ? 'disabled' : ''}">
                <a class="page-link" href="/product/page?p=${page.totalPages-1}">Last</a>
            </li>
        </ul>
    </nav>

    <!-- Thông tin phân trang -->
    <ul class="list-group">
        <li class="list-group-item">Số sản phẩm hiện tại: ${page.numberOfElements}</li>
        <li class="list-group-item">Trang số: ${page.number + 1} / ${page.totalPages}</li>
        <li class="list-group-item">Kích thước trang: ${page.size}</li>
        <li class="list-group-item">Tổng số sản phẩm: ${page.totalElements}</li>
    </ul>

    <!-- Bootstrap 5 JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
