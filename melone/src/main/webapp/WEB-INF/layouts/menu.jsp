<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="sc_ctx">${ctx}/sc</c:set>

<div class='grid_16'>
	<ul id="nav">
		<li>
			<a href="#">Home</a>
		</li>
		<li>
			<a href="#">配置管理</a>
			<ul>
				<li>
					<a href="#">产品相关</a>
					<ul>
						<li>
							<a href="#">产品品牌</a>
						</li>
						<li>
							<a href="#">产品类型</a>
						</li>
						<li>
							<a href="#">产品标签</a>
						</li>
						<li>
							<a href="#">供应商（产品）</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="#">门店相关</a>
					<ul>
						<a href="#">仓库类型</a>
						<li>
							<a href="#">仓库</a>
						</li>
						<li>
							<a href="#">门店</a>
						</li>
					</ul>
				</li>
			</ul>
		</li>
	</ul>
</div>
<div class="clear"></div>
