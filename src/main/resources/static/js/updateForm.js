$(document).ready(function () {
    let passwordVerified = false; // 비밀번호 확인 여부를 저장

    // 비밀번호 입력 필드에 이벤트 리스너 추가
    $("#password").on('input', function () {
        $("#passCheck").text("").addClass("my_hidden"); // 메시지 초기화 및 숨김
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
            data: JSON.stringify({
                password: password
            }),
            success: function (data) {

                console.log("Response data:", data);  // 응답 데이터 출력

                if (data.success) {
                    $("#passCheck").text("비밀번호 확인되었습니다").removeClass("text-danger").addClass("text-success").removeClass("my_hidden");
                    passwordVerified = true; // 비밀번호 확인 성공
                } else {
                    $("#passCheck").text("유효한 비밀번호가 아닙니다").removeClass("text-success").addClass("text-danger").removeClass("my_hidden");
                    passwordVerified = false; // 비밀번호 확인 실패
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.error('Error:', errorThrown);
                $("#passCheck").text("비밀번호 확인 중 오류가 발생했습니다.").removeClass("text-success").addClass("text-danger").removeClass("my_hidden");
                passwordVerified = false; // 비밀번호 확인 실패
            }
        });
    });

    // 비밀번호 변경
    $("#updateForm").on('submit', function (event) {
        event.preventDefault(); // 기본 폼 제출 방지
        if (!passwordVerified) {``
            alert("기존 비밀번호를 확인해주세요.");
            return; // 비밀번호 확인이 안 된 경우 폼 전송 중지
        }

        let password = $("#password").val();
        let newPassword = $("#newPassword").val();

        $.ajax({
            url: '/change-password',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                password: password,
                newPassword: newPassword
            }),
            success: function (data) {
                if (data.success) {
                    alert("비밀번호가 성공적으로 변경되었습니다.");
                    window.location.href = "/"; // 메인 페이지로 리다이렉트
                } else {
                    alert(data.message);
                    $("#passCheck").text(data.message).removeClass("text-success").addClass("text-danger").removeClass("my_hidden");
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.error('Error:', errorThrown);
                $("#passCheck").text("비밀번호 변경 중 오류가 발생했습니다.").removeClass("text-success").addClass("text-danger").removeClass("my_hidden");
            }
        });
    });
});
