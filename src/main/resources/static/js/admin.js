const deleteButton = document.getElementById('deleteButton');

deleteButton.addEventListener('click', ()=> {
    const checkedInputs = document.querySelectorAll('input[type="checkbox"]:checked');
    const userIds = [];

    for (const input of checkedInputs) {
        userIds.push(parseInt(input.value));
    }

    if (userIds.length === 0) {
        alert('삭제할 유저를 선택해주세요.');
        return;
    }

    fetch('/users', {
        method: 'DELETE',
        body: JSON.stringify({userIds}),
        headers: {'Content-Type': 'application/json'}
    })
        .then(response => {
            if (response.ok) {
                alert("삭제 성공");
                location.reload(); // 이 부분으로 이동
            } else {
                throw new Error('삭제 실패');
            }
        })
        .catch(error => {
            console.error('삭제 실패:', error);
        });
});

function searchContent() {
    event.preventDefault(); // 기본 이벤트 동작 방지

    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("searchInput");

    // 검색어를 URL에 추가
    var currentUrl = new URL(window.location.href);
    currentUrl.searchParams.set("search", input.value);
    location.replace(currentUrl); // 페이지 새로 고침
}