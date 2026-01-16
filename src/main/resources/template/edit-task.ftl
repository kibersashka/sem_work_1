

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Редактирование задачи</title>
    <link rel = "stylesheet" href="/${contextPath}/static/css/add-edit-task.css">
</head>
<body>

<script>
    <#if error??>
    alert('${error}')
    </#if>
</script>

<div class="container">
    <form action="${contextPath}/edit-task?task_id=${task.id}" method="post" >
    <input type="hidden" name="id" value="${task.id}">
        <input type="hidden" name="users_id" value="${task.users_id}">

        <h2>Редактирование задачи</h2>

        <div class="form-group">
            <label for="title">Название задачи:</label>
            <input type="text" id="title" name="title" value="${task.title}" required placeholder="Введите название задачи" maxlength="150">
        </div>

        <div class="form-group">
            <label for="description">Описание:</label>
            <textarea id="description" name="description" placeholder="Опишите задачу подробнее">${task.description}</textarea>
        </div>

        <div class="form-group">
            <label for="date_end">Дата окончания:</label>
            <input type="date" id="date_end" name="date_end" value="${task.date_end}" required>
        </div>

        <div class="form-group">
            <label for="priority">Приоритет:</label>
            <select id="priority" name="priority" required>
                <option value="">Выберите приоритет</option>
                <option value="1" <#if task.priority == 1>selected</#if>>Низкий</option>
                <option value="2" <#if task.priority == 2>selected</#if>>Средний</option>
                <option value="3" <#if task.priority == 3>selected</#if>>Высокий</option>
            </select>
        </div>

        <div class="form-group">
            <label for="status">Статус:</label>
            <select id="status" name="status" required>
                <option value="">Выберите статус</option>
                <option value="todo" <#if task.status == 'todo'>selected</#if>>К выполнению</option>
                <option value="in_progress" <#if task.status == 'in_progress'>selected</#if>>В работе</option>
                <option value="done" <#if task.status == 'done'>selected</#if>>Завершено</option>
            </select>
        </div>

        <!-- Теги -->
        <div class="form-group">
            <label>Теги:</label>
            <div class="tags-container" id="tagsContainer">
                <#if task.tags?? && task.tags?size gt 0>
                    <#list task.tags as tag>
                        <div class="tag-item">
                            <select name="tag_id[]" class="tag-select" required>
                                <option value="">Выберите тег</option>
                                <option value="1" <#if tag.id == 1>selected</#if>>Дом</option>
                                <option value="2" <#if tag.id == 2>selected</#if>>Работа</option>
                                <option value="3" <#if tag.id == 3>selected</#if>>Учеба</option>
                            </select>
                            <span class="remove-tag" onclick="removeTag(this)">×</span>
                        </div>
                    </#list>
                <#else>
                    <div class="tag-item">
                        <select name="tag_id[]" class="tag-select" required>
                            <option value="">Выберите тег</option>
                            <option value="1">Дом</option>
                            <option value="2">Работа</option>
                            <option value="3">Учеба</option>
                        </select>
                        <span class="remove-tag" onclick="removeTag(this)">×</span>
                    </div>
                </#if>
            </div>

            <div class="add-tag-btn" onclick="addTag()" id="addTagBtn">
                + Добавить еще тег
            </div>

            <small style="color: #6c7b95; font-size: 0.8rem; display:block; margin-top:8px;">
                Можно выбрать несколько тегов из списка
            </small>
        </div>


        <div class="form-group">
            <label>Вложения (ссылки):</label>
            <div class="attachments-container" id="attachmentsContainer">
                <#if task.attachments?? && task.attachments?size gt 0>
                    <#list task.attachments as attachment>
                        <div class="attachment-item">
                            <input type="url" name="attachment_url" value="${attachment.url!''}"
                                   placeholder="https://example.com/document.pdf" class="attachment-input" maxlength="500">
                            <input type="text" name="attachmentTitle" value="${attachment.title!''}"
                                   placeholder="Описание вложения" class="attachment-desc">
                            <span class="remove-attachment" onclick="removeAttachment(this)">×</span>
                        </div>
                    </#list>
                <#else>
                    <div class="attachment-item">
                        <input type="url" name="attachment_url[]" placeholder="https://example.com/document.pdf"
                               class="attachment-input">
                        <input type="text" name="attachmentTitle[]" placeholder="Описание вложения"
                               class="attachment-desc">
                        <span class="remove-attachment" onclick="removeAttachment(this)">×</span>
                    </div>
                </#if>
            </div>

            <div class="add-attachment-btn" onclick="addAttachment()">
                + Добавить еще ссылку
            </div>

            <small style="color: #6c7b95; font-size: 0.8rem; display:block; margin-top:8px;">
                Можно добавить несколько вложений и тегов
            </small>
        </div>

        <button type="submit" class="btn">Сохранить изменения</button>
    </form>

    <div class="back-link">
        <p>Вернуться обратно?</p>
        <a href="${contextPath}/showtask" class="back-btn">К календарю</a>
    </div>
</div>

<script src="/${contextPath}/static/js/task.js"></script>

</body>
</html>