


<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Вход в систему</title>
    <link rel = "stylesheet" href="${contextPath}/static/css/login-reg.css">

</head>
<body>

<div class="container">
    <form action="${contextPath}/usercheck" method="post">
        <h2>Вход в систему</h2>

        <div class="form-group">
            <label for="username">Логин:</label>
            <input type="text" id="username" name="login" required>
        </div>

        <div class="form-group">
            <label for="password">Пароль:</label>
            <input type="password" id="password" name="password" required>
        </div>

        <button type="submit" class="btn">Войти</button>

        <script>
            <#if error??>
            alert('${error}')
            </#if>
        </script>
    </form>

    <div class="register-link">
        <p>Нет аккаунта?</p>
        <a href="${contextPath}/registration" class="register-btn">Зарегистрироваться</a>
    </div>
</div>

</body>
</html>
