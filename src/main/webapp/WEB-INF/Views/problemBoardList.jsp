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
</head>
<body>
<jsp:include page="/header"/>
<div class="header-hr"></div>
<div class="board-container">
  <div class="board-title-box">
    <div class="board-title">10512번 문제 질문 게시판</div>
    <div class="board-create">질문하기</div>
  </div>
  <div>
    <table>
      <thead>
      <tr>
        <th>제목</th>
        <th>글쓴이</th>
      </tr>
      </thead>
      <tbody>
      <tr>
        <td>진짜 쉬운건데 모르겠어요..</td>
        <td>이유진</td>
      </tr>
      <tr>
        <td>철자 잘못된 거 있나요?</td>
        <td>백주원</td>
      </tr>
      <tr>
        <td>맞는데 틀렸다고 뜨네요..ㅎ</td>
        <td>조우현</td>
      </tr>
      <%-- 컨트롤러에서 받은 데이터를 반복하여 표의 내용을 생성 --%>
      <%--                <% data.forEach(function(item) { %>--%>
      <%--                <tr>--%>
      <%--                    <td><%= item.title %></td>--%>
      <%--                    <td><%= item.author %></td>--%>
      <%--                </tr>--%>
      <%--                <% }); %>--%>
      </tbody>
    </table>
  </div>
</div>
</body>
</html>
