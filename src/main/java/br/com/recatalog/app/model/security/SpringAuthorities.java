package br.com.recatalog.app.model.security;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "authorities")
public class SpringAuthorities implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	AuthorityId id;

	public SpringAuthorities(AuthorityId id) {
		this.id = id;
	}

	public AuthorityId getId() {
		return id;
	}

	public void setId(AuthorityId id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		SpringAuthorities other = (SpringAuthorities) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

@Embeddable
class AuthorityId implements Serializable  {  
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="fk_authorities_users", referencedColumnName="username")
	SpringUsers users;
	
	@Column(name = "authority")
	private String authority;

	public AuthorityId(SpringUsers users, String authority) {
		this.users = users;
		this.authority = authority;
	}

	public SpringUsers getUsers() {
		return users;
	}

	public void setUsers(SpringUsers users) {
		this.users = users;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authority == null) ? 0 : authority.hashCode());
		result = prime * result + ((users == null) ? 0 : users.hashCode());
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
		AuthorityId other = (AuthorityId) obj;
		if (authority == null) {
			if (other.authority != null)
				return false;
		} else if (!authority.equals(other.authority))
			return false;
		if (users == null) {
			if (other.users != null)
				return false;
		} else if (!users.equals(other.users))
			return false;
		return true;
	}
}