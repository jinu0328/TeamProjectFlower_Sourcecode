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

$(document).ready(function() {
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
        confirmButton.show();
    });

    confirmButton.on('click', function() {
        // Reset everything to original state
        nicknameEditBox.hide();
        confirmButton.hide();
        nicknameEditButton.show();
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
            url: "/user/mypage/profile/profilePage/checkNickNm", // 요청 URL
            data: { newNickname: newNickname },  // 전송할 데이터
            dataType: "json",  // 응답 데이터의 유형을 지정 (JSON 으로 받을 경우)
            success: function(response) {
                if (response.exists) {
                    alert("닉네임이 이미 존재합니다.");
                } else {
                    alert("사용 가능한 닉네임입니다.");
                }
            },
            error: function(error) {
                console.error(error);
                alert("오류가 발생했습니다. 다시 시도하세요.");
            }
        });
    });


    confirmButton.addEventListener('click', function() {
        const newNickname = nicknameInput.value;

        // 서버에 닉네임 변경 요청을 전송합니다:
        // (이 부분도 실제 프로젝트에 맞게 수정해야 합니다.)
        fetch('/updateNickname', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                // 필요한 다른 헤더들
            },
            body: JSON.stringify({ newNickname: newNickname })
        })
            .then(response => response.json())
            .then(data => {
                if (data.isSuccess) {
                    alert('닉네임이 변경되었습니다.');
                    document.querySelector('.name').innerText = newNickname;
                    nicknameEditBox.style.display = 'none';
                } else {
                    alert('닉네임 변경에 실패했습니다.');
                }
            })
            .catch(error => {
                console.error("Error during fetch:", error);
                alert('닉네임 변경 중 오류가 발생했습니다.');
            });
    });
});