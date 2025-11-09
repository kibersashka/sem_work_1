<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Редактирование задачи</title>
    <link rel = "stylesheet" href="/oris_semectrovka_01_war_exploded/static/css/task.css">
    <style>

        .tags-container {
            margin-top: 10px;
        }


        .tag-item {
            display: flex;
            gap: 10px;
            margin-bottom: 10px;
            align-items: center;
        }

        .tag-select {
            flex: 1;
        }

        .add-tag-btn {
            background: rgba(168, 192, 255, 0.2);
            border: 2px dashed #a8c0ff;
            color: #3f4c6b;
            padding: 10px 16px;
            border-radius: 15px;
            cursor: pointer;
            text-align: center;
            transition: all 0.3s ease;
            margin-top: 5px;
        }


        .add-tag-btn:hover {
            background: rgba(168, 192, 255, 0.3);
            border-color: #3f4c6b;
        }


        .remove-tag {
            color: #ff6b6b;
            cursor: pointer;
            font-weight: bold;
            padding: 8px 12px;
            border-radius: 50%;
            background: rgba(255, 107, 107, 0.1);
            transition: all 0.3s ease;
            flex-shrink: 0;
        }

        .remove-tag:hover {
            background: #ff6b6b;
            color: white;
        }



        @keyframes fadeIn {
            from {
                opacity: 0;
                transform: translateY(15px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        @media (max-width: 500px) {
            .container {
                padding: 30px 20px;
                border-radius: 20px;
            }

            h2 {
                font-size: 22px;
            }
            .tag-item {
                flex-direction: column;
                gap: 8px;
            }

            .tag-select {
                width: 100%;
            }
        }
    </style>
</head>
<body>

<div class="container">
    <form action="/oris_semectrovka_01_war_exploded/edit-task?task_id=${task.id}" method="post">
        <input type="hidden" name="id" value="${task.id}">
        <input type="hidden" name="users_id" value="${task.users_id}">

        <h2>Редактирование задачи</h2>

        <div class="form-group">
            <label for="title">Название задачи:</label>
            <input type="text" id="title" name="title" value="${task.title}" required placeholder="Введите название задачи">
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

            <div class="add-tag-btn" onclick="addTag()">
                + Добавить еще тег
            </div>

            <small style="color: #6c7b95; font-size: 0.8rem; display:block; margin-top:8px;">
                Можно выбрать несколько тегов из списка
            </small>
        </div>

        <!-- Вложения -->
        <div class="form-group">
            <label>Вложения (ссылки):</label>
            <div class="attachments-container" id="attachmentsContainer">
                <#if task.attachments?? && task.attachments?size gt 0>
                    <#list task.attachments as attachment>
                        <div class="attachment-item">
                            <input type="url" name="attachment_url" value="${attachment.url!''}"
                                   placeholder="https://example.com/document.pdf" class="attachment-input">
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
        <a href="/oris_semectrovka_01_war_exploded/showtask" class="back-btn">К календарю</a>
    </div>
</div>

<script>
    function addAttachment() {
        const container = document.getElementById('attachmentsContainer');
        const newAttachment = document.createElement('div');
        newAttachment.className = 'attachment-item';
        newAttachment.innerHTML = `
            <input type="url" name="attachment_url[]" placeholder="https://example.com/document.pdf" class="attachment-input">
            <input type="text" name="attachmentTitle[]" placeholder="Описание вложения" class="attachment-desc">
            <span class="remove-attachment" onclick="removeAttachment(this)">×</span>
        `;
        container.appendChild(newAttachment);
    }

    function removeAttachment(element) {
        const container = document.getElementById('attachmentsContainer');
        if (container.children.length > 1) {
            element.parentElement.remove();
        }
    }

    function addTag() {
        const container = document.getElementById('tagsContainer');
        const newTag = document.createElement('div');
        newTag.className = 'tag-item';
        newTag.innerHTML = `
            <select name="tag_id[]" class="tag-select" required>
                <option value="">Выберите тег</option>
                <option value="1">Дом</option>
                <option value="2">Работа</option>
                <option value="3">Учеба</option>
            </select>
            <span class="remove-tag" onclick="removeTag(this)">×</span>
        `;
        container.appendChild(newTag);
    }

    function removeTag(element) {
        const container = document.getElementById('tagsContainer');
        if (container.children.length > 1) {
            element.parentElement.remove();
        }
    }
</script>

</body>
</html>