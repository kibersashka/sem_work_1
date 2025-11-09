<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Управление задачей</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600&display=swap');

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Inter', -apple-system, BlinkMacSystemFont, 'Segoe UI', sans-serif;
        }

        body {
            background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            color: #5d6d7e;
            padding: 20px;
        }

        .container {
            width: 100%;
            max-width: 500px;
            background: rgba(255, 255, 255, 0.75);
            backdrop-filter: blur(15px);
            border-radius: 25px;
            padding: 40px 35px;
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.1);
            border: 1px solid rgba(255, 255, 255, 0.3);
        }

        h2 {
            text-align: center;
            font-weight: 500;
            font-size: 26px;
            margin-bottom: 30px;
            background: linear-gradient(135deg, #a8c0ff 0%, #3f4c6b 100%);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
        }

        .user-info {
            background: rgba(255, 255, 255, 0.6);
            border-radius: 20px;
            padding: 25px;
            margin-bottom: 30px;
            border: 1px solid rgba(200, 210, 220, 0.4);
        }

        .user-info h3 {
            font-weight: 500;
            font-size: 1.2rem;
            margin-bottom: 20px;
            color: #3f4c6b;
            text-align: center;
        }

        .info-grid {
            display: grid;
            gap: 15px;
        }

        .info-item {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 12px 0;
            border-bottom: 1px solid rgba(200, 210, 220, 0.3);
        }

        .info-item:last-child {
            border-bottom: none;
        }

        .info-label {
            font-weight: 500;
            color: #6c7b95;
            font-size: 0.9rem;
        }

        .info-value {
            font-weight: 400;
            color: #3f4c6b;
            font-size: 0.95rem;
            text-align: right;
        }

        .actions-container {
            display: flex;
            gap: 15px;
            margin-bottom: 30px;
        }

        .action-btn {
            flex: 1;
            padding: 15px 0;
            border-radius: 20px;
            border: none;
            cursor: pointer;
            font-size: 1rem;
            font-weight: 500;
            transition: all 0.3s ease;
            text-align: center;
            text-decoration: none;
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 8px;
        }

        .edit-btn {
            background: linear-gradient(135deg, #a8c0ff 0%, #3f4c6b 100%);
            color: white;
        }

        .edit-btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 8px 25px rgba(168, 192, 255, 0.3);
        }

        .delete-btn {
            background: linear-gradient(135deg, #ff6b6b 0%, #c53c3c 100%);
            color: white;
        }

        .delete-btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 8px 25px rgba(255, 107, 107, 0.3);
        }

        .back-link {
            text-align: center;
            margin-top: 30px;
            border-top: 1px solid rgba(200, 210, 220, 0.3);
            padding-top: 25px;
        }

        .back-btn {
            display: inline-block;
            padding: 10px 25px;
            background: rgba(255, 255, 255, 0.8);
            color: #3f4c6b;
            border-radius: 25px;
            border: 1px solid rgba(200, 210, 220, 0.5);
            text-decoration: none;
            transition: all 0.3s ease;
        }

        .back-btn:hover {
            background: rgba(255, 255, 255, 1);
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(168, 192, 255, 0.25);
        }

        @media (max-width: 500px) {
            .container {
                padding: 30px 20px;
            }
            .actions-container {
                flex-direction: column;
            }
            .info-item {
                flex-direction: column;
                align-items: flex-start;
                gap: 5px;
            }
            .info-value {
                text-align: left;
            }
        }
    </style>
</head>
<body>

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
        <a href="/oris_semectrovka_01_war_exploded/redactor" class="action-btn edit-btn">
            <span>✏️</span>
            Редактировать
        </a>
        <a href="/oris_semectrovka_01_war_exploded/delete-user" class="action-btn delete-btn"
           onclick="return confirm('Вы точно хотите удалить этот аккаунт?')">
            <span>🗑️</span>
            Удалить
        </a>
    </div>

    <div class="back-link">
        <a href="/oris_semectrovka_01_war_exploded/showtask" class="back-btn">Назад к календарю</a>
    </div>
</div>

</body>
</html>