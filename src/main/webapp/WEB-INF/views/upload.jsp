<%--
  Created by IntelliJ IDEA.
  User: imooc
  Date: 2018/7/10
  Time: 19:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>uploadFile</title>
</head>
<body>
<h1>Spring Boot file upload example</h1>
<form method="POST" action="uploadFile" enctype="multipart/form-data">
    <input type="file" name="files"/><br/><br/>
    <input type="submit" value="Submit"/>
</form>
<hr>
<form id="inputForm">
    <input type="file" name="file" id="file"/><br/><br/>
</form>
<button onclick="submit()">submit</button>
<script type="text/javascript" src="js/lib/jquery.min.js"></script>
<script>
    function submit() {
        var formData = new FormData();
        var form = $("#inputForm");
        var files = form.find("[name='file']")[0].files;
        for(var i=0;i<files.length;i++){
            formData.append("files",files[i]);
        }
        $.ajax({
            type:'post',
            url:'uploadFile',
            data:formData,
            dataType:'json',
            contentType:false,
            processData:false,
            cache:false,
            mimeType:'multipart/form-data',
            success:function(res){
                console.log(res)
            }
        })
    }
</script>
</body>
</html>
