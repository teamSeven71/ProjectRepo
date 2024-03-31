//----------------------------게시글 삭제 ----------------------------------------
const deleteButton = document.getElementById('delete-btn');

if (deleteButton) {
    console.log("버튼 있어여");
    deleteButton.addEventListener('click', event => {
        console.log("버튼 눌렷어여");
        let id = document.getElementById('article-id').value;
        let typeValue = document.getElementById('article-type').value;
        console.log(id);
        console.log(typeValue);
        fetch(`/api/articles/${id}`, {
            method: 'DELETE'
        })
            .then(response => {
                if (response.ok) {
                    alert('삭제가 완료되었습니다');
                    location.replace("/articles/" + typeValue);
                } else {
                    alert('게시물 삭제에 실패했습니다');
                }
            })
            .catch(error => {
                console.error('Error deleting article:', error);
                alert('게시물 삭제 중 오류가 발생했습니다');
            });
    });
}

//----------------------------게시글 수정 ----------------------------------------
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
//----------------------------게시글 생성 ----------------------------------------
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

//----------------------------댓글 생성 ----------------------------------------
const createComment = document.getElementById('create-comment');

if (createComment) {
    createComment.addEventListener('click', event => {

        let id = document.getElementById('article-id').value;

        fetch(`/api/comments/create`, {
            method: 'POST',
            headers: {
                "Content-Type": "application/json"
            },
            body : JSON.stringify({
                articleId: id,
                content: document.getElementById('comment').value
            }),
        }).then(() => {
            alert('등록 완료되었습니다');
            location.replace("/article/" + id);
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
