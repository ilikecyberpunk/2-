<!-- //jsp
        // java언어를 기반으로 서버 측 웹 프로그래밍 기술
        // 동적인 웹 페이지를 작성하는데 사용됨
        // html문서 내부에서 java코드를 작성할 수 있도록 되어 있음 -> 웹에서 자바언어 사용 가능

        // html - 웹브라우저, 정적페이지, 프로그래밍 불가능, db연동 불가능
        // jsp - 웹 서버, 동적 페이지, 프로그래밍 가능, db연동 가능
         -->

         jsp의 스크립트 요소 => 선언문, 스크립트릿, 표현식

<% @page contentType = "text/html; charset = "UTF-8" %>
<html>
    <head>
        <title>part7 jsp</title>
    </head>
    <body>
        <p>
            <%
                int count = 0;
                public int add(int a, int b){
                    return a+b;
                }
            %>

            <!-- 스크립트릿은 jsp내부에서 java코드를 직접 작성할 때 사용됨 -->
            <%
                int num = 100;
                out.println("현재 값 : "+num);
            %>
        </p>
    </body>
</html>

그냥 이클립스에서 ㄱ