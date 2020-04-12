package br.com.zipext.plr.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class PLRUtilsTest {

	@Test
	public void testGenPhrase() {
		String tokenA = "Articuno The Pokemon";
		long tokenB = 901;
		String phrase = PLRUtils.genPhrase(tokenA, tokenB);
	
		assertThat(phrase).isNotBlank();
		assertThat(phrase).isEqualTo("Pokemon901");
	}
	
	@Test
	public void testSkyTempoConverter() {
		String date = "21/11/2019";
		Long skyTempo = PLRUtils.getSkyTempoFromStringDate(date);
		
		assertThat(skyTempo).isEqualTo(20191121);
	}
	
	@Test
	public void testFormatCPF() {
		String cpf = "60054527129";
		String fmtCPF = PLRUtils.formatCPF(cpf);
		
		assertThat(fmtCPF).isNotBlank();
		assertThat(fmtCPF).isEqualTo("600.545.271-29");
	}
}
