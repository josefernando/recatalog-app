package br.com.recatalog.app.model.userdetails;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "tbcustom_granted_authority")
public class CustomGrantedAuthority implements GrantedAuthority, Serializable{

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	CustomGrantedAuthorityId customGrantedAuthorityId;

	public CustomGrantedAuthority () {}

	public CustomGrantedAuthority (CustomUserDetails userDetails, String authority) {
		this.customGrantedAuthorityId = new CustomGrantedAuthorityId(userDetails, authority);
	}
	
	public CustomGrantedAuthorityId getCustomGrantedAuthorityId() {
		return customGrantedAuthorityId;
	}
	
	public void setCustomGrantedAuthorityId(CustomGrantedAuthorityId customGrantedAuthorityId) {
		this.customGrantedAuthorityId = customGrantedAuthorityId;
	}

	@Override
	public String getAuthority() {
		return customGrantedAuthorityId.getAuthority();
	}
}

@Embeddable
class CustomGrantedAuthorityId implements Serializable  {  
	private static final long serialVersionUID = 1L;
	
//	@ManyToOne
//	@JoinColumn(name="fk_custom_granted_authority", referencedColumnName="username")
//	@ManyToOne	
//	@JoinColumns (foreignKey = @ForeignKey(name = "fk_custom_granted_authority"), value = { @JoinColumn(referencedColumnName = "username")})
    @ManyToOne
    @JoinColumns({
        @JoinColumn(
            name = "userName",
            referencedColumnName = "username")
    })	
	CustomUserDetails userDetails;
	
	@Column(name = "authority")
	private String authority;

	public CustomGrantedAuthorityId() {}
	
	public CustomGrantedAuthorityId(CustomUserDetails userDetails, String authority) {
		this.userDetails = userDetails;
		this.authority = authority;
	}

	public CustomUserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(CustomUserDetails userDetails) {
		this.userDetails = userDetails;
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
		result = prime * result + ((userDetails == null) ? 0 : userDetails.hashCode());
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
		CustomGrantedAuthorityId other = (CustomGrantedAuthorityId) obj;
		if (authority == null) {
			if (other.authority != null)
				return false;
		} else if (!authority.equals(other.authority))
			return false;
		if (userDetails == null) {
			if (other.userDetails != null)
				return false;
		} else if (!userDetails.equals(other.userDetails))
			return false;
		return true;
	}
}