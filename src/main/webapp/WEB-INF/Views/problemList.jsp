<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Problem List</title>
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
        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
            width: 100%;
            margin-top: 25px;
        }
        .banner {
            width: 80%;
            height: 150px;
            background-color: darkslateblue;
            border-radius: 7px;
        }
        .problem-content {
            display: flex;
            flex-direction: row;
            width: 80%;
            margin-top:20px;
        }
        .side-filter {
            display: flex;
            flex-direction: column;
            width: 250px;
            font-weight: bold;
        }
        .side-filter div {
            cursor: pointer;
            padding-top: 10px;
        }
        .side-filter div:not(:last-child) {
            margin-bottom: 10px;
        }
        .problem-list-box {
            display: flex;
            flex-direction: row;
            flex-wrap: wrap;
            width: 100%;
        }
        .problem-box {
            display: flex;
            flex-direction: column;
            width: 280px;
            height: 150px;
            box-sizing: border-box;
            margin-left: 10px;
            margin-bottom: 10px;
        }
        .pr-hr {
            width: 280px;
            height: 1.2px;
            background-color: #0066ff;
        }
        .pr-Title {
            font-weight: bold;
            font-size: 20px;
            margin-top: 15px;
            margin-left: 10px;
        }
        .pr-sub {
            font-size: 15px;
            color: darkgray;
            margin-top: 5px;
            margin-left: 10px;
        }
        .pr-lang {
            color:black;
            margin-top: 7px;
            font-size: 15px;
            margin-left: 10px;
        }
        .filter-list {
            display: none;
            margin-top: 5px;
            padding-left: 10px;
        }
        .filter-list div {
            cursor: pointer;
        }
        .active {
            color: #0066ff;
            font-weight: bold;
        }
        .pagination-btn{
            width: 20px;
            height: 22px;
            background-color: lightgray;
            margin-right:5px;
            text-align: center;
            padding-top: 2px;
            box-sizing: border-box;
        }
        .pagination{
            margin-top: 5px;
            margin-bottom: 40px;
            display: flex;
            flex-direction: row;
        }
        .problem-page{
            display: flex;
            flex-direction: column;
            width: 80%;
            align-items: center;
        }
    </style>
    <script>
        var level = "${level}"

        function toggleFilterList(filterName) {
            var filterList = document.getElementById(filterName + "-list");
            filterList.style.display = (filterList.style.display === "none") ? "block" : "none";
        }

        function applyFilter(page, level){
            var url = '/problemList?page='+ page +'&level=' + level;
            window.location.href = url;
        }

        function selectLevel(selectedLevel) {
            level = selectedLevel;
            applyFilter(${page}, level);
        }

        function toSolve(problemId){
            var url = '/solve?problemId=' + problemId;
            window.location.href = url;
        }
    </script>
</head>
<body>
<jsp:include page="/header"/>
<div class="header-hr"></div>
<div class="container">
    <div class="banner"></div>
    <div class="problem-content">
        <div class="side-filter">
            <div onclick="toggleFilterList('difficulty')">난이도</div>
            <div id="difficulty-list" class="filter-list">
                <div onclick="selectLevel('')">전체</div>
                <div onclick="selectLevel('1')">level 1</div>
                <div onclick="selectLevel('2')">level 2</div>
                <div onclick="selectLevel('3')">level 3</div>
                <div onclick="selectLevel('4')">level 4</div>
                <div onclick="selectLevel('5')">level 5</div>
            </div>
            <div onclick="toggleFilterList('language')">프로그래밍 언어</div>
            <div id="language-list" class="filter-list">
                <div>Java</div>
                <div>C++</div>
                <div>Python</div>
            </div>
        </div>
        <div class="problem-page">
            <div class="problem-list-box">
                <c:forEach items="${problemList}" var="problem">
                    <div class="problem-box" onclick="toSolve(${problem.problemId})">
                        <div class="pr-hr"></div>
                        <div class="pr-Title">${problem.title}</div>
                        <div class="pr-sub">난이도${problem.level} | 21071명 완료</div>
                        <div class="pr-lang">Java Python</div>
                    </div>
                </c:forEach>
            </div>
            <div class="pagination">
                <c:forEach var="i" begin="1" end="3">
                    <div class="pagination-btn" onclick="applyFilter(${i}, level)">${i}</div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</body>
</html>