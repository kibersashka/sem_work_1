

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Календарь</title>
    <link rel="stylesheet" href="${contextPath}/static/css/calendar.css">

</head>
<body>

<h2>${monthName} ${year}</h2>

<div class="navigation">
    <a class="nav-link" href="?year=${prevYear}&month=${prevMonth}">← Предыдущий месяц</a>
    <a class="nav-link" href="?year=${nextYear}&month=${nextMonth}">Следующий месяц →</a>
</div>

<script>
    <#if error??>
    alert('${error}')
    </#if>
</script>

<table>
    <thead>
    <tr>
        <th>Пн</th>
        <th>Вт</th>
        <th>Ср</th>
        <th>Чт</th>
        <th>Пт</th>
        <th>Сб</th>
        <th>Вс</th>
    </tr>
    </thead>
    <tbody>
    <#list weeks as week>
        <tr>
            <#list week as day>
                <td>
                    <#if day??>
                        <a href="${contextPath}/showtaskOnDate?year=${year}&month=${month}&day=${day}">${day}</a>
                    <#else>
                        &nbsp;
                    </#if>
                </td>
            </#list>
        </tr>
    </#list>
    </tbody>
</table>


<br>
<a class="back-link" href="index">← На главную</a>


</body>
</html>
