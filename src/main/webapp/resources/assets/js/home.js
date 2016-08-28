$(document).ready(function () {

    $('#contact-form').validate({
        rules: {

            firstName: {
                minlength: 3
            },
            lastName: {
                minlength: 3
            },
            email: {
                minlength: 3
            },

            birthday: {
                required: true
            },

            email: {
                required: true,
                email: true
            },
            password: {
                minlength: 8,
                required: true
            },
            conf: {
                equalTo : "#password",
                required: true
            }


        },
        highlight: function (element) {
            $(element).closest('.control-group').removeClass('success').addClass('error');
        },
        success: function (element) {
            element.addClass('valid')
                .closest('.control-group').removeClass('error').addClass('success');
        }
    });

    $('#contact-formL').validate({
        rules: {

            emailL:{
                email: true

            },

            password: {
                minlength: 8

            }

        },
        highlight: function (element) {
            $(element).closest('.control-group').removeClass('success').addClass('error');
        },
        success: function (element) {
            element.addClass('valid')
                .closest('.control-group').removeClass('error').addClass('success');
        }
    });



});