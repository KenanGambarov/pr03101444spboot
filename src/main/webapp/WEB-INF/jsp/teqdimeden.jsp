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
                    <font size="3">Hesabat verən müəssisələr</font>
                </div>
                <div class="datatable-scrollable-header widget-header">
                    <div class="datatable-scrollable-header-box" style="margin-right: 13.5px;">
                        <table cellspacing="0" align="center"   border="1" class="datatable widget datatable-scrollable" style="font-size: 13px;">                            
                            <thead class="datatable-scrollable-header datatable-scrollable-header-box" style="font-weight: bold;">                          
                                <tr align="center">
                                    <th width="3%" class="state-default" style="padding-left: 1px">Say</th> 
                                    <th width="8%" class="state-default">Kod</th>
                                    <th width="34%" class="state-default">Ad</th>
                                    <th width="7%" class="state-default">Ərazi kodu</th>
                                    <th width="6%" class="state-default">Seksiya</th>
                                    <th width="6%" class="state-default" style="padding-left: 1px;">Fəaliyyət kodu</th>
                                    <th width="6%" class="state-default">Tabelik</th>
                                    <th width="6%" class="state-default" style="padding-left: 1px;">Mülkiyyət</th>
                                    <th width="6%" class="state-default" style="padding-left: 1px;">Hüquqi forma</th>
                                    <th width="4%" class="state-default" style="padding-left: 1px;">İri, kiçik</th>
                                    <th class="state-default" width="11%">Tarix</th>
                                </tr>
                            </thead>
                        </table>  
                    </div>
                </div>
                <div class="datatable-scrollable-body" style="height: 555px;">
                    <table> 
                        <tbody class="datatable-data widget-content">
                            <c:forEach items="${teqdimedenler}" var="ara">
                                <tr class="widget-content" align="center">
                                    <td align="left" style="width: 3%;">${as.say}</td>
                                    <td style="width: 8%;"><a href="arayis/teqdimeden_pdf?kod1=${ara.sut1}&mues_ad=${ara.adi}&date=${ara.sut10}  ${ara.sut9}" target="_blank" class="${ara.sut1 == 0 ? 'disabled' :null}" style="text-decoration: none;color: blue">
                                            ${ara.sut1}</a></td>
                                    <td style="width: 34%;">${ara.adi}</td>
                                    <td style="width: 7%;">${ara.sut2}</td>
                                    <td style="width: 6%;">${ara.sut3}</td>
                                    <td style="width: 6%;">${ara.sut4}</a></td>
                                    <td style="width: 6%;">${ara.sut5}</td>
                                    <td style="width: 6%;">${ara.sut6}</td>
                                    <td style="width: 6%;">${ara.sut7}</td>
                                    <td style="width: 4%;">${ara.sut8}</td>
                                    <td style="width: 11%;">${ara.sut10}${ara.sut9}</td>
                                </tr>
                            </c:forEach>
                        </tbody>      
                    </table>
                </div>
            </div>
        </form:form>
    </body>
</html>
