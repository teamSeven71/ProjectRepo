//----------------------------게시글 삭제 ----------------------------------------
const deleteButton = document.getElementById('delete-btn');
if (deleteButton) {
    deleteButton.addEventListener('click', event => {
        let id = document.getElementById('article-id').value;
        fetch(`/api/articles/delete/${id}`, {
            method: 'DELETE'
        })
            .then(response => {
                if (response.ok) {
                    // 서버로부터 성공적인 응답을 받았을 때
                    alert('삭제가 완료되었습니다');
                    location.replace('/');
                } else if(response.status === 403){
                    // 서버로부터 403 Forbidden 응답을 받았을 때
                    alert('해당 글의 작성자만 삭제할 수있습니다.');
                } else {
                    // 서버로부터 오류 응답을 받았을 때
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

        var selectedCategories = [];
        var checkboxes = document.querySelectorAll('input[name="types[]"]:checked');

        checkboxes.forEach(function(checkbox) {
            selectedCategories.push(checkbox.value);
        });

        fetch(`/api/articles/${id}`, {
            method: 'PUT',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value,
                categories: selectedCategories
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

        var selectedCategories = [];
        var checkboxes = document.querySelectorAll('input[name="types[]"]:checked');

        checkboxes.forEach(function(checkbox) {
            selectedCategories.push(checkbox.value);
        });

        fetch(`/api/articles`, {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
            },
            body : JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value,
                categories: selectedCategories
            })
        })
            .then(response => response.json()) // JSON 형태 파싱
            .then(data => {
                const id = data.id;
                alert('등록 완료되었습니다');
                location.replace(`/article/${id}`);
            })
            .catch(error => {
                console.error('등록 실패:', error);
            });
    });
}

//----------------------------댓글 생성 ----------------------------------------
const createComment = document.getElementById('create-comment');

if (createComment) {
    createComment.addEventListener('click', event => {
        event.preventDefault(); // 클릭 이벤트의 기본 동작인 폼 제출을 막습니다.
        fetch(`/api/comments/create`, { // 랜덤 파라미터 추가
            method: 'POST',
            headers: {
                "Content-Type": "application/json"
            },
            body : JSON.stringify({
                articleId: document.getElementById('article-id').value,
                content: document.getElementById('comment').value
            }),
        }).then(() => {
            alert('등록 완료되었습니다');
            // location.replace("/article/" + id);
            location.reload(); // 현재 페이지를 다시 불러오는 함수
        })
    })
}

//----------------------------댓글 삭제 ----------------------------------------
function deleteComment(event) {
    event.preventDefault(); // 기본 이벤트 동작 방지
    // 수정하기 버튼을 누른 댓글 영역에 대한 참조를 가져옴
    let commentContainer = event.target.closest('.d-flex');
    let commentId = commentContainer.querySelector('.comment-id').value;
    let id = document.getElementById('article-id').value;

    // 댓글 삭제 API 호출
    fetch(`/api/comments/delete?commentId=${commentId}`, {
        method: 'DELETE'
    })
        .then(response => {
            if (response.ok) {
                alert('삭제가 완료되었습니다');
                location.replace("/article/" + id);
            } else if(response.status === 403){
                // 서버로부터 403 Forbidden 응답을 받았을 때
                alert('해당 글의 작성자만 삭제할 수있습니다.');
            }else {
                alert('댓글 삭제에 실패했습니다');
            }
        })
        .catch(error => {
            console.error('Error deleting comment:', error);
            alert('댓글 삭제 중 오류가 발생했습니다');
        });
}
//----------------------------댓글 수정 api----------------------------------------
function updateComment(commentId) {

    // 삭제할 댓글의 ID 받아옴, article-id 정의.
    let id = document.getElementById('article-id').value;

    // 수정할 댓글의 내용 가져오기
    let updatedContent = document.querySelector('.comment textarea').value;

    // 댓글 삭제 API 호출
    fetch(`/api/comments/update?commentId=${commentId}`, {
        method: 'PATCH',
        headers: {
            "Content-Type": "application/json"
        },
        body : JSON.stringify({
            content: updatedContent
        }),
    })
        .then(response => {
            if (response.ok) {
                alert('수정 완료되었습니다');
                location.replace("/article/" + id);
            } else if(response.status === 403){
                // 서버로부터 403 Forbidden 응답을 받았을 때
                alert('해당 글의 작성자만 삭제할 수있습니다.');
                location.reload();
            }else {
                alert('댓글 수정에 실패했습니다');
            }
        })
        .catch(error => {
            console.error('Error deleting comment:', error);
            alert('댓글 수정 중 오류가 발생했습니다');
        });
}
//----------------------------댓글 수정하기 버튼 누를시----------------------------------------
function modifyComment(event) {
    event.preventDefault(); // 기본 이벤트 동작 방지

    // 수정하기 버튼을 누른 댓글 영역에 대한 참조를 가져옴
    let commentContainer = event.target.closest('.d-flex');

    // 해당 댓글의 commentId 가져오기
    let commentId = commentContainer.querySelector('.comment-id').value;

    // 댓글 내용 텍스트 엘리먼트를 가져옴
    let commentTextElement = commentContainer.querySelector('.comment-content');

    // 댓글 내용을 수정할 수 있는 텍스트 입력란으로 변경
    let commentContent = commentTextElement.textContent.trim(); // 기존 댓글 내용 가져오기
    let textareaElement = document.createElement('textarea');
    textareaElement.value = commentContent;
    commentTextElement.replaceWith(textareaElement);

    // "좋아요", "삭제" 버튼은 숨김 처리
    commentContainer.querySelector('.likeReply').style.display = 'none';
    commentContainer.querySelector('.deleteReply').style.display = 'none';

    // "수정" 버튼은 "취소"로 텍스트 변경
    let modifyButton = commentContainer.querySelector('.updateReply');
    modifyButton.innerHTML = '<i class="bi bi-x-circle-fill"></i> 취소';
    modifyButton.removeEventListener('click', modifyComment);
    modifyButton.addEventListener('click', function() {
        // cancelModification(event); // 이벤트 객체를 전달하여 함수 호출
        location.reload(); // 현재 페이지를 다시 불러오는 함수
    });

    // 수정 완료 버튼 추가
    let confirmButton = document.createElement('a');
    confirmButton.href = '#';
    confirmButton.classList.add('confirmReply');
    confirmButton.innerHTML = '<i class="bi bi-check-circle-fill"></i> 완료';
    confirmButton.addEventListener('click', function() {
        updateComment(commentId); // 수정 완료 버튼 클릭 시 해당 commentId를 전달하여 함수 호출
    });
    commentContainer.querySelector('.deleteReply').after(confirmButton);
}

//----------------------------------수정하기 버튼 end------------------------------

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
