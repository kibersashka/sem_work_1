<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Календарь</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500&display=swap');

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
            flex-direction: column;
            align-items: center;
            padding: 3vw;
        }

        h2 {
            color: #5d6d7e;
            font-weight: 500;
            font-size: 2vw;
            margin-bottom: 2vw;
            text-align: center;
        }

        .navigation {
            display: flex;
            justify-content: center;
            flex-wrap: wrap;
            gap: 1.5vw;
            align-items: center;
            margin-bottom: 2vw;
        }

        a {
            text-decoration: none;
            color: #6c7b95;
            font-weight: 500;
            transition: all 0.3s ease;
        }

        .nav-link {
            background: rgba(255, 255, 255, 0.8);
            padding: 0.8vw 2vw;
            border-radius: 25px;
            backdrop-filter: blur(10px);
            border: 1px solid rgba(255, 255, 255, 0.5);
            font-size: 1vw;
        }

        .nav-link:hover {
            background: rgba(255, 255, 255, 0.95);
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
        }

        table {
            width: 90%;
            max-width: 1000px;
            border-collapse: separate;
            border-spacing: 0.6vw;
            background: rgba(255, 255, 255, 0.9);
            border-radius: 1.5vw;
            padding: 1.5vw;
            backdrop-filter: blur(10px);
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
            border: 1px solid rgba(255, 255, 255, 0.3);
        }

        th {
            background: linear-gradient(135deg, #a8c0ff 0%, #3f4c6b 100%);
            color: white;
            padding: 1vw;
            border-radius: 0.8vw;
            font-weight: 500;
            font-size: 1vw;
            text-align: center;
        }

        td {
            background: rgba(255, 255, 255, 0.7);
            border-radius: 1vw;
            text-align: center;
            padding: 1.5vw 0;
            transition: all 0.3s ease;
            border: 1px solid rgba(255, 255, 255, 0.5);
            width: calc(100% / 7);
        }

        td:hover {
            background: rgba(255, 255, 255, 0.95);
            transform: scale(1.05);
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
        }

        td a {
            display: block;
            width: 100%;
            height: 100%;
            color: #5d6d7e;
            font-weight: 400;
            font-size: 1.2vw;
        }

        td:hover a {
            color: #3f4c6b;
            font-weight: 500;
        }

        .back-link {
            background: rgba(255, 255, 255, 0.8);
            padding: 1vw 2.5vw;
            border-radius: 25px;
            margin-top: 2.5vw;
            backdrop-filter: blur(10px);
            border: 1px solid rgba(255, 255, 255, 0.5);
            font-size: 1vw;
        }

        .back-link:hover {
            background: rgba(255, 255, 255, 0.95);
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
        }

        /* Адаптивность */
        @media (max-width: 1024px) {
            h2 {
                font-size: 3vw;
            }

            .nav-link, .back-link {
                font-size: 1.5vw;
                padding: 1vw 3vw;
            }

            td a {
                font-size: 2vw;
            }

            th {
                font-size: 1.5vw;
            }
        }

        @media (max-width: 600px) {
            h2 {
                font-size: 5vw;
            }

            .nav-link, .back-link {
                font-size: 3.5vw;
                padding: 2vw 5vw;
            }

            table {
                width: 100%;
                border-spacing: 2vw;
                padding: 2vw;
            }

            td a {
                font-size: 4vw;
            }

            th {
                font-size: 3.5vw;
                padding: 2vw 0;
            }
        }
    </style>
</head>
<body>

<h2>${monthName} ${year}</h2>

<div class="navigation">
    <a class="nav-link" href="?year=${prevYear}&month=${prevMonth}">← Предыдущий месяц</a>
    <a class="nav-link" href="?year=${nextYear}&month=${nextMonth}">Следующий месяц →</a>
</div>

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
                        <a href="/oris_semectrovka_01_war_exploded/showtaskOnDate?year=${year}&month=${month}&day=${day}">${day}</a>
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
<a class="back-link" href="index">Назад</a>

</body>
</html>
