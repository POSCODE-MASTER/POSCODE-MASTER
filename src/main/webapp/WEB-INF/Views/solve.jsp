<%@ page import="Pack01.domain.User" %>
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
        .solve-output{
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
<div class="solve-title1">${problem.getTitle()}</div>
<div class="solve-hr"></div>
<div class="solve-container">
    <div class="solve-side-container">
        <div>
            <div class="solve-title">문제 설명</div>
            <div class="solve-text">${problem.getDescription()}</div>
        </div>
        <div class="solve-hr"></div>
<%--        <div>--%>
<%--            <div class="solve-title">제한사항</div>--%>
<%--            <div class="solve-text">1. 1 ≤ str의 길이 ≤ 1,000,000</div>--%>
<%--            <div class="solve-text">str에는 공백이 없으며, 첫째 줄에 한 줄로만 주어집니다.</div>--%>
<%--        </div>--%>
        <div class="solve-hr"></div>
        <div>
            <div class="solve-title">입출력 예</div>
            <div class="solve-text">입력</div>
            <div class="solve-ex-box">${exampleInput.get("input")}</div>
            <div class="solve-text">출력</div>
            <div class="solve-ex-box">${exampleOutput.get("output")}</div>
        </div>
        <div>
            <a href="/problemBoardList?problemId=${problem.problemId}"><div class="solve-board-btn">질문게시판</div></a>
        </div>
    </div>
    <div class="solve-side-container">
        <div id="monaco" class="monaco-editor-css"></div>
        <div class="solve-submit-btn" onclick="save()">실행하기</div>
        <div class="solve-submit-btn" onclick="getOutput()">결과보기</div>
        <div class="solve-hr"></div>
        <div>
            <div class="solve-title">실행 결과</div>
            <div class="solve-output">실행 결과가 여기에 표시됩니다.</div>
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
                'public class  ProbelmSolving{',
                'public static void main (String args[]) {',
                '',
                '   }',
                '}'

            ].join('\n')
        });
    });

    async function save(){
            var value = window.editor.getValue();
            var xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                var jsonArray = JSON.parse(xhr.response);
                console.log(jsonArray);
                var resultString = "";
                var index = 1;
                for(let result of jsonArray){
                    console.log(result + "in for loop");
                    console.log(typeof result + "in for loop");

                    var solveTextDiv = document.querySelector(".solve-output");
                    console.log(result.output+ "     " + result.answer);
                    var color = result.isCorrect === "Solved" ? "dodgerblue" : "red";
                    resultString += "<p style='color:" + color + "'>"
                        + "Testcase "+index + " "  + result.isCorrect + " <br/>"
                        + " Memory Used: "  + result.memory + " <br/>"
                        + " Time: "  + result.cpuTime + "sec <br/>"
                        + " userAnswer: " + result.output + " <br/>"
                        + " testcaseAnswer: " + result.answer + "<br/> </p>"

                    index += 1;
                }
                solveTextDiv.innerHTML =  resultString;

            }
            else if (xhr.readyState === XMLHttpRequest.OPENED) {
                console.log("코드 검사중 출력되는곳");
                var solveTextDiv = document.querySelector(".solve-output");
                solveTextDiv.innerHTML = "코드 검사중...";
            }
            else {
                // Request encountered an error
                console.log("Else문");
                console.log(xhr.readyState);
                console.log(xhr.status);
                // console.error(xhr.statusText);
            }
        };
        xhr.open("POST", "/executeUserCode", true);
        xhr.setRequestHeader("Content-Type", "application/json");

        // Handle the response
        // Send the request
        <%User user = (User)session.getAttribute("loginUser");%>
        xhr.send(JSON.stringify({ value: value , problemId:${problem.getProblemId()}, userId:<%=user.getUserId()%>}));
    }

    function getOutput(){
        var output = "<%= request.getAttribute("result") %>";
        var solveTextDiv = document.querySelector(".solve-output");

        solveTextDiv.innerHTML = output;
    }
</script>
</body>
</html>