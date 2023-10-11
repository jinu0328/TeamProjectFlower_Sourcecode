/* 닉네임 중복 관련 코드 */
$(document).ready(function() {
    $("#nickNmCheck").on("click", function() {
        var nickNm = $("#userNickNm").val();
        if (nickNm === "") {
            alert("닉네임을 입력하세요.");
            return;
        }

        $.ajax({
            type: "GET",
            url: "/user/join/checkNickNm",
            data: { nickNm: nickNm },
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
});
