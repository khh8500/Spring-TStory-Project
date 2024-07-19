let isSameUsername = false; // username 중복체크 여부
let isSamePassword = false; // password 일치 여부
let isEmailVerified = false; // email 인증 체크 여부
let isEmailNumberValid = false; // email 인증 번호가 일치 여부

// 비밀번호 일치 확인
$("#passwordCheck").keyup(function () {
    let password = $("#password").val();
    let passwordCheck = $(this).val();

    if (password !== passwordCheck) {
        $("#passCheck").text("비밀번호가 일치하지 않습니다.").removeClass("text-success").addClass("text-danger");
        isSamePassword = false;
    } else {
        $("#passCheck").text("비밀번호가 일치합니다.").removeClass("text-danger").addClass("text-success");
        isSamePassword = true;
    }
});

// 유저네임 중복체크
$("#username").on('input', function () {
    isSameUsername = false; // 유저네임이 변경될 때마다 중복 체크를 다시 하도록 설정
    $("#existsByUsername").text("").removeClass("text-success text-danger");
});

$("#checkUsername").on("click", function(){
    let username = $("#username").val().trim();

    if (username === "") {
        $("#existsByUsername").text("").removeClass("text-success text-danger");
        return; // 유저네임이 비어져있으면 검증하지 않음
    }

    $.ajax({
        url: '/check-username?username=' + username,
        method: 'GET',
    })
        .done(function (response) {
            console.log(response);
            if (response.body.exists) {
                $("#existsByUsername").text("유저네임이 이미 사용 중입니다").removeClass("text-success").addClass("text-danger");
                isSameUsername = false;
            } else {
                $("#existsByUsername").text("유저네임을 사용할 수 있습니다").removeClass("text-danger").addClass("text-success");
                isSameUsername = true;
            }
        })
        .fail(function (jqXHR, textStatus, errorThrown) {
            console.error("Error:", textStatus, errorThrown);
            $("#existsByUsername").text("중복체크 중 오류가 발생했습니다").removeClass("text-success").addClass("text-danger");
            isSameUsername = false;
        });
})


// 인증번호 메일 발송
$("#email").on('input', function () {
    isEmailVerified = false; // 이메일이 변경될 때마다 중복 체크를 다시 하도록 설정
    $("#auth-email").text("").removeClass("text-success text-danger");
    $("#emailVerification").hide();
});

function checkEmail() {
    let email = $("#email").val().trim();

    $.ajax({
        url: '/send-mail?email=' + email,
        method: 'GET',
    })
        .done(function (response) {
            console.log(response);
            isEmailVerified = true;
            alert("인증번호가 발송되었습니다");

            $("#emailVerification").show(); // 인증번호 입력칸 보이기
        })
        .fail(function (jqXHR, textStatus, errorThrown) {
            console.error("Error:", textStatus, errorThrown);
            $("#auth-email").text("이메일 인증 중 오류가 발생했습니다").removeClass("text-success").addClass("text-danger");
        });
}

// 인증번호 일치 확인
$("#emailVerification").on('input', function () {
    isEmailNumberValid = false; // 인증번호가 변경될 때마다 중복 체크를 다시 하도록 설정
});

function checkCode() {
    let inputCode = $("#emailVerificationCode").val().trim();

    $.ajax({
        url: '/check-code?inputCode=' + inputCode,
        method: 'GET',
        async: true, // 비동기식 요청으로 변경
    })
        .done(function (response) {
            if (response.body) {
                alert("인증이 완료되었습니다");
                isEmailNumberValid = true;
            } else {
                alert("인증번호가 일치하지 않습니다");
                isEmailNumberValid = false;
            }
        })
        .fail(function () {
            $("#auth-email").text("인증번호 확인 중 오류가 발생했습니다").removeClass("text-success").addClass("text-danger");
            isEmailNumberValid = false;
        });
}

function joinSubmit(){

    // 모든 검증이 완료된 후에 폼을 제출
    if (validateForm()) {
        return true;
    }
    return false;
}


function validateForm() {

    if (!isSamePassword) {
        alert("비밀번호를 해주세요");
        return false;
    }

    if (!isSameUsername) {
        alert("유저네임 중복체크를 해주세요");
        return false;
    }

    if (!isEmailVerified) {
        alert("메일 인증 버튼을 클릭해주세요");
        return false;
    }

    if (!isEmailNumberValid) {
        alert("메일 인증 확인을 해주세요");
        return false;
    }

    return true;
}