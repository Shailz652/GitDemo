package pojo;

import java.util.List;

public class Courses {
	/*private List<WebAutomation> webAutomation;
	private List<API> api;
	private List<Mobile> mobile;
	
	public List<WebAutomation> getWebAutomation() {
		return webAutomation;
	}
	public void setWebAutomation(List<WebAutomation> webAutomation) {
		this.webAutomation = webAutomation;
	}
	public List<API> getApi() {
		return api;
	}
	public void setApi(List<API> api) {
		this.api = api;
	}
	public List<Mobile> getMobile() {
		return mobile;
	}
	public void setMobile(List<Mobile> mobile) {
		this.mobile = mobile;
	}*/
	
	private List<CourseDetails> webAutomation;
	private List<CourseDetails> api;
	private List<CourseDetails> mobile;
	
	public List<CourseDetails> getWebAutomation() {
		return webAutomation;
	}
	public void setWebAutomation(List<CourseDetails> webAutomation) {
		this.webAutomation = webAutomation;
	}
	public List<CourseDetails> getApi() {
		return api;
	}
	public void setApi(List<CourseDetails> api) {
		this.api = api;
	}
	public List<CourseDetails> getMobile() {
		return mobile;
	}
	public void setMobile(List<CourseDetails> mobile) {
		this.mobile = mobile;
	}
}
