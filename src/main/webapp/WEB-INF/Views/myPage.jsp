<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Page</title>
    <style>
        body{
            margin:0;
            padding:0;
        }
        .header-hr{
            height: 1.2px;
            background-color: lightgray;
            width: 100%;
        }
        .my-page-container{
            display: flex;
            flex-direction: column;
            align-items: center;
            width: 100%;
            margin-top: 20px;
        }
        .my-page-id{
            width: 80%;
            font-weight: bold;
            font-size: 24px;
            margin-bottom: 25px;
        }
        .my-page-content{
            display: flex;
            flex-direction: row;
            width: 80%;
            flex-wrap: wrap;
        }
        .my-page-info{
            display: flex;
            flex-direction: column;
            width: 35%;
            margin-right: 25px;
        }
        .info-line{
            border-top : lightgray solid 1px;
            width: 100%;
            height: 40px;
            font-size: 15px;
            display: flex;
            flex-direction: row;
            padding-top:13px;
            box-sizing: border-box;
        }
        .info-line div{
            width: 50%;
        }
        .my-page-solved{
            display: flex;
            flex-direction: column;
            width: 60%;
        }
        .solved-title{
            background-color: #dddddd;
            padding: 20px;
            width: 100%;
            font-size: 16px;
            box-sizing: border-box;
        }
        .solved-box{
            display: flex;
            flex-direction: row;
            flex-wrap: wrap;
            padding:20px;
            width: 100%;
            border : lightgray solid 1px;
            box-sizing: border-box;
        }
        .solved-box div{
            font-size: 14px;
            font-weight: bold;
            color: #0066ff;
            margin-right: 8px;
        }
        .notice{
            font-size: 12px;
            font-weight: bold;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <jsp:include page="/header"/>
    <div class="header-hr"></div>
    <div class="my-page-container">
        <div class="my-page-id">Roop</div>
        <div class="my-page-content">
            <div class="my-page-info">
                <div class="info-line">
                    <div>email</div>
                    <div>${user.id}</div>
                </div>
                <div class="info-line">
                    <div>name</div>
                    <div>${user.name}</div>
                </div>
                <div class="info-line">
                    <div>level</div>
                    <div>${user.level}</div>
                </div>
                <div class="info-line">
                    <div>등수</div>
                    <div>${userRanking.getRanking()}</div>
                </div>
                <div class="notice">*맞은 문제가 10문제 이상일 경우,<br> 문제생성이 가능합니다.</div>
            </div>
            <div class="my-page-solved">
                <div class="solved-title">맞은 문제</div>
                <div class="solved-box">
                    <c:forEach items="${solvedProblems}" var="problem">
                        <a href="/solve?problemId=${problem.problemId}">
                            <div>${problem.problemId.toString()}</div>
                        </a>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
