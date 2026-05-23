package auth_info.models.response;

import utils.MessageResponse;
import java.util.List;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private String refreshToken;
	private Long id;
	private String username;
	private String fullname;
	private String email;
	private String is2FAEnabled;
	private List<String> roles;
	private MessageResponse returnMessage=new MessageResponse();

	public JwtResponse(String accessToken,String refreshToken, Long id, String username,String fullname,
					   String email, String is2FAEnabled,List<String> roles,MessageResponse returnMessage) {
		this.token = accessToken;
		this.refreshToken=refreshToken;
		this.id = id;
		this.username = username;
		this.fullname = fullname;
		this.email = email;
		this.is2FAEnabled=is2FAEnabled;
		this.roles = roles;
		this.returnMessage=returnMessage;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIs2FAEnabled() {
		return is2FAEnabled;
	}

	public void setIs2FAEnabled(String is2FAEnabled) {
		this.is2FAEnabled = is2FAEnabled;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public MessageResponse getReturnMessage() {
		return returnMessage;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public void setReturnMessage(MessageResponse returnMessage) {
		this.returnMessage = returnMessage;
	}
}