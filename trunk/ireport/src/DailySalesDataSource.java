import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class DailySalesDataSource implements JRDataSource {
	/**
	 * 测试数据，实际项目中是动态获取，也不一定是数组，可以是其它的数据类型 .
	 */
	private Object[][] data = { 
			{ " 货号 1", " 物品１ ", 1, 1000 },
			{ " 货号 2", " 物品 2", 2, 2000 },
			{ " 货号 3", " 物品 3", 3, 3000 },
			{ " 货号 4", " 物品 4", 4, 4000 },
			{ " 货号 5", " 物品 5", 5, 5000 },
			{ " 货号 6", " 物品 6", 6, 6000 },
			{ " 货号 7", " 物品 7", 7, 7000 },
			{ " 货号 8", " 物品 8", 8, 8000 },
			{ " 货号 9", " 物品 9", 9, 9000 },
			{ " 货号 10", " 物品 10", 10, 10000 } };
	
	private int index = -1;

	public DailySalesDataSource() {
	}

	/**
	 * 实现了 JRDataSource 中的方法．判断是否还有下一个．
	 */
	public boolean next() throws JRException {
		index++;
		return (index < data.length);
	}

	/**
	 * 实现了 JRDataSource 中的方法．
	 * 
	 * @param field 是对应报表中的要填充的字段的名称．
	 */
	public Object getFieldValue(JRField field) throws JRException {
		Object value = null;

		String fieldName = field.getName();

		if ("id".equals(fieldName)) {
			value = index + 1;
		} else if ("productNo".equals(fieldName)) {
			value = data[index][0];
		} else if ("productName".equals(fieldName)) {
			value = data[index][1];
		} else if ("number".equals(fieldName)) {
			value = data[index][2];
		} else if ("money".equals(fieldName)) {
			value = data[index][3];
		}
		return value;

	}

}
