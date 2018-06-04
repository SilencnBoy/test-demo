package com.xl.util;

public class DataUtil {

	// 状态码
	public static final String status200 = "200";
	public static final String status500 = "500";
	public static final String status404 = "404";

	// 请求结果
	public static final String SUCCESS = "success";
	public static final String FAIL = "fail";
	// 错误信息
	public static final String systemException = "系统异常";
	public static final String papramError = "参数错误";
	public static final String getDataIsNone = "获取数据失败";

	// 手机
	public static final String phoneIsNone = "请输入手机号";
	public static final String phoneFormat = "请输入正确的手机号";
	// 图形验证码
	public static final String graphCodeIsNone = "请输入图形验证码";
	public static final String graphCodeLose = "图形验证码失效";
	public static final String checkGraphCodeFail = "图形验证码校验失败";
	// 短息验证码
	public static final String getSMSCodeFail = "获取短信验证码失败";
	public static final String repeatGetSMSCodeFail = "60秒内仅能获取一次短信验证码,请稍后重试";
	public static final String SMSCodeLose = "短信验证码失效";
	public static final String SMSCodeError = "短信验证不正确";
	public static final String userRegisterFail = "用户注册失败";
	public static final String userLoginFail = "用户登陆失败";
	public static final String SMSCodeSendSuccess = "短信验证码发送成功";
	public static final String SMSCodeSendFail = "短信验证码发送失败";

	private String status;
	private String result;
	private String errorMessage;

	public DataUtil() {
			super();
		}

	public DataUtil ( String status, String result, String errorMessage) {
			super();
			this.status = status;
			this.result = result;
			this.errorMessage = errorMessage;
		}

	public String toString() {

		return "{\"status\":\"" + status + "\",\"result\":\"" + result + "\",\"errorMessage\":\"" + errorMessage
				+ "\"}";

	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public static void main(String[] args) {

	}
}
