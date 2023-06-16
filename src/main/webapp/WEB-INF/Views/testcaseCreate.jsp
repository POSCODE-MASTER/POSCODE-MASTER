<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Testcase Create</title>
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
    .testcase-create-container{
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
    .form-input-row {
      display: flex;
      align-items: center;
    }
    .form-input-row .form-input-container {
      flex: 1;
      margin-right: 10px;
    }
    .add-testcase-btn {
      background-color: #4CAF50;
      color: white;
      border: none;
      padding: 8px 16px;
      text-align: center;
      text-decoration: none;
      display: inline-block;
      font-size: 14px;
      margin-bottom: 10px;
      cursor: pointer;
    }
  </style>
</head>
<body>
<jsp:include page="/header"/>
<div class="header-hr"></div>
<div class="testcase-create-container">
  <div class="form-container">
    <div class="form-title">Testcase 생성</div>
    <form action="/testcaseCreate/${problemId}" method="POST">
      <div class="form-group">
        <div class="form-input-row">
          <div class="form-input-container">
            <label for="input">Input</label>
            <textarea class="form-input-area" id="input" name="input" required></textarea>
          </div>
          <div class="form-input-container">
            <label for="output">Output</label>
            <textarea class="form-input-area" id="output" name="output" required></textarea>
          </div>
        </div>
      </div>
      <div id="testcase-container" class="form-group">
        <button class="add-testcase-btn" type="button" onclick="addTestcase()">Testcase 추가</button>
      </div>
      <div class="form-group">
        <button type="submit">Testcase 생성</button>
      </div>
    </form>
  </div>
</div>
<script>
  function addTestcase() {
    var container = document.getElementById("testcase-container");
    var row = document.createElement("div");
    row.classList.add("form-input-row");

    var inputContainer = document.createElement("div");
    inputContainer.classList.add("form-input-container");

    var inputLabel = document.createElement("label");
    inputLabel.textContent = "Input";

    var inputTextarea = document.createElement("textarea");
    inputTextarea.classList.add("form-input-area");
    inputTextarea.setAttribute("name", "input");
    inputTextarea.setAttribute("required", "true");

    inputContainer.appendChild(inputLabel);
    inputContainer.appendChild(inputTextarea);

    var outputContainer = document.createElement("div");
    outputContainer.classList.add("form-input-container");

    var outputLabel = document.createElement("label");
    outputLabel.textContent = "Output";

    var outputTextarea = document.createElement("textarea");
    outputTextarea.classList.add("form-input-area");
    outputTextarea.setAttribute("name", "output");
    outputTextarea.setAttribute("required", "true");

    outputContainer.appendChild(outputLabel);
    outputContainer.appendChild(outputTextarea);

    row.appendChild(inputContainer);
    row.appendChild(outputContainer);

    container.insertBefore(row, container.lastElementChild);
  }
</script>
</body>
</html>