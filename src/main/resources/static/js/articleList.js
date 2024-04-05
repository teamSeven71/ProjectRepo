function searchContent() {
    event.preventDefault(); // 기본 이벤트 동작 방지

    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("searchInput");

    // 검색어를 URL에 추가
    var currentUrl = new URL(window.location.href);
    currentUrl.searchParams.set("search", input.value);
    location.replace(currentUrl); // 페이지 새로 고침
}