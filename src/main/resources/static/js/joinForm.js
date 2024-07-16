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

// 유저네임 중복체크
let isPass = false;

function joinSubmit() {
    if (!isPass) {
        alert("유저네임 중복체크를 해주세요");
    }
    return isPass;
}

$("#username").on('input', function () {
    isPass = false; // 유저네임이 변경될 때마다 중복 체크를 다시 하도록 설정
    $("#existsByUsername").text("").removeClass("text-success text-danger");
});

function checkUsername() {
    let username = $("#username").val();

    if (username === "") {
        $("#existsByUsername").text("").removeClass("text-success text-danger");
        return; // 유저네임이 비어져있으면 검증하지 않음
    }

    $.ajax({
        url: '/check-username',
        method: 'GET',
        data: {
            username: username
        }
    })
        .done(function (response) {
            if (response.exists) {
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