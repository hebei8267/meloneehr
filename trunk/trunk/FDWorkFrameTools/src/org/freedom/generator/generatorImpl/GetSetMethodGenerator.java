/*
 * Copyright 2008 by hebei, All rights reserved.
 */
package org.freedom.generator.generatorImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.freedom.domain.FileInfo;
import org.freedom.generator.IGetSetMethodGenerator;

import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;

import static org.freedom.constant.GeneratorUtilConstant.CHARSET_NAME;

/**
 * Get Set Method文件生成实现
 * 
 * @author 何贝
 * @since JDK1.5
 */
public class GetSetMethodGenerator extends AbstractGenerator implements IGetSetMethodGenerator {

    public void createMethodFile(List<FileInfo> fileInfoList) throws IOException {

        for (FileInfo fileInfo : fileInfoList) {
            JavaDocBuilder builder = new JavaDocBuilder();
            File _file = new File(fileInfo.getFilePath());

            builder.addSource(new InputStreamReader(new FileInputStream(_file), CHARSET_NAME));
            JavaClass cls = builder.getClassByName("cn.hb.entity.hr.employment.Employee_Job_Relate");

            JavaField[] fields = cls.getFields();

            Writer fileWriter = new OutputStreamWriter(new FileOutputStream("c:\\12.txt"), CHARSET_NAME);
            List<String> getList = new ArrayList<String>();
            List<String> setList = new ArrayList<String>();
            for (int i = 0; i < fields.length; i++) {
                System.out.println(fields[i].getName());
                System.out.println(fields[i].getType().getValue());
                int _index = fields[i].getType().getValue().lastIndexOf(".") == -1 ? 0 : fields[i].getType().getValue()
                        .lastIndexOf(".") + 1;
                String _type = fields[i].getType().getValue().substring(_index);
                System.out.println(_type);
                getList.add(getGetMethodComment(fields[i].getComment()));
                getList.add(getGetMethodString(fields[i].getName(), _type));

                setList.add(getSetMethodComment(fields[i].getName(), fields[i].getComment()));
                setList.add(getSetMethodString(fields[i].getName(), _type));
            }

            for (String str : getList) {
                fileWriter.write(str);
            }
            for (String str : setList) {
                fileWriter.write(str);
            }
            fileWriter.close();
        }

    }

}
