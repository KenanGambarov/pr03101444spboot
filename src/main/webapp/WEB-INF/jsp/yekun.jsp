<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="<c:url value="/resources/css/my-theme.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/messages.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/fonts/font-awesome.css" />" rel="stylesheet">
        <script src="<c:url value="/resources/jquery/jquery.js" />"></script>
        <script src="<c:url value="/resources/jquery/jquery-ui.js" />"></script>
        <script src="<c:url value="/resources/js/input.js" />"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><spring:message code="title"/></title>
        <script type="text/javascript">
            $(document).ready(function () {
                $("#href").hover(function () {
                    $("#title1").show('clip').css({'visibility': 'visible'});
                }, function () {
                    $("#title1").hide('explode', 1000);
                });
            });
            
            function Cont() {
                var val = $("#selectcode").val();
                var val2 = $("#errmes").val();
                var val3 = $("#errmes2").val();
                if (val == "") {
                    $("#error2_icon").show('clip').css({'display': 'block'}).delay(2500).hide('explode');
                    $("#error2").show('clip').css({'display': 'block'}).delay(2500).hide('explode');
                } else if (val < 1000000) {
                    $("#error1_icon").show('clip').css({'display': 'block'}).delay(2500).hide('explode');
                    $("#error1").show('clip').css({'display': 'block'}).delay(2500).hide('explode');
                } else {
                    $("form").submit();
                }

            }
            $(document).ready(function () {
                var val = $("#selectcode").val();
                var val2 = $("#errmes").val();
                if (val >= 1000000 && val2 !== "") {
                    $("#error3_icon").show('clip').css({'display': 'block'}).delay(2500).hide('explode');
                    $("#error3").show('clip').css({'display': 'block'}).delay(2500).hide('explode');                  
                } 
            });
            
        </script>
    </head>
    <body >
        <div id="left" class="panel widget-content widget corner-all" style="width: 22%;height: 625px;float: left;border-radius: 14px;box-shadow: 10px 10px 10px 10px #888888">
            <c:if test="${status == 1}">
                <div class="panel-content widget-content" style="height: 10px;">
                    <a id="href" class="tooltip" href="rayonlar" style="float: right" >
                        <span id="title1" class="tooltiptext shadow">Siyahıya qayıdış</span>
                        <img src="<c:url value="/resources/images/logout_red.png"/>" width="25" height="25"/>
                    </a>
                </div>
            </c:if>
            <c:if test="${status == 0}">
                <div class="panel-content widget-content" style="height: 10px;">
                    <a id="href"  class="tooltip" href="http://localhost:8080/loginControl/?param=pr03101444_spboot/" style="float: right">
                        <span id="title1" class="tooltiptext shadow">Hesabatdan çıxış</span>
                        <img src="<c:url value="/resources/images/logout_red.png"/>" width="25" height="25"/>
                    </a>
                </div>
            </c:if>
            <c:if test="${status == 2}">
                <div class="panel-content widget-content" style="height: 10px;">
                    <a id="href" class="tooltip" href="http://localhost:8080/loginControl/?param=pr03101444_spboot/" style="float: right">
                        <span id="title1" class="tooltiptext shadow">Hesabatdan çıxış</span>
                        <img src="<c:url value="/resources/images/logout_red.png"/>" width="25" height="25"/>
                    </a>
                </div>
            </c:if>
            <p align="center" style=" font-size: 16px; color: #003333; font-weight: bold; font-family: Cambria;"> 1-ƏF №-li forma - <spring:message code="title"/> </p>
            <form:form modelAttribute="yb">
                <div class="panel-content widget-content">
                    <form:select path="value" cssClass="inputfield inputtext widget state corner-all" cssStyle="height: 25px;padding: .2em;width:95%;font-size: 14px;float: left;">
                        <form:option label="Çıxış cədvəlini seçin" value="0" />
                        <form:option label="Daxil edilmiş hesabatlar haqqında arayış" value="1"/>
                        <form:option label="Müəssisələr haqqında arayış" value="2"/>
                        <form:option label="Sahibkarlar haqqında arayış" value="3"/>
                        <form:option label="Hesabatlara baxış" value="4"/>
                        <form:option label="Cədvəl1" value="sut1"/>
                        <form:option label="Cədvəl2" value="sut2"/>
                        <form:option label="Cədvəl3" value="sut3"/>
                        <form:option label="Cədvəl4" value="sut4"/>
                        <form:option label="Cədvəl5" value="sut5"/>
                        <form:option label="Cədvəl6" value="sut6"/>
                        <form:option label="Cədvəl7" value="sut7"/>
                    </form:select>
                    <br/><br/>
                    <form:select path="fk" cssClass="inputfield inputtext widget state corner-all" cssStyle="height: 25px;padding: .2em;width:95%;font-size: 14px;float: left;">
                        <form:option label="Fəaliyyət növü səviyyəsini seçin" value="0"/>
                        <form:option label="2-ci səviyyə" value="2"/>
                        <form:option label="3-cü səviyyyə" value="3"/>
                        <form:option label="4-cü səviyyə" value="4"/>
                        <form:option label="5-cü səviyyə" value="5"/>
                    </form:select>
                    <br/><br/>
                    <form:select path="nov" cssClass="inputfield inputtext widget state corner-all" cssStyle="height: 25px;padding: .2em;width:95%;font-size: 14px;float: left;">
                        <form:option label="İqtisadi rayonlar üzrə" value="1"/>
                        <form:option label="Mülkiyyət növləri üzrə" value="2"/>
                    </form:select>
                    <br/><br/>
                    <form:select path="mn" cssClass="inputfield inputtext widget state corner-all" cssStyle="height: 25px;padding: .2em;width:95%;font-size: 14px;float: left;">
                        <form:option label="Müəssisə növləri üzrə" value="0"/>
                        <form:option label="Bütün müəssisələr" value="4"/>
                        <form:option label="İri müəssisələr" value="2"/>
                        <form:option label="Kiçik müəssisələr" value="1"/>
                    </form:select>
                    <br/><br/>
                    <form:select path="ik" cssClass="inputfield inputtext widget state corner-all" cssStyle="height: 25px;padding: .2em;width:95%;font-size: 14px;float: left;">
                        <form:option label="Sahibkarlar üzrə" value="0"/>
                        <form:option label="Bütün sahibkarlar" value="4"/>
                        <form:option label="İri sahibkarlar" value="2"/>
                        <form:option label="Orta sahibkarlar" value="3"/>
                        <form:option label="Kiçik sahibkarlar" value="1"/>
                    </form:select>
                    <br/><br/><br/><br/>
                    <form:select path="selectiqtisadireg" cssClass="inputfield inputtext widget state corner-all" cssStyle="height: 25px;padding: .2em;width:95%;font-size: 14px;float: left;">
                        <form:options items="${iqtisadireg}" itemValue="zona" itemLabel="araziadi"/>
                    </form:select>
                    <br/><br/>
                    <form:select path="selectfnkod" cssClass="inputfield inputtext widget state corner-all" cssStyle="height: 25px;padding: .2em;width:95%;font-size: 14px;float: left;">
                        <form:option label="Fəaliyyət növü üzrə..." value="0"/>
                        <form:options items="${fnkod}" itemValue="seksiya" itemLabel="sahe"/>
                    </form:select>
                    <br/><br/>
                    <form:select path="selectmulkiyyet" cssClass="inputfield inputtext widget state state-default corner-all" cssStyle="height: 25px;padding: .2em;width:95%;font-size: 14px;float: left;">
                        <form:option label="Bütün mülkiyyət növləri üzrə" value="0"/>
                        <form:options items="${mulkiyyet}" itemLabel="mulk" itemValue="id"/>
                    </form:select>
                    <br/><br/>
                    <button type="submit" formtarget="main" style="float: right;background: none;border: none;cursor: pointer;width: 40px;height: 40px;"><img class="image" src="<c:url value="/resources/images/HTML_64x64.png"/>"/></button>
                    <button formaction="cedvel/xlsx" formtarget="blank" style="float: right;background: none;border: none;cursor: pointer;width: 40px;height: 40px;"><img class="image" src="<c:url value="/resources/images/Excel_64x64.png"/>"/></button>
                    <button formaction="cedvel/pdf" formtarget="blank" style="float: right;background: none;border: none;cursor: pointer;width: 40px;height: 40px;"><img class="image" src="<c:url value="/resources/images/PDF_64x64.png"/>"/></button>

                    Səhifə № &nbsp; <input name="seh" type="text" value="${yb.seh}" onkeypress="return nonnumber(event)" size="3" class="inputfield inputtext widget-content widget state corner-all" style="height: 17px;text-align: right;"/>
                    <br/><br/><br/>
                    <c:if test="${status==2}">
                    <tabel id="group" >
                        <fieldset style="border-radius:10px; border-color: #00cc33; padding:10px;box-shadow: 5px 5px 5px 5px #888888; ">
                            <legend align="center">
                                <output style="font-style: normal; font-size: 14px; color:#00cc33;">Hesabata keçid</output>
                            </legend>
                            <output style="font-style: normal; font-size: 1em;">Kod:</output> &nbsp;
                            <input id="selectcode" type="text" name="selectcode" size="11" value="${yb.selectcode}" onkeypress="return checkValue(this, event, 999999)" onfocus="clear0(this)"  onblur="clear0(this);" class="inputfield inputtext widget widget-content state corner-all" style="height: 17px;text-align: right">
                            &nbsp;
                            <button id="go" name="go" onclick="Cont();" type="button" formtarget="_blank" class="button widget corner-all" style="width: 85px;height: 25px;"><span class="fa fa-external-link widget"></span> Daxil ol</button>
                            <br/> 
                            <output style="font-style: normal; font-size: 1em; color: red"/>          
                        </fieldset> 
                        <div id="messages" >
                            <span id="error1_icon"  class="message-error messages-error-icon" style="display: none;"></span>
                            <span id="error1"  class="messages-error  message-error-summary" style="display: none;width: 230px;float: left"><spring:message code="errmes9"/></span>
                            <span id="error2_icon" class="messages-error messages-error-icon" style="display: none;"></span>
                            <span id="error2" class="messages-error message-error-summary" style="display: none;width: 230px;float: left"><spring:message code="errmes8"/></span>                     
                            <span id="error3_icon" class="messages-error messages-error-icon" style="display: none;"></span>
                            <span id="error3" class="messages-error message-error-summary" style="display: none;width: 230px;float: left">${errmes}</span>
                            <input type="hidden" id="errmes" value="${errmes}"/>
                            <input type="hidden" id="errmes2" value="${errmes2}"/>
                        </div>                                
                    </tabel>
                    </c:if>
                </div>
            </form:form>
        </div>
<!--             <div> 
    <object type="text/html" data="http://validator.w3.org/" style="float: right;width: 77%;height: 630px;border: none;">
    </object>
 </div>-->
<iframe name="main" style="float: right;width: 77%;height: 630px;border: none;border-radius: 14px;">

        </iframe>
    </body>
</html>
