$(document).ready(function() {
    $("#submitButton").click(function() {

        var form = $("#signin-form");

        if (form[0].checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation()
        }
        form.addClass('was-validated');
    })
});