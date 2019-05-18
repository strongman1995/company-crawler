$(document).ready(function() {
    var sessionId = "";
    var rate = $("#rate");
    var process = $("#process");
    var resDiv = $("#res-pan");
    var btn = $("#send-res");
    var altext = $("#alert-text");
    altext.hide();
    resDiv.hide();
    btn.click(function(){
        var email = $("#inputEmail").val();
        var filter  = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        if(filter.test(email)){
            altext.hide();
            $.ajax( {
                url:'addEmail',// 跳转到 action
                data:{
                    sessionId : sessionId,
                    email : email
                },
                type:'post',
                cache:false,
                dataType:'json',
                success:function(data) {
                    if(data.status == 0 ){
                        btn.text("报告结果将发送到您的邮箱,您可以关闭窗口");
                        btn.addClass("disabled");
                    }else{
                        btn.text("发送失败请重试");
                    }
                },
                error : function() {
                    // view("异常！");
                    btn.text("发送失败请重试");
                }
            });


        }else {
            altext.show();
        }
    });
    $("#s-i-bt").click(function () {
        var keyword = $("#search-key").val();

        if(keyword.length > 0){
            process.text("结果生成中...");
            resDiv.show();
            btn.removeClass("disabled");
            btn.text("将结果发送到邮箱");
            var aj = $.ajax( {
                url:'addTask',// 跳转到 action
                data:{
                    keywords : keyword
                },
                type:'post',
                cache:false,
                dataType:'json',
                success:function(data) {
                    if(data.status == 0 ){
                        // view("修改成功！");
                        //alert("成功！");
                        process.text("结果生成中...");
                        sessionId = data.sessionId;
                        setTimeout(checkProcess,1000);
                    }else{
                        process.text("生成报告失败，请重试！");
                        //alert("失败！");
                    }
                },
                error : function() {
                    // view("异常！");
                    process.text("生成报告失败，请重试！");
                }
            });
        }
    });

    function checkProcess(){
        $.ajax( {
            url:'getProcess',// 跳转到 action
            data:{
                sessionId : sessionId
            },
            type:'post',
            cache:false,
            dataType:'json',
            success:function(data) {
                if(data.status == 0 ){
                    // view("修改成功！");
                    //alert("成功！");
                    rate.text(data.process + "%");
                    if(parseFloat(data.process) >= 99.9){
                       setDownloadUrl();
                    }else {
                        setTimeout(checkProcess,1000);
                    }

                }else{
                    process.text("生成报告失败，请重试！");
                    //alert("失败！");
                }
            },
            error : function() {
                // view("异常！");
                process.text("生成报告失败，请重试！");
            }
        });
    }

    function setDownloadUrl(){
        $.ajax( {
            url:'getResultFile',// 跳转到 action
            data:{
                sessionId : sessionId
            },
            type:'post',
            cache:false,
            dataType:'json',
            success:function(data) {
                if(data.status == 0 ){

                    process.html("结果生成完毕，<a href='" + data.path + "'>点击下载 </a>");
                }else{
                    process.text("生成报告失败，请重试！");
                    //alert("失败！");
                }
            },
            error : function() {
                // view("异常！");
                process.text("生成报告失败，请重试！");
            }
        });
    }
});