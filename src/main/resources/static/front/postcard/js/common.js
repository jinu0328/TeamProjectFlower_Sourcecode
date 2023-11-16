function allowDrop(ev) {
    ev.preventDefault();
}

    function drag(ev) {
    ev.dataTransfer.setData("text", ev.target.innerText);
}

    function drop(ev) {
    ev.preventDefault();
    var data = ev.dataTransfer.getData("text");
    var promptInput = document.getElementById("prompt");
    promptInput.value += data + " "; // 여기서 공백을 추가하여 여러 이름을 연결
}
