function changePageNumber(nameButton) {
    var name = nameButton;
    $.ajax({
            type: 'get',
            data: {page: name},
            url: 'result',
            cache: false,
            "timeout": 5000,
            success: function (result) {
                $("#table").empty();
                var list = JSON.parse(result);
                $.each(list, function () {
                        $('#table').append('<tr><td>' + this['id'] + '</td><td>' + this['lastName'] +
                            '</td><td>' + this['firstName'] + '</td><td>' + '<button id="buttonShowMore" onclick="showMore(this)">Show More</button>' + '</td><tr>');
                    }
                )
            },
            error: function () {
                $('#error').text('An error occurred!');
            }
        }
    )
}

function showMore(ths) {
    var tr = ths.parentNode.parentNode;
    var code = tr.getElementsByTagName("td")[0].innerHTML;
    $.ajax({
            type: 'get',
            data: {id: code},
            url: 'result',
            cache: false,
            "timeout": 5000,
            success: function (result) {
                $('#showResult').empty();
                var inf = JSON.parse(result);
                toggle_visibility("form");
                $('#showResult').append("Age : " + inf.age + '<br>' + "gender : " + inf.gender + '<br>' + "department : " + inf.department + '<br>' + "position : " + inf.position);
            },
            error: function () {
                $('#error').text('An error occurred!');
            }
        }
    )
}

function toggle_visibility(id) {
    var e = document.getElementById(id);
    if (e.style.display == 'block')
        e.style.display = 'none';
    else
        e.style.display = 'block';
}

$(document).ajaxStart(function () {
    var e = document.getElementById(loader);
    $("#loader").css("display", "block");
});

$(document).ajaxComplete(function () {
    $("#loader").css("display", "none");
});

$(document).ajaxError(function () {
    alert("An error occurred!");
});