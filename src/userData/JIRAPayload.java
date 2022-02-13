package userData;

public class JIRAPayload {
	public static String Login() {
		return "{\r\n"
				+ "    \"username\": \"shailesh\",\r\n"
				+ "    \"password\": \"9411820728\"\r\n"
				+ "}";
	}
	
	public static String AddCommentA() {
		return "{\r\n"
				+ "    \"body\": \"This is 1st comment via Rest Assured.\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}";
	}
	public static String AddCommentB(String comment) {
		return "{\r\n"
				+ "    \"body\": \""+comment+"\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}";
	}
}
