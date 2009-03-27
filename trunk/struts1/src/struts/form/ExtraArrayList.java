/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package struts.form;

import java.util.ArrayList;

/**
 * 画面提交数据列表对象的父类<br>
 * 
 * 利用org.apache.commons.beanutils.BeanUtils.populate<br>
 * 取得request里面的参数是解决结局数组越界错误
 * 
 * @author 何贝
 * @since JDK1.5
 */
public abstract class ExtraArrayList<T> extends ArrayList<T> {
    @Override
    public T get(int index) {
        if (index >= super.size()) {
            int _index = index - super.size();
            while (_index >= 0) {
                super.add(newElement());
                _index--;
            }
        }
        return super.get(index);
    }

    protected abstract T newElement();
}
