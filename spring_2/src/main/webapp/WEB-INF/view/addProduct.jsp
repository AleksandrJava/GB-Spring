<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <spring:form modelAttribute="productFromServer" method="post" action="/app/products/addThis">
            <spring:input path="id"/>
            <spring:input path="title" placeholder="title"/>
            <spring:input path="cost"/>
            <spring:button>add product</spring:button>
        </spring:form>
    </body>
</html>