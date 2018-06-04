package test;

import org.apache.log4j.Logger;

public class LogTest {

	public static void main(String[] args) {
		Logger log = Logger.getLogger(LogTest.class);
		try {
			System.out.println(5 / 0);
		} catch (Exception e) {
			log.info("系统异常", e);
		}
	}
}
