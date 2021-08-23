package com.nicht.promote.fileservice.Utils;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.dom4j.Attribute;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.w3c.dom.Document;
import sun.misc.BASE64Encoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.*;

public class MyUtil {
	public static final String GA = "4A";   //公安
	public static final String JCY = "3A";   //检察院
	public static final String FY = "2A";   //法院
	public static final String JY = "5B";   //监狱
	public static final String SF = "5A";   //司法
	public static final String KSS = "4B";   //看守所
	//是否首次发起【2002P,2002Q交付执行 不同执行地转送】
	public static final String[] SCFQ_LIST = { "2008C" , "2007A"  ,"2007C"  ,"2005G","2005C" ,"2004C", "2004B", "2004D", "2004W", "2003C", "2003B", "2003P", "2003R", "2003A", "2006A", "2007A", "2011A","2011B","2011C","2011D","2002P","2002Q"};

	//协同消息返回状态
	public static final String JSCG_CODE = "202";
	public static final String JSCG_NAME = "接收成功";
	public static final String JSSB_CODE = "402";
	public static final String JSSB_NAME = "接收失败";
	public static final String THAJ_CODE = "502";
	public static final String THAJ_NAME = "退回案件";
	public static final String QSAJ_CODE = "602";
	public static final String QSAJ_NAME = "签收案件";

	//政法对接类型（0：司法接收 1：司法发送）
	public static final String ZFWDJ_TYPE_JS = "0";
	public static final String ZFWDJ_TYPE_FS = "1";
	//政法对接状态（SDZX：手动执行 ZDZX：自动执行）
	public static final String ZFWDJ_STATUS_SDZX = "SDZX";
	public static final String ZFWDJ_STATUS_ZDZX = "ZDZX";


	// 链接url下载图片
		public static void downloadPicture(String urlList, String path) throws Exception {
			URL url = null;
			url = new URL(urlList);
			DataInputStream dataInputStream = new DataInputStream(url.openStream());

			FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
			ByteArrayOutputStream output = new ByteArrayOutputStream();

			byte[] buffer = new byte[1024];
			int length;

			while ((length = dataInputStream.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}
			BASE64Encoder encoder = new BASE64Encoder();
			String encode = encoder.encode(buffer);// 返回Base64编码过的字节数组字符串
			// System.out.println(encode);
			fileOutputStream.write(output.toByteArray());
			dataInputStream.close();
			fileOutputStream.close();
		}
	
	public static String getCurrentDateHMS() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s = sdf.format(d);
		return s;
	}
	 
	/**
	 * 读取xml文件
	 * @param FileName
	 * @return
	 */
	
	 public static String xmlPrintFromFileName(String FileName) {
	        String xml = "";
	        try {
	            DocumentBuilderFactory factory = DocumentBuilderFactory
	                    .newInstance();
	            DocumentBuilder builder = factory.newDocumentBuilder();
	            Document document = builder.parse(FileName);
	            Source oldData = new DOMSource(document);
	            StreamResult newData = new StreamResult(new StringWriter());
	            Transformer transformer = TransformerFactory.newInstance()
	                    .newTransformer();
	            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	            transformer.setOutputProperty(
	                    "{http://xml.apache.org/xslt}indent-amount", "2");
	            transformer.transform(oldData, newData);
	            xml = newData.getWriter().toString();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return xml;
	    }
	
	
	public static Object getFieldValueByName(String fieldName, Object o) {    
	    try {    
	        String firstLetter = fieldName.substring(0, 1).toUpperCase();    
	        String getter = "get" + firstLetter + fieldName.substring(1);    
	         Method method = o.getClass().getMethod(getter, new Class[] {});    
	        Object value = method.invoke(o, new Object[] {});
	        if(value == null ) {value = "" ;}
	        return value;    
	    } catch (Exception e) {    
	        System.out.println("属性不存在");    
	        return "";    
	    }    
	} 
	
	public static String getFiledName(Object o){  
		StringBuffer bf = new StringBuffer();
	    Field[] fields = o.getClass().getDeclaredFields();  
	    String[] fieldNames=new String[fields.length];  
	    for(int i=0;i<fields.length;i++){ 
	    	bf.append("<"+fields[i].getName().toUpperCase()+">"+getFieldValueByName(fields[i].getName() , o)+"</"+fields[i].getName().toUpperCase()+">");  
	    }  
	    return new String(bf);  
	} 
	
	
	public static String readFile(String path) throws Exception {
        File file=new File(path);
        FileInputStream fis = new FileInputStream(file);
        FileChannel fc = fis.getChannel();
        ByteBuffer bb = ByteBuffer.allocate(new Long(file.length()).intValue());
        //fc向buffer中读入数据
        fc.read(bb);
        bb.flip();
        String str=new String(bb.array(),"UTF8");
        fc.close();
        fis.close();
        return str;

    }
    /**
     * xml转json
     * @param xmlStr
     * @return
     * @throws DocumentException
     */
    public static JSONObject xml2Json(String xmlStr) throws DocumentException{
         org.dom4j.Document doc = DocumentHelper.parseText(xmlStr);
		JSONObject json = new JSONObject();
        dom4j2Json(doc.getRootElement(), json);
        return json;
    }

    /**
     * xml转json
     * @param element
     * @param json
     */
    public static void dom4j2Json(Element element,JSONObject json){
        //如果是属性
        for(Object o:element.attributes()){
            Attribute attr=(Attribute)o;
            if(!isEmpty(attr.getValue())){
                json.put("@"+attr.getName(), attr.getValue());
            }
        }
        List<Element> chdEl=element.elements();
        if(chdEl.isEmpty()&&!isEmpty(element.getText())){//如果没有子元素,只有一个值
            json.put(element.getName(), element.getText());
        }

        for(Element e:chdEl){//有子元素
            if(!e.elements().isEmpty()){//子元素也有子元素
                JSONObject chdjson=new JSONObject();
                dom4j2Json(e,chdjson);
                Object o=json.get(e.getName());
                if(o!=null){
                    JSONArray jsona=null;
                    if(o instanceof JSONObject){//如果此元素已存在,则转为jsonArray
                        JSONObject jsono=(JSONObject)o;
                        json.remove(e.getName());
                        jsona=new JSONArray();
                        jsona.add(jsono);
                        jsona.add(chdjson);
                    }
                    if(o instanceof JSONArray){
                        jsona=(JSONArray)o;
                        jsona.add(chdjson);
                    }
                    json.put(e.getName(), jsona);
                }else{
                    if(!chdjson.isEmpty()){
                        json.put(e.getName(), chdjson);
                    }
                }


            }else{//子元素没有子元素
                for(Object o:element.attributes()){
                    Attribute attr=(Attribute)o;
                    if(!isEmpty(attr.getValue())){
                        json.put("@"+attr.getName(), attr.getValue());
                    }
                }
                if(!e.getText().isEmpty()){
                    json.put(e.getName(), e.getText());
                }
            }
        }
    }

    public static boolean isEmpty(String str) {

        if (str == null || str.trim().isEmpty() || "null".equals(str)) {
            return true;
        }
        return false;
    }
	
	public static String getFiledName2(Object o){  
		StringBuffer bf = new StringBuffer();
	    Field[] fields = o.getClass().getDeclaredFields();  
	    String[] fieldNames=new String[fields.length];  
	    for(int i=0;i<fields.length;i++){ 
	    	System.out.println("no_magic_number(data."+fields[i].getName().toUpperCase()+", $('#"+fields[i].getName().toUpperCase()+"'));") ;
	    	
	    }  
	    return new String(bf);  
	}
	
	
	public static JsonElement getJsonElement(String path) throws Exception{
		String xmlStr= readFile(path);
        org.dom4j.Document doc = DocumentHelper.parseText(xmlStr);
        JSONObject json=new JSONObject();
        dom4j2Json(doc.getRootElement(),json);
        
        JsonElement jsonElement = new JsonParser().parse(json.toString());
        System.out.println(jsonElement);
        return jsonElement ;
	}

	public static byte[] getXmlByte(String path) throws Exception{ 
		
	    String xmlStr= readFile(path);
        org.dom4j.Document doc = DocumentHelper.parseText(xmlStr);
        JSONObject json=new JSONObject();
        dom4j2Json(doc.getRootElement(),json);
        System.out.println("xml2Json:"+json.toJSONString());
		byte[]  contentbyte =  json.toJSONString().getBytes("UTF-8");
		
		return contentbyte;
	}
	
	
	public static Map getJsonEleAndByte(String path) throws Exception{ 
		Map<String, Object> map = new HashMap<String, Object>();
	    String xmlStr= readFile(path);
        org.dom4j.Document doc = DocumentHelper.parseText(xmlStr);
        JSONObject json=new JSONObject();
        MyUtil.dom4j2Json(doc.getRootElement(),json);
        JsonElement jsonElement = new JsonParser().parse(json.toString());
        System.out.println(jsonElement);
        byte[]  contentbyte =  json.toJSONString().getBytes("UTF-8");
        map.put("jsonEle", jsonElement);
        map.put("byte", contentbyte);
		return map;
	}


	
	public static String getTime() {
		String str="";
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		return format.format(new Date());
	}
    
   public static synchronized String getNumber4() {
        Random random = new Random();
        String fourRandom = random.nextInt(10000) + "";
        int randLength = fourRandom.length();
        if(randLength < 4){
            for(int i = 1;i <= 4 - randLength;i++)
                fourRandom = "0" + fourRandom;
        }
        return fourRandom;
    }

	/**
	 * @description 保持xml tagname与bean属性一致
	 * @param bean
	 * @param openDnTag 开启小写Tag 开启 true
	 * @return
	 */
	public  static String  bean_to_xml(Object bean,boolean openDnTag) {
		StringBuilder  sb = new StringBuilder();
		Field [] fields = bean.getClass().getDeclaredFields();
		if(fields.length>0) {
			if(openDnTag)
			{
					try {
						for (int i = 0; i < fields.length; ++i) {
							fields[i].setAccessible(true);
							sb.append("<").append(fields[i].getName().toLowerCase()).append(">")
									.append(fields[i].get(bean) == null ? "" : fields[i].get(bean))
									.append("</").append(fields[i].getName().toLowerCase()).append(">");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
			}else {
				try {
					for (int i = 0; i < fields.length; ++i) {
						fields[i].setAccessible(true);
						sb.append("<").append(fields[i].getName().toUpperCase()).append(">")
								.append(fields[i].get(bean) == null ? "" : fields[i].get(bean))
								.append("</").append(fields[i].getName().toUpperCase()).append(">");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}


	public static void main(String[] args) throws Exception {
	        Map FJXMap = new HashMap();
			FJXMap.put("01", "罚金");
			FJXMap.put("02", "剥夺政治权利");
			FJXMap.put("03", "没收财产");
			FJXMap.put("04", "驱逐出境");
			FJXMap.put("05", "无");
			FJXMap.put("99", "其它");
		String [] fjxcode =null;
		String temp ="";
		fjxcode="01,02,03,04,05,99".split(",");
		for (int i = 0; i < fjxcode.length; i++) {
			if(FJXMap.containsKey(fjxcode[i]))
			{
				temp+=FJXMap.get(fjxcode[i])+",";
			}
		}
		if(temp.length()>0){
			temp=temp.substring(0,temp.length()-1);
		}

		System.out.println(temp);
	}

}
