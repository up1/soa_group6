/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
    $.ajax({
        url: "http://localhost:9001/user?id=2"
    }).then(function(data) {
       $('.user-id').append(data.id);
       $('.user-firstname').append(data.firstname);
       $('.user-lastname').append();
    });
});
