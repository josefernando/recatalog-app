package br.com.recatalog.app.security.auth.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.data.domain.Persistable;

@Entity
@Table(name = "tbpermission")
public class Permission implements Serializable, Persistable {

	@Id
	@Column(name = "permission_id")
	private String permissionId;
	
	@Column(name = "permission_description")
	private String permissionDescription;
	
    @ManyToMany(mappedBy = "permissions", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private Set<Account> accounts;

	
//    @OneToMany(mappedBy = "Permission", cascade = CascadeType.ALL)
//	List<Permission> permissions;
	
	public Permission() {
//		permissions = new ArrayList<>();
	}
	
	public Permission(String permissonId) {
		this.permissionId = permissonId;
	}

	public String getPermissionId() {
		return permissionId;
	}

	public void setPermissonId(String permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermissionDescription() {
		return permissionDescription;
	}

	public void setPermissionDescription(String permissionDescription) {
		this.permissionDescription = permissionDescription;
	}
	
	
	
//	public List<Permission> getPermissions() {
//		return permissions;
//	}
//
//	public void setPermissions(List<Permission> permissions) {
//		this.permissions = permissions;
//	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}
	
    public void addAccount(Account account) {
    	getAccounts().add(account);
    	account.getPermissions().add(this);
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((permissionId == null) ? 0 : permissionId.hashCode());
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
		Permission other = (Permission) obj;
		if (permissionId == null) {
			if (other.permissionId != null)
				return false;
		} else if (!permissionId.equals(other.permissionId))
			return false;
		return true;
	}

	@Override
	public Object getId() {
		return permissionId;
	}

	@Override
	public boolean isNew() {
		return true;
	}

	@Override
	public String toString() {
		return "Permission [permissionId=" + permissionId + "]";
	}
}