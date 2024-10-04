<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<table class="table table-striped table-hover">
    <thead class="table-dark">
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="item" items="${items}">
            <tr>
                <td>${item.id}</td>
                <td>${item.name}</td>
                <td>
                    <a href="/category/edit/${item.id}" class="btn btn-primary btn-sm">Edit</a>
                    <a href="/category/delete/${item.id}" class="btn btn-danger btn-sm">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<c:if test="${not empty param.success}">
    <div class="alert alert-success">Category deleted successfully!</div>
</c:if>
<c:if test="${not empty param.error}">
    <div class="alert alert-danger">${param.error}</div>
</c:if>
