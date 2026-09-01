<!-- jsp
         java언어를 기반으로 서버 측 웹 프로그래밍 기술
         동적인 웹 페이지를 작성하는데 사용됨
         html문서 내부에서 java코드를 작성할 수 있도록 되어 있음 -> 웹에서 자바언어 사용 가능

        html - 웹브라우저, 정적페이지, 프로그래밍 불가능, db연동 불가능
         jsp - 웹 서버, 동적 페이지, 프로그래밍 가능, db연동 가능
         -->

         <%-- jsp의 스크립트 요소 => 선언문, 스크립트릿, 표현식 --%>

<%@page contentType = "text/html; charset=UTF-8" %>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  = 특정 태그 라이브러리를 가져오겠다 --%>

<html>
    <head>
        <title>part7 jsp</title>
        <style>
            .p1{
                background-color : yellow;
                border : 1px solid black;
            }

            .p2{
                background-color : cyan;
                border : 1px dotted blue;
            }

            .h11{
                background-color : pink;
                border : 10px dotted red;
            }
        </style>
    </head>
    <body>
        <p class = "p1">
        이렇게 느낌표 붙히면 선언문
        선언문 서클릿 클래스로 변환될 때 - 클래스의 맴버변수나 메서드로 포함된다
            <%!
                int count = 0;
                public int add(int a, int b){
                    return a+b;
                }
            %>

            스크립트릿은 jsp내부에서 java코드를 직접 작성할 때 사용됨
            변수 선언 ㄱㄴ, 반복문 ㄱㄴ, 조건문 ㄱㄴ
            <%
                int num = 100;
                out.println("현재 값 : "+num);

                int a = 0;
                for(int i = 1; i<=10; i++){
                    a += i;
                }
                out.println("1부터 10까지의 합 : " + a + "<br>");

                while(true){
                    a += 1;
                    out.println(a);
                    if(a == 500){
                        break;
                    }
                }
            %>

        </p>

            
        <p class = "p2">

        표현식 : 변수나 메서드의 값을 바로 출력할 때 사용

            <%
                int age = 22;
            %>
            나이 : <%= age%>
            
        </p>

        <h1 class = "h11">
            <%!
            String uni = "i hate uni, i hate people, i just like my mommy Alina";
        %>

        <%
            int year = 2005;
        %>

        just for jsp pratice : <%= uni%> i had born on : <%= year%>
        </h1>

        <h1>
            jsp 내장객체 - jsp페이지가 실행될 때 웹 컨테이너에 의해 자동 생성
            Scanner a = new Scanner(System.in); - 일반적인 자바에서 객체는 생성후 사용됨
            근데 jsp는 바로 사용 가능

            <%-- request = 클라이언트 요청 정보 = 페이지 주소에 ?변수명=값 넣어줘야함 --%>
            <% String name = request.getParameter("u_name"); %>
            <br>
            입력된 이름 : <%= name%> 

            <br>
            responce 객체 - 해당페이지로 이동
            <%-- <% response.sendRedirect("part7.html"); --%>     
            <%-- <%responce.sendRedirect("part7.html"); --%>                                                                                                                                                                                         ") %> --%> --%>
            
            <br>
            out 객체 - 브라우저에 출력
            <% out.print("i dont wanna live"); %>
            <br>

            <br>
            session 객체 - 사용자 정보 저장<br>
            <% 
            session.setAttribute("id", "cival"); 
            session.setAttribute("n", "kimtaeho");
            %>
        

            <%
                String id = (String)session.getAttribute("id");
                String nname = (String)session.getAttribute("n");
            %>
            <%= id%><br><%= nname%><br>

            application객체 -  웹 애플리케이션 전체에서 공유되는 데이터 저장
            <%application.setAttribute("name", "alina");%>
            <% out.println(application.getAttribute("name"));%> <br>

        </h1>

        <h1 style = "border:1px solid green;">
            jsp Directive = JSP에서 directive(디렉티브)는 JSP 페이지가 서블릿으로 변환될 때 웹 컨테이너에게 “이 페이지를 어떻게 처리해야 하는지” 지시하는 특별한 명령문
            <br>

            1. page - jsp페이지 설정 - 문자 인코딩, MIME타입, java import, 에러 페이지, 세션 사용 여부
                <%-- 기본 문법 : <%@ page 속성 = "값" %> --%>

                contentType속성 = html문서 전송 및 한글 인코딩 처리
                <%-- <%@ page contentType="text/html; charset = UTF-8" %> --%>

                import 속성 = java패키지 가져옴
                <%@ page import="java.util.Date" %>
                <% 
                Date aa = new Date();
                %>
                현 시 : <%= aa%>

                errorpage 속성 : 오류 발생 시 이동할 페이지 지정
                <%@ page errorPage="part7.jsp"%>
                    
                session속성 : session사용 여부를 지정함
                <%@ page session="true"%>   true면 사용 ㄱㄴ false면 사용 ㄴ

                종합
                <%-- <%@ page contentType="text/html; charset=UFC-8" import="java.util.Date" errorPage="part7.jsp" session="false"%> --%>

            2. include - 다른 페이지 파일을 포함
                <%-- 기본문법 - <%@ include file = "파일명"%> --%>  
                <%@ include file="some.jsp"%>  -해당 파일과 이 페이를 하나의 jsp로 합치고 servlet 변환

            3. taglib - 사용자 정의 태그 라이브러리 사용


            
        </h1>

        <h1>
        jsp는 html태그 뿐만 아닌 다양한 태그 있음
        jstl을 쓰면 java코드 줄이고 jsp를 더욱 깔끔하게 - jsp standard library
        맨 위에 써라 <%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  = 특정 태그 라이브러리를 가져오겠다 --%>
        <c:set var = "nnname" value="f"/>  <!--변수명 nnname, 값 f-->
        이름 : ${name} 

        <%-- 반복문 --%>
        <c:forEach begin = "1" end = "5" val = "i">
             ${i} <br>
        </c:forEach>


        </h1>


    </body>
</html>
