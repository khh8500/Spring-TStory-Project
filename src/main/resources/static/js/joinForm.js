let isPass = false;
let isEmailVerified = false;
let verificationCode = ""; // 서버에서 받은 인증번호를 저장

// 비밀번호 일치 확인
$("#passwordCheck").keyup(function () {
    let password = $("#password").val();
    let passwordCheck = $(this).val();

    if (password !== passwordCheck) {
        $("#passCheck").text("비밀번호가 일치하지 않습니다.").removeClass("text-success").addClass("text-danger");
    } else {
        $("#passCheck").text("비밀번호가 일치합니다.").removeClass("text-danger").addClass("text-success");
    }
});

// 회원가입 체크
// 유저네임 중복체크
$("#username").on('input', function () {
    isPass = false; // 유저네임이 변경될 때마다 중복 체크를 다시 하도록 설정
    $("#existsByUsername").text("").removeClass("text-success text-danger");
});

// 이메일 입력 시 메일 인증 상태 초기화
$("#email").on('input', function () {
    isEmailVerified = false; // 이메일이 변경될 때마다 중복 체크를 다시 하도록 설정
    $("#auth-email").text("").removeClass("text-success text-danger");
});

// 회원가입 체크
$("#joinForm").on('submit', function() {
    return joinSubmit();
});

function joinSubmit(event) {
    if (!isPass) {
        alert("유저네임 중복체크를 해주세요");
        event.preventDefault();
        return false;
    }

    if (!isEmailVerified) {
        alert("메일 인증을 해주세요");
        event.preventDefault();
        return false;
    }

    return true;
}

// 유저네임 중복체크
// $("#username").on('input', function () {
//     isPass = false; // 유저네임이 변경될 때마다 중복 체크를 다시 하도록 설정
//     $("#existsByUsername").text("").removeClass("text-success text-danger");
// });

function checkUsername() {
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
                isPass = false;
            } else {
                $("#existsByUsername").text("유저네임을 사용할 수 있습니다").removeClass("text-danger").addClass("text-success");
                isPass = true;
            }
        })
        .fail(function (jqXHR, textStatus, errorThrown) {
            console.error("Error:", textStatus, errorThrown);
            $("#existsByUsername").text("중복체크 중 오류가 발생했습니다").removeClass("text-success").addClass("text-danger");
            isPass = false;
        });
}

// 메일인증
// $("#email").on('input', function () {
//     isEmailVerified = false; // 이메일이 변경될 때마다 중복 체크를 다시 하도록 설정
//     $("auth-email").text("").removeClass("text-success text-danger");
// });

function checkEmail() {
    let email = $("#email").val().trim();

    $.ajax({
        url: '/send-mail?email=' + email,
        method: 'GET',
    })
        .done(function (response) {
            console.log(response);
            alert("인증번호가 발송되었습니다");
            $("#emailVerification").show(); // 인증번호 입력칸 보이기
            $("#emailVerificationCode").attr('required', 'required'); // 인증번호 입력칸에 required 속성 추가
            // $("#auth-email").text("").removeClass("text-danger").addClass("text-success");
            isEmailVerified = true;
        })
        .fail(function (jqXHR, textStatus, errorThrown) {
            console.error("Error:", textStatus, errorThrown);
            $("#auth-email").text("이메일 인증 중 오류가 발생했습니다").removeClass("text-success").addClass("text-danger");
            isEmailVerified = false;
        });

}