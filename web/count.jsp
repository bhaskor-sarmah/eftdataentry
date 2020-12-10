<%-- 
    Document   : index
    Created on : Jan 29, 2020, 12:31:30 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Count Page</title>
        <jsp:include page="./fragments/styles.jsp" />
    </head>
    <body>
        <div class="loader"></div>
        <div class="container-fluid">
            <div class="row">
                <div class="col d-flex">
                    <span class="small ml-auto">Version: 1.0</span>
                </div>
            </div>
            <c:if test="${not empty msg}">
                <div class="row">
                    <div class="col-sm-12">
                        ${msg}
                    </div>
                </div>
            </c:if>
            <div class="row">
                <div class="col-sm-12">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Sl. No.</th>
                                <th>Date</th>
                                    <c:forEach var="user" items="${userList}">
                                    <th>${user}</th>
                                    </c:forEach>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="obj" varStatus="status" items="${countList}">
                                <tr>
                                    <td rowspan="${rowSpan}">${status.count}</td>
                                    <td rowspan="${rowSpan}">${obj.date}</td>
                                    <c:forEach var="user" items="${userList}">
                                        <c:forEach var="usr" items="${obj.userList}">
                                            <c:if test="${user == usr.userId}">
                                                <td>${usr.userCount}</td>
                                            </c:if>
                                        </c:forEach>
                                    </c:forEach>

                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <jsp:include page="./fragments/footer.jsp" />
        <jsp:include page="./fragments/scripts.jsp" />
    </body>
</html>
