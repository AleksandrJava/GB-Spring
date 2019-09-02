<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
    <body>
        <h1>All Products</h1>
        <c:forEach var="product" items="${products}">
            <span>${product.id} ${product.title} ${product.cost}</span>
            <br>
        </c:forEach>
    </body>
</html>