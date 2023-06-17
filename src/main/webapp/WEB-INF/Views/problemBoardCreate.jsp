<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Problem Board Create</title>
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
            margin-top:35px;
            width: 100%;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .form-container {
            width: 80%;
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
        .monaco-board-css{
            width:100%;
            height: 350px;
            padding:10px;
            box-sizing: border-box;
            margin-top:10px;
        }
    </style>
</head>
<body>
    <jsp:include page="/header"/>
    <div class="header-hr"></div>
    <div class="problem-create-container">
        <div class="form-title">10512번 문제 질문하기</div>
        <form action="problemBoardCreate" method="POST" class="form-container">
            <input type="hidden" name="problemId" value="${problemId}" />
            <div class="form-group">
                <label for="title">제목</label>
                <input class="form-input" type="text" id="title" name="title" required>
            </div>
            <div class="form-group">
                <label for="description">설명</label>
                <textarea class="form-input-area" id="description" name="description" required></textarea>
                <div id="monaco" class="monaco-board-css"></div>
            </div>
            <div class="form-group">
                <button type="submit">질문 생성</button>
            </div>
        </form>
    </div>
</body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/require.js/2.3.6/require.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/monaco-editor/0.16.2/min/vs/loader.js"></script>
<script>
    var editor;

    require.config({ paths: { 'vs': 'https://cdnjs.cloudflare.com/ajax/libs/monaco-editor/0.16.2/min/vs' }});
    require(['vs/editor/editor.main'], function() {
        editor = monaco.editor.create(document.getElementById('monaco'), {
            theme: 'atom-one-dark',
            fontFamily: 'Nanum Gothic Coding',
            automaticLayout: true,
            language: 'java',
            fontSize: 15,
            readOnly: true,
            minimap: {enabled:false},
            value: [
                '//Monaco 코드 편집기',
                '//Java',
                'class HelloWorld {',
                'public static void main (String args[]) {',
                '   plus(a+b);',
                '}'
            ].join('\n')
        });
    });
</script>
</html>
