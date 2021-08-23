package com.nicht.promote.fileservice.Utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.RoundingMode;
import java.net.*;
import java.nio.file.NoSuchFileException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * <编写代码时提供简单逻辑判断控制的函数类> <功能详细描述>
 * 
 * @author 薛会生
 * @version [版本号, 2013-09-25]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class CodeHelper {

	private static BeanFactory factory = null;

	/**
	 * 获取Spring容器中配置的Bean
	 * 
	 * @param id Spring_id
	 * @return Object
	 */
	public static Object getSpringBean(String id) {
		if (CodeHelper.isNull(CodeHelper.factory)) {
			factory = new ClassPathXmlApplicationContext("config/spring/*.xml");
		}
		return factory.getBean(id);
	}

	/**
	 * 对象是否为NULL
	 * 
	 * @param param Object
	 * @return Null:TRUE ; NotNull : FALSE
	 */
	public static boolean isNull(Object param) {
		return null == param;
	}

	/**
	 * 对象是否不为NULL
	 * 
	 * @param param Object
	 * @return Null:FALSE ; NotNull : TRUE
	 */
	public static boolean isNotNull(Object param) {
		return null != param;
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param param String
	 * @return TRUE:Null Or Empty ; FALSE:length > 0
	 */
	public static boolean isNullOrEmpty(String param) {
		return null == param || param.length() == 0;
	}

	/**
	 * 判断集合是否为空
	 * 
	 * @param param Collection
	 * @return TRUE:Null Or Empty ; FALSE:size > 0
	 */
	public static boolean isNullOrEmpty(Collection<? extends Object> param) {
		return null == param || param.isEmpty();
	}

	/**
	 * 判断字符串是否不为空
	 * 
	 * @param param String
	 * @return TRUE:length > 0 ; FALSE:Null Or Empty
	 */
	public static boolean isNotEmpty(String param) {
		return null != param && param.length() > 0;
	}

	/**
	 * 判断集合是否不为空
	 * 
	 * @param param Collection
	 * @return TRUE:size > 0 ; FALSE:Null Or Empty
	 */
	public static boolean isNotEmpty(Collection<? extends Object> param) {
		return null != param && !param.isEmpty();
	}

	public static boolean isNotEmpty(Integer integer) {
		return CodeHelper.isNotNull(integer);
	}

	// 获取当前时间 String
	public static String getCurrentDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(new Date());
	}

	// 获取当前时间 String
	public static String getCurrentDateYYYYMM() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		return format.format(new Date());
	}

	// 获取当前时间 String
	public static String getCurrentDateYYYYMMDD() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		return format.format(new Date());
	}

	// 获取当前时间 String
	public static String getCurrentDateYYYY_MM_DD() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(new Date());
	}

	// 获取当前年 String
	public static String getCurrentYear() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		return format.format(new Date());
	}

	// 获取当前年 String
	public static String getCurrentMonth() {
		Calendar now = Calendar.getInstance();
		return (now.get(Calendar.MONTH) + 1) + "";
	}

	// 获取当前年 String
	public static String getCurrentDay() {
		Calendar now = Calendar.getInstance();
		return String.valueOf(now.get(Calendar.DAY_OF_MONTH));
	}

	// 获取当前时间
	public static String getCurrentDateHMS() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s = sdf.format(d);
		return s;
	}

	/**
	 * 当前时间+、-小时
	 *
	 * @param operator 运算符"+"或者"-"
	 * @param hours    小时数
	 * @return 时间 yyyy-MM-dd hh:mm:ss
	 */
	public static String beforeOrAfterTime(char operator, Integer hours) {
		long beforeLongTime = 0;

		switch (operator) {
		case '+':
			beforeLongTime = System.currentTimeMillis() + (hours * 60 * 60 * 1000);
			break;

		default:
			beforeLongTime = System.currentTimeMillis() - (hours * 60 * 60 * 1000);
			break;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(beforeLongTime);

		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
	}

	// 当前时间n天
	public static String getCurrentDateHMS1(int days) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date beginDate = new Date();
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE) - days);
		Date endDate = null;
		try {
			endDate = dft.parse(dft.format(date.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dft.format(endDate);
	}

	/**
	 * 比较两个字符串格式的时间的大小，如果第二个参数时间大，那么返回true
	 */
	public static boolean compareTime(String newestLocateTime, String currentLocateTime) {
		boolean flag = false;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date newestTime = null;
		Date currentTime = null;
		try {
			newestTime = df.parse(newestLocateTime);
			currentTime = df.parse(currentLocateTime);
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("比价最新时间失败！\n" + e);
		}
		long l = currentTime.getTime() - newestTime.getTime();
		if (l > 0) {
			flag = true;
		}
		return flag;
	}

	// 字符串转为整形
	public static int parseInteger(String str) {
		int result = 0;
		try {
			if (str != null && !"".equals(str)) {
				result = Integer.parseInt(str);
			} else {
				result = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String parseStrUTF8(String str) {
		String temp = "";
		if (str != null && !"".equals(str)) {
			try {
				temp = new String(str.getBytes("iso-8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}

	public static String parseStrToGb(String str) {
		String temp = "";
		if (str != null && !"".equals(str)) {
			try {
				temp = new String(str.getBytes("iso-8859-1"), "gb2312");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return temp;
	}

	public static String checkNull(String s) {
		return s == null ? "" : s;
	}

	public static boolean checkSpecial(String s) {
		if (CodeHelper.isNotEmpty(s)) {
			return (s.contains("%") || s.contains("_") || s.contains("％"));
		} else {
			return false;
		}
	}

	/**
	 * 公共解析properties文件
	 * 
	 * @param key        配置文件key
	 * @param properties 配置文件名称
	 * @return
	 */
	public static String getCommonVariable(String key, String properties) {

		Properties p = new Properties();
		String value = "";
		try {
			p = PropertiesLoaderUtils.loadAllProperties(properties);
			value = p.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * 向客户端下载文件,弹出下载框.
	 * 
	 * @param response
	 * @param file
	 * @param isDel
	 * @throws IOException
	 */
	public static void exportFile(HttpServletResponse response, File file, boolean isDel) {
		FileInputStream in = null;
		PrintWriter out = null;
		response.reset();
		response.setContentType("application/force-download");
		// 获得文件名
		String filename = null;
		try {
			byte[] b = file.getName().getBytes("GBK");
			filename = new String(b, "ISO8859_1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		try {
			// 定义输出文件头
			response.setHeader("Content-Disposition", "attachment;filename=" + filename);
			in = new FileInputStream(file.getPath());
			out = response.getWriter();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			response.setHeader("content-type", "text/html;charset=UTF-8");
			int i;
			while ((i = in.read()) != -1) {
				out.write(i);
			}
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			} catch (IOException e) {
				in = null;
				out = null;
				e.printStackTrace();
			}
		}

		if (isDel) {
			// 删除文件,删除前关闭所有的Stream.
			if (file.exists()) {
				file.delete();
			}
		}
	}


	/**
	 * 计算时长
	 * 
	 * @param minDate 最小时间
	 * @param maxDate 最大时间
	 * @return
	 */
	public static String calculateDuration(String minDate, String maxDate) {
		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date begin = new Date();
		Date end = new Date();

		try {
			begin = dfs.parse(minDate);
			end = dfs.parse(maxDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		double between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒long
		double hour1 = between % (24 * 3600) / 3600;

		return String.format("%.2f", hour1);
	}

	/**
	 * 计算时间相差天
	 */
	public static int calculateDay(String minDate, String maxDate) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date now = null;
		Date date = null;
		try {
			now = df.parse(maxDate);
			date = df.parse(minDate);
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("计算时间相差天失败！\n" + e);
		}
		long l = now.getTime() - date.getTime();
		long day = l / (24 * 60 * 60 * 1000);

		return (int) day;
	}

	/**
	 * 计算差时长
	 * 
	 * @param totalCount  总时间
	 * @param shouldCount 应该的时间
	 * @return
	 */
	public static String calculateDifferenceFloat(String totalCount, String shouldCount) {
		return String.format("%.2f", (Float.parseFloat(totalCount) - Float.parseFloat(shouldCount)));
	}

	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}


	/**
	 * 返回当前年-月 格式：yyyy-MM
	 */
	public static String getYearMonth() {
		return new SimpleDateFormat("yyyy-MM").format(new Date());
	}

	/**
	 * 返回当前年月 格式：yyyyMM
	 */
	public static String getYearMonthFormat() {
		return new SimpleDateFormat("yyyyMM").format(new Date());
	}

	/**
	 * 得到本月的最后一天
	 * 
	 * @return
	 */
	public static String getMonthLastDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(calendar.getTime());
	}

	/**
	 * 得到本月的第一天
	 * 
	 * @return
	 */
	public static String getMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(calendar.getTime());
	}

	/**
	 * 得到本月的第一天
	 * 
	 * @return
	 */
	public static String getMonthFirstDayHousr() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(calendar.getTime());
	}

	/**
	 * 解密
	 * 
	 * @param url
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String decodeURL(String url) throws UnsupportedEncodingException {
		if (url != null && url.length() > 0) {
			return URLDecoder.decode(url, "UTF-8");
		} else {
			return url;
		}

	}

	/**
	 * 加密
	 * 
	 * @param
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String encodeURL(String url) throws UnsupportedEncodingException {
		if (url != null && url.length() > 0) {
			return URLEncoder.encode(url, "UTF-8");
		} else {
			return url;
		}

	}

	/***
	 * 解密
	 * 
	 * @param url
	 * @return
	 */
	public static String decodeUtf8URL(String url) {
		if (url != null && url.length() > 0) {
			try {
				return URLDecoder.decode(url, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return "";
	}

	/***
	 * 加密
	 * 
	 * @param url
	 * @return
	 */
	public static String encodeUtf8URL(String url) {
		if (url != null && url.length() > 0) {
			try {
				return URLEncoder.encode(url, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return url;
	}

/*	// 加密
	public static String base64Encode(String str) throws UnsupportedEncodingException {
		byte[] info = Base64Codec.encodeBase64(str.getBytes());
		return new String(info, "utf-8");
	}*/

	// 解密
/*
	public static String base64Decode(String str) {
		byte[] info = Base64Codec.decodeBase64(str.getBytes());
		return new String(info);
	}
*/

	/**
	 * 根据身份证计算年龄
	 * 
	 * @param identity 身份证
	 */
	public static Integer countAge(String identity) {
		if (CodeHelper.isNullOrEmpty(identity)) {
			return 0;
		} else {
			// 身份证截取的出生年份
			identity = identity.replace(".", "");

			if (identity.length() > 9) {
				String strAge = identity.substring(6, 10);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
				String currentYear = sdf.format(new Date());
				return Integer.parseInt(currentYear) - Integer.parseInt(strAge);
			} else {
				return 0;
			}
		}
	}

	/**
	 * 获取当年的第一天
	 * @return
	 */
	public static String getCurrYearFirst() {
		Calendar currCal = Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		return getYearFirst(currentYear);
	}

	/**
	 * 获取当年的最后一天
	 *
	 * @return
	 */
	public static String getCurrYearLast() {
		Calendar currCal = Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		return getYearLast(currentYear);
	}

	/**
	 * 获取某年第一天日期
	 * 
	 * @param year 年份
	 * @return Date
	 */
	public static String getYearFirst(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
	}

	/**
	 * 获取某年最后一天日期
	 * 
	 * @param year 年份
	 * @return Date
	 */
	public static String getYearLast(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);

		return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
	}

	/**
	 * 字符串时间 转 字符串标准格式“yyyy-MM-dd”
	 */
	public static String convertStringDate(String date) {
		String stringDate = null;
		try {
			Date dateDate = null;
			if (date.contains("/")) {
				dateDate = new SimpleDateFormat("yyyy/MM/dd").parse(date);
			} else if (date.contains(".")) {
				dateDate = new SimpleDateFormat("yyyy.MM.dd").parse(date);
			} else if (date.contains("-")) {
				dateDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			}

			stringDate = new SimpleDateFormat("yyyy-MM-dd").format(dateDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return stringDate;
	}

	public static boolean syncFile(String name, String filePath) {
		boolean result = false;
		File file = new File(name);
		URL urlObj = null;
		OutputStream out = null;
		DataInputStream in = null;
		HttpURLConnection con = null;
		InputStream is = null;
		try {
			String fileName = file.getName();

			urlObj = new URL(CodeHelper.getCommonVariable("INNER_FILESERVICE", "system-config.properties")
					+ "FileService/manager/upload/syncFile.action?filePath=" + filePath);
			con = (HttpURLConnection) urlObj.openConnection();

			/**
			 * 设置关键值
			 */
			con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false); // post方式不能使用缓存
			// 设置请求头信息
			con.setRequestProperty("Connection", "Keep-Alive");
			con.setRequestProperty("Charset", "UTF-8");
			// 设置边界
			String BOUNDARY = "----------" + System.currentTimeMillis();
			con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
			con.connect();

			// 请求正文信息

			// 第一部分：
			StringBuilder sb = new StringBuilder();
			sb.append("--"); // ////////必须多两道线
			sb.append(BOUNDARY);
			sb.append("\r\n");
			sb.append("Content-Disposition: form-data;name=\"fileInput\";fileName=\"" + fileName + "\"\r\n");
			sb.append("Content-Type:application/octet-stream\r\n\r\n");

			byte[] head = sb.toString().getBytes("utf-8");

			// 获得输出流

			out = new DataOutputStream(con.getOutputStream());
			out.write(head);

			// 文件正文部分
			in = new DataInputStream(new FileInputStream(file));
			int bytes = 0;
			byte[] bufferOut = new byte[1024];
			while ((bytes = in.read(bufferOut)) != -1) {
				out.write(bufferOut, 0, bytes);
			}

			// 结尾部分
			byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线

			out.write(foot);

			out.flush();
			if (con.getResponseCode() == 200) {

				String resultCode = "";
				is = con.getInputStream();
				BufferedInputStream bf = new BufferedInputStream(is);
				int b = 0;
				while ((b = bf.read()) != -1) {
					resultCode += (char) b;
				}

				if (resultCode.equals("1")) {

					result = true;
				}
			}
		} catch (MalformedURLException e) {
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
					in = null;
				}
				if (out != null) {
					out.close();
					out = null;
				}
				if (con != null) {
					con.disconnect();
					con = null;
				}
				if (is != null) {
					is.close();
					is = null;
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 计算时间相差月
	 */

	public static int getMonthSpace(String date1, String date2) throws ParseException {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		Date datekshi = null;
		Date dateend = null;

		datekshi = format.parse(date1);
		dateend = format.parse(date2);

		Calendar cal1 = new GregorianCalendar();
		cal1.setTime(datekshi);
		Calendar cal2 = new GregorianCalendar();
		cal2.setTime(dateend);
		int c = (cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR)) * 12 + cal1.get(Calendar.MONTH)
				- cal2.get(Calendar.MONTH) + 1;
		return c;
	}

	/**
	 * 获得该月最后一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getLastDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		// 设置年份
		cal.set(Calendar.YEAR, year);
		// 设置月份
		cal.set(Calendar.MONTH, month - 1);
		// 获取某月最大天数
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		// 设置日历中月份的最大天数
		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		// 格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String lastDayOfMonth = sdf.format(cal.getTime());
		return lastDayOfMonth;
	}

	/**
	 * 根据日期，提取年/月
	 * 
	 * @param date
	 * @param type
	 * @return
	 */
	public static Integer getYearOrMonth(String date, String type) {
		Date d1;
		try {
			d1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			d1 = new Date();
		}
		if (type.equals("year")) {
			return Integer.parseInt(new SimpleDateFormat("yyyy").format(d1));
		} else {
			return Integer.parseInt(new SimpleDateFormat("MM").format(d1));
		}
	}
    /**
    * gson序列化时排除某个字段
    * */
	public static Gson ExcludeFieldGson(String FieldName) {
		ExclusionStrategy myExclusionStrategy = new ExclusionStrategy() {
			@Override
			public boolean shouldSkipField(FieldAttributes fa) {
				return fa.getName().toLowerCase().equals(FieldName.toLowerCase()); // <---
			}

			@Override
			public boolean shouldSkipClass(Class<?> clazz) {
				return false;
			}

		};
		Gson gson = new GsonBuilder()
				.setExclusionStrategies(myExclusionStrategy) // <---
				.create();

		return  gson;
	}

	public static void main(String[] args) throws ParseException {
		try {
			ClassPathResource resource  = new ClassPathResource("ibatis");//原xml文件夹
			ClassPathResource resource1  = new ClassPathResource("SaveFile");//修改保存文件夹
			xmlhandle(resource.getPath(),resource1.getAbsolutePath());
		}catch (Exception e){e.printStackTrace();}
	/*	try {
			downloadPicture("http://222.174.84.58:10000/FileService/ImageFile/2021-04/2021041309561672205.jpg",
					"/2021041309561672205.jpg");
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}

	@SuppressWarnings("unused")
	private static Date getLastDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -1);
		return cal.getTime();
	}

	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static synchronized String getUUID() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		String uuidStr = str.replace("-", "");
		return uuidStr;
	}

	/**
	 */
	public static String getNum(String str) {
		String str2 = "";
		if (str != null && str.length() > 0) {
			str = str.trim();
			if (str != null && !"".equals(str)) {
				for (int i = 0; i < str.length(); i++) {
					if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
						str2 += str.charAt(i);
					}
				}

			}
		}
		return str2;

	}

	public static String numberSeq(String seq) {
		String seqName = "";
		if (CodeHelper.isNotEmpty(seq) && !seq.equals("0")) {
			switch (seq.length()) {
			case 1:
				seqName = "000" + seq;
				break;
			case 2:
				seqName = "00" + seq;
				break;
			case 3:
				seqName = "0" + seq;
				break;
			default:
				seqName = seq;
				break;
			}
		}
		return seqName;
	}

	// 身份证号码提取生日
	public static String countSR(String number) {
		String csrq = "";

		if (number.length() > 0 && number.length() == 18) {
			String year = number.substring(6, 10);
			String month = number.substring(10, 12);
			String day = number.substring(12, 14);

			csrq = year + "-" + month + "-" + day;
		} else if (number.length() > 0 && number.length() == 15) {
			String year = number.substring(6, 8);
			String month = number.substring(8, 10);
			String day = number.substring(10, 12);
			if (Integer.valueOf(year) >= 1 && Integer.valueOf(year) <= 25) {
				csrq = "20" + year + "-" + month + "-" + day;
			} else {
				csrq = "19" + year + "-" + month + "-" + day;
			}
		}

		return csrq;
	}

	public static String formatDouble(String content, int digit) {
		if (isNullOrEmpty(content)) {
			return "";
		}
		try {
			Double d = Double.valueOf(content);
			DecimalFormat formater = new DecimalFormat();
			formater.setMaximumFractionDigits(digit);
			formater.setRoundingMode(RoundingMode.UP);
			return formater.format(Double.valueOf(d));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	// 将 BASE64 编码的字符串 s 进行解码
	public static String getFromBASE64(String encodeStr) {

		if (isNotEmpty(encodeStr)) {
			byte[] b;
			try {
				b = encodeStr.getBytes("GBK");
				org.apache.commons.codec.binary.Base64 base64 = new org.apache.commons.codec.binary.Base64();
				b = base64.decode(b);
				encodeStr = new String(b, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		}
		return encodeStr;
	}

	/**
	 * 读取txt文件的内容
	 * 
	 * @param file 想要读取的文件对象
	 * @return 返回文件内容
	 */
	public static String txt2String(File file) {
		StringBuilder result = new StringBuilder();
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			String s = null;
			while ((s = bufferedReader.readLine()) != null) {// 使用readLine方法，一次读一行
				result.append(s);
			}
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}


	/**
	 * 获取工作日
	 * 
	 * @param yearMonth yyyy-MM
	 * @return 天数
	 */
	public static int getDates(String yearMonth) {
		if (isNullOrEmpty(yearMonth)) {
			return 0;
		}
		String[] yearMonthArray = yearMonth.split("-");
		int year = Integer.parseInt(yearMonthArray[0]);
		int month = Integer.parseInt(yearMonthArray[1]);

		List<Date> dates = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DATE, 1);
		while (cal.get(Calendar.YEAR) == year && cal.get(Calendar.MONTH) < month) {
			int day = cal.get(Calendar.DAY_OF_WEEK);
			if (!(day == Calendar.SUNDAY || day == Calendar.SATURDAY)) {
				dates.add((Date) cal.getTime().clone());
			}
			cal.add(Calendar.DATE, 1);
		}
		return dates.size();
	}

	/**
	 * 
	 * @param days 当前时间减去的天数
	 * @return 字符串日期 格式yyyy-MM-dd
	 */
	public static String getBeforeDates(int days) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = new Date();
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE) - days);
		Date endDate = null;
		try {
			endDate = dft.parse(dft.format(date.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dft.format(endDate);
	}

	/***
	 * 加密
	 * 
	 * @param content
	 * @return
	 */
	public static String encode64(String content) {
		try {
			byte[] b = content.getBytes("utf-8");
			return new String(new Base64().encode(b));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

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

	/**
	 * 根据yyyy-MM-dd获取定位表名
	 * 
	 * @return
	 */
	public static String getTableNameByTime(String time) {
		Date date = DateUtil.parse(time, "yyyy-MM-dd");
		return "T_SQJZ_POSITION" + DateUtil.format(date, "yyyyMM");
	}

	public  static boolean ListIsNotNull(List list){
		return list != null && list.size() > 0 && list.get(0) != null;
	}
	/**

     * BASE转图片

     * @param baseStr  base64字符串

     * @param imagePath 生成的图片

     * @return

     */

    public static boolean base64ChangeImage(String baseStr,String imagePath){

        if (baseStr == null){

            return false;

        }

        BASE64Decoder decoder = new BASE64Decoder();

        try {

            // 解密

            byte[] b = decoder.decodeBuffer(baseStr);

            // 处理数据

            for (int i = 0; i < b.length; ++i) {

                if (b[i] < 0) {

                    b[i] += 256;

                }

            }

            OutputStream out = new FileOutputStream(imagePath);

            out.write(b);

             out.flush();

            out.close();

            return true;

        } catch (Exception e) {

            return false;

        }

    }


	public static String firstCharToLowerCase(String str) {
		char firstChar = str.charAt(0);
		if (firstChar >= 'A' && firstChar <= 'Z') {
			char[] arr = str.toCharArray();
			arr[0] = (char)(arr[0] + 32);
			return new String(arr);
		} else {
			return str;
		}
	}

	/**
	 * 根据地址获得数据的输入流
	 * @param strUrl 网络连接地址
	 * @return url的输入流
	 */
	public static InputStream getInputStreamByUrl(String strUrl){
		HttpURLConnection conn = null;
		try {
			URL url = new URL(strUrl);
			conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(20 * 1000);
			final ByteArrayOutputStream output = new ByteArrayOutputStream();
			IOUtils.copy(conn.getInputStream(),output);
			return  new ByteArrayInputStream(output.toByteArray());
		} catch (Exception e) {
		 e.printStackTrace();
		}finally {
			try{
				if (conn != null) {
					conn.disconnect();
				}
			}catch (Exception e){
			e.printStackTrace();
			}
		}
		return null;
	}

/*
	public static String uploadFile(File file, String url,Boolean isDel) {
		if (!file.exists()) {
			return "";
		}
		PostMethod   postMethod = new PostMethod(url);
		try {
			//FilePart：用来上传文件的类
			FilePart fp = new FilePart("file", file);
			Part[] parts = { fp };

			//对于MIME类型的请求，httpclient建议全用MulitPartRequestEntity进行包装
			MultipartRequestEntity mre = new MultipartRequestEntity(parts, postMethod.getParams());
			postMethod.setRequestEntity(mre);
			HttpClient client = new HttpClient();
			client.getHttpConnectionManager().getParams().setConnectionTimeout(50000);// 设置连接时间
			int status = client.executeMethod(postMethod);
			if (status == HttpStatus.SC_OK) {
				return postMethod.getResponseBodyAsString();
			} else {
				return  "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//释放连接
			postMethod.releaseConnection();
			if(isDel){
				file.delete();
			}
		}
		return  "";
	}
*/

	/**
	 * 根据yyyy-MM-dd获取定位表名
	 *
	 * @return
	 */

	/**
	 * @description sql注入处理
	 * @param filepackage xml文件夹名称
	 * @param savepath 新xml保存文件夹名称
	 */
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
			saxReader.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
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
			CodeHelper.RegexMatch(rootElement);
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
        List<Element> eleList = rootElement.elements();
		eleList.forEach(element ->{
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
				List<Element>  childList  = element.elements();
				childList.forEach(childelement ->{
					switch (childelement.getName()){
						case KeyWord.ISEQUAL:
						case KeyWord.ISNOTEMPTY:
							String  contenttemp = childelement.getTextTrim();
							contenttemp=contenttemp.replaceAll(ReplaceRegex.Equals.regex, ReplaceRegex.Equals.getReplaceRegex())
									.replaceAll(ReplaceRegex.Like.regex, ReplaceRegex.Like.getReplaceRegex())
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
		/*针对insert 中,'$type$' */
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