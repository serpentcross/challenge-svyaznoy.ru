<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Задание от Связного</title>
    <link rel="stylesheet" href="<c:url value='/static/css/bootstrap.css' />"/>
    <link rel="stylesheet" href="<c:url value='/static/css/bootstrap-theme.css' />"/>
    <link rel="stylesheet" href="<c:url value='/static/css/bootstrap-override.css' />"/>
    <link rel="stylesheet" href="<c:url value='/static/css/uikit/uikit.min.css' />"/>
    <link rel="stylesheet" href="<c:url value='/static/css/uikit/components/notify.min.css' />"/>
    <script src="<c:url value='/static/js/jquery-1.12.3.min.js' />"></script>
    <script src="<c:url value='/static/js/uikit/uikit.min.js' />"></script>
    <script src="<c:url value='/static/js/uikit/components/notify.min.js' />"></script>
    <script src="<c:url value='/static/js/svyaznoy-scripts.js' />"></script>
</head>
    <body>
        <div class="container">
            <fieldset class="group-border" style="width: 70%; margin: auto;">
                <br>
                <legend class="group-border" style="text-align: center"><h2>Сервис отправки сообщений</h2></legend>
                <div class="row">
                    <div class="col-md-3 center_div">

                        <div class="form-group">
                            <label for="smsnumber">Номер телефона</label>
                            <input type="text" class="form-control" rows="5" id="smsnumber" placeholder="79031234567" style="width:600px;resize:none" maxlength="11" pattern="^79\d{9}$" required="required">
                        </div>

                        <div class="form-group">
                            <label for="smstext">Сообщение</label>
                            <textarea class="form-control" rows="5" id="smstext" placeholder="Текст сообщения" style="width:600px;resize:none" disabled></textarea>
                        </div>

                        <div class="form-group">
                            <button type="button" id="sendsms" class="btn btn-primary" disabled>Отправить сообщение</button>
                        </div>
                    </div>
                </div>
            </fieldset>
            <hr>
            <div class="msgstatus"></div>
            <br>
            <div class="table-responsive">
                <table class="table table-striped table-hover tg" id="resultstable">
                    <thead>
                        <tr class="bg-primary">
                            <th class="tg-s6z2">#</th>
                            <th class="tg-s6z2">Message ID</th>
                            <th class="tg-s6z2">Phone</th>
                            <th class="tg-s6z2">Sent Date</th>
                            <th class="tg-s6z2">Sent Status</th>
                            <th class="tg-s6z2">Message</th>
                            <th class="tg-s6z2"  colspan="2">Options</th>
                        </tr>
                    </thead>
                    <tbody id="searchedvalues">
                    </tbody>
                </table>
                <div class="col-md-12 text-center">
                    <ul class="pagination pagination-lg pager" id="myPager"></ul>
                </div>
            </div>
        </div>
    </body>
</html>