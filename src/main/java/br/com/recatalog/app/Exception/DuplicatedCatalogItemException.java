package br.com.recatalog.app.Exception;

public class DuplicatedCatalogItemException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    public DuplicatedCatalogItemException(String message) {
        super(message);
    }
}