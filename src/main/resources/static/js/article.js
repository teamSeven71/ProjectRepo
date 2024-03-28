const deleteButton = document.getElementById('delete-btn');

if (deleteButton) {
    deleteButton.addEventListener('click', event => {
        let id = document.getElementById('article-id').value;
        fetch(`/api/article/${id}`, {
            method: 'DELETE'
        }).then(() => {
            alert('삭제가 완료되었습니다');
            location.replace('/articles');
        });
    });
}

const modifyButton = document.getElementById('modify-btn');

if (modifyButton) {
    // 클릭 이벤트가 감지되면 수정 API 요청
    modifyButton.addEventListener('click', event => {
        let params = new URLSearchParams(location.search);
        let id = params.get('id');
        console.log(id);

        const typeElements = document.getElementsByName('type');
        let typeValue;

        for (let i = 0; i < typeElements.length; i++) {
            if (typeElements[i].checked) {
                typeValue = typeElements[i].value;
                break; // 체크된 요소를 찾으면 반복문 종료
            }
        }

        fetch(`/api/articles/${id}`, {
            method: 'PUT',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value,
                type: typeValue
            })
        }).then(() => {
            alert('수정이 완료되었습니다');
            location.replace(`/article/${id}`);
        });
    });
}

const createButton = document.getElementById('create-btn');

if (createButton) {
    createButton.addEventListener('click', event => {

        const typeElements = document.getElementsByName('type');
        let typeValue;

        for (let i = 0; i < typeElements.length; i++) {
            if (typeElements[i].checked) {
                typeValue = typeElements[i].value;
                break; // 체크된 요소를 찾으면 반복문 종료
            }
        }

        fetch(`/api/articles`, {
            method: 'POST',
            headers: {
                "Content-Type": "application/json"
            },
            body : JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value,
                type: typeValue
            }),
        }).then(() => {
            alert('등록 완료되었습니다');
            location.replace("/articles/" + typeValue);
        })
    })
}

document.addEventListener("DOMContentLoaded", function() {
    var dropdownBtn = document.getElementById("categoryDropdownBtn");
    var dropdownMenu = document.getElementById("categoryDropdown");

    dropdownBtn.addEventListener("click", function() {
        dropdownMenu.classList.toggle("show");
    });

    document.addEventListener("click", function(event) {
        if (!dropdownBtn.contains(event.target) && !dropdownMenu.contains(event.target)) {
            dropdownMenu.classList.remove("show");
        }
    });
});
