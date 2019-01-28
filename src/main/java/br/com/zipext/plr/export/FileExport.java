package br.com.zipext.plr.export;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class FileExport {
	
	private FileInputStream fis;
	
	public FileExport() {}
	
	public FileInputStream getFileInputStream(String filePath) throws FileNotFoundException {
		if (fis == null) {
			fis = new FileInputStream(new File(filePath));
		}
		
		return
				fis;
	}
	
	public void closeFileInputStream() {
		try {
			this.fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}
}
