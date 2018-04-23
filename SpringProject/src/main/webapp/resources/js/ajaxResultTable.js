function changePageNumber(nameButton) {
    $.ajax({
            type: 'get',
            data: {page: nameButton},
            url: 'result',
            cache: false,
            success: function (result) {
                $("#table").empty();
                var viewObject = jQuery.parseJSON(JSON.stringify(result));
                var list = viewObject.listOfUser;
                $.each(list, function () {
                        $('#table').append('<tr><td>' + this['id'] + '</td><td>' + this['lastName'] +
                            '</td><td>' + this['firstName'] + '</td><td>' + '<button class="btn btn-default" data-toggle="modal" data-target="#myModal" id="buttonShowMore"\n' +
                            '                            onclick="showMore(this)">\n' +
                            '                        <i class="glyphicon glyphicon-info-sign"></i>\n' + strings['result.showMore.button.js'] +
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
            url: 'details',
            cache: false,
            success: function (result) {
                $('#showResult').empty();
                var viewObject =jQuery.parseJSON(JSON.stringify(result));
                var inf = viewObject.userInfo;
                var img = new Image();
                img.src = "data:image/jpeg;base64," + hexToBase64(inf.image);
                var width = "250";
                $('#showResult').append('<img src=' + img.src + ' width=' + width + ' class="img-responsive pull-right" ' + '>' + '<h4>' + strings['popup.age.js'] + inf.age + '</h4><h4>' + strings['popup.gender.js'] + inf.gender + '</h4><h4>' + strings['popup.department.js'] + inf.department + '</h4><h4>' + strings['popup.position.js'] + inf.position + '</h4>');
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

