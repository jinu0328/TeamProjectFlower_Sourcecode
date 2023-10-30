// 가중치 수정 S
function getSelectedId() {
    // 선택된 체크박스의 id 값을 반환
    let checkboxes = document.querySelectorAll('.checkbox-col input[type=checkbox]:checked');
    if (checkboxes.length === 0) {
        return null;
    }
    return checkboxes[0].value; // 첫 번째 선택된 체크박스의 값 반환
}
function editWeight() {
    let selectedId = getSelectedId();
    if (!selectedId) {
        alert("수정할 가중치를 선택하세요.");
        return;
    }

    let newWeight = prompt("새 가중치을 입력하세요.");
    if (newWeight) {
        // 데이터를 서버에 전송
        fetch('/admin/recommend/editWeight', {
            method: 'POST', // HTTP 메서드
            headers: {
                'Content-Type': 'application/json',
                // CSRF 토큰 추가 (필요한 경우)
                'X-CSRF-TOKEN': document.querySelector("input[name='_csrf']").value
            },
            body: JSON.stringify({
                id: selectedId,
                weight: newWeight,
            }), // 서버로 전송할 데이터를 JSON 형태로 변환
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json(); // 서버로부터의 응답을 JSON으로 파싱
            })
            .then(data => {
                if (data.success) {
                    alert("가중치가 성공적으로 수정되었습니다.");
                    // UI 업데이트 or 페이지 리로드
                    location.reload();
                } else {
                    alert("가중치 수정에 실패하였습니다: " + data.error);
                }
            })
            .catch(error => {
                alert("서버 통신 오류: " + error);
            });
    }
}
/* 가중치 수정 E */


// function showWeightList(buttonElement) {
//     const flowerNo = buttonElement.closest('tr').getAttribute('data-flowerNo');  // flowerNo 추출
//
//     // Ajax 요청을 사용하여 flowerNo의 가중치 및 키워드 이름을 가져옵니다.
//     fetch(`/admin/recommend/${flowerNo}`)
//         .then(response => response.json())
//         .then(data => {
//             let tableHtml = '<table>';
//             tableHtml += '<tr><th>키워드 이름</th><th>가중치</th></tr>';
//
//             data.forEach(item => {
//                 tableHtml += `<tr><td>${item.keywordNm}</td><td><input type="text" value="${item.weight}" name="weights[${item.keywordNo}]" /></td></tr>`;
//             });
//
//             tableHtml += '</table>';
//
//             // 페이지에 동적으로 생성한 표를 삽입
//             const formDiv = document.querySelector('#weightListForm');
//             formDiv.innerHTML = tableHtml + formDiv.innerHTML;  // 기존의 저장 버튼 위에 표를 추가
//             formDiv.style.display = 'block';  // 표시
//         });
// }

// 꽃 추가
function showAddFlowerForm() {
    document.getElementById('flowerAddForm').style.display = 'block';
}

/* 꽃 수정 S */
document.addEventListener('DOMContentLoaded', function() {
    const editFlowerButton = document.getElementById('editFlowerButton');
    if(editFlowerButton) {
        editFlowerButton.addEventListener('click', function() {
            const isEditing = editFlowerButton.getAttribute('data-editing') === 'true';

            if(isEditing) {
                saveFlowerChanges();
                editFlowerButton.innerText = "수정";
                editFlowerButton.setAttribute('data-editing', 'false')
            } else {
                enableFlowerEditingMode();
                editFlowerButton.innerText = '저장';
                editFlowerButton.setAttribute('data-editing', 'true');
            }
        });
    } else {
        console.error("Element with ID 'editFlowerButton' not found!");
    }
});

function enableFlowerEditingMode() {
    const checkboxes = document.querySelectorAll('input[name="selectedFlower"]:checked');
    const tbody = document.querySelector('.flower-table tbody');

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
function saveFlowerChanges() {
    const checkboxes = document.querySelectorAll('input[name="selectedFlower"]:checked');
    const updatedDataArray = []; // 여러 주문의 데이터를 저장하는 배열

    checkboxes.forEach(checkbox => {
        const row = checkbox.closest('tr');
        const cells = row.querySelectorAll('td:not(:first-child):not(:last-child):not(.no-input)');

        const updatedRowData = { flowerNo : checkbox.value };

        cells.forEach(cell => {
            const input = cell.querySelector('input');
            const field = cell.getAttribute('data-field');
            let value = input.value;
            if (input && field) {
                if (field === 'flowerNo') {
                    value = parseInt(value, 10);
                    console.log('Converted flowerNo:', value); // 이 부분을 추가
                }
                updatedRowData[field] = value;
                cell.innerText = value;
            }
        });

        updatedDataArray.push(updatedRowData); // 배열에 추가
        console.log(updatedRowData);
    });

    var csrfToken = document.querySelector('input[name="_csrf"]').value;

    fetch('/admin/recommend/editFlower', {
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
/* 꽃 수정 E */

/* 꽃 삭제 S */
function deleteFlower() {
    // 선택된 키워드 번호들을 담을 배열
    var flowerNos = [];
    // 체크된 체크박스의 값을 배열에 담기
    document.querySelectorAll('.flower-table input[type="checkbox"]:checked').forEach(function (checkbox) {
        flowerNos.push(checkbox.value);
    });

    // 체크된 키워드가 없으면 함수 종료
    if (flowerNos.length === 0) {
        alert('삭제할 키워드를 선택하세요.');
        return;
    }

    // 서버에 삭제 요청
    fetch('/admin/recommend/deleteFlower', {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            // CSRF 토큰을 헤더에 추가
            'X-CSRF-Token': document.querySelector('input[name="_csrf"]').value
        },
        body: JSON.stringify({ flowerNos: flowerNos })
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert('꽃이 삭제되었습니다.');
                // 페이지 새로고침 혹은 다시 데이터를 불러와 UI 업데이트
                location.reload();
            } else {
                alert('삭제 중 오류 발생: ' + data.message);
            }
        })
        .catch(error => console.error('Error:', error));
}
/* 꽃 삭제 E */

/* 키워드 추가 */
function showAddKeywordForm() {
    document.getElementById('keywordAddForm').style.display = 'block';
}

function getSelectedKeywordNo() {
    // 선택된 체크박스의 keywordNo 값을 반환
    let checkboxes = document.querySelectorAll('.checkbox-col input[type=checkbox]:checked');
    if (checkboxes.length === 0) {
        return null;
    }
    return checkboxes[0].value; // 첫 번째 선택된 체크박스의 값 반환
}

/* 키워드 수정 S */
function editKeyword() {
    let selectedKeywordNo = getSelectedKeywordNo();
    if (!selectedKeywordNo) {
        alert("수정할 키워드를 선택하세요.");
        return;
    }

    let newKeywordNm = prompt("새 키워드명을 입력하세요.");
    if (newKeywordNm) {
        // 데이터를 서버에 전송
        fetch('/admin/recommend/editKeyword', {
            method: 'POST', // HTTP 메서드
            headers: {
                'Content-Type': 'application/json',
                // CSRF 토큰 추가 (필요한 경우)
                'X-CSRF-TOKEN': document.querySelector("input[name='_csrf']").value
            },
            body: JSON.stringify({
                keywordNo: selectedKeywordNo,
                keywordNm: newKeywordNm,
            }), // 서버로 전송할 데이터를 JSON 형태로 변환
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json(); // 서버로부터의 응답을 JSON으로 파싱
            })
            .then(data => {
                if (data.success) {
                    alert("키워드가 성공적으로 수정되었습니다.");
                    // UI 업데이트 or 페이지 리로드
                    location.reload();
                } else {
                    alert("키워드 수정에 실패하였습니다: " + data.error);
                }
            })
            .catch(error => {
                alert("서버 통신 오류: " + error);
            });
    }
}
/* 키워드 수정 E */

/* 키워드 삭제 S */
function deleteKeywords() {
    // 선택된 키워드 번호들을 담을 배열
    var keywordNos = [];
    // 체크된 체크박스의 값을 배열에 담기
    document.querySelectorAll('.keywords-table input[type="checkbox"]:checked').forEach(function (checkbox) {
        keywordNos.push(checkbox.value);
    });

    // 체크된 키워드가 없으면 함수 종료
    if (keywordNos.length === 0) {
        alert('삭제할 키워드를 선택하세요.');
        return;
    }

    // 서버에 삭제 요청
    fetch('/admin/recommend/deleteKeywords', {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            // CSRF 토큰을 헤더에 추가
            'X-CSRF-Token': document.querySelector('input[name="_csrf"]').value
        },
        body: JSON.stringify({ keywordNos: keywordNos })
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert('키워드가 삭제되었습니다.');
                // 페이지 새로고침 혹은 다시 데이터를 불러와 UI 업데이트
                location.reload();
            } else {
                alert('삭제 중 오류 발생: ' + data.message);
            }
        })
        .catch(error => console.error('Error:', error));
}
/* 키워드 삭제 E */