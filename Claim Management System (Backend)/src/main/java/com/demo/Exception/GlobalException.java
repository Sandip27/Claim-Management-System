package com.demo.Exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.demo.Common.ApiResponse;
import com.demo.Exception.AccessDeniedException;

@RestControllerAdvice
public class GlobalException {
	
	@ExceptionHandler(DataNotFound.class)
	public ResponseEntity<ErrorMsg> dataNotAvailable(DataNotFound msg, WebRequest request) {
		ErrorMsg obj = new ErrorMsg();
		obj.setMessage(msg.getMessage());
		obj.setTimestamp(new Date());
		obj.setDetails(request.getDescription(false));
	
		return new ResponseEntity<ErrorMsg>(obj, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ApiResponse> AccessDeniedException(AccessDeniedException msg, WebRequest request) {

			ApiResponse apiResponse= new ApiResponse(); 
			apiResponse.setStatus(HttpStatus.BAD_REQUEST.value()); 
			apiResponse.setError(msg.getMessage());

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);

}
}
