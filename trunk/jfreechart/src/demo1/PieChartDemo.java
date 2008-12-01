package demo1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieToolTipGenerator;
import org.jfree.chart.plot.MultiplePiePlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.util.TableOrder;

public class PieChartDemo {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String bookType[] = new String[] { "社科类", "文学类", "体育类", "少儿类" };
        String week[] = new String[] { "第1周", "第2周", "第3周", "第4周" };

        String title = "各周图书销量";

        // 创建数据集
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // 构建数据
        int bookSales;
        for (int i = 0; i < bookType.length; i++) {
            for (int j = 0; j < week.length; j++) {
                bookSales = 50 + i * 10 + j * 100;
                dataset.addValue(bookSales, bookType[i], week[j]);
            }
        }

        // 获取JFreeChart对象
        JFreeChart chart = ChartFactory.createMultiplePieChart(

        title, // 图表标题
                dataset, // 数据集
                TableOrder.BY_COLUMN, // 指定被提取数据的顺序
                false, // 是否包含图例
                true, // 是否包含提示工具
                true // 是否包含url
                );

        // 获取PiePlot对象
        MultiplePiePlot multiPlot = (MultiplePiePlot) chart.getPlot();
        JFreeChart obj = multiPlot.getPieChart();
        PiePlot plot = (PiePlot) obj.getPlot();
        // 开启MAP信息
        plot.setToolTipGenerator(new StandardPieToolTipGenerator());
        // 设置标签格式
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}:{1}({2})", NumberFormat.getNumberInstance(),
                new DecimalFormat("0.00%")));

        // 设置饼图标签的绘制字体
        // plot.setLabelFont(new Font("黑体", Font.PLAIN, 12));

        StandardEntityCollection sec = new StandardEntityCollection();
        ChartRenderingInfo info = new ChartRenderingInfo(sec);
        // 图片生成
        ChartUtilities.writeChartAsJPEG(new FileOutputStream("c:\\fruit1.jpg"), 1, chart, 800, 600, info);

        PrintWriter imageMapWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(new File(
                "c:\\fruit1.txt")), "UTF-8"));
        ChartUtilities.writeImageMap(imageMapWriter, "AAA", info, false);
        imageMapWriter.flush();

    }
}
