$(document).ready(function (){

    function ajaxSubmitForm(button, form) {
        form.submit(function(event){
            event.preventDefault();
        });
        button.on('click', function(){
                $.ajax({
                    type: form.attr('method'),
                    url: form.attr('action'),
                    data: form.serialize(),
                    success: function (data) {
                        $('.right-column').html(JsonHuman.format(data));
                    }
                });
            });
    }

//    ajaxSubmitForm($('#login-button'), $('#loginForm'));
    ajaxSubmitForm($('#login-button'), $('#loginFormSpringSecurity'));
    ajaxSubmitForm($('#create-button'), $('#createForm'));
    ajaxSubmitForm($('#find-button'), $('#findForm'));
    ajaxSubmitForm($('#remove-button'), $('#removeForm'));
    ajaxSubmitForm($('#find-all-button'), $('#findAllForm'));
    ajaxSubmitForm($('#update-button'), $('#updateForm'));
    ajaxSubmitForm($('#logout-button'), $('#logoutForm'));
});
