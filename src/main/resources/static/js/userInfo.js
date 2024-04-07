const form = document.querySelector('.board_write');
const nickNameInput = document.querySelector('#nickName');
const emailInput = document.querySelector('#email');
const nameInput = document.querySelector('#name');

document.querySelector('#create-btn').addEventListener('click', () => {
    event.preventDefault();
    const data = {
        nickName: nickNameInput.value,
        name: nameInput.value,
        email: emailInput.value,
    };

    const xhr = new XMLHttpRequest();
    xhr.open('PUT', form.action);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify(data));

    xhr.onload = () => {
        if (xhr.status === 200) {
            // 성공 처리
            alert('사용자 정보가 업데이트되었습니다.');
            location.reload();
        } else if (xhr.status === 409) {
            // 중복값 처리
            const response = xhr.responseText;
            if (response === 'DUPLICATE_EMAIL') {
                alert('이미 사용 중인 이메일입니다.');
            } else if (response === 'DUPLICATE_NICKNAME') {
                alert('이미 사용 중인 닉네임입니다.');
            } else if (response === 'DUPLICATE_NICKNAME_AND_EMAIL') {
                alert('이미 사용 중인 닉네임과 이메일입니다.');
            } else {
                alert('오류가 발생했습니다. 다시 시도해주세요.');
            }
        } else {
            // 기타 오류 처리
            alert('오류가 발생했습니다. 다시 시도해주세요.');
        }
    };
});

const deleteButton = document.getElementById('deleteButton');

deleteButton.addEventListener('click', ()=> {
    const input = document.getElementById('user-id');
    const userIds = [parseInt(input.value)];

    fetch('/users', {
        method: 'DELETE',
        body: JSON.stringify({userIds}),
        headers: {'Content-Type': 'application/json'}
    })
        .then(response => {
            if (response.ok) {
                fetch('/logout', {
                    method: 'GET',
                    headers: {'Content-Type': 'application/json'}
                }).then(response => {
                    if (response.ok) {
                        alert("탈퇴 성공");
                        location.replace("/");
                    }
                })

            } else {
                throw new Error('탈퇴 실패');
            }
        })
        .catch(error => {
            console.error('탈퇴 실패:', error);
        });
});