<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<form:form action="/category/index" modelAttribute="item" class="mb-4" enctype="multipart/form-data"> <!-- Thêm enctype -->
    <!-- ID Input -->
    <div class="mb-3">
        <form:label path="id" class="form-label">Category Id</form:label>
        <form:input path="id" class="form-control" placeholder="Category Id"/>
    </div>
    <!-- Name Input -->
    <div class="mb-3">
        <form:label path="name" class="form-label">Category Name</form:label>
        <form:input path="name" class="form-control" placeholder="Category Name"/>
    </div>
    <!-- Action Buttons -->
     <div class="d-flex justify-content-between">
        <button class="btn btn-success" formaction="/category/create">Create</button>
        <button class="btn btn-warning" formaction="/category/update">Update</button>
        <a href="/category/index" class="btn btn-secondary">Reset</a>
        <!-- Export Button -->
		<a href="/category/exportExcel" class="btn btn-info">Export to Excel</a>
        <!-- Import Button -->
		<form action="/category/import" method="post" enctype="multipart/form-data" class="d-inline">
		    <input type="file" name="file" accept=".xlsx" required />
		    <button type="submit" class="btn btn-primary">Import from Excel</button>
		</form>

    </div>	
</form:form>
