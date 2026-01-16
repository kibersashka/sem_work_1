

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Регистрация</title>
    <link rel = "stylesheet" href="${contextPath}/static/css/login-reg.css">
</head>
<body>
<script>
    <#if error??>
    alert('${error}')
    </#if>
</script>
<div class="container">
    <div class="form-section">

        <form id="registrationForm" action="${contextPath}/registration" method="post">
            <h2>Регистрация</h2>

            <div class="form-group">
                <label for="login">Логин:</label>
                <input type="text" id="login" name="login" required maxlength="100">
            </div>

            <div class="form-group">
                <label for="password">Пароль:</label>
                <input type="password" id="password" name="password" required>
            </div>

            <div class="form-group">
                <label for="name">Имя:</label>
                <input type="text" id="name" name="name" required maxlength="100">
            </div>

            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>

            <button type="submit" class="elegant-btn btn-primary">
                Зарегистрироваться
            </button>

            <!--  для вывода ошибок регистрации -->
            <div id="regMessage" class="message" style="display: none;"></div>
        </form>

        <!-- вход -->
        <div class="login-link">
            <p>Уже есть аккаунт?</p>
            <a href="${contextPath}/usercheck" class="login-btn">
                Войти в систему
            </a>
        </div>
    </div>
</div>

</body>
</html>