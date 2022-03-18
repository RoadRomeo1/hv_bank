package com.hv.foundation.bank.hv_bank.model;



public class ExceptionResponse {
		
	private long timestamp;
	private int status;
	private String error;
	private String path;
	
	public ExceptionResponse() {
	}
	
	public ExceptionResponse(long timestamp, int status, String path, String error) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.path = path;
		this.error = error;
	}
	
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timstamp) {
		this.timestamp = timstamp;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "ExceptionResponse [timestamp=" + timestamp + ", status=" + status + ", error=" + error + ", path="
				+ path + "]";
	}
 
}
