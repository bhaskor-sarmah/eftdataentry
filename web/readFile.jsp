<%-- 
    Document   : index
    Created on : Jan 29, 2020, 12:31:30 PM
    Author     : admin
--%>

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
        <jsp:include page="./fragments/header.jsp" />
        <div class="container-fluid">
            <div class="row" style="margin-top:225px;">
                <div class="col-3"></div>
                <div class="col-6 text-center">
                    <c:if test="${not empty msg}">${msg}</c:if>
                </div>
                <div class="col-3"></div>
                <div class="col-3"></div>
                <div class="col-6 alert alert-info" style="height: 150px;padding-top: 30px;">
                    <form action="./ReadFile" method="post">
                        <label for="path">Enter the Path to Read</label>
                        <input type="text"  name="path" id="path" value="${imagePathRead}" style="width:240px;"/>
                        &nbsp;&nbsp;<input class="btn btn-primary" type="submit" value="Read File" name="Submit" style=""/>
                    </form>
                    <form action="./DigitizeFile" method="post">
                        &nbsp;&nbsp;<input class="btn btn-success" type="submit" value="Start Digitization" name="Submit"/>
                    </form>
                </div>
                <div class="col-3"></div>
            </div>
        </div>
        <jsp:include page="./fragments/footer.jsp" />
        <jsp:include page="./fragments/scripts.jsp" />
        <script type="text/javascript" src="./fullscreenJs/jquery.fullscreen.min.js"></script>
    </body>
</html>
