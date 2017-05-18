package com.foxera.models;

/**
 * 返回结果类
 * @author lailai
 *
 */
public class ResultModel {

		private String message;
		private boolean sccessful;
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public boolean isSccessful() {
			return sccessful;
		}
		public void setSccessful(boolean sccessful) {
			this.sccessful = sccessful;
		}
		
}
