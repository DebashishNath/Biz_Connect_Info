package auth_info.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "mst_users",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "username"),
				@UniqueConstraint(columnNames = "email")
		}
)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20)
	@Column(nullable = false, unique = true)
	private String username;

	@NotBlank
	@Size(max = 45)
	@Column(nullable = false)
	private String fullName;

	@Size(max = 50)
	@Email
	@Column(nullable = false, unique = true)
	private String email;

	@NotBlank
	@Size(max = 120)
	@Column(nullable = false)
	private String password;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "trn_user_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@Size(max = 15)
	@Column(nullable = false)
	private String mobileNo;

	@Column(name="is_2fa_enabled")
	private String is2FAEnabled;

	private String resetToken;

	// --- Constructors ---
	protected User() {
		// JPA requires a no-arg constructor
	}

	public User(Long userId){
		this.id = userId;
	}

	public User(String username,String fullName, String email, String password) {
		this.username = username;
		this.fullName = fullName;
		this.email = email;
		this.password = password;
	}

	// --- Getters and Setters ---
	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public @Size(max = 15) String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(@Size(max = 15) String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getIs2FAEnabled() {
		return is2FAEnabled;
	}

	public void setIs2FAEnabled(String is2FAEnabled) {
		this.is2FAEnabled = is2FAEnabled;
	}

	public String getResetToken() {
		return resetToken;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}

	public void generateResetToken() {
		this.resetToken = UUID.randomUUID().toString();
	}
}