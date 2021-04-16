package br.com.zipext.plr.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;

public class PLRUtils {

	public static final String DATE_PATTERN_JS = "dd/MM/yyyy";
	
	public static final String DATE_PATTERN_DB = "yyyy-MM-dd";
	
	public static final String DATE_PATTERN_FILE = "yyyyMMdd";
	
	public static final String SYS_USER = "SISTEMA";
	
	public static Long getSkyTempoFromStringDate(String stringDate) {
		if (!StringUtils.isNotBlank(stringDate)) {
			return -1L;
		}

		String result;
		try {
			String[] stringArrayDate = stringDate.split("/");
			String dia = stringArrayDate[0];
			String mes = stringArrayDate[1];
			String ano = stringArrayDate[2];

			result = ano.concat(mes).concat(dia);
		} catch (Exception e1) {
			result = "-1";
		}
		
		return
				Long.parseLong(result);
	}
	
	public static String genPhrase(String tokenA, long tokenB) {
		String phrase = "";
		if (tokenB % 2 == 0) {
			phrase = tokenA.split(" ")[0].concat(String.valueOf(tokenB));
		} else {
			int len = tokenA.split(" ").length;
			phrase = tokenA.split(" ")[len - 1].concat(String.valueOf(tokenB));
		}
		return phrase;
	}
	
	public static String today() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN_FILE);
		
		return
				now.format(formatter);
	}
	
	public static String formatCPF(String cpf) {
		if (!StringUtils.isNotBlank(cpf)) {
			return "N/I";
		}
		
		if (cpf.length() != 11) {
			return "INVALID CPF";
		}
		
		String firstGrp = cpf.substring(0, 3);
		String secondGrp = cpf.substring(3, 6);
		String thirdGrp = cpf.substring(6, 9);
		String lastDigits = cpf.substring(9, 11);
		
		String formatedCPF = firstGrp.concat(".")
									 .concat(secondGrp)
									 .concat(".")
									 .concat(thirdGrp)
									 .concat("-")
									 .concat(lastDigits);
		
		return formatedCPF;
	}
}
