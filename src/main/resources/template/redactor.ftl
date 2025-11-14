

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Изменение страницы</title>
    <link rel = "stylesheet" href="/oris_semectrovka_01_war_exploded/static/css/login-reg.css">

</head>
<body>
<script>
    <#if error??>
    alert('${error}')
    </#if>
</script>
<div class="container">
    <form action="/oris_semectrovka_01_war_exploded/redactor" method="post">
        <h2>Изменение пользователя</h2>

        <div class="form-group">
            <label for="password">Пароль:</label>
            <input type="password" id="password" name="password" required>
        </div>

        <div class="form-group">
            <label for="name">Имя:</label>
            <input type="text" id="name" name="name" required>
        </div>

        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
        </div>

        <button type="submit" class="btn">Готово</button>
    </form>

    <div class="back-link">
        <p>Вернуться обратно?</p>
        <a href="index" class="back-btn">На главную</a>
    </div>
</div>
</body>
</html>