$(document).ready(function () {
    $('.newBtn, .table .editBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text();
        if (text === "Edit") {
            $.get(href, function (compPart, status) {
                $('.myForm .idBox').hide();
                $('.myForm #id').val(compPart.id);
                $('.myForm #name').val(compPart.name);
                $('.myForm #isMustHave').prop("checked", compPart.mustHave);
                $('.myForm #quantity').val(compPart.quantity);
            });
        } else {
            $('.myForm .idBox').hide();
            $('.myForm #id').val('0');
            $('.myForm #name').val('');
            $('.myForm #isMustHave').prop("checked", false);
            $('.myForm #quantity').val('');
        }
        $('.myForm #myModal').modal();
    });

    $('.table .deleteBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#deleteModal #delete-ref').attr('href', href);
        $('#deleteModal').modal();
    });

    $('#searchBtn').on("click", function (event) {
        //console.log('Search');
        event.preventDefault();
        var search = $('#partSearch').val();
        window.location.replace('/?nameForSearch='+search);
    });

    $('#partSearch').keypress(function (event) {
        if (event.keyCode === 13) {
            event.preventDefault();
            $('#searchBtn').click();
        }
    });
});