//登录 注册验证url
var login_url = 'http://localhost:8080/bookShop/userValidate';
var register_url = 'http://localhost:8080/bookShop/userRegister';
var judge_url = 'http://localhost:8080/bookShop/judgeLogin';

// //刷新验证
// window.onload = function(){
// 		let url = 'http://localhost:8080/bookShop/judgeLogin'
// 		let xhr = new XMLHttpRequest()
// 		xhr.responseType = 'json'
// 		xhr.open('post', url)
// 		xhr.setRequestHeader('withCredentials', true)
// 		xhr.onload = (e) => {
// 		    let response = e.target.response
// 		    console.log(JSON.stringify(response))
// 		    //Do something...
// 		    if (response[0].success == 'true') {
// 		    	//xxx
// 		    	$("#right1").css("display","none");
// 				$("#right2").css("display","block");
// 				document.getElementById('user').innerHTML = '['+response[1].username+']';
// 		    }
// 		    else{
// 		    	$("#right1").css("display","block");
// 		    	$("#right2").css("display","none");
// 		    }
// 		}
// 		xhr.send()

// }


//验证登录输入
var validate = function(){
	var login_username=$('#login_username').val();
	var login_password=$('#login_password').val();
	var register_username=$('#register_username').val();
	var register_password1=$('#register_password1').val();
	var register_password2=$('#register_password2').val();
	if(login_username.length<4 || login_username.length>10){
		document.getElementById('login_username_validate').innerHTML="请输入4-10位的用户名";
	}
	else{
		document.getElementById('login_username_validate').innerHTML="";
	}
	if(login_password.length<6 || login_password.length>16){
		document.getElementById('login_password_validate').innerHTML="请输入6-16位的密码";
	}
	else{
		document.getElementById('login_password_validate').innerHTML="";
	}
	if(register_username.length<4 || register_username.length>10){
		document.getElementById('register_username_validate').innerHTML="请输入4-10位的用户名"
	}
	else{
		document.getElementById('register_username_validate').innerHTML="";
	}
	if(register_password1.length<6 || register_password1.length>16){
		document.getElementById('register_password1_validate').innerHTML="请输入6-16位的密码"
	}
	else{
		document.getElementById('register_password1_validate').innerHTML="";
	}
	if(register_password2 != register_password1){
		document.getElementById('register_password2_validate').innerHTML="请输入相同的密码"
	}
	else{
	    document.getElementById('register_password2_validate').innerHTML=""	}
	
};



//验证注册输入

$(document).ready(function(){

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
			var url=login_url
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
		var url= register_url
		var register_message=new XMLHttpRequest()
		register_message.responseType='json'
		register_message.open('post',url)
		register_message.setRequestHeader('Content-Type','application/x-javascript;charset=UTF-8')
		register_message.onload=(e)=>{
			var register_response=e.target.response
			// console.log(e.target.response)
			if(register_response.success == true){
				var url=login_url
				var login_message=new XMLHttpRequest()
				login_message.responseType='json'
				login_message.open('post',url)
				login_message.setRequestHeader('Content-Type','application/x-javascript;charset=UTF-8')
				login_message.onload=(e)=>{
					var login_response=e.target.response
					console.log(login_response[0].success)
					$("#right1").css("display","none");
					$("#right2").css("display","block");
					console.log(register_response.data)
					document.getElementById('user').innerHTML='['+register_response.data.username+']';
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

//退出登录
var logout = function(){
	console.log("logout")
		let url = login_url
		let xhr = new XMLHttpRequest()
		xhr.responseType = 'json'
		xhr.open('GET', url)
		xhr.onload = (e) => {
		    let response = e.target.response
		    console.log(JSON.stringify(response))
		    //Do something...
		    if (response[0].success == 'true') {
		    	//xxx
		    	$("#right2").css('display','none');
		    	$("#right1").css('display','block');
		    	alert("退出成功");
		    	
		    }
		    else{
		    	alert("退出出现错误");
		    }
		}
		xhr.send()
}

