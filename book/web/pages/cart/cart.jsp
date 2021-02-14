<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<!--静态包含base标签、css样式、jQuery文件-->
	<%@include file="/pages/common/head.jsp"%>

	<script>
		$(function () {
			/*给删除商品绑定单击事件*/
			$(".deleteItem").click(function () {
				let b = confirm("确定删除【" + $(this).parent().parent().find("td:first").text() + "】吗?");
				return b;
			});

			/*给清空购物车绑定单击事件*/
			$("#clearCart").click(function () {
				let confirm1 = confirm("确认要清空购物车吗?");
				return confirm1;
			});

			//给输入框绑定 onchange() 事件 当文本框中的信息发生改变时
			$(".updateCount").change(function () {
				//获取商品名称
				let name = $(this).parent().parent().find("td:first").text();
				//获取商品数量
				let count = this.value;
				//获取bookId
				let id = $(this).attr("bookId");
				let confirm2 = confirm("确认将【" + name + "】数量修改为【" + count + "】吗？");
				if(confirm2){
					//发起请求，给服务器保存修改
					location.href = "cartServlet?action=updateCount&count=" + count + "&id=" + id;
				}else {
					//defaultValue是表单的Dom对象属性,它表示默认的value属性值
					this.value = this.defaultValue;
				}
			});
		});
	</script>
</head>
<body>
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<!--静态包含 登录成功之后的菜单-->
			<%@include file="/pages/common/login_success_menu.jsp"%>
	</div>
	
	<div id="main">

		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<c:if test="${empty sessionScope.cart.items}">
				<%--购物车为空的情况--%>
				<tr>
					<td colspan="5"><a href="index.jsp">当前购物车为空，点击继续浏览商品</a></td>
				</tr>
			</c:if>

			<c:if test="${not empty sessionScope.cart.items}">
				<%--购物车非空的情况--%>
				<c:forEach items="${sessionScope.cart.items}" var="entry">
					<tr>
						<td>${entry.value.name}</td>
						<td>
							<input class="updateCount" style="width: 60px; text-align: center"
								   bookId="${entry.value.id}"
								   type="text" value="${entry.value.count}">
						</td>
						<td>${entry.value.price}</td>
						<td>${entry.value.totalPrice}</td>
						<td><a class="deleteItem" href="cartServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>


		</table>

		<%--如果session中有Cart才会输出下面的内容--%>
		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span"><a id="clearCart" href="cartServlet?action=clear">清空购物车</a></span>
				<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>

	</div>

	<%--静态包含页脚内容--%>
	<%@include file="/pages/common/foot.jsp"%>
</body>
</html>