$(document).on('click', 'button', function () {
    var name = $(this).text();
    $.ajax({
        type: 'get',
        data: {page: name},
        url: 'result',
        success: function (result) {
            $("#table").empty();
            var list = JSON.parse(result);
            $.each(list, function () {
                $('#table').append('<tr><td>' + this['id'] + '</td><td>' + this['lastName'] +
                    '</td><td>' + this['firstName'] + '</td><tr>')//}
            });
        }
    })
})