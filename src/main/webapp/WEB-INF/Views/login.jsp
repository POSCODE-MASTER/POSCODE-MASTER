<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <style>
        body{
            width: 100%;
            height: 100%;
            background-color: #06f;
        }
        .container{
            width: 100%;
            height: 100%;
            display: flex;
            flex-direction: row;
            justify-content: space-around;
        }
        .login-text{
            font-size: 23px;
            font-weight: bold;
            color:white;
            margin-top: 30px;
            margin-bottom: 30px;
        }
        .login-content{
            display: flex;
            flex-direction: column;
            margin-top: 20px;
        }
        .tabs {
            display: flex;
            width: 450px;
            height: 50px;
            background-color: lightgray;
            border-top-left-radius: 20px;
            border-top-right-radius: 20px;
        }
        .tab {
            background-color: lightgray;
            width:275px;
            height:50px;
            text-align: center;
            padding-top:15px;
            box-sizing: border-box;
            border-top-left-radius: 20px;
            border-top-right-radius: 20px;
            cursor: pointer;
        }
        .tab-active{
            width:275px;
            height:50px;
            text-align: center;
            padding-top:15px;
            box-sizing: border-box;
            border-top-left-radius: 20px;
            border-top-right-radius: 20px;
            font-weight: bold;
            color: #0066ff;
            background-color: white;
            cursor: pointer;
        }

        .tab-content {
            display: none;
        }

        .tab-content.show {
            display: block;
        }
        .tab-content {
            display: none;
        }
        .tab-content.show {
            display: block;
        }
        form {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            background-color: white;
            width: 450px;
            padding: 25px;
            box-sizing: border-box;
            border-bottom-left-radius: 25px;
            border-bottom-right-radius: 25px;
        }
        form input {
            width: 100%;
            height: 40px;
            padding: 5px;
            box-sizing: border-box;
            border: 1px solid lightgray;
            border-radius: 5px;
            margin-bottom: 10px;
        }
        .tab-text{
            font-size: 15px;
            font-weight: bold;
            margin-bottom: 5px;
            margin-top: 10px;
        }
        .login-btn{
            width: auto;
            height: 40px;
            font-weight: bold;
            font-size: 20px;
            color:white;
            text-align: center;
            background-color: #0066ff;
            padding-top: 8px;
            box-sizing: border-box;
            border-radius: 5px;
            margin-top: 20px;
            margin-bottom: 15px;
        }
    </style>
    <script>
        function changeTab(event, tabId) {
            // 모든 탭 콘텐츠 숨김
            var tabContents = document.getElementsByClassName("tab-content");
            for (var i = 0; i < tabContents.length; i++) {
                tabContents[i].classList.remove("show");
            }

            // 선택한 탭 콘텐츠 보여줌
            var selectedTabContent = document.getElementById(tabId);
            selectedTabContent.classList.add("show");

            // 모든 탭 비활성화
            var tabs = document.getElementsByClassName("tab");
            for (var i = 0; i < tabs.length; i++) {
                tabs[i].classList.remove("tab-active");
            }

            // 선택한 탭 활성화
            var selectedTab = event.target;
            selectedTab.classList.add("tab-active");
        }
    </script>
</head>
<body class="login">
    <jsp:include page="/header"/>
    <div class="container">
        <div class="login-content">
            <div class="login-text">반가워요, 개발자의 성장을 돕는</br>POSCODE 입니다.</div>
            <div class="tabs">
                <div class="tab tab-active" onclick="changeTab(event, 'tab1')">로그인</div>
                <div class="tab" onclick="changeTab(event, 'tab2')">회원가입</div>
            </div>

            <form id="tab1" class="tab-content show">
                <div class="tab-text">이메일</div>
                <input type="text"/>
                <div class="tab-text">비밀번호</div>
                <input type="password"/>
                <div class="login-btn">로그인</div>
            </form>

            <form id="tab2" class="tab-content">
                <div class="tab-text">이메일</div>
                <input type="text"/>
                <div class="tab-text">비밀번호</div>
                <input type="password"/>
                <div class="login-btn">회원가입</div>
            </form>
        </div>
    </div>
</body>
</html>
