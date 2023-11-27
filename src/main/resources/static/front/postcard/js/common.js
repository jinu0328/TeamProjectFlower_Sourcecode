function allowDropFlower(ev) {
    ev.preventDefault();
}

function dragFlower(ev) {
    var dragData = {
        flowerNm: ev.target.innerText,
        englishNm: ev.target.getAttribute('data-englishnm'),
        flowerImage: ev.target.getAttribute('data-flowerImage')
    };
    ev.dataTransfer.setData("text", JSON.stringify(dragData));
}

function dropFlower(ev) {
    ev.preventDefault();
    var dragData = JSON.parse(ev.dataTransfer.getData("text"));
    var droppedFlowersTable = document.getElementById("droppedFlowersBody");

    // 기존에 드롭된 꽃이 있으면 모두 제거
    while (droppedFlowersTable.firstChild) {
        droppedFlowersTable.removeChild(droppedFlowersTable.firstChild);
    }

    // 새로 드롭된 꽃의 이름을 테이블에 추가
    var newTableRow = droppedFlowersTable.insertRow();
    var newCell = newTableRow.insertCell(0);
    newCell.innerText = dragData.flowerNm;

    // 드롭된 꽃의 영문 이름을 입력 필드에 저장
    var promptInput = document.getElementById("prompt");
    promptInput.value = dragData.englishNm;

    // 드롭 영역 이미지 변경 (옵션)
    var dropZoneImage = document.getElementById("dropZoneImage");
    dropZoneImage.src = "/front/images/openedEnvelope.png";

    // 새로운 꽃 이미지 영역에 이미지 추가
    var flowerImagesZone = document.getElementById("flowerImagesZone");

    // 기존에 추가된 이미지가 있으면 제거
    while (flowerImagesZone.firstChild) {
        flowerImagesZone.removeChild(flowerImagesZone.firstChild);
    }

    // 새로운 이미지 요소 생성 및 추가
    var imageElement = document.createElement("img");
    imageElement.id = "flowerImage";
    imageElement.src = dragData.flowerImage;
    flowerImagesZone.appendChild(imageElement);
}


/* 로딩창 관련 */
document.addEventListener('DOMContentLoaded', function() {
    var button = document.getElementById('createPostCard');
    if (button) {
        button.addEventListener('click', function() {
            document.getElementById('loadingOverlay').style.display = 'flex';
        });
    }
});
