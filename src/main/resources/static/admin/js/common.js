
// 리스트 체크박스 전체 선택 기능
function toggleCheckboxes() {
    const masterCheckbox = document.querySelector('th.checkbox-col > input[type="checkbox"]');
    const checkboxes = document.querySelectorAll('td.-col > input[type="checkbox"]');

    checkboxes.forEach(checkbox => {
        checkbox.checked = masterCheckbox.checked;
    })
}
