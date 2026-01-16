<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Календарь</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="${contextPath}/static/css/open-user-window.css">



</head>
<body>

<section class="hero">
    <div class="hero-content">
        <h1>Добро пожаловать в календарь!</h1>
        <p class="subtitle">Платформа для записей важных дел!</p>
        <a href="${contextPath}/login" class="btn">Начать сейчас</a>
    </div>
    <div class="scroll-indicator">
        <span>Листайте вниз</span>
        <i class="fas fa-chevron-down"></i>
    </div>
</section>

<section class="section" id="section3">
    <div class="container">
        <div class="section-text">
            <h2>Мощные возможности</h2>
            <p>Откройте для себя полный спектр функций, разработанных для решения ваших задач.</p>

            <div class="features">
                <div class="feature">
                    <i class="fas fa-shield-alt"></i>
                    <h3>Безопасность</h3>
                    <p>Ваши данные защищены</p>
                </div>
                <div class="feature">
                    <i class="fas fa-sync-alt"></i>
                    <h3>Синхронизация</h3>
                    <p>Работайте на любом устройстве</p>
                </div>
                <div class="feature">
                    <i class="fas fa-chart-line"></i>
                    <h3>Аналитика</h3>
                    <p>Анализируйте свои задачи</p>
                </div>
                <div class="feature">
                    <i class="fas fa-microchip"></i>
                    <h3>Развитие</h3>
                    <p>Ставьте новые цели</p>
                </div>
            </div>
        </div>
    </div>
</section>

<footer>
    <div class="container">

        <p>Современные решения для современных задач</p>
        <p>&copy; 2025 Все права защищены</p>
    </div>
</footer>


<script >

    document.addEventListener('DOMContentLoaded', function() {
        const sections = document.querySelectorAll('.section');

        const observer = new IntersectionObserver((entries) => {
            entries.forEach(entry => {
                if (entry.isIntersecting) {
                    entry.target.classList.add('visible');
                }
            });
        }, {threshold: 0.1
        });

        sections.forEach(section => {
            observer.observe(section);
        });
    });
</script>

</body>
</html>