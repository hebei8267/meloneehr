/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package struts.form;

import java.io.Serializable;

/**
 * @author 何贝
 * @since JDK1.5
 */
public class DataBean implements Serializable{
    private String a;
    private String b;

    /**
     * @return the a
     */
    public String getA() {
        return a;
    }

    /**
     * @param a the a to set
     */
    public void setA(String a) {
        this.a = a;
    }

    /**
     * @return the b
     */
    public String getB() {
        return b;
    }

    /**
     * @param b the b to set
     */
    public void setB(String b) {
        this.b = b;
    }

}
