$(document).ready(function() {

    var rootURL = document.URL;

    loadHistory(rootURL);

    function loadHistory(url) {
        $("#searchedvalues").empty();
        $.ajax({
            dataType: "JSON",
            contentType: 'application/json',
            type: "GET",
            url: url + "messages"
        }).then(function(response) {
            if ($.isEmptyObject(response)) {
                UIkit.notify({message: 'История отправки пустая!', status: 'info', timeout: 5000, pos: 'top-center'});
            } else {
                renderDetails(response);
            }
        });
    }

    $("#sendsms").click(function() {
        var jsonObject = {"phnum" : $("#smsnumber").val() , "msgtxt" : $("#smstext").val()};
        var obj = JSON.stringify(jsonObject);

        blockFields(true);

        $(".msgstatus").text("СТАТУС: Идёт отправка...");

        if((/^79\d{9}$/).test($("#smsnumber").val())) {

            $.ajax({
                dataType: "JSON",
                contentType: 'application/json',
                type: "POST",
                url: rootURL + "send",
                data: obj
            }).then(function (response) {
                switch (response['errcode']) {
                    case 0:
                        $(".msgstatus").text("СТАТУС: " + response['message']).css('color', 'green');
                        UIkit.notify({
                            message: response['message'],
                            status: 'success',
                            timeout: 5000,
                            pos: 'top-center'
                        });
                        $("#smsnumber").val();
                        $("#smstext").val();
                        break;
                    case 1:
                        $(".msgstatus").text("СТАТУС: " + response['message']).css('color', 'red');

                        UIkit.notify({
                            message: response['message'],
                            status: 'danger',
                            timeout: 5000,
                            pos: 'top-center'
                        });
                        break;
                    case 2:
                        $(".msgstatus").text("СТАТУС: " + response['message']).css('color', 'red');
                        UIkit.notify({
                            message: response['message'],
                            status: 'danger',
                            timeout: 5000,
                            pos: 'top-center'
                        });
                        break;
                    case 3:
                        $(".msgstatus").text("СТАТУС: " + response['message']).css('color', 'red');
                        UIkit.notify({
                            message: response['message'],
                            status: 'danger',
                            timeout: 5000,
                            pos: 'top-center'
                        });
                        break;
                    case 4:
                        $(".msgstatus").text("СТАТУС: " + response['message']).css('color', 'red');
                        UIkit.notify({
                            message: response['message'],
                            status: 'danger',
                            timeout: 5000,
                            pos: 'top-center'
                        });
                        break;
                }
                loadHistory(rootURL);
            });
        } else {
            UIkit.notify({message: "Ошибка! Номер в неверном формате!", status: 'danger', timeout: 5000, pos: 'top-center'});
            $(".msgstatus").text("СТАТУС: Номер в неверном формате!").css('color', 'red');
        }
        blockFields(false);
    });

    function renderDetails(response) {
        var globalCycleCounter = 0;
        var rowCounter = 0;

        var id_message = "";
        var id_Cell = "";
        var phone_Cell = "";
        var date_Cell = "";
        var status_Cell = "";
        var message_Cell = "";

        var isNeedResend = false;

        for (var property in response) {
            rowCounter++;
            for(var prop in response[property]) {
                switch (prop) {
                    case "id":
                        id_Cell = rowCounter;
                        id_message = response[property][prop];
                        break;
                    case "phnum":
                        phone_Cell = response[property][prop];
                        break;
                    case "msgdate":
                        date_Cell = response[property][prop];
                        break;
                    case "status":
                        status_Cell = response[property][prop];
                        break;
                    case "msgtxt":
                        message_Cell = response[property][prop];
                        break;
                }
            }

            isNeedResend = (status_Cell === 'Отправлено') ? 'disabled' : '';

            singleTableRow = "<tr id='searchresults'>" +
                "<td class='tg-s6z2'>" + id_Cell + "</td>" +
                "<td class='tg-s6z2'>" + id_message + "</td>" +
                "<td class='tg-s6z2'>" + phone_Cell + "</td>" +
                "<td class='tg-s6z2'>" + date_Cell + "</td>" +
                "<td class='tg-s6z2'>" + status_Cell + "</td>" +
                "<td class='tg-s6z2'>" + message_Cell + "</td>" +
                "<td class='tg-s6z2'><input type='button' class='option_resend' id='resend_" + id_message + "' href='#' alt='повторить отправку' " + isNeedResend + "></td>" +
                "<td class='tg-s6z2'><input type='button' class='option_delete' id='delete_" + id_message + "' alt='удалить'></td>" +
            "</tr>";

            $("#resultstable").append(singleTableRow);
            singleTableRow = "";
            globalCycleCounter++;
        }
    }


    $("#smsnumber").keydown(function (e) {
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
            (e.keyCode == 65 && ( e.ctrlKey === true || e.metaKey === true ) ) ||
            (e.keyCode >= 35 && e.keyCode <= 40)) {
            return;
        }
        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
            UIkit.notify("Ошибка! Это поле только для цифр!", {status:'danger', pos:'top-center', timeout: 2000});
            e.preventDefault();
        }
    });

    $("#smsnumber").keyup(function(){
        if($(this).val().length !=0) {
            $("#smstext").attr("disabled", false);
        } else {
            $("#smstext").attr("disabled", true);
            $("#sendsms").attr("disabled", true);
        }
    });

    $("#smstext").keyup(function(){
        if($(this).val().length !=0)
            $("#sendsms").attr("disabled", false);
        else
            $("#sendsms").attr("disabled",true);
    });

    $(document).delegate("input[id^='delete']", "click", function (e) {

        e.preventDefault();

        var messageToDelete = $(this).attr('id').replace(/delete_/, '');

        if (confirm("Запись № " + messageToDelete+ " будет удалена! Продолжить?")) {

            var jsonObject = {"id" : messageToDelete};
            var obj = JSON.stringify(jsonObject);

            $.ajax({
                dataType: "JSON",
                contentType: 'application/json',
                type: "POST",
                url: rootURL + "delete",
                data: obj
            }).then(function(response) {
                loadHistory(rootURL);
            });

        } else {
            return false;
        }
    });

    function blockFields(prop) {
        var selectorsSet = {smsnumber:1, smstext:2, sendsms:3};
        for (var selectors in selectorsSet) {
            $('#' + selectors ).attr('disabled', prop);
        }
    }
});
