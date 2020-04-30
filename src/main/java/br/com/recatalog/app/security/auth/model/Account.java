package br.com.recatalog.app.security.auth.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.data.domain.Persistable;

@Entity
@Table(name = "tbaccount")
public class Account implements Serializable, Persistable {

	@Id
	@Column(name = "user_login")
	private String userLogin;
	
	@Column(name = "user_full_name")
	private String userFullName;

	@Column(name = "password")
	private String password;

	@Column(name = "enabled")
	private boolean enabled;

	@Column(name = "account_expired")
	private boolean accountExpired;

	@Column(name = "credentials_expired")
	private boolean credentialsExpired;

	@Column(name = "account_locked")
	private boolean accountLocked;

	@ManyToMany(
			fetch = FetchType.EAGER,
			cascade = CascadeType.PERSIST)
		@JoinTable(
				name = "tbaccount_permission",
					joinColumns = @JoinColumn(
					name = "account_id",		
					referencedColumnName = "user_login"),
					inverseJoinColumns = @JoinColumn(
					name = "permission_id",
					referencedColumnName = "permission_id") )
	private Set<Permission> permissions;

	public Account() {
//		permissions = new ArrayList<>();
	}

	public Account(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
	
	public String getUserFullName() {
		return userFullName;
	}

	public void setUserName(String userFullName) {
		this.userFullName = userFullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isAccountExpired() {
		return accountExpired;
	}

	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}

	public boolean isAccountLocked() {
		return accountLocked;
	}

	public void setAccountNonLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}
	
	

	public boolean isCredentialsExpired() {
		return credentialsExpired;
	}

	public void setCredentialsExpired(boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	/* IMPORTANTE: implements PERSITABLE para pegar (catch) "duplicate key"
	 *             senão haverá update no método "save"
	 *  Spring JPA repository: prevent update on save
	 *  
	 *  - By default, a Property-ID inspection is performed, if it is null, then it is a new entity, otherwise is not.
	    - If the entity implements Persistable the detection will 
	      be delegated to the isNew() method implemented by the entity.
	 */	
	@Override
	public boolean isNew() {
		return true;
	}

	@Override
	public Object getId() {
		return userLogin;
	}
}