/* 주문 리스트 수정 S */
document.addEventListener('DOMContentLoaded', function() {
    const editButton = document.getElementById('editButton');

    if (editButton) {
        editButton.addEventListener('click', function() {
            const isEditing = editButton.getAttribute('data-editing') === 'true';

            if (isEditing) {
                saveChanges();
                editButton.innerText = '수정';
                editButton.setAttribute('data-editing', 'false');
            } else {
                enableEditingMode();
                editButton.innerText = '저장';
                editButton.setAttribute('data-editing', 'true');
            }
        });
    } else {
        console.error("Element with ID 'editButton' not found!");
    }
});

function enableEditingMode() {
    const checkboxes = document.querySelectorAll('input[name="selectedOrders"]:checked');
    const tbody = document.querySelector('.order-table tbody');

    checkboxes.forEach(checkbox => {
        const row = checkbox.closest('tr');
        const cells = row.querySelectorAll('td:not(:first-child):not(:last-child):not(.no-input)');

        cells.forEach(cell => {
            const originalText = cell.innerText;
            cell.innerHTML = `<input type="text" value="${originalText}">`;
        });

        tbody.appendChild(row);
    });
}

function saveChanges() {
    const checkboxes = document.querySelectorAll('input[name="selectedOrders"]:checked');
    const updatedDataArray = []; // 여러 주문의 데이터를 저장하는 배열

    checkboxes.forEach(checkbox => {
        const row = checkbox.closest('tr');
        const cells = row.querySelectorAll('td:not(:first-child):not(:last-child):not(.no-input)');

        const updatedRowData = { orderNo: checkbox.value }; // 체크박스 값 추가

        cells.forEach(cell => {
            const input = cell.querySelector('input');
            const field = cell.getAttribute('data-field');
            let value = input.value;
            if (input && field) {
                if (field === 'orderNo') {
                    value = parseInt(value, 10);
                    console.log('Converted orderNo:', value); // 이 부분을 추가
                }
                updatedRowData[field] = value;
                cell.innerText = value;
            }
        });

        updatedDataArray.push(updatedRowData); // 배열에 추가
        console.log(updatedRowData)
    });

    var csrfToken = document.querySelector('input[name="_csrf"]').value;

    fetch('/admin/order/editOrderList', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': csrfToken
        },
        body: JSON.stringify(updatedDataArray)
    })
        .then(response => response.json()) // 응답을 JSON으로 변환
        .then(data => {
            console.log("Server response:", data); // 서버 응답 출력

            if (Array.isArray(data) && data.length > 0) {  // 응답이 배열이고 그 길이가 0보다 큰지 확인
                alert(`성공적으로 수정되었습니다.`);
            } else {
                alert(`수정에 실패하였습니다: Unknown error`);
            }
        })
        .catch(error => {
            console.error("Error during fetch:", error); // 오류를 콘솔에 출력
            alert('서버 통신 오류: ' + error.message);
        });
}
/* 주문 리스트 수정 E */

/* 주문 리스트 삭제 S */
function deleteSelectedOrders() {
    var selectedOrderIds = [];
    var checkboxes = document.querySelectorAll('input[name="selectedOrders"]:checked');

    checkboxes.forEach(function(checkbox) {
        selectedOrderIds.push(Number(checkbox.value)); // 문자열을 숫자로 변환
    });

    if(selectedOrderIds.length === 0) {
        alert("삭제할 주문을 선택해주세요.");
        return;
    }

    fetch('/admin/order/deleteSelected', {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            // CSRF 토큰 추가
            'X-CSRF-TOKEN': document.querySelector('input[name="_csrf"]').value
        },
        body: JSON.stringify(selectedOrderIds)
    }).then(response => response.json()).then(data => {
        alert(data.message); // 응답의 message를 alert로 표시
        if(data.message === "선택된 주문이 성공적으로 삭제되었습니다.") {
            location.reload(true); // 페이지 새로고침
        }
    }).catch(error => {
        console.error('Error during fetch:', error);
        alert('서버 통신 오류: ' + error.message);
    });
}
/* 주문 리스트 삭제 E */