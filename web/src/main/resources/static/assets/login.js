(function ($) {
    $(function () {
       $('#login-submit').on('click', function (e) {
           e.preventDefault();
           let username = $('#username').val();
           let password = $('#password').val();
           let data = {
               username: username,
               password: password
           };
           $.ajax({
               method: 'POST',
               url: '/login',
               contentType: 'application/x-www-form-urlencoded',
               data: data
           }).then(data => {
               if (data.code === 200) {
                   window.location.assign('/ui/index.html');
               } else {
                   console.log(data);
                   $('#login-error').html(data.message);
               }
           }, data => {
               console.log(data.message);
           })
       })
    });
})(jQuery);