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

$(document).ready(function() {
    var csrfToken = $("meta[name='_csrf']").attr("content");
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");

    // 모든 AJAX 요청에 CSRF 토큰을 헤더로 보내도록 설정
    $.ajaxSetup({
        beforeSend: function(xhr) {
            if (csrfToken && csrfHeader) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            }
        }
    });

    $('.OKButton').on('click', function() {
        console.log('버튼 클릭됨!');
        var orderNo = $(this).closest('tr').attr('data-order-no');
        $.ajax({
            type: "POST",
            url: "/user/mypage/orders/accept", // URL 수정
            data: { orderNo: orderNo }, // 데이터로 orderNo 전달

            success: function(response) {
                console.log('response : ', response);
                alert("주문이 수락되었습니다.");
                location.reload();
                // 여기서 UI를 업데이트하거나 페이지를 새로 고침할 수 있습니다.
            },
            error: function(xhr) {
                console.error('Error occurred: ', xhr);
                var errorMsg = xhr.status + ": " + (xhr.statusText || "Unknown error");
                alert("오류가 발생했습니다: " + errorMsg);
            }
        });
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