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
                                <th class="state-default" width="50%">Rayonlar</th> 
                                <th class="state-default" style="padding-left: 1px">Dövlət müəssisələri</th>
                                <th class="state-default" style="padding-left: 1px">Qeyri-dövlət müəssisələri</th>
                                <th class="state-default">Cəmi</th>
                            </tr> 
                        </thead>
                    </table>  
                </div>
            </div>
            <div class="datatable-scrollable-body" style="height: 570px;">
                <table> 
                    <tbody class="datatable-data widget-content">
                    <c:forEach items="${muessise}" var="mues">
                        <tr class="widget-content" align="center">
                            <td align="left" style="width: 50%;">
                        <c:if test="${mues.zona != '000'}">
                            <output>${mues.adi}</output>
                        </c:if>
                        <c:if test="${mues.zona == '000'}">
                            <output style="font-weight: bold;">${mues.adi}</output>
                        </c:if></td>
                        <td><a href="teqdimeden?action=9&id=${mues.sut4}" class="${mues.sut1 == 0 ? 'disabled' :null}" style="text-decoration: none;color: blue">
                                ${mues.sut1}</a></td>
                        <td><a href="teqdimeden?action=10&id=${mues.sut4}" class="${mues.sut2 == 0 ? 'disabled' :null}" style="text-decoration: none;color: blue">
                                ${mues.sut2}</a></td>
                        <td><a href="teqdimeden?action=11&id=${mues.sut4}" class="${mues.sut3 == 0 ? 'disabled' :null}" style="text-decoration: none;color: blue">
                                ${mues.sut3}</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>      
                </table>
            </div>
        </div>
    </form:form>
</body>
</html>
