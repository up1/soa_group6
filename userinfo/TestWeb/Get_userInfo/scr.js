/**
 * Created by jongzazaal on 6/3/2560.
 */


function getInfoByID() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8093/user?name=" + $("#inputid").val(),
        dataType: 'json',
        success: function (data) {

            $('#user_firstname_th').text(data.user_firstname_th);
            $('#user_lastname_th').text(data.user_lastname_th)

        }
    });
}

$("#buttonGetUserInfo").click(function (){
    console.log("click");
    getInfoByID();
});