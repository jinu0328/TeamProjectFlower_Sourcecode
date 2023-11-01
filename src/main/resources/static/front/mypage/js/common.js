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

document.addEventListener('DOMContentLoaded', function() {
    const nicknameEditButton = document.getElementById('nicknameEditButton');
    const nicknameEditBox = document.getElementById('nicknameEditBox');
    const confirmButton = document.getElementById('confirmButton');
    const checkDuplicateButton = document.getElementById('checkDuplicateButton');
    const nicknameInput = document.getElementById('nicknameInput');

    nicknameEditButton.addEventListener('click', function() {
        // 닉네임 변경 버튼 숨기기
        nicknameEditButton.style.display = 'none';

        // 중복 확인 박스와 확인 버튼 표시
        nicknameEditBox.style.display = 'flex';
        confirmButton.style.display = 'block'; // Confirm button displayed along with duplicate check
    });

    // 추가적으로, 확인 버튼을 클릭하면 모든 것을 다시 원래 상태로 숨기고 "닉네임 변경" 버튼만 다시 표시할 수 있습니다.
    confirmButton.addEventListener('click', function() {
        // Reset everything to original state
        nicknameEditBox.style.display = 'none';
        confirmButton.style.display = 'none';
        nicknameEditButton.style.display = 'block';
    });


    checkDuplicateButton.addEventListener('click', function() {
        const newNickname = nicknameInput.value;

        if (newNickname === '') {
            alert('닉네임을 입력해주세요.');
            return;
        }

        // 서버에 중복 확인 요청을 해야 합니다. 아래는 예시 코드입니다:
        // (실제로는 해당 URL과 메서드, 헤더 등을 프로젝트에 맞게 수정해야 합니다.)
        fetch('/checkNickname', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                // 필요한 다른 헤더들
            },
            body: JSON.stringify({ nickname: newNickname })
        })
            .then(response => response.json())
            .then(data => {
                if (data.isAvailable) {
                    alert('사용 가능한 닉네임입니다.');
                    confirmButton.style.display = 'block';
                } else {
                    alert('이미 사용 중인 닉네임입니다.');
                }
            })
            .catch(error => {
                console.error("Error during fetch:", error);
                alert('중복 확인 중 오류가 발생했습니다.');
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