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
        .board-comment-container{
            display: flex;
            flex-direction: column;
            margin-top:20px;
        }
        .input-wrapper {
            display: flex;
            align-items: center;
            border: 2px solid #ccc;
            border-radius: 5px;
            padding: 10px;
        }
        .input-wrapper input {
            flex: 1;
            border: none;
            outline: none;
            padding: 5px;
        }
        .input-wrapper button {
            margin-left: 10px;
            border: none;
            outline: none;
            background-color: #0066ff;
            color: white;
            padding: 10px;
            border-radius: 5px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <jsp:include page="/header"/>
    <div class="header-hr"></div>
    <div class="board-container">
        <div class="board-title-box">
            <div class="board-title">진짜 쉬운건데 모르겠어요..</div>
            <div class="board-author">이유진</div>
        </div>
        <div>
            <table>
                <thead><tr><th>userId</th></tr></thead>
                <tbody>
                <tr>
                    <td>
                        <div>
                            진짜 오바에요 왜 틀린거죠? 문제가 잘못됐나?
                        </div>
                        <div id="monaco" class="monaco-board-css"></div>
                    </td>
                </tr>
                </tbody>
            </table>
            <div class="board-comment-container">
                <table class="board-comment-box">
                    <thead><th class="board-comment-head">userId</th></thead>
                    <tbody><td class="board-comment-body">아니 ㅋㅋ넌 알려줘도 의미없을 듯 그냥 접어라</td></tbody>
                </table>
                <table>
                    <thead><th class="board-comment-head">rooproop1111</th></thead>
                    <tbody><td class="board-comment-body">함수를 정의하세요..아니면 println에 a+b하시던가 그리고 모르면 알려줍시다 뭐라하지말고</td></tbody>
                </table>
            </div>
            <div class="input-wrapper">
                <input type="text" placeholder="Enter your text">
                <button>작성</button>
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
</body>
</html>
