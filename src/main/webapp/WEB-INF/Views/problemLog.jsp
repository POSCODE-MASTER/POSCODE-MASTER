<%--
  Created by IntelliJ IDEA.
  User: FastPc
  Date: 2023-06-18
  Time: 오후 4:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>

    <style>
        body {
            margin: 0;
            padding: 0;
        }
        .header-hr {
            height: 1.2px;
            background-color: lightgray;
            width: 100%;
        }
        .my-page-container {
            display: flex;
            flex-direction: column;
            align-items: center;
            width: 100%;
            margin-top: 20px;
        }
        .my-page-id {
            width: 80%;
            font-weight: bold;
            font-size: 24px;
            margin-bottom: 25px;
        }
        .my-page-content {
            display: flex;
            flex-direction: column; /* 수정된 부분 */
            width: 80%;
            flex-wrap: wrap;
        }
        .my-page-info {
            display: flex;
            flex-direction: column;
            width: 35%;
            margin-right: 25px;
        }
        .info-line {
            border-top: lightgray solid 1px;
            width: 100%;
            height: 40px;
            font-size: 15px;
            display: flex;
            flex-direction: row;
            padding-top: 13px;
            box-sizing: border-box;
        }
        .info-line div {
            width: 50%;
        }
        .my-page-solved {
            display: flex;
            flex-direction: column;
            width: 100%; /* 수정된 부분 */
            margin-top: 20px;
        }
        .solved-title {
            background-color: #dddddd;
            padding: 20px;
            width: 100%;
            font-size: 16px;
            box-sizing: border-box;
        }
        table {
            border-collapse: collapse;
            width: 100%;
        }
        th, td {
            text-align: center;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tr:hover {
            background-color: #e2e2e2;
        }
    </style>
</head>
<body>
<jsp:include page="/header"/>
<div class="header-hr"></div>
<div class="my-page-container">
    <div class="my-page-id">${problem.getProblemId()} ${problem.getTitle()}</div>
    <div class="my-page-content">
        <div class="my-page-info">
            <div class="info-line">
                <div>문제 레벨</div>
                <div>${problem.getLevel()}</div>
            </div>
            <div class="info-line">
                <div>풀이를 시도한 유저 수</div>
                <div>${triedUserNum}</div>
            </div>
            <div class="info-line">
                <div>풀이에 성공한 유저 수</div>
                <div>${solvedUserNum}</div>
            </div>
        </div>
        <div class="my-page-solved">
            <div class="solved-title">문제 풀이 이력</div>
            <table>
                <tr>
                    <th>문제 풀이 시각</th>
                    <th>결과</th>
                </tr>
                <c:forEach items="${problemLogs}" var="problemLog">
                    <tr>
                        <td>${problemLog.getSolveTime()}</td>
                        <td>${problemLog.getResult()}</td>
                    </tr>
                </c:forEach>

                <!-- 나머지 랭킹 데이터를 동적으로 추가할 수 있습니다. -->
            </table>
        </div>
    </div>
</div>
</body>
</html>
