<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://fonts.googleapis.com/earlyaccess/nanumgothiccoding.css"/>
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
            margin-bottom: 10px;
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
            margin-bottom: 20px;
        }
        .board-title{
            font-weight: bold;
            font-size: 24px;
            margin-top:10px;
        }
        .board-author{
            font-size: 18px;
            margin-top:14px;
            margin-left: 15px;
        }
        .header-hr{
            height: 1.2px;
            background-color: lightgray;
            width: 100%;
        }
        .monaco-board-css{
            width:700px;
            height: 350px;
            padding:10px;
            box-sizing: border-box;
            margin-top:10px;
        }
        .board-comment-container {
            display: flex;
            flex-direction: column;
            margin-top: 20px;
            border-top: 1px solid lightgray;
            padding-top: 10px;
        }
        .comment-heading {
            font-weight: bold;
            font-size: 20px;
            margin-bottom: 10px;
        }
        .input-wrapper {
            display: flex;
            align-items: center;
            border: 2px solid #ccc;
            border-radius: 5px;
            padding: 10px;
            box-sizing: border-box;
        }

        .input-wrapper form {
            display: flex;
            flex: 1;
        }

        .input-wrapper input {
            flex: 1;
            border: none;
            outline: none;
            padding: 5px;
        }

        .input-wrapper button {
            border: none;
            outline: none;
            background-color: #0066ff;
            color: white;
            padding: 10px;
            border-radius: 5px;
            cursor: pointer;
        }
        .post-table {
            background-color: #f9f9f9;
        }
        .edit-link, .delete-link {
            font-size: 12px;
            font-weight: bold;
            margin-right: 10px;
        }
    </style>
</head>
<body>
<jsp:include page="/header"/>
<div class="header-hr"></div>
<div class="board-container">
    <div class="board-title-box">
        <div class="board-title">${post.problem_id}번 문제</div>
        <div class="board-author"></div>
    </div>
    <div>
        <table class="post-table">
            <thead><tr><th>${post.title}</th></tr></thead>
            <tbody>
            <tr>
                <td>
                    <div>
                        ${post.content}
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
        <div>
            <a href="/editPost?postId=${param.postId}&problemId=${post.problem_id}" class="edit-link">게시글 수정</a>
            <a href="/deletePost?postId=${param.postId}&problemId=${post.problem_id}" class="delete-link">게시글 삭제</a>
        </div>
        <div class="board-comment-container">
            <p class="comment-heading">댓글</p>
            <c:forEach items="${commentList}" var="comment">
                <table class="board-comment-box">
                    <thead><th class="board-comment-head">${comment.name}</th></thead>
                    <tbody>
                        <td class="board-comment-body">${comment.comment}
                            <a href="/deleteComment?postId=${param.postId}&problemId=${post.problem_id}&commentId=${comment.postCommentId}" class="edit-link">댓글 삭제</a>
                        </td>
                    </tbody>
                </table>
            </c:forEach>
        </div>
        <div class="input-wrapper">
            <form action="/submitComment?postId=${param.postId}&problemId=${post.problem_id}" method="post">
                <input type="text" name="comment" placeholder="Enter your text">
                <button type="submit">작성</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>