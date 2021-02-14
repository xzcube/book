<%--
  Created by IntelliJ IDEA.
  User: kono
  Date: 2021/1/31
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="page_nav">
    <%--页码大于首页页码，才显示上一页--%>
    <c:if test="${requestScope.page.pageNo > 1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo - 1}">上一页</a>
    </c:if>

    <%--页码输出的开始--%>
    <c:choose>
        <%--情况1：总页码小于5，显示范围是1 ~ 总页码--%>
        <c:when test="${requestScope.page.pageTotal <= 5}">
            <c:set var="begin" value="1"/>
            <c:set var="end" value="${requestScope.page.pageTotal}"/>
        </c:when>

        <%--情况2：总页码大于5的情况(假设一共有10页)--%>
        <c:when test="${requestScope.page.pageTotal > 5}">
            <%--分为三种小情况，需要多路的选择判断--%>
            <c:choose>
                <%--当页码为前面3个时--%>
                <c:when test="${requestScope.page.pageNo <= 3}">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="5"/>
                </c:when>

                <%--情况2：当前页码为最后3页时--%>
                <c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal - 3}">
                    <c:set var="begin" value="${requestScope.page.pageTotal - 4}"/>
                    <c:set var="end" value="${requestScope.page.pageTotal}"/>
                </c:when>

                <%--情况3：当前页码为中间页码--%>
                <c:otherwise>
                    <c:set var="begin" value="${requestScope.page.pageNo - 2}"/>
                    <c:set var="end" value="${requestScope.page.pageNo + 2}"/>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>

    <%--将所有的forEach循环写在这里，前面的判断中只需要记录begin和end的值--%>
    <c:forEach begin="${begin}" end="${end}" var="i">
        <c:if test="${i == requestScope.page.pageNo}">
            【${i}】
        </c:if>
        <c:if test="${i != requestScope.page.pageNo}">
            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>

    <%--页码输出的结束--%>

    <%--当前页码小于总页码时，才会显示下一页 末页--%>
    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo + 1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>

    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
    到第<input value="${requestScope.page.pageNo}" name="pn" id="pn_input"/>页
    <input id="searchPage" type="button" value="确定">

    <script>
        $(function () {
            //跳到指定的页码
            $("#searchPage").click(function () {
                let pageNo = $("#pn_input").val();
                if(pageNo > ${requestScope.page.pageTotal}){
                    pageNo = ${requestScope.page.pageTotal};
                }else if(pageNo < 1){
                    pageNo = 1;
                }
                // javaScript语言中提供了一个location地址栏对象
                //它有一个属性叫href，可以获取浏览器地址栏中的地址
                //href属性可读，可写
                location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo=" + pageNo;
            });
        });
    </script>
</div>
</body>
</html>
