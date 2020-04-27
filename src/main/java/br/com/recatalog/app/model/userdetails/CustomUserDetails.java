package br.com.recatalog.app.model.userdetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.domain.Persistable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "tbcustom_user_details")
public class CustomUserDetails implements UserDetails, Serializable, Persistable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "enabled")
	private boolean enabled;
	
	@Column(name = "accountNonExpired")
    private boolean accountNonExpired;

	@Column(name = "credentialsNonExpired")
	private boolean credentialsNonExpired;
	
	@Column(name = "accountNonLocked")
	private boolean accountNonLocked;
	
	@OneToMany(
		    mappedBy = "customGrantedAuthorityId.userDetails",
		    fetch = FetchType.EAGER,
		    cascade = CascadeType.ALL,
		    orphanRemoval = true
		)
	private List<CustomGrantedAuthority> grantedAuthorities;
	
	public CustomUserDetails() {}
	
	public CustomUserDetails(String username) {
		this.username = username.toUpperCase();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username.toUpperCase();
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

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	
	public List<CustomGrantedAuthority> getGrantedAuthorities() {
		return grantedAuthorities;
	}

	public void setGrantedAuthorities(List<CustomGrantedAuthority> grantedAuthorities) {
		this.grantedAuthorities = grantedAuthorities;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomUserDetails other = (CustomUserDetails) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public Object getId() {
		return username;
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
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
}