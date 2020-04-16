package br.com.recatalog.app.util;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component()
public class BreadCrumbSession {
	String sessionId;
	String catalogName;
	String catalogId;
	String projectName;
	String projectId;
	String codeName;
	String codeId;
	
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public String getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	
	public void clearCatalog() {
		setCatalogId(null);
		setCatalogName(null);
		clearProject();
	}
	
	public void clearProject() {
		setProjectId(null);
		setProjectName(null);
		clearCode();
	}
	
	public void clearCode() {
		setCodeId(null);
		setCodeName(null);
	}
}