package br.com.recatalog.app.Exception;

public class ParentCatalogItemNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
    public ParentCatalogItemNotFoundException(String message) {
        super(message);
    }
}