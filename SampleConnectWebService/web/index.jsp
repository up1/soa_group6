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
            <p class="user-id">The ID is </p>
            <p class="user-firstname">The firstname is </p>
            <p class="user-lastname">The lastname is </p>
        </div>
        <table id="excelDataTable" border="1">
        </table>
        <script>
            function arrayToTable(tableData) {
                var table = $('<table></table>');
                $(tableData).each(function (i, rowData) {
                    var row = $('<tr></tr>');
                    $(rowData).each(function (j, cellData) {
                        row.append($('<td>' + cellData + '</td>'));
                    });
                    table.append(row);
                });
                return table;
            }
//
//$('body').append(arrayToTable([
//    ["John","Slegers",4],
//    ["Tom","Stevens",25],
//    ["An","Davies",28],
//    ["Miet","Hansen",42],
//    ["Eli","Morris",18]
//]));
            $.ajax({
                type: "GET",
                url: "http://192.168.1.6:9001/user2",
                dataType: 'json',
                success: function (data) {

                    var user = [];
                    data.forEach(function (element) {
                        console.log(element.id);
                        user.push([element.id, element.firstname, element.lastname]);
                    });

                    $('body').append(arrayToTable(user));
                }
            });
        </script>


    </body>
</html>
