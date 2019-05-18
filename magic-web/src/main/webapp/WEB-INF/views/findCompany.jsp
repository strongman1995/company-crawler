<%@ page import="cn.thu.info.model.CompanyWithBLOBs" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: bo
  Date: 2015/7/11
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>findCompany</h1>
<table>
    <% List<CompanyWithBLOBs> list =(List<CompanyWithBLOBs>) request.getAttribute("companies");%>
    <% out.print(list.size());%>
    <c:forEach items="${companies}" var="c">
        <tr>
            <td>${c.name}</td>
            <td>${c.website}</td>
            <td>${c.city}</td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
