

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Управление профилем</title>
    <link rel = "stylesheet" href="${contextPath}/static/css/account.css">

</head>
<body>
<script>
    <#if error??>
    alert('${error}')
    </#if>
</script>
<div class="container">
    <h2>Управление профилем</h2>

    <!-- Информация о пользователе -->
    <div class="user-info">
        <h3>Информация о профиле</h3>
        <div class="info-grid">
            <div class="info-item">
                <span class="info-label">Логин:</span>
                <span class="info-value">${user.login}</span>
            </div>
            <div class="info-item">
                <span class="info-label">Имя:</span>
                <span class="info-value">${user.name}</span>
            </div>
            <div class="info-item">
                <span class="info-label">Email:</span>
                <span class="info-value">${user.email}</span>
            </div>
        </div>
    </div>

    <!-- Кнопки действий -->
    <div class="actions-container">
        <a href="${contextPath}/redactor" class="action-btn edit-btn">
            <span>✏️</span>
            Редактировать
        </a>
        <a href="${contextPath}/delete-user" class="action-btn delete-btn"
           onclick="return confirm('Вы точно хотите удалить этот аккаунт?')">
            <span>🗑️</span>
            Удалить
        </a>
    </div>

    <div class="back-link">
        <a href="${contextPath}/showtask" class="back-btn">Назад к календарю</a>
    </div>
</div>

</body>
</html>