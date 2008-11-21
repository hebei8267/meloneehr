/**
 * 2005-12-14/15:53:19
 * 
 * @version
 * @author ｺﾎ ｱｴ
 */
package com.yehui;

import static junit.framework.Assert.assertNotNull;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class CreateTable {
    public static void main(String[] args) {
        AnnotationConfiguration conf = new AnnotationConfiguration().configure("hibernate.cfg.xml");

        assertNotNull(conf);

        SchemaExport dbExport = new SchemaExport(conf);
        dbExport.setOutputFile("Sql.txt");
        dbExport.create(true, true);
    }
}
