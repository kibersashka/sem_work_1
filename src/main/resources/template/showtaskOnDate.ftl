

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Календарь задач</title>
    <link rel = "stylesheet" href="${contextPath}/static/css/calendar-date-task.css">



</head>
<body>

<div class="header">
    <div class="month">${monthName} ${year}</div>
</div>

<div class="nav">
    <a href="${contextPath}/showtask?year=${prevYear}&month=${prevMonth}">← Назад</a>
    <a href="${contextPath}/showtask?year=${nextYear}&month=${nextMonth}">Вперёд →</a>
</div>
<script>
    <#if error??>
    alert('${error}')
    </#if>
</script>
<div class="calendarContainer">


<div class="container">
    <div class="calendar">
        <table>
            <thead>
            <tr><th>Пн</th><th>Вт</th><th>Ср</th><th>Чт</th><th>Пт</th><th>Сб</th><th>Вс</th></tr>
            </thead>
            <tbody>
            <#list weeks as week>
                <tr>
                    <#list week as day>
                        <td>
                            <#if day??><a href="?year=${year}&month=${month}&day=${day}">${day}</a><#else>&nbsp;</#if>
                        </td>
                    </#list>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>

    <div class="tasks">
        <div class="tasks-header">
            <div class="tasks-title">${dayPick} ${monthName}</div>
            <a href="${contextPath}/add-task?year=${year}&month=${month}&day=${dayPick}" class="add-btn">+ Новая задача</a>
        </div>

        <div class="tasks-list">
            <#if tasks?has_content>
                <#list tasks as task>
                    <div class="task">
                        <div class="task-header">
                            <div class="task-title">${task.title}</div>
                            <div class="task-actions">
                                <a href="${contextPath}/edit-task?task_id=${task.id}" class="edit-btn">✏️</a>
                                <a href="${contextPath}/delete-task?task_id=${task.id}" class="delete-btn">🗑️</a>
                            </div>
                        </div>
                        <div class="task-desc">${task.description}</div>
                        <#if task.attachments?? && task.attachments?size gt 0><div class="task-attachments">
                            📎
                            <#list task.attachments as attachment>
                                <#if attachment.url?? && attachment.url != "">
                                    <a href="${attachment.url}" target="_blank" style="color: #a8c0ff; text-decoration: none; margin-right: 10px;">
                                        ${attachment.title!"Ссылка"}
                                    </a>
                                </#if>
                            </#list>
                            </div>
                        </#if>
                    </div>
                </#list>
            <#else>
                <div style="text-align: center; color: #6c7b95; padding: 40px;">
                    Нет задач на этот день
                </div>
            </#if>
        </div>
    </div>
</div>
</div>

<div class="back">
    <a href="index">← На главную</a>
</div>

</body>
</html>