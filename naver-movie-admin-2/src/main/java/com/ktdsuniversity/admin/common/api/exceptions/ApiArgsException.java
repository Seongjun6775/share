package com.ktdsuniversity.admin.common.api.exceptions;

public class ApiArgsException extends ApiException{

	private static final long serialVersionUID = -6134504930900766840L;

	public ApiArgsException(String errorCode, String message) {
		super(errorCode, message);
	}

}
