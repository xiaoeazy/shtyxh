package com.shtyxh.manager.bean;

public class FileInfo {
		private String fileName;
		private String filePath;
		
		public FileInfo(String fileName, String filePath) {
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
