// 로딩 페이지에서 7초후 자동으로 결과 페이지로 이동
document.addEventListener('DOMContentLoaded', function() {
    setTimeout(function() {
        const keywordNos = document.querySelector('body').getAttribute('data-keyword-nos');
        const keywordNosStr = encodeURIComponent(keywordNos);
        window.location.href = `http://localhost:3001/recommend/result?keywordNos=${keywordNosStr}`;
    }, 7000);  // 7초 후 실행
});
