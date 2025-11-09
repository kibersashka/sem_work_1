<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Регистрация</title>
    <link rel="stylesheet" href="/oris_semectrovka_01_war_exploded/static/css/task.css">
    <style>


        .message {
            padding: 12px;
            border-radius: 8px;
            margin-top: 15px;
            text-align: center;
            font-size: 0.9rem;
            background: rgba(255, 235, 238, 0.5);
            border: 1px solid rgba(244, 143, 177, 0.2);
        }



        @keyframes fadeInUp {
            from {
                opacity: 0;
                transform: translateY(20px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        .fade-in {
            animation: fadeInUp 0.6s ease-out;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="form-section">
        <!-- Форма регистрации -->
        <form id="registrationForm" action="/oris_semectrovka_01_war_exploded/registration" method="post">
            <h2>Регистрация</h2>

            <div class="form-group">
                <label for="login">Логин:</label>
                <input type="text" id="login" name="login" required>
            </div>

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

            <button type="submit" class="elegant-btn btn-primary">
                Зарегистрироваться
            </button>

            <!-- Блок для вывода ошибок регистрации -->
            <div id="regMessage" class="message" style="display: none;"></div>
        </form>

        <!-- Ссылка на вход -->
        <div class="login-link">
            <p>Уже есть аккаунт?</p>
            <a href="/oris_semectrovka_01_war_exploded/usercheck" class="login-btn">
                Войти в систему
            </a>
        </div>
    </div>
</div>

</body>
</html>