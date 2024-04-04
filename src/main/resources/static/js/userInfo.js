const form = document.querySelector('.board_write');
const nickNameInput = document.querySelector('#nickName');

document.querySelector('#create-btn').addEventListener('click', () => {
    event.preventDefault();
    const nickName = nickNameInput.value;

    const xhr = new XMLHttpRequest();
    xhr.open('PUT', form.action);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.send(`nickName=${nickName}`);

    xhr.onload = () => {
        if (xhr.status === 200) {
            // 성공 처리
            alert('사용자 정보가 업데이트되었습니다.');
            location.reload();
        } else {
            // 오류 처리
            alert('오류가 발생했습니다. 다시 시도해주세요.');
        }
    };
});