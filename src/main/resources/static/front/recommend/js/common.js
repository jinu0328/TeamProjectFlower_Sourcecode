/*
function allowDrop(ev) {
    ev.preventDefault();
}

function drag(ev) {
    ev.dataTransfer.setData("text", ev.target.innerText);
}

function drop(ev) {
    ev.preventDefault();
    var data = ev.dataTransfer.getData("text");
    var basketList = document.querySelector("#basket ul");
    var placeholderText = document.querySelector("#basket p");

    var newListItem = document.createElement("li");
    newListItem.innerText = data;
    newListItem.draggable = true;
    newListItem.ondragstart = drag;
    newListItem.ondragend = function(e) { removeItem(e, newListItem); };

    basketList.appendChild(newListItem);
    placeholderText.style.display = "none";
}

function removeItem(ev, item) {
    var basketRect = document.getElementById("basket").getBoundingClientRect();
    if (
        ev.clientX < basketRect.left || ev.clientX > basketRect.right ||
        ev.clientY < basketRect.top || ev.clientY > basketRect.bottom
    ) {
        item.remove();


        if (document.querySelector("#basket ul").childElementCount === 0) {
            document.querySelector("#basket p").style.display = "block";
        }
    }
}

document.getElementById('recommendBtn').addEventListener('click', function () {
    window.location.href = "recommend/recommend/loading";
});

document.getElementById('recommendBtn').addEventListener('click', function() {
    // 바구니의 모든 키워드를 가져옴
    var keywords = Array.from(document.querySelector("#basket ul").children).map(function(li) {
        return li.innerText;
    });

    // 키워드를 하나의 문자열로 결합
    document.getElementById('selectedKeywords').value = keywords.join(',');

    // 폼을 제출하여 키워드를 '/loading' 경로로 POST
    document.getElementById('keywordsForm').submit();
});*/

// js 적용을 외부 파일에서 동적으로 바꾸는 과정에서 오류가 생겨서 일부 바꿨습니다.
// 각각 코드에 대한 간단한 주석도 추가하면 좋을 것 같아용
// 다시 확인해주시면 감사하겠습니다. 확인 후 이 주석은 지우셔도 됩니다. -대윤-

function allowDrop(ev) {
    ev.preventDefault();
}

function drag(ev) {
    ev.dataTransfer.setData("text", ev.target.innerText);
}

function drop(ev) {
    ev.preventDefault();
    var data = ev.dataTransfer.getData("text");
    var basketList = document.querySelector("#basket ul");
    var placeholderText = document.querySelector("#basket p");

    var newListItem = document.createElement("li");
    newListItem.innerText = data;
    newListItem.draggable = true;
    newListItem.ondragstart = drag;
    newListItem.ondragend = function(e) { removeItem(e, newListItem); };

    basketList.appendChild(newListItem);
    placeholderText.style.display = "none";
}

function removeItem(ev, item) {
    var basketRect = document.getElementById("basket").getBoundingClientRect();
    if (
        ev.clientX < basketRect.left || ev.clientX > basketRect.right ||
        ev.clientY < basketRect.top || ev.clientY > basketRect.bottom
    ) {
        item.remove();

        if (document.querySelector("#basket ul").childElementCount === 0) {
            document.querySelector("#basket p").style.display = "block";
        }
    }
}

document.addEventListener('DOMContentLoaded', function() {

    var recommendBtn = document.getElementById('recommendBtn');
    if (recommendBtn) {
        recommendBtn.addEventListener('click', function () {
            window.location.href = "recommend/recommend/loading";
        });

        recommendBtn.addEventListener('click', function() {
            var keywords = Array.from(document.querySelector("#basket ul").children).map(function(li) {
                return li.innerText;
            });

            document.getElementById('selectedKeywords').value = keywords.join(',');
            document.getElementById('keywordsForm').submit();
        });
    }
});
