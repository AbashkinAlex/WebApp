$(document).ready(function () {

    $('#contact-form').validate({
        rules: {

            firstName: {
                minlength: 3,
                required: true
            },
            lastName: {
                minlength: 3,
                required: true
            },
            // email: {
            //     minlength: 3,
            //     required: true
            // },

            birthday: {
                required: false
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