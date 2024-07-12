let passwordVerified = false; // 비밀번호 확인 여부를 저장

// 비밀번호 입력 필드에 이벤트 리스너 추가
$("#password").on('input', function () {
    $("#message-passwordCheck").text("").addClass("my_hidden"); // 메시지 초기화 및 숨김
    passwordVerified = false; // 비밀번호가 변경되면 다시 검증 필요
});

// 기존 비밀번호 확인
$("#password").on('blur', function () {
    let password = $("#password").val();

    if (password.trim() === "") return; // 비밀번호가 비어있으면 검증하지 않음

    $.ajax({
        url: '/check-password',
        method: 'POST',
        contentType: 'application/json',
        dataType: 'json', // 서버에서 JSON 형식으로 응답을 받을 것을 명시
        data: JSON.stringify({
            password: password
        }),
        headers: {
            'Accept': 'application/json' // JSON 형식으로 응답을 요청
        }
    }).done(function (data) {
        console.log("Response data:", data);  // 응답 데이터 출력

        if (data.status === 200) {
            $("#message-passwordCheck").text("비밀번호 확인하였습니다").removeClass("text-danger").addClass("text-success").removeClass("my_hidden");
            passwordVerified = true; // 비밀번호 확인 성공
        } else {
            $("#message-passwordCheck").text("유효한 비밀번호가 아닙니다").removeClass("text-success").addClass("text-danger").removeClass("my_hidden");
            passwordVerified = false; // 비밀번호 확인 실패
        }
    }).fail(function (jqXHR, textStatus, errorThrown) {
        console.error('Error:', errorThrown);
        let errorMessage = jqXHR.responseJSON ? jqXHR.responseJSON.msg : "비밀번호 확인 중 오류가 발생했습니다.";
        $("#message-passwordCheck").text(errorMessage).removeClass("text-success").addClass("text-danger").removeClass("my_hidden");
        passwordVerified = false; // 비밀번호 확인 실패
    });
});

// 비밀번호 변경
$("#updateForm").on('submit', function (event) {
    event.preventDefault(); // 기본 폼 제출 방지
    if (!passwordVerified) {
        alert("기존 비밀번호를 확인해주세요");
        return; // 비밀번호 확인이 안 된 경우 폼 전송 중지
    }

    // requestBody 객체 생성
    let requestBody = {
        password: $("#password").val(),
        newPassword: $("#newPassword").val()
    };

    $.ajax({
        url: '/change-password',
        method: 'POST',
        contentType: 'application/json',
        dataType: 'json', // 서버에서 JSON 형식으로 응답을 받을 것을 명시
        data: JSON.stringify(requestBody),
        headers: {
            'Accept': 'application/json' // JSON 형식으로 응답을 요청
        }
    }).done(function (data) {
        if (data.status === 200) {
            alert("비밀번호가 성공적으로 변경완료하였습니다");
            window.location.href = "/"; // 메인 페이지로 리다이렉트
        } else {
            alert(data.msg);
            $("#message-passwordCheck").text(data.msg).removeClass("text-success").addClass("text-danger").removeClass("my_hidden");
        }
    }).fail(function (jqXHR, textStatus, errorThrown) {
        console.error('Error:', errorThrown);
        let errorMessage = jqXHR.responseJSON ? jqXHR.responseJSON.msg : "비밀번호 변경 중 오류가 발생했습니다";
        $("#message-passwordCheck").text(errorMessage).removeClass("text-success").addClass("text-danger").removeClass("my_hidden");
    });
});
