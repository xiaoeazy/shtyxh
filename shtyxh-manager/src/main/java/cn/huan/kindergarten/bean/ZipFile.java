package cn.huan.kindergarten.bean;

import java.io.File;

public class ZipFile {
		private String fileName;
		private File file;
		
		public ZipFile() {
			super();
		}
		public ZipFile(String fileName, File file) {
			super();
			this.fileName = fileName;
			this.file = file;
		}
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public File getFile() {
			return file;
		}
		public void setFile(File file) {
			this.file = file;
		}
		
}
