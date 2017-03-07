/**
 * Created by sekcg on 3/7/2017.
 */
$(function () {
    $('.form-signin').submit(function (e) {
        var username = $(this).find('#username').next().val();
        var password = $(this).find('#password').next().val();
        $.ajax({
            type: 'POST',
            url: 'http://10.0.102.145:8094/authentication',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify({
                user_username: username,
                user_password: password
            }),
            error: function (data) {
                var result = JSON.parse(data.responseText.toLowerCase());
                if (result.response) {
                    window.location.href = '/dashboard';
                } else {
                    alert('Username not found');
                }
            }
        });
        e.preventDefault();
    });
});
