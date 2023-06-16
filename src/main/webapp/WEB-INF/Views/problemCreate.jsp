<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Problem Create</title>
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
        .problem-create-container{
            margin-top:30px;
        }
        .form-container {
            max-width: 500px;
            margin: 0 auto;
            background-color: #f5f5f5;
            padding: 20px;
            border: 1px solid #ddd;
        }
        .form-container h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }.form-container {
             max-width: 500px;
             margin: 0 auto;
             background-color: #f5f5f5;
             padding: 20px;
             border: 1px solid #ddd;
         }
        .form-container h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }
        .form-input{
            font-size: 15px;
            width: 100%;
            height: 40px;
            padding: 5px;
            box-sizing: border-box;
            border: 1px solid lightgray;
            border-radius: 5px;
            margin-bottom: 10px;
            font-family: Arial, sans-serif;
        }
        .form-input-area{
            font-size: 15px;
            width: 100%;
            height: 80px;
            padding: 5px;
            box-sizing: border-box;
            border: 1px solid lightgray;
            border-radius: 5px;
            margin-bottom: 10px;
            font-family: Arial, sans-serif;
        }
        .form-title{
            font-size: 23px;
            font-weight: bold;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <jsp:include page="/header"/>
    <div class="header-hr"></div>
    <div class="problem-create-container">
        <div class="form-container">
            <div class="form-title">문제 생성</div>
            <form action="problemCreate" method="POST">
                <div class="form-group">
                    <label for="title">문제 제목</label>
                    <input class="form-input" type="text" id="title" name="title" required>
                </div>
                <div class="form-group">
                    <label for="description">문제 설명</label>
                    <textarea class="form-input-area" id="description" name="description" required></textarea>
                </div>
                <div class="form-group">
                    <label for="level">레벨</label>
                    <select class="form-input" id="level" name="level" required>
                        <option value="" disabled selected>레벨을 선택하세요</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>
                </div>
                <div class="form-group">
                    <button type="submit">문제 생성</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
