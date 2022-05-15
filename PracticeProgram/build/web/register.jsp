<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>register ka na !!</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <form action="RegisterController" method="POST">
            <input type="text" name="username" placeholder="username"><br>
            <select name="role" id="role">
                <option>Guest</option>
                <option>Admin</option>
            </select><br>
            <input type="password" name="password" placeholder="password"><br>
            <input type="password" name="confirm password" placeholder="confirmpassword"><br>
            <img src="SimpleImg"><br>
            <input type="text" name="rcaptcha" required><br>
            <input type="submit" value="Register">
        </form>    
        
    </body>
</html>
