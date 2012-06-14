package demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.urls.CustomPieURLGenerator;
import org.jfree.data.general.DefaultPieDataset;

public class CreateInfo001 {
    public static void main(String[] args) throws IOException {
        // 数据
        DefaultPieDataset data = new DefaultPieDataset();
        data.setValue("高中以下", 6);
        data.setValue("高中", 5);
        data.setValue("大专", 4);
        data.setValue("本科", 3);
        data.setValue("硕士", 2);
        data.setValue("博士", 1);

        // 3D饼图
        PiePlot3D plot = new PiePlot3D(data);
        // 开启MAP信息
        plot.setToolTipGenerator(new StandardPieToolTipGenerator());

        // 设定链接
        // ***************************************************************
        // ----------------------------------------------
        // 构造一个Map用于指定具体类别的url
        // ----------------------------------------------
        Map<String, String> urls = new HashMap<String, String>();
        urls.put("高中以下", "http://www.livahu.com");
        urls.put("高中", "http://www.google.com");
        urls.put("大专", "http://www.baidu.com");
        urls.put("本科", "http://www.163.com");
        urls.put("硕士", "http://www.csdn.net");
        urls.put("博士", "http://gmail.com");
        CustomPieURLGenerator cpug = new CustomPieURLGenerator();
        cpug.addURLs(urls);
        plot.setURLGenerator(cpug);
        // ----------------------------------------------
        // 指定单个url
        // ----------------------------------------------
        // plot.setURLGenerator(new StandardPieURLGenerator("barview.jsp"));

        // ***************************************************************

        JFreeChart chart = new JFreeChart("", JFreeChart.DEFAULT_TITLE_FONT, plot, true);
        chart.setBackgroundPaint(java.awt.Color.white);// 可选，设置图片背景色
        chart.setTitle("程序员学历情况调查表");// 可选，设置图片标题

        StandardEntityCollection sec = new StandardEntityCollection();
        ChartRenderingInfo info = new ChartRenderingInfo(sec);

        // 500是图片长度，300是图片高度
        ChartUtilities.saveChartAsPNG(new File("c:\\001.jpg"), chart, 500, 300, info);
        // 输出MAP信息
        PrintWriter imageMapWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(
                new File("c:\\001.txt")), "UTF-8"));
        // 注意最后一个参数,是否利用OverLib(一个javascript库)
        ChartUtilities.writeImageMap(imageMapWriter, "map0", info, true);
        imageMapWriter.flush();

    }
}
