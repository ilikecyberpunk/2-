<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri=http://java.sun.com/jsp/jstl/core prefix="c"%>

<html>
<body>
    jstl : jsp를 깔끔하게

    <%-- 변수선언 --%>
    <c:set var="a" value=12/>

    <%-- 데이터 출력 --%>
    <c:out value="${a}">

    <%-- 반복문 --%>
    <c:forEach start=1 end=5 var="i">
        ${i}<br>
    </c:forEach>

    <%-- 조건문 --%>
    <c:if test="${a>10}">
        합격
    </c:if>

    <%-- jstl의 switch문 === choose --%>
    <c:set var="score" value="50">
    <c:choose>
        <c:when test="${score==50}">
            middle score
        </c:when>

        <c:when test="${score!=50}">
            idk
        </c:when>
    </c:choose>

    <%-- 배열 --%>
    <%-- jsp와 조합하기 --%>
    <%
        String aa[] = {1,2,3,4,5};
        request.setAttribute("f", aa);
    %>
    <c:forEach items="${f}" var="ii">
        ${ii}<br>
    </c:forEach>
    
</body>
</html>