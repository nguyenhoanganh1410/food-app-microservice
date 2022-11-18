package com.example.inventoryservice.controller;

public class InventoryNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InventoryNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	public InventoryNotFoundException(String message) {
		super(message);
	}
	public InventoryNotFoundException( Throwable cause) {
		super( cause);
	}
}
