<%@ page contentType="text/html;charset=utf-8"%>


<%@ page import="java.io.PrintWriter"%>

<%@ page import="org.jfree.chart.ChartRenderingInfo"%>
<%@ page import="org.jfree.chart.ChartUtilities"%>
<%@ page import="org.jfree.chart.JFreeChart"%>
<%@ page import="org.jfree.chart.entity.StandardEntityCollection"%>
<%@ page import="org.jfree.chart.labels.StandardPieSectionLabelGenerator"%>
<%@ page import="org.jfree.chart.labels.StandardPieToolTipGenerator"%>
<%@ page import="org.jfree.chart.plot.PiePlot3D"%>
<%@ page import="org.jfree.chart.servlet.ServletUtilities"%>
<%@ page import="org.jfree.chart.urls.StandardPieURLGenerator"%>
<%@ page import="org.jfree.data.general.DefaultPieDataset"%>

<HTML>
<HEAD>
<META http-equiv=Content-Type content="text/html; charset=GBK">
<TITLE>nacl_zhuang@hotmail.com</TITLE>
</HEAD>
<BODY>
<%
		DefaultPieDataset data = new DefaultPieDataset();
        data.setValue("高中以下", 6);
        data.setValue("高中", 5);
        data.setValue("大专", 4);
        data.setValue("本科", 3);
        data.setValue("硕士", 2);
        data.setValue("博士", 1);
        
		PiePlot3D plot = new PiePlot3D(data);//3D饼图
		plot.setURLGenerator(new StandardPieURLGenerator("barview.jsp"));//设定链接
		JFreeChart chart = new JFreeChart("",JFreeChart.DEFAULT_TITLE_FONT, plot, true);
		chart.setBackgroundPaint(java.awt.Color.white);//可选，设置图片背景色
		chart.setTitle("程序员学历情况调查表");//可选，设置图片标题
		plot.setToolTipGenerator(new StandardPieToolTipGenerator());
		StandardEntityCollection sec = new StandardEntityCollection();
		ChartRenderingInfo info = new ChartRenderingInfo(sec);
		PrintWriter w = new PrintWriter(out);//输出MAP信息
		//500是图片长度，300是图片高度
		String filename = ServletUtilities.saveChartAsPNG(chart, 500, 300, info, session);
		ChartUtilities.writeImageMap(w, "map0", info, false);
        
        String graphURL = request.getContextPath() + "/servletDisplayChart?filename=" + filename;
%>
<P ALIGN="CENTER">
<img src="<%= graphURL %>" width=500 height=300 border=0 usemap="#map0">
</P>
</BODY>
</HTML> 