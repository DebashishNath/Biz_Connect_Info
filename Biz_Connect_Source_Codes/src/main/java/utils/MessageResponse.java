package utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageResponse {
	// Return Code which can be Success, Failure or other type
	@JsonProperty("code")
	private Integer code;

	// Return message which can be Successful message, Failure message or other type message
	@JsonProperty("message")
	private String message;

	@JsonProperty("tempToken")
	private String tempToken;

	public MessageResponse() {}

	public MessageResponse(String message) {
		this.message = message;
	}

	public MessageResponse(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public MessageResponse(Integer code, String message,String tempToken) {
		this.code = code;
		this.message = message;
		this.tempToken = tempToken;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTempToken() {
		return tempToken;
	}

	public void setTempToken(String tempToken) {
		this.tempToken = tempToken;
	}
}