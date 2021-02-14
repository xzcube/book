<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>尚硅谷会员注册页面</title>

	<!--静态包含base标签、css样式、jQuery文件-->
	<%@include file="/pages/common/head.jsp"%>
	<script>
		$(function(){
			$("#username").blur(function () {
				// 获取输入的用户名
				let username = this.value;
				$.getJSON("http://localhost:8081/book/userServlet", "action=ajaxExistsUsername&username=" + username, function (data) {
					if(data.existsUsername){
						$("span.errorMsg").text("用户名已被注册!");
					}else if(username == ""){
						$("span.errorMsg").text("请输入用户名");
					}else {
						$("span.errorMsg").text("该用户名尚未被注册");
					}
				});
			});

			//给验证码的图片绑定单击事件
			$("#code_img").click(function () {
				//在事件响应的function函数中有一个this对象，这个this对象是当前正在响应事件的dom对象
				//src属性表示验证码img标签的图片路径，它可读可写
				// 在路径的末尾增加一个内容，每次访问赋予不同的值，以绕过浏览器缓存
				this.src = "${basePath}kaptcha.jpg?d=" + new Date().getTime();
			});

			$("#sub_btn").click(function () {
				//验证用户名是否合法
				var userName = $("#username").val();
				var $userPatt = /^\w{5,12}$/;
				if (userName.length == 0) {
					$("span.errorMsg").text("请输入用户名");
					return false;
				}

				if (!$userPatt.test(userName)) {
					$("span.errorMsg").text("用户名不合法");
					return false;
				}

				//验证密码是否合法
				var passwordText = $("#password").val();
				var $passwordPatt = /^\w{5,12}$/;
				if(passwordText.length == 0){
					$("span.errorMsg").text("请输入密码");
					return false;
				};

				if(!$passwordPatt.test(passwordText)){
					$("span.errorMsg").text("密码不符合要求");
					return false;
				}

				//确认密码
				var confirmPassword = $("#repwd").val();
				if(!(confirmPassword == passwordText)){
					$("span.errorMsg").text("两次输入的密码不一致");
					return false;
				}

				//验证电子邮箱
				var emailTest = $("#email").val();
				var $emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
				if(emailTest.length == 0){
					$("span.errorMsg").text("请输入邮箱");
					return false;
				}
				if(!$emailPatt.test(emailTest)){
					$("span.errorMsg").text("请输入正确的邮箱");
					return false;
				}

				//验证验证码
				var codeText = $("#code").val();
				codeText = $.trim(codeText); // 防止输入空格
				if(codeText.length == 0 || codeText == "") {
					$("span.errorMsg").text("验证码不能为空");
					return false;
				}
				$("span.errorMsg").text("");
			});
		});
	</script>
	<style type="text/css">
		.login_form{
			height:420px;
			margin-top: 25px;
		}

	</style>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg">
									<%--<%=request.getAttribute("msg") == null ? "" : request.getAttribute("msg")%>--%>
									${requestScope.msg}
								</span>
							</div>

							<div class="form">
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="register">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="5-12位的字母，数字，下划线" autocomplete="off"
										   tabindex="1" name="username" id="username"
										   <%--value="<%=request.getAttribute("username") == null ? "" : request.getAttribute("username")%>"/>--%>
											value="${requestScope.username}"
									<br/>
									<br/>
									<br>
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="5-12位的字母，数字，下划线" autocomplete="off"
										   tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off"
										   tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off"
										   tabindex="1" name="email" id="email"
										   <%--用EL表达式代替jsp的表达式脚本--%>
										   <%--value="<%=request.getAttribute("email") == null ? "" : request.getAttribute("email")%>"/>--%>
											value="${requestScope.email}"
									<br />
									<br />
									<br>
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 100px;" name="code" id="code"/>
									<img alt="" id="code_img" src="kaptcha.jpg" style="float: right; margin-right: 40px; width: 120px; height: 35px">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn"/>
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<%--静态包含页脚内容--%>
		<%@include file="/pages/common/foot.jsp"%>
</body>
</html>