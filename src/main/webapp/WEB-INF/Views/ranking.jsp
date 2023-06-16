<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ranking</title>
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
        .ranking-hr{
            height: 1.2px;
            background-color: lightgray;
            width: 80%;
        }
        table {
            border-collapse: collapse;
            width: 80%;
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
        .ranking-container{
            margin-top:30px;
            display: flex;
            align-items: center;
            width: 100%;
            flex-direction: column;
        }
        .ranking-my{
            font-size: 15px;
            font-weight: bold;
            margin-top: 20px;
            margin-bottom: 20px;
            text-align: left;
            width: 80%;
        }
        .ranking-title{
            font-size: 22px;
            font-weight: bold;
            margin-bottom: 10px;
            text-align: left;
            width: 80%;
        }
        .ranking-my-table{
            margin-bottom: 25px;
        }
        .ranking-my-table td {
            color: #E8587D;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <jsp:include page="/header"/>
    <div class="header-hr"></div>
    <div class="ranking-container">
        <div class="ranking-title">유저 랭킹</div>
        <div class="ranking-hr"></div>
        <div class="ranking-my">내 랭킹</div>
        <table class="ranking-my-table">
            <tr>
                <th>Ranking</th>
                <th>User ID</th>
                <th>Solved Problems</th>
                <th>Correct Ratio</th>
            </tr>
            <tr>
                <td>829011</td>
                <td>yujin</td>
                <td>12</td>
                <td>31%</td>
            </tr>
        </table>
        <table>
            <tr>
                <th>Ranking</th>
                <th>User ID</th>
                <th>Solved Problems</th>
                <th>Correct Ratio</th>
            </tr>
            <tr>
                <td>1</td>
                <td>user123</td>
                <td>50</td>
                <td>75%</td>
            </tr>
            <tr>
                <td>2</td>
                <td>user456</td>
                <td>40</td>
                <td>80%</td>
            </tr>
            <tr>
                <td>3</td>
                <td>user789</td>
                <td>35</td>
                <td>65%</td>
            </tr>
            <!-- 나머지 랭킹 데이터를 동적으로 추가할 수 있습니다. -->
        </table>
    </div>
</body>
</html>
