let selectedTags = new Set();

function addTag() {
    const container = document.getElementById('tagsContainer');


    if (container.children.length >= 3) {
        alert('Максимум можно добавить 3 тега');
        return;
    }

    const newTag = document.createElement('div');
    newTag.className = 'tag-item';
    newTag.innerHTML = `
        <select name="tag_id[]" class="tag-select" required">
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
    if (container.children.length >= 1) {
        const select = element.parentElement.querySelector('.tag-select');
        if (select.value) {
            selectedTags.delete(select.value);
        }
        element.parentElement.remove();
    }
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