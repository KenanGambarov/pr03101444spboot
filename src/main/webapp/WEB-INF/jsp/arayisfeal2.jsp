<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="<c:url value="/resources/css/my-theme.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/body.css" />" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form:form commandName="as">
            <div class="datatable widget datatable-scrollable" style="height: 630px;background-color: #e6e6e6;">
                <div  class="datatable-header widget-header corner-top">
                    <font size="3">Hesabatlar haqqında arayış</font>
                </div>
                <div class="ui-datatable-scrollable-header">
                        <table cellspacing="0" align="center"   border="1" class="datatable widget datatable-scrollable" style="font-size: 13px;">                            
                            <thead class="datatable-scrollable-header" style="font-weight: bold;">                          
                                <tr align="center">
                                    <th width="23.5%" class="state-default" rowspan="3">Göstəricinin adı</th> 
                                    <th width="6.5%" class="state-default" rowspan="3" style="padding-left: 1px">Fəaliyyətdə olan</th>
                                    <th width="24%" class="state-default" colspan="3">Hesabat təqdim edən</th>
                                    <th width="24%" class="state-default" colspan="3">%-lə</th>
                                    <th width="6%" class="state-default" rowspan="3">Hesabat təqdim etməyən</th>
                                    <th width="6%" class="state-default" rowspan="3">Adam-saat</th>
                                </tr> 
                                <tr align="center">
                                    <th width="6%" class="state-default" rowspan="2">Cəmi</th>
                                    <th width="18%" class="state-default" colspan="2">o cümlədən</th>
                                    <th width="6%" class="state-default" rowspan="2">Cəmi</th>
                                    <th width="18%" class="state-default" colspan="2">o cümlədən</th>
                                </tr>
                                <tr align="center">
                                    <th class="state-default" width="6%">Müəssisə</th>
                                    <th class="state-default" width="6%">RSİ</th>
                                    <th class="state-default" width="6%">Müəssisə</th>
                                    <th class="state-default" width="6%">RSİ</th>
                                </tr>
                            </thead>
                        </table>  
                    </div>
                <div class="datatable-scrollable-body" style="height: 520px;">
                    <table> 
                        <tbody class="datatable-data widget-content">
                            <c:forEach items="${listfeal2}" var="mulk">
                                <tr class="widget-content" align="center">
                                    <td align="left" style="width: 23.5%;">
                                        <c:if test="${mulk.zona != '000'}">
                                            <output>${mulk.adi}</output>
                                            </c:if>
                                            <c:if test="${mulk.zona == '000'}">
                                            <output style="font-weight: bold;">${mulk.adi}</output>
                                        </c:if></td>
                                    <td style="width: 6.5%;"><a href="arayis/pdf?action=1&id=${mulk.sut10}&ad=${mulk.adi}" target="_blank" class="${mulk.sut1 == 0 ? 'disabled' :null}" style="text-decoration: none;color: blue">
                                            ${mulk.sut1}</a></td>
                                    <td style="width: 8%;"><a href="teqdimeden?action=6&id=${mulk.sut10}" class="${mulk.sut2 == 0 ? 'disabled' :null}" style="text-decoration: none;color: blue">
                                        ${mulk.sut2}</a></td>
                                    <td style="width: 8%;"><a href="teqdimeden?action=7&id=${mulk.sut10}" class="${mulk.sut3 == 0 ? 'disabled' :null}" style="text-decoration: none;color: blue">
                                            ${mulk.sut3}</a></td>
                                    <td style="width: 8%;"><a href="teqdimeden?action=8&id=${mulk.sut10}" class="${mulk.sut4 == 0 ? 'disabled' :null}" style="text-decoration: none;color: blue">
                                            ${mulk.sut4}</a></td>
                                    <td style="width: 8%;">${mulk.sut5}</td>
                                    <td style="width: 8%;">${mulk.sut6}</td>
                                    <td style="width: 8%;">${mulk.sut7}</td>
                                    <td style="width: 6%;"><a href="arayis/pdf?action=2&id=${mulk.sut10}&ad=${mulk.adi}" class="${iqt.sut1 == 0 ? 'disabled' : null}" target="_blank" style="text-decoration: none;color: blue">
                                            ${mulk.sut8}</a></td>
                                    <td style="width: 6%;">${mulk.sut9}</td>
                                </tr>
                            </c:forEach>
                        </tbody>      
                    </table>
                </div>
            </div>
        </form:form>
    </body>
</html>
