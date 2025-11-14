

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Добавление задачи</title>
    <link rel = "stylesheet" href="/oris_semectrovka_01_war_exploded/static/css/add-edit-task.css">
</head>
<body>
<script>
    <#if error??>
    alert('${error}')
    </#if>
</script>
<div class="container">
    <form action="/oris_semectrovka_01_war_exploded/add-task" method="post" onsubmit="return validateForm()">
        <h2>Добавление задачи</h2>

        <div class="form-group">
            <label for="title">Название задачи:</label>
            <input type="text" id="title" name="title" required placeholder="Введите название задачи">
        </div>

        <div class="form-group">
            <label for="description">Описание:</label>
            <textarea id="description" name="description" placeholder="Опишите задачу подробнее"></textarea>
        </div>

        <div class="form-group">
            <label for="date_end">Дата окончания:</label>
            <input type="date" id="date_end" name="date_end" required>
        </div>

        <div class="form-group">
            <label for="priority">Приоритет:</label>
            <select id="priority" name="priority" required>
                <option value="">Выберите приоритет</option>
                <option value="1">Низкий</option>
                <option value="2">Средний</option>
                <option value="3">Высокий</option>
            </select>
        </div>

        <div class="form-group">
            <label for="status">Статус:</label>
            <select id="status" name="status" required>
                <option value="">Выберите статус</option>
                <option value="todo">К выполнению</option>
                <option value="in_progress">В работе</option>
                <option value="done">Завершено</option>
            </select>
        </div>

        <!-- Множественные теги -->
        <div class="form-group">
            <label>Теги задачи :</label>
            <div class="tags-container" id="tagsContainer">
                <div class="tag-item">
                    <select name="tag_id[]" class="tag-select">
                        <option value="">Выберите тег</option>
                        <option value="1">Дом</option>
                        <option value="2">Работа</option>
                        <option value="3">Учеба</option>
                    </select>
                    <span class="remove-tag" onclick="removeTag(this)">×</span>
                </div>
            </div>

            <div class="add-tag-btn" onclick="addTag()" id="addTagBtn">
                + Добавить еще тег
            </div>
        </div>

        <!-- Вложения -->
        <div class="form-group">
            <label>Вложения (ссылки):</label>
            <div class="attachments-container" id="attachmentsContainer">
                <div class="attachment-item">
                    <input type="url" name="attachment_url[]" placeholder="https://example.com/document.pdf"
                           class="attachment-input">
                    <input type="text" name="attachmentTitle[]" placeholder="Описание вложения"
                           class="attachment-desc">
                    <span class="remove-attachment" onclick="removeAttachment(this)">×</span>
                </div>
            </div>

            <div class="add-attachment-btn" onclick="addAttachment()">
                + Добавить еще ссылку
            </div>

            <small style="color: #6c7b95; font-size: 0.8rem; display:block; margin-top:8px;">
                Можно добавить несколько ссылок и описаний (например: ссылка на документ, картинку, видео и т.п.)
            </small>
        </div>

        <button type="submit" class="btn">Добавить задачу</button>
    </form>

    <div class="back-link">
        <p>Вернуться обратно?</p>
        <a href="/oris_semectrovka_01_war_exploded/showtask" class="back-btn">К календарю</a>
    </div>
</div>
<script src="/oris_semectrovka_01_war_exploded/static/js/task.js"></script>

</body>
</html>