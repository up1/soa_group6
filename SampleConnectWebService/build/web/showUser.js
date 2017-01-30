/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function arrayToTable(tableData) {
//                document.getElementById("tablea").remove();
    var table = $('<table id = "tablea"></table>');
    $(tableData).each(function (i, rowData) {
        var row = $('<tr></tr>');
        $(rowData).each(function (j, cellData) {
            row.append($('<td>' + cellData + '</td>'));
        });
        table.append(row);
    });
    return table;
}

function getAllPage(item_per_page){
    $.ajax({
        type: "GET",
        url: "http://10.0.102.218:9001/getPageAll?item_per_page="+item_per_page,
        dataType: 'json',
        success: function (data) {
            $('#posText').text(data);
        }
    });
}
function showUser(){
//    typeof(val)  === "undefined"
//    if(!typeof($("#tablea")) === "undefined"){
         $("#tablea").remove();
//    }
//        $("#preText").text("1");
    
   
    
    $.ajax({
        type: "GET",
        url: "http://10.0.102.218:9001/user2?item_per_page=" + $("#inputnum").val()+"&page="+$("#preText").text(),
        dataType: 'json',
        success: function (data) {

            var user = [];
            data.forEach(function (element) {
                user.push([element.id, element.firstname, element.lastname]);
            });
            $('body').append(arrayToTable(user));


        }
    });
}
showUser();

//OnValueChange
$("#inputnum").change('change keydown paste input', function () {
    $("#preText").text("1");
    showUser();
    getAllPage($("#inputnum").val()); 
    
});

$("#preButton").click(function (){
    if(parseInt($("#preText").text())-1===0){
        return;
    }
    $("#preText").text(parseInt($("#preText").text())-1);
    showUser();
});

$("#posButton").click(function (){
    if(parseInt($("#preText").text())+1>parseInt($("#posText").text())){
        return;
    }
    $("#preText").text(parseInt($("#preText").text())+1);
    showUser();
});

