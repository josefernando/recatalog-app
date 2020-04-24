package br.com.recatalog.app.model.security;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.domain.Persistable;

@Entity
@Table(name = "users")
public class SpringUsers implements Serializable, Persistable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "username")
	private String userName;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "enabled")
	private boolean enabled;
	
	public SpringUsers() {}

	public SpringUsers(String userName, String password, Boolean enabled) {
		this.userName = userName;
		this.password = password;
		this.enabled  = enabled;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		SpringUsers other = (SpringUsers) obj;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public Object getId() {
		return userName;
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
}