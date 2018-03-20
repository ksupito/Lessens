$(document).on('click', 'button', function () {
    var name1 = $(this).text();
    $.ajax({
        type: 'get',
        data: {page: name1},
        url: 'result',
        success: function (result1) {
            $("#table").empty();
            var list = JSON.parse(result1);
            $.each(list, function () {
                $('#table').append('<tr><td>' + this['id'] + '</td><td>' + this['lastName'] +
                    '</td><td>' + this['firstName'] + '</td><tr>')//}
            });
        }
    })
})