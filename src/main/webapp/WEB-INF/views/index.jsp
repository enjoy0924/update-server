<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
   <head>
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
   </head>
  <body>
    <center>
    	<h3>补丁或更新文件上传</h3>
    	<form enctype="multipart/form-data" method="post" action="/update/upload">
    	    <table border="1">
                <tr>
                    <th>适用系统类型</th>
                    <th><input type="text" name="sys_type" /></th>
                </tr>

                <tr>
                    <th>系统版本号</th>
                    <th><input type="text" name="sys_version" /></th>
                </tr>

    		    <tr>
                    <th>升级文件描述</th>
                    <th><textarea name="description" rows="5" cols="22"></textarea></th>
                </tr>

                <tr>
                    <th>版本选择文件</th>
                    <th><input type="file" name="update_file" /></th>
                </tr>

                <tr>
                    <th>检验和</th>
                    <th><label name="checksum"></label></th>
                </tr>

    		</table>

            <tr>
                <input type="submit" value="提交" />
            </tr>

    	</form>
    </center>
  </body>
</html>
