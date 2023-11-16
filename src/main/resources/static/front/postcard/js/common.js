function allowDropFlower(ev) {
    ev.preventDefault();
}

function dragFlower(ev) {
    // data-englishnm 속성에서 영문 이름을 가져옵니다.
    var englishNm = ev.target.getAttribute('data-englishnm');
    ev.dataTransfer.setData("text", englishNm);
}

function dropFlower(ev) {
    ev.preventDefault();
    var data = ev.dataTransfer.getData("text");
    var promptInput = document.getElementById("prompt");
    promptInput.value += data + " "; // 여기서 공백을 추가하여 여러 이름을 연결
}

