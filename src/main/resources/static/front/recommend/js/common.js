function allowDrop(ev) {
    ev.preventDefault();
}

function drag(ev) {
    var dragData = {
        text: ev.target.innerText,
        keywordNo: ev.target.getAttribute('data-keywordNo')
    };
    ev.dataTransfer.setData("text", JSON.stringify(dragData));
}

function drop(ev) {
    ev.preventDefault();
    var dragData = JSON.parse(ev.dataTransfer.getData("text"));
    var basketList = document.querySelector("#basket ul");
    var placeholderText = document.querySelector("#basket p");

    var newListItem = document.createElement("li");
    newListItem.innerText = dragData.text;
    newListItem.setAttribute('data-keywordNo', dragData.keywordNo);
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
            var keywordNames = Array.from(document.querySelector("#basket ul").children).map(function(li) {
                return li.innerText;
            });
            var keywordNos = Array.from(document.querySelector("#basket ul").children).map(function(li) {
                return li.getAttribute('data-keywordNo');
            });

            document.getElementById('selectedKeywordNames').value = keywordNames.join(',');
            document.getElementById('selectedKeywordNos').value = keywordNos.join(',');
            document.getElementById('keywordsForm').submit();
        });
    }
});
