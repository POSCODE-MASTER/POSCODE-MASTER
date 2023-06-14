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
        }
        .header-btns{
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            width: 200px;
        }
        .header-btns div{
            width: 65px;
            font-size: 15px;
        }
        a{
            text-decoration: none;
        }
        a:hover{
            cursor: pointer;
            text-decoration: none;
        }
        a:visited{
            text-decoration: none;
        }
        a:focus{
            text-decoration: none;
        }
        a:active{
            text-decoration: none;
        }
    </style>
</head>
<body>
    <div class="header">
        <div class="logo">POSCODE</div>
        <div class="header-btns">
            <a href="/login"><div id="loginBtn">로그인</div></a>
            <div id="joinBtn">회원가입</div>
            <div id="myPageBtn">마이페이지</div>
        </div>
    </div>
</body>
</html>
