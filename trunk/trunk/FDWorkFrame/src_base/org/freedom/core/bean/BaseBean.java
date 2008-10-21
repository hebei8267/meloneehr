/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.core.bean;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 公共Java Bean对象基类
 * 
 * @author 何贝
 * @since JDK1.5
 */
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
