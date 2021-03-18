package com.pojo;

import java.util.List;

public class Courses {

	private List<WebAutomation> web_automation;
	private List<Api> api;
	private List<Mobile> mobile;
	
	public List<WebAutomation> getWeb_automation() {
		return web_automation;
	}
	public void setWeb_automation(List<WebAutomation> web_automation) {
		this.web_automation = web_automation;
	}
	public List<Api> getApi() {
		return api;
	}
	public void setApi(List<Api> api) {
		this.api = api;
	}
	public List<Mobile> getMobile() {
		return mobile;
	}
	public void setMobile(List<Mobile> mobile) {
		this.mobile = mobile;
	}
	
}
