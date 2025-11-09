<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Календарь задач</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500&display=swap');
        * { margin: 0; padding: 0; box-sizing: border-box; font-family: 'Inter', sans-serif; }
        body { background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%); min-height: 100vh; padding: 20px; }

        .header { text-align: center; margin-bottom: 30px; }
        .header h1 { color: #3f4c6b; font-weight: 600; font-size: 28px; margin-bottom: 10px; }
        .header .month { color: #6c7b95; font-size: 18px; }

        .nav { display: flex; justify-content: center; gap: 15px; margin-bottom: 30px; }
        .nav a { background: white; padding: 10px 20px; border-radius: 20px; text-decoration: none; color: #5d6d7e;
            box-shadow: 0 3px 10px rgba(0,0,0,0.1); transition: all 0.3s; }
        .nav a:hover { transform: translateY(-2px); box-shadow: 0 5px 15px rgba(0,0,0,0.2); }

        .container { display: flex; gap: 20px; max-width: 1200px; margin: 0 auto; }

        .calendar { flex: 1; background: white; border-radius: 15px; padding: 20px; box-shadow: 0 5px 20px rgba(0,0,0,0.1); }
        .calendar table { width: 100%; border-collapse: collapse; }
        .calendar th { background: linear-gradient(135deg, #a8c0ff, #3f4c6b); color: white; padding: 12px; border-radius: 8px; }
        .calendar td { text-align: center; padding: 15px 0; border-radius: 10px; transition: all 0.3s; }
        .calendar td:hover { background: #f0f4ff; transform: scale(1.05); }
        .calendar td a { display: block; color: #5d6d7e; text-decoration: none; font-weight: 500; }

        .tasks { flex: 1; background: white; border-radius: 15px; padding: 20px; box-shadow: 0 5px 20px rgba(0,0,0,0.1); }
        .tasks-header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px; }
        .tasks-title { color: #3f4c6b; font-size: 20px; font-weight: 600; }
        .add-btn { background: linear-gradient(135deg, #a8c0ff, #3f4c6b); color: white; padding: 10px 20px;
            border: none; border-radius: 20px; cursor: pointer; text-decoration: none; font-weight: 500; }

        .task { padding: 15px; border-bottom: 1px solid #eee; }
        .task-header { display: flex; justify-content: space-between; align-items: center; margin-bottom: 8px; }
        .task-title { color: #3f4c6b; font-weight: 600; font-size: 16px; }
        .task-actions { display: flex; gap: 8px; }
        .edit-btn, .delete-btn { padding: 6px 12px; border-radius: 15px; text-decoration: none; font-size: 14px; font-weight: 500; }
        .edit-btn { background: #a8c0ff; color: white; }
        .delete-btn { background: #ff6b6b; color: white; }
        .task-desc { color: #6c7b95; font-size: 14px; margin-bottom: 8px; }
        .task-attachments { font-size: 13px; color: #888; }

        .back { text-align: center; margin-top: 30px; }
        .back a { background: white; padding: 10px 25px; border-radius: 20px; text-decoration: none; color: #5d6d7e;
            box-shadow: 0 3px 10px rgba(0,0,0,0.1); }

        @media (max-width: 768px) {
            .container { flex-direction: column; }
            .nav { flex-wrap: wrap; }
        }
    </style>
</head>
<body>

<div class="header">
    <h1>Календарь задач</h1>
    <div class="month">${monthName} ${year}</div>
</div>

<div class="nav">
    <a href="/oris_semectrovka_01_war_exploded/showtask?year=${prevYear}&month=${prevMonth}">← Назад</a>
    <a href="/oris_semectrovka_01_war_exploded/showtask?year=${nextYear}&month=${nextMonth}">Вперёд →</a>
</div>

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
            <a href="/oris_semectrovka_01_war_exploded/add-task?year=${year}&month=${month}&day=${dayPick}" class="add-btn">+ Новая задача</a>
        </div>

        <div class="tasks-list">
            <#if tasks?has_content>
                <#list tasks as task>
                    <div class="task">
                        <div class="task-header">
                            <div class="task-title">${task.title}</div>
                            <div class="task-actions">
                                <a href="/oris_semectrovka_01_war_exploded/edit-task?task_id=${task.id}" class="edit-btn">✏️</a>
                                <a href="/oris_semectrovka_01_war_exploded/delete-task?task_id=${task.id}" class="delete-btn">🗑️</a>
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

<div class="back">
    <a href="index">← На главную</a>
</div>

</body>
</html>