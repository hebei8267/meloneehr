<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="extjs" uri="http://www.freedom.org/tags/form"%>

<html>
	<head>
		<%@ include file="/WEB-INF/jsp/base/pageHeader.jsp" %>
        <%@ include file="/WEB-INF/jsp/base/commonCssJs.jsp" %>
        <%@ include file="/WEB-INF/jsp/base/importCommonPackage.jsp" %>
		<script type="text/javascript">
		<!--
			
		-->
        </script>
	</head>
	<body>
		<div class="defaultBody">
			<table class="appTitleTable"> 
                <tr> 
                    <td class="appTitle">
                        国家
                    </td> 
                    <td class="appScreenID"> 
                        -COUNTRY SETTING 001-
                    </td> 
                </tr>
                <tr> 
                    <table class="spiltLine"> 
                        <tr> 
                            <td><!-- 分割线 --></td>
                        </tr> 
                    </table> 
                </tr> 
                <tr> 
                    <td colspan="2" class="operTip">
                        <table>
                            <tr>
                                <td>
                                    <img src="${pageContext.request.contextPath}/images/tip.png">
                                </td>
                                <td>
                                    <span class="need">注意:[<img src="${pageContext.request.contextPath}/images/need-input.gif">]为必填项</span>
                                </td>
                            </tr>
							<tr>
								<td></td>
								<td>删除已有[国家]&nbsp;(可多选&nbsp;--&nbsp;<span style="font-weight: bold;">按住Ctrl多选,Shift连续选择</span>)</td>
							</tr>
                        </table>
                    </td> 
                </tr> 
            </table>
			<div>
				<form:form id="countryCfgForm" method="post" modelAttribute="CountrySetting001ViewObject">
					<table>
            			<tr height="10">
                		</tr>
						<tr>
							<td style="padding-left: 20px"> 
		                        &nbsp;
		                    </td>
		                    <td>
		                    	<table>
		                    		<tr>
		                    			<td>
		                    				<table>
		                    					<tr>
		                    						<!-- 添加国家 -->
		                    						<td align="left">
		                    							<input value="添  加" class="buttonSubmitLong" type="button" onclick="addRole();">
		                    						</td>
													<td width="20"> 
                                            		</td>
													<!-- 删除国家 -->
													<td align="left">
		                                                <input value="删  除" class="buttonDeleteLong" type="button" onclick="delRole();"> 
		                                            </td>
		                    					</tr>
											</table>
										</td>
									</tr>
									<tr height="10">
                            		</tr>
									<tr>
		                                <td> 
		                                    <div id="countryInfoGridDiv"> 
		                                    </div> 
		                                </td> 
		                                <td width="30">
		                                </td> 
		                                <td valign="top">
		                                    <table>
		                                    	<tr> 
					                                <td colspan="2" class="x-panel-header">
					                                    国家详细信息
					                                </td>
					                            </tr>
												<tr>
													<td class="inputItemName" height="30" width="100">
					                                    <img src="${pageContext.request.contextPath}/images/need-input.gif">编号
					                                </td> 
					                                <td class="inputItemCell" height="30" width="200">
					                                    <input type="text" size="20" maxlength="20" class="x-form-text inputText"/>
					                                </td>
												</tr>
												<tr>
													<td class="inputItemName" height="30" width="100">
					                                    <img src="${pageContext.request.contextPath}/images/need-input.gif">名称
					                                </td> 
					                                <td class="inputItemCell" height="30" width="200">
					                                    <input type="text" size="20" maxlength="20" class="x-form-text inputText"/>
					                                </td>
												</tr>
												<tr>
					                                <td class="inputItemName" height="30" width="100" valign="top" style="padding-top: 5px;">
					                                    详细描述
					                                </td>
					                                <td class="inputItemCell" height="115" width="200">
					                                    <textarea rows="5" cols="20" class="inputTextarea"></textarea>
					                                </td>
					                            </tr>
												<tr height="10">
	                            				</tr>
												<tr>
													<!-- 按钮 -->
					                                <td colspan="2" align="right">
					                                    <table> 
					                                        <tr> 
					                                            <td align="right"> 
					                                                <input value="更  新" class="buttonSubmitLong" type="button"> 
					                                            </td> 
					                                            <td width="20"> 
					                                            </td> 
					                                            <td align="right"> 
					                                                <input value="重  置" class="buttonResetLong" type="button">
					                                            </td> 
					                                        </tr> 
					                                    </table>
					                                </td> 
					                            </tr>
		                                    </table>
		                                </td>
		                            </tr>
								</table>
		                	</td>
						</tr>
					</table>
				</form:form>
			</div>
			<%@ include file="/WEB-INF/jsp/base/pageFooter.jsp" %>
            <%@ include file="/WEB-INF/jsp/base/sysErrorFrom.jsp" %>
		</div>
	</body>
</html>
