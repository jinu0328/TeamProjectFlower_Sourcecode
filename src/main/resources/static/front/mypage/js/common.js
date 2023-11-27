function openTab(evt, tabName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablink");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(tabName).style.display = "block";
    evt.currentTarget.className += " active";
}
function getUserNo() {
    // div 태그에서 data-user-no 속성을 읽어와 반환합니다.
    return $('#userData').data('userNo');
}


$(document).ready(function() {

    var csrfToken = $("meta[name='_csrf']").attr("content");
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");

    // 모든 AJAX 요청에 CSRF 토큰을 헤더로 보내도록 설정
    $.ajaxSetup({
        beforeSend: function(xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        }
    });

    const nicknameEditButton = $('#nicknameEditButton');
    const nicknameEditBox = $('#nicknameEditBox');
    const confirmButton = $('#confirmButton');
    const checkDuplicateButton = $('#checkDuplicateButton');
    const nicknameInput = $('#nicknameInput');

    nicknameEditButton.on('click', function() {
        // 닉네임 변경 버튼 숨기기
        nicknameEditButton.hide();

        // 중복 확인 박스와 확인 버튼 표시
        nicknameEditBox.css('display', 'flex');

    });




    checkDuplicateButton.on('click', function() {
        const newNickname = nicknameInput.val();

        if (newNickname === '') {
            alert('닉네임을 입력해주세요.');
            return;
        }

        // 여기서 $.ajax를 사용하여 닉네임 중복 확인 로직을 수행합니다.
        $.ajax({
            type: "GET",

            url: "/user/mypage/profile/check/checkNickNm", // 요청 URL
            data: { newNickname: newNickname },  // 전송할 데이터
            dataType: "json",  // 응답 데이터의 유형을 지정 (JSON 으로 받을 경우)
            success: function(response) {
                if (response.exists) {
                    alert("닉네임이 이미 존재합니다.");
                    confirmButton.hide();
                } else {
                    alert("사용 가능한 닉네임입니다.");
                    confirmButton.show();
                }
            },
            error: function(error) {
                console.error(error);
                alert("오류가 발생했습니다. 다시 시도하세요.");
            }
        });
    });






   confirmButton.on('click', function() {
        // Reset everything to original state
        const newNickname = nicknameInput.val();
        nicknameEditBox.hide();
        confirmButton.hide();
        nicknameEditButton.show();

        const userEditInfo = {
            userNo: getUserNo(), // userNo를 얻는 함수를 호출
            userNickNm: newNickname // 입력한 새 닉네임
        };

        $.ajax({
            type: "POST",
            url: "/user/mypage/profile/profilePage/update/updateNickname",
            contentType: "application/json",
            data: JSON.stringify(userEditInfo),
            dataType: "json",
            success: function(response) {
                // 서버에서 응답으로 온 UserEditInfo 객체 사용
                alert('닉네임이 <' + response.userNickNm + '>으로 변경되었습니다.');
                console.log('Nickname updated to: ', response.userNickNm);
                // 페이지 내 닉네임을 표시하는 요소를 업데이트 할 수 있습니다.
                $('#userNicknameDisplay').text(response.userNickNm);
                nicknameEditBox.hide();
                confirmButton.hide();
                nicknameEditButton.show();
            },or: function(xhr, status, error) {
                // 오류 메시지를 표시
                alert("An error occurred: " + xhr.responseText);
            }
        });
    });


});

function setupAjax(csrfToken, csrfHeader) {
    $.ajaxSetup({
        beforeSend: function(xhr) {
            if (csrfToken && csrfHeader) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            }
        }
    });
}

// 중복된 부분 처리 - orderstatus 수정
function sendOrderRequest(orderNo, url, successMessage) {
    $.ajax({
        type: "POST",
        url: url,
        data: { orderNo: orderNo },
        success: function(response) {
            console.log('response : ', response);
            alert(successMessage);
            location.reload();
        },
        error: function(xhr) {
            console.error('Error occurred: ', xhr);
            var errorMsg = xhr.status + ": " + (xhr.statusText || "Unknown error");
            alert("오류가 발생했습니다: " + errorMsg);
        }
    });
}

// 중복을 제거한 orderstatus 수정 ajax 구문 + csrfToken
$(document).ready(function() {
    var csrfToken = $("meta[name='_csrf']").attr("content");
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");
    setupAjax(csrfToken, csrfHeader);

    $('.OKButton').on('click', function() {
        var orderNo = $(this).closest('tr').attr('data-order-no');
        sendOrderRequest(orderNo, "/user/mypage/orders/accept", "주문이 수락되었습니다.");
    });

    $('.StartButton').on('click', function() {
        var orderNo = $(this).closest('tr').attr('data-order-no');
        sendOrderRequest(orderNo, "/user/mypage/orders/start", "상품 준비를 시작합니다.");
    });
    $('.PreparedButton').on('click', function() {
        var orderNo = $(this).closest('tr').attr('data-order-no');
        sendOrderRequest(orderNo, "/user/mypage/orders/prepared", "픽업 대기 상태입니다.");
    });
    $('.PickedUpButton').on('click', function() {
        var orderNo = $(this).closest('tr').attr('data-order-no');
        sendOrderRequest(orderNo, "/user/mypage/orders/pickup", "주문이 종료되었습니다.");
    });
});


// CSRF 토큰 설정, Post 방식 오류 제거 방법임
$(document).ready(function() {
    // AJAX 호출을 위한 CSRF 설정
    var csrfToken = $("meta[name='_csrf']").attr("content");
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");

    $.ajaxSetup({
        beforeSend: function(xhr) {
            if (csrfToken) { // CSRF 토큰이 존재하는지 확인
                xhr.setRequestHeader(csrfHeader, csrfToken);
            }
        }
    });

});