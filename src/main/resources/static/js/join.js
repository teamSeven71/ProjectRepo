let password = document.getElementById('password');
let password2 = document.getElementById('password2');
let emailInput = document.getElementById('id');
let nicknameInput = document.getElementById('nickName');

function checkPassword() {
    if (password.value !== password2.value) {
        alert('비밀번호가 동일하지 않습니다.');
        password.value = "";
        password2.value = "";
        return false;
    }
    alert('비밀번호가 동일합니다.');
    return true;
}

function checkEmail() {
    const email = emailInput.value;

    if (!email) {
        alert("이메일을 입력해주세요.");
        return false;
    }

    fetch('/checkEmail', {
        method: 'POST',
        body: JSON.stringify({email}),
        headers: {'Content-Type': 'application/json'}
    })
        .then(response => {
            if (response.ok) {
                alert("사용 가능한 이메일 입니다.");
                return true;
            } else {
                alert("사용중인 이메일 입니다.");
                emailInput.value = "";
                return false;
            }
        })
}

function checkNickname() {
    const nickName = nicknameInput.value;

    if (!nickName) {
        alert("닉네임을 입력해주세요.");
        return false;
    }

    fetch('/checkNickName', {
        method: 'POST',
        body: JSON.stringify({nickName}),
        headers: {'Content-Type': 'application/json'}
    })
        .then(response => {
            if (response.ok) {
                alert("사용 가능한 닉네임 입니다.");
                return true;
            } else {
                alert("사용중인 닉네임 입니다.");
                nicknameInput.value = "";
                return false;
            }
        })
}

var form = document.getElementById('form');

function formSubmit() {
    if (checkEmail() && checkNickname() && checkPassword()) {
        form.action = "/user";
        form.method = "POST";
        form.submit();
    }
}