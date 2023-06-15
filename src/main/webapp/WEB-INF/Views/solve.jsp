<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://fonts.googleapis.com/earlyaccess/nanumgothiccoding.css"/>
    <title>Solve</title>
    <style>
        body{
            margin:0;
            padding:0;
            background-color: #2a3746;
            color: white;
        }
        .header-hr{
            height: 1.2px;
            background-color: #223247;
            width: 100%;
            margin-bottom: 20px;
        }
        .solve-container{
            width: 100%;
            display: flex;
            flex-direction: row;
        }
        .solve-side-container{
            width: 50%;
            display: flex;
            flex-direction: column;
            padding: 20px;
            box-sizing: border-box;
        }
        .solve-hr{
            height: 1.2px;
            background-color: #222b3c;
            width: 100%;
        }
        .solve-title1{
            font-size: 18px;
            font-weight: bold;
            margin-bottom: 10px;
            margin-left: 20px;
            margin-top:10px;
        }
        .solve-title{
            font-size: 15px;
            font-weight: bold;
            margin-bottom: 10px;
            margin-top: 10px;
        }
        .solve-text{
            font-size: 18px;
            margin-bottom: 15px;
            color:rgba(255, 255, 255, 0.8);
        }
        .solve-ex-box{
            height: 40px;
            width: 90%;
            background-color: #222b3c;
            padding-top: 10px;
            padding-left: 10px;
            box-sizing: border-box;
            border-radius: 5px;
            color:rgba(255, 255, 255, 0.8);
            margin-bottom: 10px;
        }
        .monaco-editor-css{
            height: 500px;
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
            margin-bottom: 20px;
        }
        .solve-board-btn{
            margin-top:10px;
            margin-bottom: 10px;
            width: 85px;
            height: 30px;
            background-color: #222b3c;
            padding-top: 10px;
            font-size: 15px;
            font-weight: bold;
            color: white;
            border-radius: 5px;
            text-align: center;
        }
        .solve-submit-btn{
            margin-top:10px;
            margin-bottom: 10px;
            width: 70px;
            height: 25px;
            background-color: #cccccc;
            padding-top: 8px;
            font-size: 15px;
            font-weight: bold;
            color: black;
            border-radius: 10px;
            text-align: center;
        }
    </style>
</head>
<body class="solve">
<jsp:include page="/header"/>
<div class="header-hr"></div>
<div class="solve-title1">문자열 출력하기</div>
<div class="solve-hr"></div>
<div class="solve-container">
    <div class="solve-side-container">
        <div>
            <div class="solve-title">문제 설명</div>
            <div class="solve-text">문자열 str이 주어질 때, str을 출력하는 코드를 작성해 보세요.</div>
        </div>
        <div class="solve-hr"></div>
        <div>
            <div class="solve-title">제한사항</div>
            <div class="solve-text">1. 1 ≤ str의 길이 ≤ 1,000,000</div>
            <div class="solve-text">str에는 공백이 없으며, 첫째 줄에 한 줄로만 주어집니다.</div>
        </div>
        <div class="solve-hr"></div>
        <div>
            <div class="solve-title">입출력 예</div>
            <div class="solve-text">입력</div>
            <div class="solve-ex-box">HelloWorld!</div>
            <div class="solve-text">출력</div>
            <div class="solve-ex-box">HelloWorld!</div>
        </div>
        <div>
            <a href="/problemBoardList"><div class="solve-board-btn">질문게시판</div></a>
        </div>
    </div>
    <div class="solve-side-container">
        <div id="monaco" class="monaco-editor-css"></div>
        <div class="solve-submit-btn">실행하기</div>
        <div class="solve-hr"></div>
        <div>
            <div class="solve-title">실행 결과</div>
            <div class="solve-text">실행 결과가 여기에 표시됩니다.</div>
        </div>
    </div>
</div>

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
            minimap: {enabled:false},
            value: [
                '//Monaco 코드 편집기',
                '//Java',
                'class HelloWorld {',
                'public static void main (String args[]) {',
                'System.out.println("Hello World");',
                '}'
            ].join('\n')
        });
    });
</script>
</body>
</html>