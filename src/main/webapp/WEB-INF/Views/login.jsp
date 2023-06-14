<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <style>
        body{
            width: 100%;
            height: 100%;
            background-color: #cee7fc;
        }
        .login-content{
            width: 100%;
            height: 100%;
            display: flex;
            flex-direction: column;
            justify-content: space-around;
        }
        .tabs {
            display: flex;
            padding-right: 20px;
            padding-left:20px;
        }

        .tab {
            width: 80px;
            height: 40px;
            padding: 10px 20px;
            background-color: #f2f2f2;
            cursor: pointer;
        }

        .tab:hover {
            background-color: #e0e0e0;
        }

        .tab-content {
            display: none;
        }

        .tab-content.show {
            display: block;
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
        }

    </script>
</head>
<body>
    <jsp:include page="/header"/>
    <div class="login-content">
        <div class="tabs">
            <div class="tab" onclick="changeTab(event, 'tab1')">로그인</div>
            <div class="tab" onclick="changeTab(event, 'tab2')">회원가입</div>
        </div>

        <div id="tab1" class="tab-content">
            <h2>Tab 1 Content</h2>
            <p>This is the content of Tab 1.</p>
        </div>

        <div id="tab2" class="tab-content">
            <h2>Tab 2 Content</h2>
            <p>This is the content of Tab 2.</p>
        </div>
    </div>
</body>
</html>
