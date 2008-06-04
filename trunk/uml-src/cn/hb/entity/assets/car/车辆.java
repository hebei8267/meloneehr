package cn.hb.entity.assets.car;

public class 车辆 {
 
	private String 车牌号码(PK);
	 
	private String 厂牌型号;
	 
	private String 出厂日期;
	 
	private String 购置日期;
	 
	private String 购买价格;
	 
	private String 照片;
	 
	private String 发动机号(unique);
	 
	private String 车架号(unique);
	 
	private String 载重;
	 
	private String 座位;
	 
	private String 详细描述;
	 
	private 车辆状态 车辆状态;
	 
	private 车辆类型 车辆类型;
	 
	private 车辆颜色 车辆颜色;
	 
	private 组织-车辆 组织-车辆;
	 
	private 汽油种类 汽油种类;
	 
	private 车辆年检[] 车辆年检;
	 
	private 车辆维护[] 车辆维护;
	 
}
 
