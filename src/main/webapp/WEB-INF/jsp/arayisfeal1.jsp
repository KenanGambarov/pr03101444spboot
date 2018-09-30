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
                <div class="datatable-scrollable-header widget-header">
                    <div class="datatable-scrollable-header-box" style="padding-right: 2.5px;">
                        <table cellspacing="0" align="center"   border="1" class="datatable widget datatable-scrollable" style="font-size: 13px;">                            
                            <thead class="datatable-scrollable-header datatable-scrollable-header-box" style="font-weight: bold;">                          
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
                </div>
                <div class="datatable-scrollable-body" style="height: 520px;">
                    <table> 
                        <tbody class="datatable-data widget-content">
                            <c:forEach items="${listfeal}" var="iqt">
                                <tr class="widget-content" align="center">
                                    <td align="left" style="width: 23.5%;">
                                        <c:if test="${iqt.zona != '000'}">
                                            <output>${iqt.adi}</output>
                                            </c:if>
                                            <c:if test="${iqt.zona == '000'}">
                                            <output style="font-weight: bold;">${iqt.adi}</output>
                                        </c:if></td>
                                    <td style="width: 6.5%;"><a href="arayis/pdf?action=1&id=${iqt.sut10}&ad=${iqt.adi}" target="_blank" class="${iqt.sut1 == 0 ? 'disabled' :null}" style="text-decoration: none;color: blue">
                                            ${iqt.sut1}</a></td>
                                    <td style="width: 8%;"><a href="teqdimeden?action=3&id=${iqt.sut10}" class="${iqt.sut2 == 0 ? 'disabled' :null}" style="text-decoration: none;color: blue;">
                                        ${iqt.sut2}</a></td>
                                    <td style="width: 8%;"><a href="teqdimeden?action=4&id=${iqt.sut10}" class="${iqt.sut3 == 0 ? 'disabled' :null}" style="text-decoration: none;color: blue">
                                            <input name="as.action" type="hidden" value="4"/>
                                            <input name="as.id" type="hidden" value="${iqt.sut10}"/>${iqt.sut3}</a></td>
                                    <td style="width: 8%;"><a href="teqdimeden?action=5&id=${iqt.sut10}" class="${iqt.sut4 == 0 ? 'disabled' :null}" style="text-decoration: none;color: blue">
                                            ${iqt.sut4}</a></td>
                                    <td style="width: 8%;">${iqt.sut5}</td>
                                    <td style="width: 8%;">${iqt.sut6}</td>
                                    <td style="width: 8%;">${iqt.sut7}</td>
                                    <td style="width: 6%;"><a href="arayis/pdf?action=2&id=${iqt.sut10}&ad=${iqt.adi}" class="${iqt.sut8 == 0 ? 'disabled' : null}" target="_blank" style="text-decoration: none;color: blue">
                                            ${iqt.sut8}</a></td>
                                    <td style="width: 6%;">${iqt.sut9}</td>
                                </tr>
                            </c:forEach>
                        </tbody>      
                    </table>
                </div>
            </div>
        </form:form>
    </body>
</html>