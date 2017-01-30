<%-- 
    Document   : index
    Created on : Jan 28, 2017, 9:56:00 AM
    Author     : jongzazaal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <script src="hello.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <div>
            <p id="limit">Limit Page</p>
            <input id="inputnum" type="number" value="5" >
            <p class="user-id">The ID is </p>
            <p class="user-firstname">The firstname is </p>
            <p class="user-lastname">The lastname is </p>
        </div>
        <div>
            <button id="preButton" ><<</button>
            <a id = "preText">1</a>
            /
            <a id = "posText">15</a>
            <button id="posButton" >>></button>
            
        </div>
        
        <script src="showUser.js"></script>


    </body>
</html>
