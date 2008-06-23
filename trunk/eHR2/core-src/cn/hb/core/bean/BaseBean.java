package cn.hb.core.bean;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public abstract class BaseBean implements Serializable {

	public BaseBean() {

	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public String toString(ToStringStyle style) {
		return ToStringBuilder.reflectionToString(this, style);
	}
}
