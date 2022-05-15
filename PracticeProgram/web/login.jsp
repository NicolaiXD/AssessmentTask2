<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>login page</title>
    </head>
    <body>
        <form name="myForm" method="post" action="LoginController" onsubmit="return" validate()">
            <input type="text" name="username" placeholder="username"><br>
            <input type="password" name="password" placeholder="password"><br>
            <img src="SimpleImg"><br>
            <input type="text" name="rcaptcha" required><br>
            <input type="submit" value="submit">
        </form>
        
        
    </body>
</html>
