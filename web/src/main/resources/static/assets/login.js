(function ($) {
    $(function () {
       $('#login-submit').on('click', function (e) {
           e.preventDefault();
           let username = $('#username').val();
           let password = $('#password').val();
           let captcha = $('#captcha').val();
           let data = {
               username: username,
               password: password,
               captcha: captcha
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
           });
       });
       
       $('#captchaImg').on('click', function () {
           let src = $(this).attr('src');
           src = src.split('?')[0] + '?' + Math.random();
           $(this).attr('src', src);
       });
    });
})(jQuery);