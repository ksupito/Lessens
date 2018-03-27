function changePageNumber(nameButton) {
    var name = nameButton;
    $.ajax({
            type: 'get',
            data: {page: name},
            url: 'result',
            cache: false,
            success: function (result) {
                $("#table").empty();
                var list = JSON.parse(result);
                $.each(list, function () {
                        $('#table').append('<tr><td>' + this['id'] + '</td><td>' + this['lastName'] +
                            '</td><td>' + this['firstName'] + '</td><td>' + '<button class="btn btn-default" data-toggle="modal" data-target="#myModal" id="buttonShowMore"\n' +
                            '                            onclick="showMore(this)">\n' +
                            '                        <i class="glyphicon glyphicon-info-sign"></i>\n' +
                            '                        Show More \n' +
                            '                    </button>' + '</td><tr>');
                    }
                )
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
            success: function (result) {
                $('#showResult').empty();
                var inf = JSON.parse(result);
                var img = new Image();
                img.src = "data:image/jpeg;base64," + hexToBase64(inf.image);
                var width = "150";
                $('#showResult').append("Age : " + inf.age + '<br>' + "Gender : " + inf.gender + '<br>' + "Department : " + inf.department + '<br>' + "Position : " + inf.position + '<br>' + '<img src=' + img.src + ' width=' + width + '>');
            }

        }
    )
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

function hexToBase64(str) {
    return btoa(String.fromCharCode.apply(null, str.replace(/\r|\n/g, "").replace(/([\da-fA-F]{2}) ?/g, "0x$1 ").replace(/ +$/, "").split(" ")));
}

