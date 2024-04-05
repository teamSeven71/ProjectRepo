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
    filter = input.value.toUpperCase();
    table = document.querySelector(".table_user");
    tr = table.getElementsByTagName("tr");

    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[1]; // Change the index according to the column you want to search
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}