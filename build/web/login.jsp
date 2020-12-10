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
        <title>JSP Page</title>
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
            <div class="row">
                <div class="col-4"></div>
                <div class="col-4" style="margin-top: 120px;">
                    <div class="card">
                        <div class="card-header bg-secondary text-white">
                            <h5 class="card-title">Login | eFT Digitization</h5>
                        </div>
                        <div class="card-body">
                            <c:if test="${not empty msg}">${msg}</c:if>
                            <form action="./Login" method="post">
                                <fieldset>
                                    <!-- Login Controls -->
                                    <div class="form-group">
                                        <label for="username">Username</label>
                                        <input type="text" class="form-control" id="username" name="username"
                                               placeholder="Username">
                                    </div>
                                    <div class="form-group">
                                        <label for="password">Password</label>
                                        <input type="password" class="form-control" id="password" name="password"
                                               placeholder="Password">
                                    </div>
                                    <!-- Login Button -->
                                    <div class="form-actions" style="margin-top: 12px;">
                                        <button type="submit" class="btn btn-success">Log in</button>
                                        <a href="./GetCount" target="_blank" class="btn btn-primary">Report</a>
                                    </div>
                                </fieldset>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-4"></div>
            </div>
        </div>
        <jsp:include page="./fragments/footer.jsp" />
        <jsp:include page="./fragments/scripts.jsp" />
    </body>
</html>
