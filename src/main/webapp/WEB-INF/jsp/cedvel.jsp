<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="<c:url value="/resources/css/my-theme.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/body.css" />" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="<c:url value="/resources/jquery/jquery.js" />"></script>
        <title>JSP Page</title>
        <script type="text/javascript">
            $(document).ready(function (){
            $('#body').scroll(function () {
                var scroll = $('#body').scrollLeft();               
                $('#box').css('margin-left', -scroll + 'px');
            });
        });
        </script>
    </head>
    <body>
        <form:form commandName="yn">
            <div class="datatable widget datatable-scrollable" style="height: 630px;background-color: #e6e6e6;font-size: 14px;">
                <div  class="datatable-header widget-header corner-top" style="width: 97.8%">
                    <font size="3">${basliq}</font>
                </div>
                <div class="widget-header datatable-scrollable-header">
                    <div id="box" class="datatable-scrollable-header-box" style="margin-right: 12px;">
                        <table style="width: ${yn.ced_uz}">                            
                            <thead>                          
                                <tr align="center" >
                                    <th class="state-default" style="width: 300px;">Göstəricinin adı</th> 
                                    <th class="state-default" style="width: 80px;">Məsrəfin kodu</th>
                                    <th class="state-default" style="${yn.leng2}px" colspan="${leng}">Fəaliyyət növləri</th>
                                </tr> 
                                <tr align="center">
                                    <th class="state-default"></th>
                                    <th class="state-default"></th>
                                    <c:forEach begin="1" end="${leng}" var="i" >
                                       <th class="state-default" style="width: 100px;">${yn.feal1[i]}</th>
                                    </c:forEach>
                                </tr>
                            </thead>
                        </table>  
                    </div>
                </div>
                <div id="body" class="datatable-scrollable-body" style="height: 522px;margin-right: 0px;">
                    <table style="width: ${yn.ced_uz}"> 
                        <thead class="datatable-scrollable-theadclone">                          
                            <tr align="center">
                                <th class="state-default" style="width: 300px;"></th> 
                                <th class="state-default" style="width: 80px;"></th>
                                <th class="state-default" style="${yn.leng2}px" colspan="${leng}"></th>
                            </tr> 
                            <tr align="center">
                                <th class="state-default"></th>
                                <th class="state-default"></th>
                                <c:forEach begin="1" end="${leng}" var="i" >
                                   <th class="state-default" style="width: 100px;"></th>
                                </c:forEach>
                            </tr>
                        </thead>
                        <tbody class="datatable-data widget-content">
                            <c:forEach begin="1" end="${yn.setr}" var="i">
                                <tr class="ui-widget-content" align="left">
                                    <td>
                                        ${yn.ced[i][1]}
                                    </td>
                                    <td style="text-align: center">
                                        ${yn.ced[i][2]}
                                    </td>
                                    <c:forEach begin="3" end="${yn.sut + 2}" var="j">
                                        <td align="right">
                                            ${yn.ced[i][j]}
                                        </td>   
                                    </c:forEach>    
                                </tr>     
                            </c:forEach>    
                        </tbody>      
                    </table>
                </div>
            </div>    
        </form:form>
    </body>
</html>