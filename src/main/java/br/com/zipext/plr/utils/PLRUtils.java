package br.com.zipext.plr.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PLRUtils {

	public static final String DATE_PATTERN_JS = "dd/MM/yyyy";
	
	public static final String DATE_PATTERN_DB = "yyyy-MM-dd";
	
	public static final String DATE_PATTERN_FILE = "yyyyMMdd";
	
	public static final String XLS_SHEET_NAME = "FOLHA METAS";
	
	public static String today() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN_FILE);
		
		return
				now.format(formatter);
	}
}
