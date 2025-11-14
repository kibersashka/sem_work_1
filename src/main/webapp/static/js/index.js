function toggleTask(element) {
    const allTasks = document.querySelectorAll('.task-item');
    allTasks.forEach(task => {
        if (task !== element && task.classList.contains('expanded')) {
            task.classList.remove('expanded');
        }
    });
    element.classList.toggle('expanded');
}
function refreshTasks() {
    fetch('/oris_semectrovka_01_war_exploded/index')
        .then(r => r.json())
        .then(console.log)
        .catch(console.error);
}