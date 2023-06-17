<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Header Component</title>
    <script></script>
    <style>
        .header{
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            width: 100%;
            height: 60px;
            margin: 0px;
            padding: 20px;
            font-weight: bold;
            box-sizing: border-box;
        }
        .logo{
            font-size: 25px;
            color: black;
        }
        .header-menu{
            display: flex;
            flex-direction: row;
        }
        .header-btns{
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            width: 130px;
            color:black;
        }
        .header-btns div{
            font-size: 15px;
            margin-top:5px;
        }
        .header-btn{
            font-size: 15px;
            font-weight: bold;
            color:black;
            margin-top:5px;
            margin-left: 25px;
        }
        a{
            text-decoration: none;
            color:black;
        }
        a:hover{
            cursor: pointer;
            text-decoration: none;
        }
        a:visited{
            text-decoration: none;
            color:black;
        }
        body.login .header .logo,
        body.login .header .header-btns,
        body.login .header .header-btn,
        body.login .header .header-btns a,
        body.solve .header .logo,
        body.solve .header .header-btns,
        body.solve .header .header-btn,
        body.solve .header .header-btns a{
            color: white;
        }
    </style>
</head>
<body>
    <div class="header">
        <div class="header-menu">
            <div class="logo">POSCODE</div>
            <a href="/problemList?page=1&level="><div class="header-btn">문제풀기</div></a>
            <a href="/ranking"><div class="header-btn">랭킹보기</div></a>
            <a href="/problemCreate"><div class="header-btn">문제생성</div></a>
        </div>
        <div class="header-btns">
            <div id="loginBtn">로그아웃</div>
            <a href="/myPage"><div id="myPageBtn">마이페이지</div></a>
        </div>
    </div>
</body>
</html>
