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