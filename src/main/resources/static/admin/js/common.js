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

/* 키워드 수정 */
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

/* 키워드 삭제 */
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

// 리스트 체크박스 전체 선택 기능
function toggleCheckboxes() {
    const masterCheckbox = document.querySelector('th.checkbox-col > input[type="checkbox"]');
    const checkboxes = document.querySelectorAll('td.checkbox-col > input[type="checkbox"]');

    checkboxes.forEach(checkbox => {
        checkbox.checked = masterCheckbox.checked;
    })
}
