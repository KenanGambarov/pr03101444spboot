<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html style="">
    <head>
        <link href="<c:url value="/resources/css/my-theme.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/body.css" />" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="<c:url value="/resources/jquery/jquery.js" />"></script>
        <script src="<c:url value="/resources/js/input.js" />"></script>
        <title>JSP Page</title>
        <script type="text/javascript">
            $(document).ready(function () {
                $('#body').scroll(function () {
                    var scroll = $('#body').scrollLeft();
                    $('#box').css('margin-left', -scroll + 'px');
                });
            });
            
        </script>
    </head>
    <body>
        <form:form modelAttribute="yn">
            <div id="bax" class=" widget-content" style="width: 99.7%;height: 80px;background-color: lightgray;border-radius: 13px 13px 0px 0px;"> 
                <br/>
                <table style="width: 100%;text-align: center">
                    <tbody>
                        <tr>
                            <td>Müəssisənin kodu:</td>
                            <td><input type="type" name="kod" onkeypress="return checkValueDec(this, event, 999999);" size="5" min="0"  max="9999999" maxlength="7" class="inputfield inputtext widget state widget-content corner-all" ></td>
                            <td>&nbsp;&nbsp;</td>
                            <td>Sətrin kodu:</td>
                            <td><input type="number" name="sira" min="0" onkeypress="return nonnumber(event);" style="width: 35%" class="inputfield inputtext widget widget-content state corner-all"></td>
                            <td>Fəaliyyət kodu:</td>
                            <td><input type="number" name="feal" min="0" onkeypress="return nonnumber(event);" style="width: 45%" class="inputfield inputtext widget widget-content state corner-all"></td>
                            <td>Mülkiyyət kodu:</td>
                            <td><input type="number" name="mn2" min="0" onkeypress="return nonnumber(event);" style="width: 55%" class="inputfield inputtext widget widget-content state corner-all"></td>
                            <td>Seksiya:&nbsp;<input  type="text" name="sek"  style="width: 15%" class="inputfield inputtext widget-content widget state corner-all"></td>
                            <td>Tabelik:&nbsp;<input type="number" min="0" onkeypress="return nonnumber(event);" name="tab" style="width: 30%" class="inputfield inputtext widget widget-content state corner-all"></td>
                            <td>Ərazinin kodu:</td>
                            <td><form:select path="iqt" cssClass="inputfield inputtext widget state corner-all" cssStyle="height: 25px;padding: .2em;width:95%;font-size: 14px;float: left;">
                                    <form:options items="${iqtisadireg}" itemValue="zona1" itemLabel="araziadi"/>
                                </form:select></td>
                            <td>&nbsp;&nbsp;</td>
                            <td><button class="button widget corner-all button-text-icon-left"  type="submit" style="width: 50px;height: 30px;">Ok</button></td>
                        </tr>
                    </tbody>
                </table>
                <div id="text">

                </div>
            </div>
            <div  id="baxis" class="datatable widget datatable-scrollable" style="height: 525px;background-color: #e6e6e6;font-size: 14px;box-shadow: 10px 10px 10px 10px #888888;">
                <div  class="datatable-header widget-header corner-top">
                    <font size="3">Əsas vəsaitlərin hərəkəti və əsaslı təmiri (torpaq və faydalı qazıntılar istisna olunmaqla)</font>
                </div>
                <div class="widget-header datatable-scrollable-header">
                    <div id="box" class="datatable-scrollable-header-box" style="margin-right: 12px;">
                        <table>
                            <thead>
                                <tr align="center">
                                    <th width="28" rowspan="2" class="state-default" style="padding-left: 1px;">Ərazi kodu</th>
                                    <th width="63" rowspan="2" class="state-default" style="padding-left: 1px;">Müəssisə kodu</th>
                                    <th width="41" rowspan="2" class="state-default" style="padding-left: 1px;">Sətrin №-si</th>
                                    <th width="302" rowspan="2" class="state-default">Göstəricinin adı</th>
                                    <th width="55" rowspan="2" class="state-default" style="padding-left: 1px;">Məhsulun kodu</th>
                                    <th width="106" rowspan="2" class="state-default">Hesabat dövrü ərzində maddi əsas vəsaitlərin əldə edilməsinə çəkilmiş xərclər (torpaq və faydalı qazıntılar istisna olunmaqla)</th>
                                    <th width="188" colspan="2" class="state-default">o cümlədən:</th>
                                    <th width="90" rowspan="2" class="state-default">Əsas vəsaitlərin əsaslı təmiri üzrə kənar müəssisə və təşkilatların göstərdikləri xidmətlərin dəyəri</th>
                                    <th width="69" rowspan="2" class="state-default" style="padding-left: 1px;">o cümlədən, xarici ölkələrin hüquqi şəxslərinin göstərdikləri xidmətlər</th>
                                    <th width="70" rowspan="2" class="state-default">Silinmiş əsas vəsaitlər bazar qiyməti ilə</th>
                                    <th width="72" rowspan="2" class="state-default">Silinmiş əsas vəsaitlərin yaşı (tam rəqəmlə)</th>
                                </tr>
                                <tr align="center">
                                    <th class="state-default">ölkədə istehsal edilmiş yeni (istifadədə olmamış) əsas vəsaitlər</th>
                                    <th class="state-default">idxal olunmuş yeni və istifadə edilmiş əsas vəsaitlər</th>
                                </tr>
                            </thead>
                        </table>
                    </div>
                </div>
                <div id="body" class="datatable-scrollable-body" style="height: 317px;margin-right: 0px;">
                    <table>
                        <thead class="datatable-scrollable-theadclone">
                            <tr align="center">
                                <th width="28" rowspan="2" class="state-default" style="padding-left: 1px;"></th>
                                <th width="63" rowspan="2" class="state-default" style="padding-left: 1px;"></th>
                                <th width="41" rowspan="2" class="state-default" style="padding-left: 1px;"></th>
                                <th width="302" rowspan="2" class="state-default"></th>
                                <th width="55" rowspan="2" class="state-default" style="padding-left: 1px;"></th>
                                <th width="106" rowspan="2" class="state-default"></th>
                                <th width="188" colspan="2" class="state-default"></th>
                                <th width="90" rowspan="2" class="state-default"></th>
                                <th width="69" rowspan="2" class="state-default" style="padding-left: 1px;"></th>
                                <th width="70" rowspan="2" class="state-default"></th>
                                <th width="72" rowspan="2" class="state-default"></th>
                            </tr>
                            <tr align="center">
                                <th class="state-default"></th>
                                <th class="state-default"></th>
                            </tr>
                        </thead>
                        <tbody class="datatable-data widget-content">
                            <c:forEach items="${baxis}" var="bax">
                                <tr class="${bax.nomre == 0 ? 'row-style' : null}">
                                    <td  style="text-align: left;font-size: 14px;">
                                        <c:if test="${bax.nomre == 0}">
                                            <output style="font-weight: bold">${bax.adi_h}</output>
                                            </c:if>
                                    </td>
                                    <td colspan="${bax.nomre == 0 ? '2' : null}" style="text-align: center;font-size: 14px;">
                                        <c:if test="${bax.nomre == 0}">
                                            <output style="font-weight: bold">${bax.kod_c}</output>
                                            </c:if>
                                    </td>
                                    <td colspan="${bax.nomre == 0 ? '5' : null}" style="text-align: ${bax.nomre == 0 ? 'left' : 'center'};font-size: 14px;">
                                        <c:if test="${bax.nomre != 0}">
                                            <output>${bax.sira}</output>
                                            </c:if>
                                            <c:if test="${bax.nomre == 0}">
                                            <output style="font-weight: bold">Müəssisənin adı: ${bax.adi}</output>
                                            </c:if>
                                    </td>
                                    <td colspan="${bax.nomre == 0 ? '5' : null}" style="font-size: 14px;">  
                                        <c:if test="${bax.nomre == 1}"> 
                                            <output>${bax.adi}</output>
                                            </c:if>
                                    </td>
                                    <c:if test="${bax.nomre != 0}">
                                        <td style="text-align: center;font-size: 14px;">
                                            <output>${bax.kod}</output>
                                        </td>
                                    </c:if>
                                    <c:if test="${bax.nomre != 0}">
                                        <td style="font-size: 12px; text-align: right; padding: 2px;">
                                            <output>${bax.sut1}</output>
                                        </td>
                                    </c:if>
                                    <c:if test="${bax.nomre != 0}">
                                        <td style="font-size: 12px; text-align: right; padding: 2px;">
                                            <output>${bax.sut2}</output>
                                        </td>
                                    </c:if>
                                    <c:if test="${bax.nomre != 0}">
                                        <td style="font-size: 12px; text-align: right; padding: 2px;">
                                            <output>${bax.sut3}</output>
                                        </td>
                                    </c:if>
                                    <c:if test="${bax.nomre != 0}">
                                        <td style="font-size: 12px; text-align: right; padding: 2px;">
                                            <output>${bax.sut4}</output>
                                        </td>
                                    </c:if>
                                    <c:if test="${bax.nomre != 0}">
                                        <td style="font-size: 12px; text-align: right; padding: 2px;">
                                            <output>${bax.sut5}</output>
                                        </td>
                                    </c:if>
                                    <c:if test="${bax.nomre != 0}">
                                        <td style="font-size: 12px; text-align: right; padding: 2px;">
                                            <output>${bax.sut6}</output>
                                        </td>
                                    </c:if>
                                    <c:if test="${bax.nomre != 0}">
                                        <td style="font-size: 12px; text-align: right; padding: 2px;">
                                            <output>${bax.sut7}</output>
                                        </td>
                                    </c:if>
                                </tr>
                            </c:forEach>    
                        </tbody>
                    </table>
                </div>
            </div>
        </form:form>                            
    </body>
</html>
