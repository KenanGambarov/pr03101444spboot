<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="<c:url value="/resources/css/my-theme.css" />" rel="stylesheet">
        <script src="<c:url value="/resources/jquery/jquery.js" />"></script>
        <script type="text/javascript">
//            function doAjax() {
//                $.ajax({
//                    type: 'GET',
//                    url: 'rayonlar',
//                    data: ({code: $("#code").val()}),
//                    success: function (data) {
//                        
//                    }
//                });
//            }
        </script>
    </head>
    <body>
        <form:form commandName="ht">
            <div class="widget widget-content" style="height: 665px;overflow: hidden;">
                <div id="panel" class="panel widget widget-content corner-all" style="width: 59%;margin: auto;left: 19.5%;right: 19.5%;">
                    <div class="panel-content widget-content">
                        <span style="font-size: 22px">${rayon}</span>
                        <div class="tabmenu widget widget-content corner-all" style="height: 30px;">
                            <ul class="tabmenu-nav helper-reset helper-clearfix widget-header corner-all">
                                <li class="tabmenuitem state-default button corner-top"><a class="menuitem-link corner-all" href="yekun">Yekun</a></li>
                                <li class="tabmenuitem state-default button corner-top"><a class="menuitem-link corner-all" href="http://localhost:8080/loginControl/?param=pr03101444/" >Sistemden cixis</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="datatable widget">
                        <div class="widget-header datatable-scrollable-header" >
                            <div class="datatable-scrollable-header-box" style="margin-right: 12px;">
                                <table cellspacing="0" align="center"   border="1" class="datatable widget datatable-scrollable">
                                    <thead class="widget-header datatable-scrollable-header datatable-scrollable-header-box">
                                        <tr>
                                            <th class="state-default" style="width: 6%">№</th>
                                            <th class="state-default" style="width: 10%">Kodu</th>
                                            <th class="state-default">Müəssisənin adı</th>
                                        </tr>
                                    </thead>
                                </table>
                            </div>
                        </div>    
                        <div class="datatable-scrollable-body" style="height: 520px;">
                            <table>
                                <thead class="datatable-scrollable-theadclone">
                                    <tr>
                                        <th class="state-default" style="width: 6%"></th>
                                        <th class="state-default" style="width: 10%"></th>
                                        <th class="state-default"></th>
                                    </tr>
                                </thead>
                                <tbody class="datatable-data widget-content">
                                    <c:forEach items="${ht.list2}" var="ray" varStatus="status">
                                        <tr class="widget-content">
                                            <td>${ht.say}</td>
                                            <td><a href="<c:url value="/hesabat/${ray.kod}"/>" style="text-decoration: none;color: blue;"><input id="code" type="hidden" name="code" value="${ray.kod}"/>${ray.kod}</a></td>
                                            <td>${ray.ad}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div style="clear: both;"></div>
                    </div>    
                </div>
            </div>
        </form:form>
    </body>
</html>
