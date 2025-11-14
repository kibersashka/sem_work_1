

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Личный кабинет</title>
    <meta name="description" content="Личный кабинет - задачи на сегодня">
    <link rel = "stylesheet" href="/oris_semectrovka_01_war_exploded/static/css/hello-window.css">

</head>
<body>
<script>
    <#if error??>
    alert('${error}')
    </#if>
</script>
<div class="container">
    <div class="tasks-column fade-in">
        <h2 class="tasks-header">Задачи на сегодня</h2>

        <div class="tasks-list">
            <#if tasks?has_content>
                <#list tasks as task>
                    <div class="task-item fade-in <#if task_index % 2 == 0>delay-1<#else>delay-2</#if>"
                         onclick="toggleTask(this)">
                        <div class="expand-icon">▼</div>
                        <#if task.tags?has_content>
                            <#list task.tags as tag>
                                <#if tag.name?? && tag.name != "">
                                    <span class="task-tag tag-<#if tag.id == 1>home<#elseif tag.id == 2>work<#else>study</#if>">
                                        <#if tag.id == 1>Дом
                                        <#elseif tag.id == 2>Работа
                                        <#else>Учеба
                                        </#if>
                                    </span>
                                </#if>
                            </#list>
                        </#if>
                        <div class="task-title">${task.title}
                            <span class="task-priority priority-<#if task.priority == 3>high<#elseif task.priority == 2>medium<#else>low</#if>">
                                <#if task.priority == 3>Высокий
                                <#elseif task.priority == 2>Средний
                                <#else>Низкий
                                </#if>
                            </span>
                        </div>
                        <div class="task-preview">
                            ${task.description!''}
                        </div>
                        <div class="task-meta">
                            <div class="task-time">
                                <#if task.date_end??>
                                    ${task.date_end}
                                <#else>
                                    Без срока
                                </#if>
                            </div>
                            <div class="task-status">${task.status!'Не начато'}</div>
                        </div>
                        <div class="task-details">
                            <div class="task-description">
                                ${task.description!''}
                            </div>
                            <div class="attachments">
                                <div class="attachments-title">Вложения:</div>
                                <#if task.attachments?? && task.attachments?size gt 0>
                                    <ul class="attachment-list">
                                        <#list task.attachments as attachment>
                                            <li class="attachment-item">
                                                <#if attachment.url?? && attachment.url != "">
                                                    <a href="${attachment.url}" target="_blank">
                                                        ${attachment.title!"Без названия"}
                                                    </a>
                                                <#else>
                                                    <span class="no-url">
                                                        ${attachment.title!"Без названия"}
                                                    </span>
                                                </#if>
                                            </li>
                                        </#list>
                                    </ul>
                                <#else>
                                    <div class="no-attachments">Вложения отсутствуют</div>
                                </#if>
                            </div>
                        </div>
                    </div>
                </#list>
            <#else>
                <div class="no-tasks">Задачи на этот день отсутствуют</div>
            </#if>
        </div>
    </div>

    <div class="menu-column fade-in delay-1">
        <div class="user-welcome">
            <div class="welcome-text">Добро пожаловать!</div>
            <div class="user-login">${login}</div>
        </div>

        <div class="menu-buttons">
            <a href="showtask" class="menu-btn active">
                <span>📅</span> Календарь задач
            </a>
            <a href="show-account" class="menu-btn">
                <span>✏️</span> Настройки
            </a>
            <a href="logout" class="menu-btn">
                <span>️🌸</span> Выйти
            </a>
            <button class="menu-btn" onclick="refreshTasks()" style="border: none; background: inherit;">
                <span>🔄</span> Обновить задачи
            </button>

        </div>
    </div>
</div>
<script src="/oris_semectrovka_01_war_exploded/static/js/index.js"></script>
</body>
</html>