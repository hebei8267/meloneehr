<%@	page contentType="text/html;charset=UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@	taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@	page import="com.tjhx.common.utils.DateUtils"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"	/>
<c:set var="sc_ctx">
    ${ctx}/sc
</c:set>
<!DOCTYPE html>
<html>
    <head>
        <style type="text/css">
            .form-horizontal .control-label {
                width: 130px;
            }
            .form-horizontal .controls {
                margin-left: 145px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="span12">
                    <legend>
                        <h3>${sessionScope.__SESSION_USER_INFO.orgName} 销售明细信息</h3>
                    </legend>
                </div>
                <c:if test="${!empty	cashRun1}" >
                    <div class="span6"	style="margin-top: 10px;">
                        <form:form method="POST" class="form-horizontal" id="inputForm"	modelAttribute="cashRun1">
                            <form:hidden path="uuid"/>
                            <div class="control-group">
                                <label class="control-label">日期 :</label>
                                <label class="left-control-label">${cashRun1.optDateShow}</label>
                            </div>
                            <div class="control-group">
                                <label class="control-label">上班类型 :</label>
                                <label class="left-control-label">
                                    <c:if test="${cashRun1.jobType == 1}">
                                        早班
                                    </c:if>
                                    <c:if test="${cashRun1.jobType == 2}">
                                        晚班
                                    </c:if>
                                    <c:if test="${cashRun1.jobType == 4}">
                                        全天班
                                    </c:if> </label>
                            </div>
                            <div class="control-group">
                                <label class="control-label">班前余额 :</label>
                                <label class="left-control-label">${cashRun1.initAmt} 元</label>
                            </div>
                            <div class="control-group">
                                <label class="control-label">当前销售 :</label>
                                <label class="left-control-label">${cashRun1.saleAmt} 元</label>
                            </div>
                            <div class="control-group">
                                <label class="control-label">实际现金 :</label>
                                <label class="left-control-label">${cashRun1.cashAmt} 元</label>
                            </div>
                            <div class="control-group">
                                <label class="control-label">刷卡金额(单据) :</label>
                                <label class="left-control-label">${cashRun1.cardAmt} 元</label>
                            </div>
                            <div class="control-group">
                                <label class="control-label">刷卡金额(百威) :</label>
                                <label class="left-control-label">${cashRun1.cardAmtBw} 元</label>
                            </div>
                            <div class="control-group">
                                <label class="control-label">刷卡笔数 :</label>
                                <label class="left-control-label">${cashRun1.cardNum}</label>
                            </div>
                            <div class="control-group">
                                <label class="control-label">凭证号 :</label>
                                <label class="left-control-label">${cashRun1.cardCertNo}</label>
                            </div>
                            <div class="control-group">
                                <label class="control-label">存款金额 :</label>
                                <label class="left-control-label">${cashRun1.depositAmt} 元</label>
                            </div>
                            <div class="control-group">
                                <label class="control-label">存款人 :</label>
                                <label class="left-control-label">${cashRun1.depositor}</label>
                            </div>
                            <div class="control-group">
                                <label class="control-label">存款银行卡号 :</label>
                                <label class="left-control-label">${cashRun1.bankCardNo}</label>
                            </div>
                            <div class="control-group">
                                <label class="control-label">留存金额 :</label>
                                <label class="left-control-label">${cashRun1.retainedAmt} 元</label>
                            </div>
                            <div class="control-group">
                                <label class="control-label">备注 :</label>
                                <label class="left-control-label">${cashRun1.descTxt}</label>
                            </div>
                        </form:form>
                    </div>
                </c:if>

                <c:if test="${!empty	cashRun2}" >
                    <div class="span12 cash_daily visible-phone"></div>
                    <div class="span6"	style="margin-top: 10px;">
                        <form:form method="POST" class="form-horizontal" id="inputForm2"	modelAttribute="cashRun2">
                            <form:hidden path="uuid"/>
                            <div class="control-group">
                                <label class="control-label">日期 :</label>
                                <label class="left-control-label">${cashRun2.optDateShow}</label>
                            </div>
                            <div class="control-group">
                                <label class="control-label">上班类型 :</label>
                                <label class="left-control-label">
                                    <c:if test="${cashRun2.jobType == 1}">
                                        早班
                                    </c:if>
                                    <c:if test="${cashRun2.jobType == 2}">
                                        晚班
                                    </c:if>
                                    <c:if test="${cashRun2.jobType == 4}">
                                        全天班
                                    </c:if> </label>
                            </div>
                            <div class="control-group">
                                <label class="control-label">班前余额 :</label>
                                <label class="left-control-label">${cashRun2.initAmt} 元</label>
                            </div>
                            <div class="control-group">
                                <label class="control-label">当前销售 :</label>
                                <label class="left-control-label">${cashRun2.saleAmt} 元</label>
                            </div>
                            <div class="control-group">
                                <label class="control-label">实际现金 :</label>
                                <label class="left-control-label">${cashRun2.cashAmt} 元</label>
                            </div>
                            <div class="control-group">
                                <label class="control-label">刷卡金额(单据) :</label>
                                <label class="left-control-label">${cashRun2.cardAmt} 元</label>
                            </div>
                            <div class="control-group">
                                <label class="control-label">刷卡金额(百威) :</label>
                                <label class="left-control-label">${cashRun2.cardAmtBw} 元</label>
                            </div>
                            <div class="control-group">
                                <label class="control-label">刷卡笔数 :</label>
                                <label class="left-control-label">${cashRun2.cardNum}</label>
                            </div>
                            <div class="control-group">
                                <label class="control-label">凭证号 :</label>
                                <label class="left-control-label">${cashRun2.cardCertNo}</label>
                            </div>
                            <div class="control-group">
                                <label class="control-label">存款金额 :</label>
                                <label class="left-control-label">${cashRun2.depositAmt} 元</label>
                            </div>
                            <div class="control-group">
                                <label class="control-label">存款人 :</label>
                                <label class="left-control-label">${cashRun2.depositor}</label>
                            </div>
                            <div class="control-group">
                                <label class="control-label">存款银行卡号 :</label>
                                <label class="left-control-label">${cashRun2.bankCardNo}</label>
                            </div>
                            <div class="control-group">
                                <label class="control-label">留存金额 :</label>
                                <label class="left-control-label">${cashRun2.retainedAmt} 元</label>
                            </div>
                            <div class="control-group">
                                <label class="control-label">备注 :</label>
                                <label class="left-control-label">${cashRun2.descTxt}</label>
                            </div>
                        </form:form>
                    </div>
                </c:if>
            </div>
        </div>
    </body>
</html>