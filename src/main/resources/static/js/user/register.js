var userNameresult = true;

var userPhoneresult = true;

var userEmailresult = true;

var countdown = 60;


/**
 * 发送验证码
 */
$("#sendYzm").on('click', function () {
    var userPhone = $("#number").val();
    var yzm = $("#yzNum");
    checkPhone(userPhone);
    //if (userPhoneresult == true) {
        /*checkUserByPhone(userPhone);*/
        $("#codeHint").show();
        settime();
        /*$.ajax({
            url: "sendYzm",
            method: "post",
            data: {"userPhone": userPhone},
            async: false,
            success: function (data) {
                alert(data.msg);
            }, error: function () {
                /!*alert("系统异常，请重新登录");*!/
                yzm.attr("placeholder","校验失败");
            }
        });*/
    //}
});

/**
 * 定时器
 */
function settime() {
    if (countdown == 0) {
        $("#sendYzm").attr("disabled", false);
        $("#sendYzm").text("获取验证码");
        countdown = 60;
        return;
    } else {
        $("#sendYzm").attr("disabled", true);
        $("#sendYzm").text("(" + countdown + ") s 重新发送");
        countdown--;
    }
    setTimeout(function () {
            settime()
        }
        , 1000)
}


/**
 * 注册表单提交时校验....
 */
$("#registerBtn").on("click", function () {
    var username = $("#username").val();
    var number = $("#number").val();
    var yzNum = $("#yzNum").val();
    var email = $("#email");
    var password = $("#password").val();
    checkEmail(email);
   /* checkUserByName(username);
    checkUserByEmail(email);*/
    if (userNameresult == true) {//userEmailresult != false
        $.ajax({
            url: "/afterRegister",
            method: "post",
            dataType: "json",
            async: false,
            data: {
                "userName": username,
                "userPassword": password,
                "userEmail": email,
                "userPhone": number,
                "yzNum": yzNum
            },
            success: function (data) {
                alert(data.msg);
            }, error: function (data) {
                alert("系统异常，请重新登录");
            }
        });
    }else{
        alert("请按提示正确填写注册表单");
    }
});

function checkUserByName(userName) {
    $.ajax({
        url: "../../user/checkUser.do",
        method: "post",
        dataType: "json",
        data: {"userName": userName},
        async: false,
        success: function (data) {
            if (data.success) {
                userNameresult = true;
            } else {
                /*alert("用户名已被使用，请更换！");*/
                userNameresult = false;
            }
        }
    });
}

function checkUserByPhone(userPhone) {
    $.ajax({
        url: "../../user/checkUser.do",
        method: "post",
        dataType: "json",
        data: {"userPhone": userPhone},
        async: false,
        success: function (data) {
            if (data.success) {
                userPhoneresult = true;
            } else {
                /*alert("手机号已被注册，请更换或者登陆！");*/
                userPhoneresult = false;
            }
        }
    });
}

/**
 * 修改data
 * @param userEmail
 */
function checkUserByEmail(userEmail) {
    $.ajax({
        url: "../../user/checkUser.do",
        method: "post",
        dataType: "json",
        data: {"userEmail": userEmail.val()},
        async: false,
        success: function (data) {
            if (data.success) {
                userEmailresult = true;
            } else {
                /* alert("邮箱已被注册，请更换或者登陆！");*/
                userEmailresult = false;
            }
        }
    });
}

/**
 * 校验手机的格式
 * @param userPhone
 */
function checkPhone(userPhone) {
    if (userPhone && /^1(3\d|47|(5[0-3|5-9])|(7[0|7|8])|(8[0-3|5-9]))-?\d{4}-?\d{4}$/.test(userPhone)) {
        if (userPhone.length > 11 || userPhone.length < 11) {
            /*alert("请输入11位的手机号码");*/
            userPhoneresult = false;
        }
    } else {
        /*alert("请输入正确的手机号码");*/
        userPhoneresult = false;
    }
}

//检测邮箱格式
function checkEmail(email) {
    var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"); //正则表达式
    if (email.val() === "") { //输入不能为空
        userEmailresult = false;
    } else if (!reg.test(email.val())) { //正则验证不通过，格式不对
        userEmailresult = false;
    } else {
        userEmailresult = true;
    }
}

// $(function(){
//     $("#registerBtn").on("click",function(){
//         var username = $("#username").val();
//         var number = $("#number").val();
//         var email = $("#email").val();
//         var password = $("#password").val();
//         $.ajax({
//             url:"../../user/register.do",
//             method:"post",
//             dataType:"json",
//             data:{"userName":username,"userPassword":password,"userEmail":email,"userPhone":number},
//             success:function (data) {
//                 if(data.success){
//                     alert("请登录163邮箱进行账户激活!");
//                     self.location = data.data;
//                 }else{
//                     alert(data.msg);
//                 }
//             },error:function (data) {
//                 alert("系统异常，请重新登录");
//             }
//         });
//     });
// });