package com.horan.dsmlmanager.utils;

import com.horan.dsmlmanager.entity.Annotation;
import com.horan.dsmlmanager.entity.Label;
import com.horan.dsmlmanager.entity.Size;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JavaBeanToXml {
    public static String beanToXml(Object obj, Class<?> load) throws JAXBException {
         JAXBContext context = JAXBContext.newInstance(load);
         Marshaller marshaller = context.createMarshaller();
         marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
         marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
         StringWriter writer = new StringWriter();
         marshaller.marshal(obj, writer);
         return writer.toString();

    }
    public static void main(String[] args) throws JAXBException, IOException {
        Annotation annotation=new Annotation();
        List<Label> labelList=new ArrayList<>();
        Size size=new Size();
        size.setDepth(100);
        size.setWidth(100);
        size.setHeight(100);
        for(int i=0;i<10 ;i++){
            Label label=new Label();
            label.setEx(10);
            label.setEy(10);
            label.setX(10);
            label.setY(10);
            labelList.add(label);
        }
        annotation.setLabel(labelList);
        annotation.setSize(size);
        annotation.setFileName("134123");
        annotation.setFolder("123213");
        String str =  JavaBeanToXml.beanToXml(annotation, Annotation.class);

                     //写入到xml文件中
                    String xmlPath = "D:/testConfig.xml";
                    BufferedWriter bfw = new BufferedWriter(new FileWriter(new File(xmlPath)));
                    bfw.write(str);
                     bfw.close();
    }
}