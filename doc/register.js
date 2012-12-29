$().ready(function() {
          
          var preference_from_station_suggest = null;
          getPassengerTypeOption(passengerTypeId );
          getEnterYearOption(enter_yearId);
          
          if($("#passengerType").val()=="3"){
          $("#student_info").slideDown(function() {
                                       parent.doIframe();
                                       $("#student_info").removeAttr("style");
                                       $("#student_info").css({"display":"block"});
                                       });
          }
          //隐藏和显示附加信息开始
          $("#passengerType").change(function(){
                                     if($("#passengerType").val()=="3"){
                                     $("#student_info").slideDown(function() {
                                                                  parent.doIframe();
                                                                  $("#student_info").removeAttr("style");
                                                                  $("#student_info").css({"display":"block"});
                                                                  });
                                     }else{
                                     $("#student_info").slideUp(function() {
                                                                $("#student_info").css({"display":"none"});
                                                                parent.doIframe();
                                                                });
                                     }
                                     });
          //隐藏和显示附加信息结束
          //  	$("#preference_from_station_name").val($("#city_code").val());
          //		$("#city_code").change(function(){
          //			$("#preference_from_station_name").val($("#city_code").val());
          //		});
          //验证附加消息开始
          $("#schoolNameText").click(function(){
                                     $("#schoolNameVal").css({display:"none"});
                                     $("#schoolNameText").attr("class","input_20txt");
                                     });
          
          $("#preference_from_station_name").click(function(){
                                                   $("#preferenceFromStationNameVal").css({display:"none"});
                                                   $("#preference_from_station_name").attr("class","input_20txt");
                                                   });
          // 优惠区间
          $("#preference_to_station_name").click(function(){
                                                 $("#preferenceFromStationNameVal").css({display:"none"});
                                                 $("#preference_to_station_name").attr("class","input_20txt");
                                                 });
          // 优惠到站
          //		$("#preference_to_station_name").click(function(){
          //			$("#preferenceToStationNameVal").css({display:"none"});
          //			$("#preference_to_station_name").attr("class","input_20txt");
          //		});
          $("#student_no").blur(function(){ 
                                if($("#student_no").val()==""){
                                $("#studentNoVal1").css({display:"inline"});
                                $("#student_no").attr("class","input_20txt error");
                                document.getElementById("studentNoVal1").innerHTML = "请输入学号！";
                                }else {
                                $("#studentNoVal1").css({display:"none"});
                                }
                                if(!checkNameChar1($.trim($("#student_no").val()))&&$("#student_no").val()!=""){
                                $("#studentNoVal").css({display:"inline"});
                                $("#student_no").attr("class","input_20txt error");
                                document.getElementById("studentNoVal").innerHTML = "填写的学号只能包含中文、英文、数字！";
                                }else {
                                $("#studentNoVal").css({display:"none"});
                                } 
                                });
          $("#department").blur(function(){ 
                                if(!checkNameChar1($.trim($("#department").val()))&&$("#department").val()!=""){
                                $("#departmentVal").css({display:"inline"});
                                $("#department").attr("class","input_20txt error");
                                document.getElementById("departmentVal").innerHTML = "填写的院系只能包含中文、英文、数字！";
                                }else {
                                $("#departmentVal").css({display:"none"});
                                }
                                });
          $("#school_class").blur(function(){ 
                                  if(!checkNameChar1($.trim($("#school_class").val()))&&$("#school_class").val()!=""){
                                  $("#schoolClassVal").css({display:"inline"});
                                  $("#school_class").attr("class","input_20txt error");
                                  document.getElementById("schoolClassVal").innerHTML = "填写的班级只能包含中文、英文、数字！";
                                  }else {
                                  $("#schoolClassVal").css({display:"none"});
                                  }
                                  });
          $("#preference_card_no").blur(function(){ 
                                        if(!checkNameChar1($.trim($("#preference_card_no").val()))&&$("#preference_card_no").val()!=""){
                                        $("#preferenceCardNoVal").css({display:"inline"});
                                        $("#preference_card_no").attr("class","input_20txt error");
                                        document.getElementById("preferenceCardNoVal").innerHTML = "填写的优惠卡号只能包含中文、英文、数字！";
                                        }else {
                                        $("#preferenceCardNoVal").css({display:"none"});
                                        }
                                        });
          //验证附加消息结束
          $("#submitLink").click(function(){
                                 var flag0=true;
                                 var flag1=true;
                                 var flag2=true;
                                 var flag3=true;
                                 var flag4=true;
                                 var flag5=true;
                                 var flag6=true;
                                 var flag7=true;
                                 if($("#passengerType").val()=="3"){
                                 //验证附加消息开始
                                 if($("#schoolNameText").val()=="" || $("#schoolNameText").val()=="简码/汉字") {
                                 $("#schoolNameVal").css({display:"inline"});
                                 $("#schoolNameText").attr("class","input_20txt error");
                                 $("#schoolNameVal").html("请选择学校！");
                                 flag0 = false;
                                 } else {
                                 $("#schoolNameVal").css({display:"none"});
                                 flag0 = true;
                                 }
                                 if($("#student_no").val()==""){
                                 $("#studentNoVal1").css({display:"inline"});
                                 $("#student_no").attr("class","input_20txt error");
                                 $("#studentNoVal1").html("请输入学号！");
                                 flag1 = false;
                                 }else {
                                 $("#studentNoVal1").css({display:"none"});
                                 flag1 = true;
                                 }
                                 if(!checkNameChar1($.trim($("#student_no").val()))&&$("#student_no").val()!=""){
                                 $("#studentNoVal").css({display:"inline"});
                                 flag2 = false;
                                 }else {
                                 $("#studentNoVal").css({display:"none"});
                                 flag2 = true;
                                 }
                                 if(!checkNameChar1($.trim($("#department").val()))&&$("#department").val()!=""){
                                 $("#departmentVal").css({display:"inline"});
                                 flag3 = false;
                                 }else {
                                 $("#departmentVal").css({display:"none"});
                                 flag3 = true;
                                 }
                                 if(!checkNameChar1($.trim($("#school_class").val()))&&$("#school_class").val()!=""){
                                 $("#schoolClassVal").css({display:"inline"});
                                 flag4 = false;
                                 }else {
                                 $("#schoolClassVal").css({display:"none"});
                                 flag4 = true;
                                 }
                                 if(!checkNameChar1($.trim($("#preference_card_no").val()))&&$("#preference_card_no").val()!=""){
                                 $("#preferenceCardNoVal").css({display:"inline"});
                                 flag5 = false;
                                 }else {
                                 $("#preferenceCardNoVal").css({display:"none"});
                                 flag5 = true;
                                 }
                                 //				if($("#preference_from_station_name").val()=="" || $("#preference_from_station_name").val()=="简码/汉字") {
                                 //				$("#preferenceFromStationNameVal").css({display:"inline"});
                                 //				$("#preference_from_station_name").attr("class","input_20txt error");
                                 //				$("#preferenceFromStationNameVal").html("请选择优惠发站！");
                                 //				flag6 = false;
                                 //			} else {
                                 //				$("#preferenceFromStationNameVal").css({display:"none"});
                                 //				flag6 = true;
                                 //			}
                                 //			if($("#preference_to_station_name").val()=="" || $("#preference_to_station_name").val()=="简码/汉字") {
                                 //				$("#preferenceToStationNameVal").css({display:"inline"});
                                 //				$("#preference_to_station_name").attr("class","input_20txt error");
                                 //				$("#preferenceToStationNameVal").html("请选择优惠到站！");
                                 //				flag7 = false;
                                 //			} else {
                                 //				$("#preferenceToStationNameVal").css({display:"none"});
                                 //				flag7 = true;
                                 //			}
                                 
                                 if($("#preference_from_station_name").val()=="" || $("#preference_from_station_name").val()=="简码/汉字") {
                                 $("#preferenceFromStationNameVal").css({display:"inline"});
                                 $("#preference_from_station_name").attr("class","input_20txt error");
                                 $("#preferenceFromStationNameVal").html("请选择优惠区间！");
                                 flag6 = false;
                                 } else {
                                 $("#preferenceFromStationNameVal").css({display:"none"});
                                 flag6 = true;
                                 }
                                 if($("#preference_to_station_name").val()=="" || $("#preference_to_station_name").val()=="简码/汉字") {
                                 $("#preferenceFromStationNameVal").css({display:"inline"});
                                 $("#preference_to_station_name").attr("class","input_20txt error");
                                 $("#preferenceFromStationNameVal").html("请选择优惠区间！");
                                 flag7 = false;
                                 } else {
                                 $("#preferenceFromStationNameVal").css({display:"none"});
                                 flag7 = true;
                                 }
                                 //验证附加消息结束
                                 }
                                 if(flag0&&flag1&&flag2&&flag3&&flag4&&flag5&&flag6&&flag7){
                                 $("#registform").submit();
                                 }
                                 });
          //页面加载时，checkbo选中，提示信息
          var reg=/[Y]{1}/;
          var smValue=$("#revSmCode:checked").val();
          if(reg.test(smValue)){
          $("#sm_error_msg").show();
          $("#sm_error_msg").html("*要接收铁路客户服务短信，请您填写手机号码！");
          }else{
          $("#sm_error_msg").hide();
          }
          jQuery.validator.addMethod("isEmail", function(value, element){
                                     var reg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
                                     return this.optional(element) ||  (reg.test(trim(value)));
                                     },"wrong email");
          
          //设置默认的属性
          $("#registform").validate({
                                    rules: {
                                    'loginUser.user_name': {
                                    required: true,
                                    validateUsersName:true,
                                    minlength: 6, 
                                    maxlength: 30,
                                    remote:"registAction.do?method=ansycheckUserName",
                                    checkWriteSpace:true
                                    },
                                    'user.password': {
                                    required: true,
                                    minlength: 6,
                                    checkPassward:true,
                                    checkWriteSpace:true,
                                    differentFrom:"#userName",
                                    validatPwd:true
                                    },
                                    'confirmPassWord': {
                                    required: true,
                                    checkPassward:true,
                                    equalTo: "#passWord",
                                    checkWriteSpace:true
                                    },
                                    
                                    'user.IVR_passwd':{
                                    required: true,
                                    number: true,
                                    minlength: 6,
                                    maxlength: 6
                                    },
                                    
                                    'confirmIvr_pwd':{
                                    required: true,
                                    equalTo: "#ivr_pwd"
                                    },
                                    
                                    'user.email': {
                                    required: true,
                                    isEmail:true,
                                    //					email: true,
                                    remote:"registAction.do?method=ansycheckEmail"
                                    },
                                    'loginUser.id_type_code': {
                                    required: true
                                    },
                                    'loginUser.id_no':{
                                    required: true,
                                    checkIdValidStr:true,
                                    //isIDCard:"#cardType",
                                    isSecIDCard:"#cardType",
                                    isFirIDCard:"#cardType",
                                    checkHkongMacao:"#cardType",
                                    checkTaiw:"#cardType",
                                    checkPassport:"#cardType"
                                    }, 
                                    'loginUser.name':{
                                    required:true,
                                    byteRangeLength: [3,20], 
                                    checkNameCharBlank:"cardType@cardCode"
                                    },
                                    'randCode':{
                                    required:true,
                                    validateRandCode:true
                                    },
                                    'user.mobile_no':{
                                    required:true,
                                    isMobile:true
                                    },
                                    'user.phone_no':{
                                    illegalChar:true
                                    },
                                    'user.address':{
                                    illegalChar:true
                                    },
                                    'user.postalcode':{
                                    isZipCode:true
                                    },
                                    'user.pwd_answer':{
                                    isQuestionNull:["#userPwdQuestion","#otherpasswordQuestion"],
                                    isAnswerNull:["#userPwdQuestion","#otherpasswordQuestion"],
                                    illegalChar:true
                                    }
                                    },
                                    
                                    
                                    messages: {
                                    'loginUser.user_name': {
                                    required: "请输入用户名！",
                                    remote: "该用户名已经占用，请重新选择用户名！",
                                    minlength:"用户名长度不能少于6个字符！",
                                    maxlength:"用户名长度不能多于30个字符！",
                                    validateUsersName:"用户名只能由字母、数字和_组成,须以字母开头！",
                                    checkWriteSpace:"输入的用户名不能包含空格"
                                    },
                                    'user.password': {
                                    required: "请输入密码！",
                                    minlength: "密码长度不能少于6个字符！",
                                    checkPassward: "输入的密码不能包含非法字符！",
                                    checkWriteSpace:"输入的密码不能包含空格",
                                    differentFrom:"密码不能与用户名相同！",
                                    validatPwd:"密码强度太弱，必须包含字母，数字，下划线中的两种或两种以上！"
                                    },
                                    'confirmPassWord': {
                                    required: "请输入确认密码！",
                                    minlength: "确认密码长度不能少于6个字符！",
                                    checkPassward: "输入的确认密码不能包含非法字符！",
                                    equalTo: "确认密码与密码不同！",
                                    checkWriteSpace:"输入的确认密码不能包含空格"
                                    },
                                    
                                    'user.IVR_passwd':{
                                    required:"请输入语音查询密码！",
                                    number:"语音查询密码只能为6位数字",
                                    minlength:"语音查询密码只能为6位数字",
                                    maxlength:"语音查询密码只能为6位数字"
                                    },
                                    
                                    'confirmIvr_pwd':{
                                    required:"请确认语音查询密码！",
                                    equalTo:"两次输入的语音查询密码不同！"
                                    },
                                    
                                    'loginUser.name':{
                                    required: "请输入您的姓名！",
                                    byteRangeLength:"允许输入的字符串在3-20个字符之间！",
                                    checkNameCharBlank:"姓名只能包含中文或者英文！"
                                    },
                                    'user.email':{ 
                                    required: "请输入电子邮件地址！",
                                    isEmail:"请输入有效的电子邮件地址！",
                                    //					email:"请输入有效的电子邮件地址！",
                                    remote:"电子邮件地址已被注册，请使用其他email！"
                                    },
                                    'loginUser.id_type_code': {
                                    required: "请选择证件类型！"
                                    },
                                    'loginUser.id_no': {
                                    required: "请输入证件号码！",
                                    //isIDCard:"请正确输入15或者18位的身份证号！",
                                    isSecIDCard:"请正确输入18位的身份证号！",
                                    isFirIDCard:"请正确输入15或者18位的身份证号！",
                                    checkIdValidStr:"输入的证件编号中包含中文信息或特殊字符！",
                                    //remote:"该证件编号对应的姓名已注册！",
                                    checkHkongMacao:"请输入有效的港澳居民通行证号码！",
                                    checkTaiw:"请输入有效的台湾居民通行证号码！",
                                    checkPassport:"请输入有效的护照号码！"
                                    },
                                    'randCode':{
                                    required:"请输入验证码！",
                                    validateRandCode:"验证码只能由数字或字母组成！"
                                    },
                                    'user.mobile_no':{
                                    required:"请输入手机号码！",
                                    isMobile:"您输入的手机号码不是有效的格式！"
                                    },
                                    'user.phone_no':{
                                    illegalChar:"您输入的固定电话中含有非法字符！"
                                    },
                                    'user.address':{
                                    illegalChar:"您输入的地址中含有非法字符！"
                                    },
                                    'user.postalcode':{
                                    isZipCode:"您输入的邮编不是有效的格式！"
                                    },
                                    'user.pwd_answer':{
                                    isQuestionNull:"您没有输入密码提示问题！",
                                    isAnswerNull:"您没有输入密码提示答案！",
                                    illegalChar:"您输入的密码提示答案中含有非法字符！"
                                    }
                                    },
                                    errorPlacement: function(error, element) {
                                    if ( element.is(":radio") )
                                    error.appendTo( element.parent().next() );
                                    else if ( element.is(":checkbox") )
                                    error.appendTo ( element.next() );
                                    else
                                    error.appendTo( element.parent().next() );
                                    }
                                    });
          
          
          var dp = function(){WdatePicker({isShowClear:false,readOnly:true,startDate:"1970-01-01 00:00:00",qsEnabled:false});};
          $("#bornDate").focus(dp).click(dp);
          
          $("#userPwdQuestion").change(function() {
                                       if($("#userPwdQuestion").val()=="customQuestion") {
                                       $("#otherpasswordQuestion").css({display:"inline"});
                                       } else {
                                       $("#otherpasswordQuestion").val("");
                                       $("#otherpasswordQuestion").css({display:"none"});
                                       }
                                       });
          
          $("#registform").submit(function(){
                                  // 验证性别
                                  var sexValue = null;
                                  $("input[name=user.sex_code]").each(function(){
                                                                      if(this.checked){
                                                                      sexValue = this.value;
                                                                      }
                                                                      });
                                  if(!checkSex(sexValue,"#cardType","#cardCode")){
                                  return false;
                                  }
                                  // 验证出生日期
                                  if(!checkBirdate($("#bornDate").val(),"#cardType","#cardCode")){
                                  return false;
                                  }
                                  
                                  if((jQuery.trim($("#mobileNo").val()).length)==0){
                                  var reg=/[Y]{1}/;
                                  var smValue=$("#revSmCode:checked").val();
                                  if(reg.test(smValue)){
                                  $("#sm_error_msg").show();
                                  $("#sm_error_msg").html("*要接收铁路客户服务短信，请您填写手机号码！");
                                  return false;
                                  }
                                  
                                  }
                                  });
          //当用户选择港澳台具名身份证时加载不同的帮助信息
          //$("#cardType").change(function(){
          //	var cardType = this.value;
          //	if(cardType != '2'&& cardType != '1'){
          //		$("#cardType_tooltip")[0].style.display = "inline";
          //		$("#tooltip_id").html($("#"+cardType+"_")[0].innerHTML);
          //	}else{
          //		$("#cardType_tooltip")[0].style.display = "none";
          //	}
          //	
          //});
          //$("#cardType_tooltip").tooltip({effect: 'slide',position:"top right"});
          
          //异步请求站名
          $.ajax({
                 url : ctx + '/registAction.do?method=getSchoolName',
                 type : "POST",
                 dataType : "json",
                 data:{provinceCode : $("#province_code").val()},
                 success : function(data, textStatus) {
                 $("#schoolNameText").schoolSuggest(data, {
                                                    hot_list : data,
                                                    dataContainer : '#schoolCode',
                                                    attachObject : '#schoolNameSuggest',
                                                    resultsClass:'ac_results_school'
                                                    });
                 },
                 error : function(e) {
                 }
                 });
          
          //出发地下拉效果开始
          $("#schoolNameText").click(function() {
                                     $("#schoolNameText").val('');
                                     $("#schoolCode").val('');
                                     });
          
          $("#province_code").change(function() {
                                     $("#schoolNameText").val('');
                                     $("#schoolCode").val('');
                                     //异步请求站名
                                     $.ajax( {
                                            url : ctx + '/registAction.do?method=getSchoolName',
                                            type : "POST",
                                            dataType : "json",
                                            data:{provinceCode : $("#province_code").val()},
                                            success : function(data, textStatus) {
                                            $("#schoolNameText").schoolSuggest(data, {
                                                                               hot_list : data,
                                                                               dataContainer : '#schoolCode',
                                                                               attachObject : '#schoolNameSuggest',
                                                                               resultsClass:'ac_results_school'
                                                                               });
                                            },
                                            error : function(e) {
                                            }
                                            });
                                     });
          
          
          $.ajax({
                 url : ctx + '/registAction.do?method=getAllCity',
                 type : "POST",
                 dataType : "json",
                 data:{
                 station_name:''
                 },
                 //contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                 success : function(data, textStatus) {
                 $("#preference_from_station_name").cityNameSuggest(data, {
                                                                    hot_list : data,
                                                                    dataContainer : '#preferenceFromStationCode',
                                                                    attachObject : '#preferenceFromStationNameSuggest',
                                                                    resultsClass:'ac_results',
                                                                    isMustInput:true
                                                                    });
                 },
                 error : function(e) {
                 }
                 });
          
          
          //出发地下拉效果开始
          $("#preference_from_station_name").click(function() {
                                                   $("#preference_from_station_name").val('');
                                                   $("#preferenceFromStationCode").val('');
                                                   });
          
          /*$("#preference_from_province_code").change(function() {
           $("#preference_from_station_name").val('');
           $("#preferenceFromStationCode").val('');
           //异步请求站名
           $.ajax( {
           url : ctx + '/registAction.do?method=getCityName',
           type : "POST",
           dataType : "json",
           data:{provinceCode : $("#preference_from_province_code").val()},
           success : function(data, textStatus) {
           $("#preference_from_station_name").cityNameSuggest(data, {
           hot_list : data,
           dataContainer : '#preferenceFromStationCode',
           attachObject : '#preferenceFromStationNameSuggest',
           resultsClass:'ac_results'
           });
           },
           error : function(e) {
           }
           });
           });*/
          
          /**
           $("#preference_to_station_name").keypress(function() {
           //alert($("#preference_from_station_name").val());
           var to_station_name = $("#preference_to_station_name");
           if(to_station_name != "" && to_station_name.length > 2) {
           //异步请求优惠到站城市名称
           $.ajax({
           url : ctx + '/registAction.do?method=getAllCity&station_name=' + to_station_name,
           type : "POST",
           dataType : "json",
           success : function(data, textStatus) {
           $("#preference_to_station_name").cityNameSuggest(data, {
           hot_list : data,
           dataContainer : '#preferenceToStationCode',
           attachObject : '#preferenceToStationNameSuggest',
           resultsClass:'ac_results',
           isMustInput:true
           });
           },
           error : function(e) {
           }
           });
           }
           });
           **/
          $.ajax({
                 url : ctx + '/registAction.do?method=getAllCity',
                 type : "POST",
                 dataType : "json",
                 data:{
                 station_name:''
                 },
                 //contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                 success : function(data, textStatus) {
                 $("#preference_to_station_name").cityNameSuggest(data, {
                                                                  hot_list : data,
                                                                  dataContainer : '#preferenceToStationCode',
                                                                  attachObject : '#preferenceToStationNameSuggest',
                                                                  resultsClass:'ac_results',
                                                                  isMustInput:true
                                                                  });
                 },
                 error : function(e) {
                 }
                 });
          //出发地下拉效果开始
          $("#preference_to_station_name").click(function() {
                                                 $("#preference_to_station_name").val('');
                                                 $("#preferenceToStationCode").val('');
                                                 });
          
          /*$("#preference_to_province_code").change(function() {
           $("#preference_to_station_name").val('');
           $("#preferenceToStationCode").val('');
           //异步请求站名
           $.ajax( {
           url : ctx + '/registAction.do?method=getCityName',
           type : "POST",
           dataType : "json",
           success : function(data, textStatus) {
           $("#preference_to_station_name").cityNameSuggest(data, {
           hot_list : data,
           dataContainer : '#preferenceToStationCode',
           attachObject : '#preferenceToStationNameSuggest',
           resultsClass:'ac_results'
           });
           },
           error : function(e) {
           }
           });
           });*/
          
          // 设置简码/汉字
          if ($("#schoolNameText").val() == '') {
          $("#schoolNameText").val("简码/汉字");
          }
          if ($("#preference_from_station_name").val() == '') {
          $("#preference_from_station_name").val("简码/汉字");
          }
          if ($("#preference_to_station_name").val() == '') {
          $("#preference_to_station_name").val("简码/汉字");
          }
          });

function inputMobileWhileChoseSM(){
    $("#sm_error_msg").hide();
    var smValue=$("#revSmCode:checked").val();
    var mobileLength=jQuery.trim($("#mobileNo").val()).length;
    var reg=/[Y]{1}/;
    if(reg.test(smValue) && mobileLength<1){
        $("#sm_error_msg").show();
        $("#sm_error_msg").html("*要接收铁路客户服务短信，请您填写手机号码！");
    }
    
}

function refreshImg(){
    $("#img_rrand_code").attr("src","passCodeAction.do?rand=rrand"+'&'+Math.random());
    
}

function checkNameChar1(value){
    return  /^[a-zA-Z\u4E00-\u9FA50-9]+$/.test(value);
}

//鼠标悬停显示提示框-姓名填写说明
function onStopHover_name(obj) {
    var position_ = $(obj).position();
    var pos_top = position_.top + 20;
    var pos_left = position_.left;
    var pos_top_new = pos_top;
    var pos_left_new = pos_left - 110;
    onStopHover($("#tip_info_div_name"),pos_top_new,pos_left_new);
}

// 鼠标悬停显示提示框-证件号码填写说明
function onStopHover_cardtype(obj) {
    var position_ = $(obj).position();
    var pos_top = position_.top;
    var pos_left = position_.left;
    var pos_top_new = pos_top;
    var pos_left_new = pos_left + 60;
    onStopHover($("#tip_info_div_cardtype"),pos_top_new,pos_left_new);
}

// 鼠标悬停显示提示框
function onStopHover(divObj,pos_top,pos_left) {
    divObj.attr("style","display:block;position:absolute;z-index: 120;top:"+pos_top+"px;left:"+pos_left+"px;");
    divObj.show();
}


//鼠标移开隐藏提示框(所有提示框)
function onStopOut() {
    $("#tip_info_div_name").hide();
    $("#tip_info_div_cardtype").hide();
}	
