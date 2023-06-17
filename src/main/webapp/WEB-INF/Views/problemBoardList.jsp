<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Problem Board</title>
  <style>
    body{
      margin:0;
      padding:0;
    }
    .board-container{
      padding: 20px;
      box-sizing: border-box;
    }
    table {
      width: 100%;
      border-collapse: collapse;
    }

    th, td {
      padding: 10px;
      text-align: left;
      border-bottom: 1px solid #ddd;
    }

    th {
      background-color: #f2f2f2;
    }
    .board-title-box{
      display: flex;
      flex-direction: row;
      margin-bottom: 25px;
    }
    .board-title{
      font-weight: bold;
      font-size: 24px;
      margin-top:10px;
    }
    .board-create{
      margin-left: 20px;
      height: 30px;
      width: 60px;
      background-color: #0066ff;
      color: white;
      font-size: 13px;
      font-weight: bold;
      padding-top: 6px;
      box-sizing: border-box;
      text-align: center;
      border-radius: 5px;
      margin-top: 10px;
    }
    .header-hr{
      height: 1.2px;
      background-color: lightgray;
      width: 100%;
    }
  </style>
  <script>
    function toBoard(postId){
      var url = '/problemBoard?postId=' + postId;
      window.location.href = url;
      console.log(url);
    }
  </script>
</head>
<body>
<jsp:include page="/header"/>
<div class="header-hr"></div>
<div class="board-container">
  <div class="board-title-box">
    <div class="board-title">${problemId}번 문제 게시판</div>
    <a href="/problemBoardCreate?problemId=${problemId}"><div class="board-create">질문하기</div></a>
  </div>
  <div>
    <table>
      <thead>
      <tr>
        <th>제목</th>
        <th>글쓴이</th>
        <th style="text-align: right;">날짜</th>
      </tr>
      </thead>
      <tbody>
        <c:forEach items="${problemBoardList}" var="board">
              <tr onclick="toBoard(${board.postId})">
                <td>${board.title}</td>
                <td>${board.name}</td>
                <td style="text-align: right;">${board.writtenDate}</td>
              </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>
</div>
</body>
</html>
