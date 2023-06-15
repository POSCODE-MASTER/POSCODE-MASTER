<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Problem List</title>
    <style>
        .container{
            display: flex;
            flex-direction: column;
            align-items: center;
            width: 100%;
            margin-top: 25px;
        }
        .banner{
            width: 80%;
            height: 100px;
            background-color: darkslateblue;
            border-radius: 7px;
        }
        .problem-content{
            display: flex;
            flex-direction: row;
            width: 80%;
            margin-top:20px;
        }
        .side-filter{
            display: flex;
            flex-direction: column;
            width: 200px;
            background-color: lightgray;
            font-weight: bold;
        }
        .problem-list-box{
            display: flex;
            flex-direction: row;
            flex-wrap: wrap;
            width: 800px;
            background-color: lightgray;
        }
        .problem-box{
            display: flex;
            flex-direction: column;
            width: 300px;
            height: 150px;
            background-color: gray;
        }
        .pr-hr{
            width: 300px;
            height: 1px;
            background-color: darkgray;
        }
        .pr-Title{
            font-weight: bold;
            font-size: 20px;
            margin-top: 8px;
        }
        .pr-sub{
            font-size: 15px;
            color: darkgray;
            margin-top: 5px;
        }
        .pr-lang{
            color:black;
            margin-top: 7px;
            font-size: 15px;
        }
    </style>
</head>
<body>
    <jsp:include page="/header"/>
    <div class="container">
        <div class="banner"></div>
        <div class="problem-content">
            <div class="side-filter">
                <div>난이도</div>
                <div>프로그래밍 언어</div>
            </div>
            <div class="problem-list-box">
                <div class="problem-box">
                    <div class="pr-hr"></div>
                    <div class="pr-Title">Problem Title</div>
                    <div class="pr-sub">난이도1 | 21071명 완료</div>
                    <div class="pr-lang">Java Python</div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
