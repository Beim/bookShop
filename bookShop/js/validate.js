//验证登录框
$(document).ready(function(){
		$("#login_input").validate({
			rules:{
				login_username:{
					required:true,
					minlength:4,
					maxlength:10
				},
				login_password:{
					required:true,
					minlength:6,
					maxlength:16
				}
			},
			messages:{
				login_username:{
					required:"必须要填写用户名",
					minlength:"用户名长度要大于4位",
					maxlength:"用户名长度要小于10位"
				},
				login_password:{
					required:"必须要填写密码",
					minlength:"密码长度必须大于6位",
					maxlength:"密码长度必须小于16位"
				}
			},
		})
	});



//验证注册框
$(document).ready(function(){
		$("#register_input").validate({
			rules:{
				register_username:{
					required:true,
					minlength:4,
					maxlength:10
				},
				register_password1:{
					required:true,
					minlength:6,
					maxlength:16
				},
				register_password2:{
					required:true,
					minlength:6,
					maxlength:16,
					equalTo:"#register_password1"
				},
			},
			messages:{
				register_username:{
					required:"必须要填写用户名",
					minlength:"用户名长度要大于4位",
					maxlength:"用户名长度要小于10位"
				},
				register_password1:{
					required:"必须要填写密码",
					minlength:"密码长度必须大于6位",
					maxlength:"密码长度必须小于16位"
				},
				register_password2:{
					required:"必须要填写密码",
					minlength:"密码长度必须大于6位",
					maxlength:"密码长度必须小于16位",
					equalTo:"两次填写密码必须一致"
				}
			},
		})
	});


//验证登录信息
	var login = function(){
		var login_username =  $('#login_username').val();
		var login_password = $('#login_password').val();
		if (login_username.length>3 && login_password.length>5){
			var data = {
				username:login_username,
				password:login_password
			}
			var url='http://192.168.10.5:8080/bookShop/userValidate'
			var login_message=new XMLHttpRequest()
			login_message.responseType='json'
			login_message.open('post',url)
			login_message.setRequestHeader('Content-Type','application/x-javascript;charset=UTF-8')
			login_message.onload=(e)=>{
				var login_response=e.target.response
				console.log(login_response[0])
				if(login_response[0].success == "true"){
					$("#right1").css("display","none");
					$("#right2").css("display","block");
					document.getElementById('user').innerHTML = '['+login_username+']';
					document.getElementById('login_close').click();
				}
				else{
					alert("输入的账号密码有错误");
				}
				console.log(JSON.stringify(login_response))
			}
			login_message.send(JSON.stringify(data))
		}
		else{
			alert('请输入正确格式的账号密码');
		}
	}

//验证注册信息
var register = function(){
	var register_username=$("#register_username").val();
	var register_password=$("#register_password1").val();
	var register_password1=$("#register_password1").val();
	var register_password2=$("#register_password2").val();
	if (register_username.length>3 && register_password.length>5 && register_password1 == register_password2){
		var data = {
			username:register_username,
			password:register_password
		}
		var url='http://192.168.10.5:8080/bookShop/UserRegister'
		var register_message=new XMLHttpRequest()
		register_message.responseType='json'
		register_message.open('post',url)
		register_message.setRequestHeader('Content-Type','application/x-javascript;charset=UTF-8')
		register_message.onload=(e)=>{
			var register_response=e.target.response
			// console.log(e.target.response)
			if(register_response.success == true){
				var url='http://192.168.10.5:8080/bookShop/userValidate'
				var login_message=new XMLHttpRequest()
				login_message.responseType='json'
				login_message.open('post',url)
				login_message.setRequestHeader('Content-Type','application/x-javascript;charset=UTF-8')
				login_message.onload=(e)=>{
					var login_response=e.target.response
					console.log(login_response[0].success)
					$("#right1").css("display","none");
					$("#right2").css("display","block");
					document.getElementById('user').innerHTML='['+register_response.data+']';
					document.getElementById('register_close').click();
				}
				login_message.send(JSON.stringify(data))
			}
			else{
				alert("用户名已经存在");
			}
		}
		register_message.send(JSON.stringify(data))
	}
	else{
		alert("请输入合法的注册信息");
	}
}