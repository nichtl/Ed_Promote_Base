package com.nicht.promote.fileservice.Dom4j;

import cn.hutool.core.io.resource.ClassPathResource;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/8/9
 * @Link
 */
public class ibatisHandle {

    public static void main(String[] args)  {
          try {
            ClassPathResource resource  = new ClassPathResource("ibatis");//原xml文件夹
            ClassPathResource resource1  = new ClassPathResource("SaveFile");//修改保存文件夹
            xmlhandle(resource.getPath(),resource1.getAbsolutePath());
        }catch (Exception e){e.printStackTrace();}
    }
    public static void  xmlhandle(String  filepackage,String savepath){
        File  files  = new File(filepackage);
        System.out.println();
        List<String>  filenames = getFileNames(filepackage);
        for (String s: filenames) {
            ReWrite(filepackage+"/"+s,savepath+"/"+s);
        }
    }

    public static Document load(String filename)  {
        Document document = null;
        try {

        ClassPathResource resource  = new ClassPathResource(filename);
        File  file  = null;
        if(resource.getFile().exists()){
            file  = resource.getFile();
        }else{
            throw new NoSuchFileException(filename,"","error path ");
        }
            SAXReader saxReader= new SAXReader();
            saxReader.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            document = saxReader.read(file);  //读取XML文件,获得document对象
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return document;
    }

    public static void ReWrite(String filename,String savepath){
        try {
            Document document = load(filename);
            Element rootElement = document.getRootElement();
            ibatisHandle.RegexMatch(rootElement);
            //格式化为缩进格式
            OutputFormat format = OutputFormat.createPrettyPrint();
            //设置编码格式
            format.setEncoding("UTF-8");
            try {
                XMLWriter writer = new XMLWriter(new FileWriter(checkResourceFile(savepath)), format);
                //写入数据
                writer.setEscapeText(false);

                writer.write(document);
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e){
               e.printStackTrace();
        }
    }

    private static  void  RegexMatch (Element  rootElement){
        rootElement.elements().forEach(element ->{
            switch (element.getName()){
                case KeyWord.UPDATE:
                case KeyWord.DELETE:
                case KeyWord.INSERT:
                    String var4  = element.getTextTrim();
                           var4=var4.replaceAll(ReplaceRegex.UpdateEquals.regex, ReplaceRegex.UpdateEquals.getReplaceRegex())
                               .replaceAll(ReplaceRegex.Equals.regex, ReplaceRegex.Equals.getReplaceRegex())
                               .replaceAll(ReplaceRegex.InsertId.regex, ReplaceRegex.InsertId.getReplaceRegex())
                               .replaceAll(ReplaceRegex.ToDate.regex, ReplaceRegex.ToDate.getReplaceRegex())
                               .replaceAll(ReplaceRegex.Like.regex, ReplaceRegex.Like.getReplaceRegex())
                               .replaceAll(ReplaceRegex.ToChar.regex, ReplaceRegex.ToChar.getReplaceRegex());
                           element.setText("<![CDATA["+var4+"]]>");
                    break;
                case KeyWord.SELECT:
                    String var2  = element.getTextTrim();
                    List  ac  = element.attributes();
                    System.out.println(var2);
                    var2=var2.replaceAll(ReplaceRegex.Equals.regex, ReplaceRegex.Equals.getReplaceRegex())
                            .replaceAll(ReplaceRegex.InsertId.regex, ReplaceRegex.InsertId.getReplaceRegex())
                            .replaceAll(ReplaceRegex.ToDate.regex, ReplaceRegex.ToDate.getReplaceRegex())
                            .replaceAll(ReplaceRegex.Like.regex, ReplaceRegex.Like.getReplaceRegex())
                            .replaceAll(ReplaceRegex.ToChar.regex, ReplaceRegex.ToChar.getReplaceRegex());
                     element.setText("<![CDATA["+var2+"]]>");
                     break;
                default: break;
            }
            if(!element.elements().isEmpty()){
                List  es  =  element.content();
                es.set(0,es.get(es.size()-1));
                es.remove(es.size()-1);
                element.elements().forEach(childelement ->{
                    switch (childelement.getName()){
                        case KeyWord.ISEQUAL:
                        case KeyWord.ISNOTEMPTY:
                            String  contenttemp = childelement.getTextTrim();
                            contenttemp=contenttemp.replaceAll(ReplaceRegex.Equals.regex, ReplaceRegex.Equals.getReplaceRegex())
                                    .replaceAll(ReplaceRegex.Like.regex,ReplaceRegex.Like.getReplaceRegex())
                                    .replaceAll(ReplaceRegex.ToDate.regex, ReplaceRegex.ToDate.getReplaceRegex());
                            childelement.setText("<![CDATA["+contenttemp+"]]>");
                            break;
                        default: break;
                    }
                });
            }
        });


    }

    public   interface KeyWord{
        public  static  final String SELECT="select";
        public  static  final String UPDATE="update";
        public  static  final String DELETE="delete";
        public  static  final String INSERT="insert";
        public  static  final String ISEQUAL="isEqual";
        public  static  final String ISNOTEMPTY="isNotEmpty";
    }

    private  enum ReplaceRegex {
        /*处理to_char('$$')*/
        ToChar("(?i)to_char\\('\\$(\\w*)\\$'","to_char(#$1#)"),
        /*处理to_date('$year$'*/
        ToDate("(?i)to_date\\('\\$(\\w*)\\$'","to_date(#$1#"),
        /*处理 ='$type$' */
        Equals("={1}\\s*(')?\\$(\\w*)\\$(')?","=#$2#"),
        /*针对insert update 中,'$type$' */
        UpdateEquals(",\\s*(')?\\$(\\w*)\\$(')?",",#$2#"),
        /*处理('$id$'*/
        InsertId("(?i)values\\s*\\(\\s*(')?\\$(\\w*)\\$(')?","values(#$2#"),
        /*like('$type$')*/
        Like("(?i)like\\s*(\\()?(')?%\\$(\\w*)\\$%(')?(\\))?","like ('%' || #$3# || '%')");
        private  String regex;
        private  String replaceRegex;
        ReplaceRegex(String regex, String replaceRegex) {
            this.regex = regex;
            this.replaceRegex = replaceRegex;
        }
        public String getRegex() {
            return regex;
        }
        public String getReplaceRegex() {
            return replaceRegex;
        }
    }

    public static List<String> getFileNames(String path) {

        List<String> files = new ArrayList<String>();
        ClassPathResource file = new ClassPathResource(path);
        File[] tempList =  new File(file.getAbsolutePath()).listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                files.add(tempList[i].getName());
                //文件名，不包含路径
                //String fileName = tempList[i].getName();
            }
            if (tempList[i].isDirectory()) {
                //这里就不递归了，
            }
        }
        return files;
    }

    public static String checkResourceFile(String filename){
        File  file  = new File(filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();

            }
            System.out.println("文件已创建");

        } else {
            System.out.println("文件已存在");

        }
       return  file.getPath();
    }
}
