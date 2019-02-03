package com.mytectra.springboot.playground.core;

public class ChocolatesNotFoundException extends Exception {

	private CNFError cnfError;
	

	public ChocolatesNotFoundException(CNFError error) {
		super();
		this.cnfError = error;
	}


	public CNFError getCnfError() {
		return cnfError;
	}
	

}
