let selectedTags = new Set();

function addTag() {
    const container = document.getElementById('tagsContainer');

    // Проверяем максимальное количество тегов
    if (container.children.length >= 3) {
        alert('Максимум можно добавить 3 тега');
        return;
    }

    const newTag = document.createElement('div');
    newTag.className = 'tag-item';
    newTag.innerHTML = `
        <select name="tag_id[]" class="tag-select" required onchange="validateTagSelection(this)">
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
        const select = element.parentElement.querySelector('.tag-select');
        if (select.value) {
            selectedTags.delete(select.value);
        }
        element.parentElement.remove();
    }
}

function validateTagSelection(selectElement) {
    const value = selectElement.value;

    if (!value) {
        selectElement.style.borderColor = '';
        return;
    }

    if (selectedTags.has(value)) {
        alert('Этот тег уже выбран! Выберите другой тег.');
        selectElement.value = '';
        selectElement.style.borderColor = '';
    } else {
        // Удаляем предыдущее значение этого селекта из множества
        const allSelects = document.querySelectorAll('.tag-select');
        allSelects.forEach(select => {
            if (select !== selectElement && select.dataset.originalValue === value) {
                selectedTags.delete(value);
            }
        });

        // Добавляем новое значение
        selectedTags.add(value);
        selectElement.dataset.originalValue = value;
        selectElement.style.borderColor = '#a8c0ff';
    }
}

// Функция для инициализации существующих тегов при загрузке
function initializeExistingTags() {
    const selects = document.querySelectorAll('.tag-select');
    selects.forEach(select => {
        if (select.value) {
            selectedTags.add(select.value);
            select.dataset.originalValue = select.value;
        }
        select.addEventListener('change', function() {
            validateTagSelection(this);
        });
    });
}

// Вызываем при загрузке страницы
document.addEventListener('DOMContentLoaded', function() {
    initializeExistingTags();
});

// Проверка перед отправкой формы
function validateForm() {
    const selects = document.querySelectorAll('.tag-select');
    const values = new Set();
    let hasDuplicates = false;

    selects.forEach(select => {
        if (select.value) {
            if (values.has(select.value)) {
                hasDuplicates = true;
                select.style.borderColor = '#ff6b6b';
            } else {
                values.add(select.value);
                select.style.borderColor = '';
            }
        }
    });

    if (hasDuplicates) {
        alert('Пожалуйста, убедитесь, что все теги уникальны!');
        return false;
    }

    return true;
}
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