<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><spring:message code="title"/></title>
        <!--<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>-->
        <script src="<c:url value="/resources/js/input.js" />"></script>
        <script src="<c:url value="/resources/jquery/jquery.js" />"></script>
        <script src="<c:url value="/resources/jquery/jquery-ui.js" />"></script>
        <link href="<c:url value="/resources/css/my-theme.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/messages.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/jquery/jquery-ui.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/fonts/font-awesome.css" />" rel="stylesheet">
        <script type="text/javascript">
//            $("#span2").animate({width: 'toggle'}, 350);



            $(document).scroll(function () {
                if ($(window).scrollTop() > 200 && $(window).scrollTop() < 1400) {
                    $('#head').addClass('sticky shadow').css({'width': 1310});
                } else if ($(window).scrollTop() < 200) {
                    $('#head').removeClass('sticky shadow');
                }
//                $('#head').toggleClass('sticky', $(window).scrollTop() > targetOffset);
            });
            $(document).ready(function () {
                $("#href").hover(function () {
                    $("#title1").show('clip').css({'visibility': 'visible'});
                }, function () {
                    $("#title1").hide('explode', 1000);
                });
            });

            $(document).ready(function () {
                $("#adamsaat").mouseover(function () {
                    $("#title2").show('slide').css({'visibility': 'visible'});
                });
                $("#adamsaat").mouseout(function () {
                    $("#title2").hide('explode', 1000);
                });
            });

            function Cont() {
                var val = $("#adamsaat").val();
                if (val == "") {
                    $("#error2_icon").show('clip').css({'display': 'block'}).delay(2500).hide('explode');
                    $("#error2").show('clip').css({'display': 'block'}).delay(2500).hide('explode');
                } else if (val == 0) {
                    $("#error1_icon").show('clip').css({'display': 'block'}).delay(2500).hide('explode');
                    $("#error1").show('clip').css({'display': 'block'}).delay(2500).hide('explode');
                } else {
                    $("form").submit();
                }

            }
        </script>

    </head>
    <body>
        <div class=" widget widget-content" style="border-radius: 14px;box-shadow: 5px 5px 5px 5px rgba(0,0,0,0.8);padding: 10px;">
            <c:if test="${status == 1}">
                <div style="height: 20px;">
                    <a id="href" class="tooltip" href="<c:url value="/rayonlar"/>"  style="float: right">
                        <span id="title1" class="tooltiptext shadow">Siyahıya qayıdış</span>
                        <img src="<c:url value="/resources/images/logout_red.png"/>" width="25" height="25"/>
                    </a>
                </div>
            </c:if>
            <c:if test="${status == 0}">
                <div style="height: 20px;">
                    <a id="href"  class="tooltip" href="http://localhost:8080/loginControl/?param=pr03101444_sp/" style="float: right">
                        <span id="title1" class="tooltiptext shadow">Hesabatdan çıxış</span>
                        <img src="<c:url value="/resources/images/logout_red.png"/>" width="25" height="25"/>
                    </a>
                </div>
            </c:if>
            <c:if test="${status == 2}">
                <div style="height: 20px;">
                    <a id="href" class="tooltip" href="<c:url value="yekun"/>" style="float: right">
                        <span id="title1" class="tooltiptext shadow">Yekuna qayıdış</span>
                        <img src="<c:url value="/resources/images/logout_red.png"/>" width="25" height="25"/>
                    </a>
                </div>
            </c:if>
            <br/>
            <form:form modelAttribute="ht" method="POST">
                <div class="datatable widget" style="width:80%;font-size: 14px;">
                    <table id="table">
                        <thead>
                            <tr>
                                <th class="state-default" style="width: 40%;font-size: 0.9em;">Müəssisənin adı</th>
                                <th class="state-default" >Ərazi</th>
                                <th class="state-default" >Fəaliyyəti</th>
                                <th class="state-default" style="padding-left: 1px;" >Mülkiyyəti</th>
                                <th class="state-default" >Tabeçiliyi</th>
                                <th class="state-default" >Hüquqi təşkilatı forması</th>
                                <th class="state-default" >Büdcə</th>
                                <th class="state-default" >İri-kiçik</th>
                                <th class="state-default" >İşçilərin sayı</th>
                            </tr>
                        </thead>
                        <tbody class="datatable-data widget-content">
                            <tr class="widget-content">
                                <td class="state-default" style="text-align: center;font-weight: normal">${kat.ad}</td>
                                <td class="state-default" style="font-weight: normal">${kat.arazi}</td>
                                <td class="state-default" style="text-align: center;font-weight: normal">${kat.fn}</td>
                                <td class="state-default" style="text-align: center;font-weight: normal">${kat.mn}</td>
                                <td class="state-default" style="text-align: center;font-weight: normal">${kat.tab}</td>
                                <td class="state-default" style="text-align: center;font-weight: normal">${kat.htforma}</td>
                                <td class="state-default" style="text-align: center;font-weight: normal">${kat.bucce}</td>
                                <td class="state-default" style="text-align: center;font-weight: normal">${kat.ik}</td>
                                <td class="state-default" style="text-align: center;font-weight: normal">${kat.say}</td>
                            </tr>
                        </tbody>
                    </table> 
                </div>
                <br/><br/><br/>
                <div class="datatable widget datatable-sticky " style="width: 100%;font-size: 14px;">
                    <div  class="datatable-header widget-header corner-top" style="width: 98.35%">
                        <font size="3">Əsas vəsaitlərin hərəkəti və əsaslı təmiri (torpaq və faydalı qazıntılar istisna olunmaqla)</font>
                    </div>
                    <div id="head" class="datatable datatable-sticky widget">
                        <table>
                            <thead class="widget-header datatable-scrollable-header">
                                <tr>
                                    <th class="state-default" rowspan="2" style="text-align: center;width: 3%;padding: 1px;text-align: center;font-weight: normal">Sətrin №-si</th>
                                    <th class="state-default" rowspan="2" style="text-align: center;width: 24%;font-weight: normal">Göstəricinin adı</th>
                                    <th class="state-default" rowspan="2" style="text-align: center;width: 5%;font-weight: normal;padding-left: 1px;">Məhsulun kodu</th>
                                    <th class="state-default" rowspan="2" style="text-align: center;font-weight: normal">Hesabat dövrü ərzində maddi əsas vəsaitlərin əldə edilməsinə çəkilmiş xərclər (torpaq və faydalı qazıntılar istisna olunmaqla)</th>
                                    <th class="state-default" colspan="2" style="text-align: center;width: 19%;font-weight: normal">o cümlədən:</th>
                                    <th class="state-default" rowspan="2" style="text-align: center;font-weight: normal">Əsas vəsaitlərin əsaslı təmiri üzrə kənar müəssisə və təşkilatların göstərdikləri xidmətlərin dəyəri</th>
                                    <th class="state-default" rowspan="2" style="text-align: center;width: 10%;font-weight: normal">o cümlədən, xarici ölkələrin hüquqi şəxslərinin göstərdikləri xidmətlər</th>
                                    <th class="state-default" rowspan="2" style="text-align: center;width: 6%;font-weight: normal">Silinmiş əsas vəsaitlər bazar qiyməti ilə</th>
                                    <th class="state-default" rowspan="2" style="text-align: center;width: 6%;font-weight: normal">Silinmiş əsas vəsaitlərin yaşı (tam rəqəmlə)</th>
                                </tr>
                                <tr>
                                    <td class="state-default" style="text-align: center;font-weight: normal">ölkədə istehsal edilmiş yeni (istifadədə olmamış) əsas vəsaitlər</td>
                                    <td class="state-default" style="text-align: center;font-weight: normal">idxal olunmuş yeni və istifadə edilmiş əsas vəsaitlər</td>
                                </tr>
                                <tr>
                                    <td class="state-default" style="text-align: center;font-weight: normal">A</td>
                                    <td class="state-default" style="text-align: center;font-weight: normal">B</td>
                                    <td class="state-default" style="text-align: center;font-weight: normal">C</td>
                                    <td class="state-default" style="text-align: center;font-weight: normal">1</td>
                                    <td class="state-default" style="text-align: center;font-weight: normal">2</td>
                                    <td class="state-default" style="text-align: center;font-weight: normal">3</td>
                                    <td class="state-default" style="text-align: center;font-weight: normal">4</td>
                                    <td class="state-default" style="text-align: center;font-weight: normal">5</td>
                                    <td class="state-default" style="text-align: center;font-weight: normal">6</td>
                                    <td class="state-default" style="text-align: center;font-weight: normal">7</td>
                                </tr>
                            </thead>
                        </table>
                    </div>
                    <div class="datatable-tablewrapper" style="-webkit-backface-visibility: hidden;">        
                        <table>
                            <tbody class="datatable-data widget-content">
                                <c:if test="${not empty ht.list}">
                                    <c:forEach items="${ht.list}" var="hes" varStatus="status">
                                        <tr class="widget-content">
                                            <td style="text-align: center;font-weight: normal;width: 3%"><form:hidden path="list[${status.index}].sira"/>${hes.sira}</td>
                                            <td style="font-weight: normal;width: 24%;"><form:hidden path="list[${status.index}].adi"/>${hes.adi}</td>
                                            <td style="text-align: center;font-weight: normal;width: 5%">
                                                <form:hidden  path="list[${status.index}].mehs_kodu"/>
                                                ${hes.mehs_kodu == '00000' ? 'X' : hes.mehs_kodu}</td>
                                            <td style="font-weight: normal;padding: 2px;width: 13.65%">
                                                <form:input class="inputfield inputtext  widget-content widget state corner-all"  onkeypress="return checkValueDec(this, event, 999999999.99);" onfocus="clear0(this);" onkeyup="getValue(this);" maxlength="12" path="list[${status.index}].sut1" cssStyle="text-align: right;padding-left: 2px;width: 96%"/>
                                                <!--<input name="list[${status.index}].sut1" value="${hes.sut1}"  pattern="[\d\.]" type="text" style="text-align: right;"/>-->
                                            </td>
                                            <td style="font-weight: normal;padding: 2px;width: 9.5%">
                                                <form:input class="inputfield inputtext  widget-content widget state corner-all" size="15" onkeypress="return checkValueDec(this, event, 999999999.99);" onfocus="clear0(this);" onkeyup="getValue(this);" onmouseup="getValue(this);" maxlength="12" path="list[${status.index}].sut2" cssStyle="text-align: right;"/>
                                                <!--<input name="list[${status.index}].sut2" value="${hes.sut2}" type="text" style="text-align: right;"/>-->
                                            <td style="font-weight: normal;padding: 2px;width: 9.5%">
                                                <form:input class="inputfield inputtext  widget-content widget state corner-all" size="15" onkeypress="return checkValueDec(this, event, 999999999.99);" onfocus="clear0(this);" onkeyup="getValue(this);" maxlength="12" path="list[${status.index}].sut3" cssStyle="text-align: right;"/>
                                                <!--<input name="list[${status.index}].sut3" value="${hes.sut3}" type="text" style="text-align: right;"/></td>-->
                                            <td style="font-weight: normal;padding: 2px;">
                                                <form:input class="inputfield inputtext  widget-content widget state corner-all"  onkeypress="return checkValueDec(this, event, 999999999.99);" onfocus="clear0(this);" onkeyup="getValue(this);" maxlength="12" path="list[${status.index}].sut4" cssStyle="text-align: right;width: 95%"/>
                                                <!--<input name="list[${status.index}].sut4" value="${hes.sut4}" type="text" style="text-align: right;"/></td>-->
                                            <td style="font-weight: normal;padding: 2px;width: 10%">
                                                <form:input class="inputfield inputtext  widget-content widget state corner-all" onkeypress="return checkValueDec(this, event, 999999999.99);" onfocus="clear0(this);" onkeyup="getValue(this);" maxlength="12" path="list[${status.index}].sut5" cssStyle="text-align: right;width: 93%"/>
                                                <!--<input name="list[${status.index}].sut5" value="${hes.sut5}" type="text" style="text-align: right;"/></td>-->
                                            <td style="font-weight: normal;padding: 2px;width: 6%">
                                                <form:input class="inputfield inputtext  widget-content widget state corner-all" onkeypress="return checkValueDec(this, event, 999999999.99);" onfocus="clear0(this);" onkeyup="getValue(this);" maxlength="12" path="list[${status.index}].sut6" cssStyle="text-align: right;width: 88%"/>
                                                <!--<input name="list[${status.index}].sut6" value="${hes.sut6}" type="text" style="text-align: right;"/></td>-->
                                            <td style="font-weight: normal;padding: 2px;width: 6%">
                                                <form:input class="inputfield inputtext  widget-content widget state corner-all" onkeypress="return checkValue(this, event, 999);" onfocus="clear0(this);" onkeyup="getValue(this);" maxlength="3" path="list[${status.index}].sut7" cssStyle="text-align: right;width: 88%"/>
                                                <!--<input name="list[${status.index}].sut7" value="${hes.sut7}" type="text" style="text-align: right;"/></td>-->
                                        </tr>
                                    </c:forEach>
                                </c:if>
                            </tbody>
                        </table>
                    </div>
                </div>
                <br/>
                <div id="refresh" style="height: 140px;">
                    <c:if test="${not empty message}">
                        <textarea id="area" class="inputtextarea widget widget-content state inputfield corner-all" name="area" style="color: red;font-weight: bold;width: 50%;height: 100px;float: right;font-size: 15px;position: fixed;right: 25px;bottom: 25px;">
                            ${message}
                        </textarea>
                    </c:if>
                    &nbsp;&nbsp;
                    <table>
                        <tr>
                            <td>
                                <output for="adamsaat" name="adamsaat">Adamsaat:</output> 
                                <div class="tooltip-right">
                                    <input id="adamsaat"  name="tlr.adamsaat" value="${ht.tlr.adamsaat}" size="3"  class="inputfield inputtext widget-content widget state corner-all tooltip-right " onkeypress="return checkValue0(this, event, 1.89);" onfocus="clear0d0()"  max="1.9" maxlength="4" style="text-align: right;"/>
                                    <span id="title2" class="tooltiptext shadow">0.01 və 1.9 arası ədəd daxil edin</span></div></td>
                            <td><div id="messages" >
                                    <span id="error1_icon"  class="message-error messages-error-icon" style="display: none;"></span>
                                    <span id="error1"  class="messages-error  message-error-summary" style="display: none;width: 180px;"><spring:message code="errmes6"/></span>
                                    <span id="error2_icon" class="messages-error messages-error-icon" style="display: none;"></span>
                                    <span id="error2" class="messages-error message-error-summary" style="display: none;width: 180px;"><spring:message code="errmes7"/></span>
                                </div></td>
                            <td>
                                <div id="div" style="font-size: 14px;color: #0000FF; font-style:oblique;padding-left: 15px;">
                                    <form:hidden path="tlr.ip"/>&nbsp;&nbsp;İP ünvanı: &nbsp;${ht.tlr.ip}</div></td></tr>
                    </table>
                    <br/>
                    &nbsp;&nbsp;
                    <button id="save" name="save" value="true" onclick="Cont()" type="button" class="button widget corner-all" style="width: 100px;height: 25px;"><span class="fa fa-save"/> Yadda saxla</button>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button formaction="<c:url value="/hesabat/pdf"/>" class="button widget corner-all" formtarget="blank" style="width: 100px;height: 25px;"><span class="fa fa-print"/> Çap et (PDF)</button>
                </div> 
            </form:form>
        </div>
    </body>
</html>
