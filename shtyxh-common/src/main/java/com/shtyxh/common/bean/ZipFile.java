package com.shtyxh.common.bean;

public class ZipFile {
		private String fileName;
		private String filePath;
		
		public ZipFile() {
			super();
		}
		public ZipFile(String fileName, String filePath) {
			super();
			this.fileName = fileName;
			this.filePath = filePath;
		}
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public String getFilePath() {
			return filePath;
		}
		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}
		
}
