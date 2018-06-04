package com.xul.socket;

import java.io.OutputStream;
import java.io.Writer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class MessageXmlUtil extends XStream {

	/**
	 * java 转换成xml
	 * 
	 * @Title: toXml
	 * @Description: TODO
	 * @param obj
	 *            对象实例
	 * @return String xml字符串
	 */
	public static String toXml(Object obj) {
		XStream xstream = new MessageXmlUtil();
		// XStream xstream=new XStream(new DomDriver()); //直接用jaxp dom来解释
		// XStream xstream=new XStream(new DomDriver("utf-8"));
		// //指定编码解析器,直接用jaxp dom来解释

		//// 如果没有这句，xml中的根元素会是<包.类名>；或者说：注解根本就没生效，所以的元素名就是类的属性
		xstream.processAnnotations(obj.getClass()); // 通过注解方式的，一定要有这句话
		// return xstream.toXML(obj).replace("<NULL>NULL</NULL>", "NULL");
		return xstream.toXML(obj);
	}

	/**
	 * 将传入xml文本转换成Java对象
	 * 
	 * @Title: toBean
	 * @Description: TODO
	 * @param xmlStr
	 * @param cls
	 *            xml对应的class类
	 * @return T xml对应的class类的实例对象
	 * 
	 *         调用的方法实例：PersonBean person=XmlUtil.toBean(xmlStr,
	 *         PersonBean.class);
	 */
	public static <T> T toBean(String xmlStr, Class<T> cls) {
		// 注意：不是new Xstream(); 否则报错：java.lang.NoClassDefFoundError:
		// org/xmlpull/v1/XmlPullParserFactory
		XStream xstream = new XStream(new DomDriver());
		xstream.processAnnotations(cls);
		@SuppressWarnings("unchecked")
		T obj = (T) xstream.fromXML(xmlStr.substring(xmlStr.indexOf("<?xm")));
		return obj;
	}

	private static Log log = LogFactory.getLog(MessageXmlUtil.class);
	private String version;

	private String ecoding;

	public MessageXmlUtil() {
		this("1.0", "gb2312");
	}

	public MessageXmlUtil(String version, String ecoding) {
		this.version = version;
		this.ecoding = ecoding;

	}

	public String getDeclaration() {

		return "<?xml version=\"" + this.version + "\" encoding=\"" + this.ecoding + "\"?>\n";

	}

	@Override
	public void toXML(Object arg0, OutputStream arg1) {

		try {

			String dec = this.getDeclaration();

			byte[] bytesOfDec = dec.getBytes(this.ecoding);

			arg1.write(bytesOfDec);

		} catch (Exception e) {

			log.error("输出Declaration时候出现异常", e);

			return;

		}

		super.toXML(arg0, arg1);

	}

	@Override
	public void toXML(Object arg0, Writer arg1) {

		try {

			arg1.write(getDeclaration());

		} catch (Exception e) {

			log.error("输出Declaration时候出现异常", e);

			return;

		}

		super.toXML(arg0, arg1);

	}
}
