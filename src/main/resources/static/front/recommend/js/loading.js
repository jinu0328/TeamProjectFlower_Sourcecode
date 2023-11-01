// 로딩 페이지에서 7초후 자동으로 결과 페이지로 이동
/*document.addEventListener('DOMContentLoaded', function() {
    setTimeout(function() {
        const keywordNos = document.querySelector('body').getAttribute('data-keyword-nos');
        window.location.href = `http://localhost:3001/recommend/result?keywordNos=${keywordNos}`;
    }, 5000);  // 5초 후 실행
});*/

document.addEventListener("DOMContentLoaded", function() {
    const iconContainer = document.getElementById('iconContainer');
    let count = 0;

    const addIcon = () => {
        const icon = document.createElement('i');
        icon.className = 'xi-garden';
        iconContainer.appendChild(icon);

        setTimeout(() => {
            icon.classList.add('visible');
        }, 50);

        count++;

        if (count < 5) {
            setTimeout(addIcon, 1000);
        }
    };

    addIcon();
});


