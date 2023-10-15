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

/* 주문 추가 S*/
document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('addOrderButton').addEventListener('click', function() {
        const tbody = document.querySelector('.order-table tbody');
        const newRow = document.createElement('tr');
        const newOrderHTML = `
            <!-- OrderEntity -->
            <td><input type="checkbox" name="newOrderCheckbox"></td>
            <td><input type="text" name="orderNo"></td>
            <td><input type="text" name="userNo"></td>
            <td><input type="text" name="createdAt"></td>
            <td><input type="text" name="userEmail"></td>
            <td><input type="text" name="userNickNm"></td>
            <td><input type="text" name="userNm"></td>
            <td><input type="text" name="cellPhone"></td>
            <td><input type="text" name="pickupDate"></td>
            <td><input type="text" name="pickupTime"></td>
            <td><input type="text" name="flowertype"></td>
            <td><input type="text" name="flowercolor"></td>
            <td><input type="text" name="pricerange"></td>
            <td><input type="text" name="message"></td>
            
            <td><button type="button" onclick="submitNewOrder(this)">추가</button></td>`;
            console.log("여기서 에러가 발생4");
        newRow.innerHTML = newOrderHTML;
        tbody.appendChild(newRow);
    });
    console.log("여기서 에러가 발생3");
    // ... 여기에 다른 자바스크립트 코드를 추가 ...
});

function submitNewOrder(buttonElement) {
    console.log("여기서 에러가 발생2");
    const rowData = buttonElement.closest('tr');
    const inputData = {
        orderNo: rowData.querySelector('input[name="orderNo"]').value,
        userNo: rowData.querySelector('input[name="userNo"]').value,
        createdAt: rowData.querySelector('input[name="createdAt"]').value,
        userEmail: rowData.querySelector('input[name="userEmail"]').value,
        userNickNm: rowData.querySelector('input[name="userNickNm"]').value,
        userNm: rowData.querySelector('input[name="userNm"]').value,
        cellPhone: rowData.querySelector('input[name="cellPhone"]').value,
        pickupDate: rowData.querySelector('input[name="pickupDate"]').value,
        pickupTime: rowData.querySelector('input[name="pickupTime"]').value,
        flowertype: rowData.querySelector('input[name="flowertype"]').value,
        flowercolor: rowData.querySelector('input[name="flowercolor"]').value,
        pricerange: rowData.querySelector('input[name="pricerange"]').value,
        message: rowData.querySelector('input[name="message"]').value,
    };
    
    console.log("여기서 에러가 발생");

    fetch('/admin/order/orderList', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': document.querySelector("input[name='_csrf']").value
        },
        body: JSON.stringify(inputData)
    })
        .then(response => response.json())
        .then(data => {
            console.log('Order submitted:', data);
            // 성공 메시지 출력 등의 후속 처리
        })
        .catch((error) => {
            console.error('Error:', error);
            // 실패 메시지 출력 등의 에러 처리
        });
}
/* 주문 추가 E*/