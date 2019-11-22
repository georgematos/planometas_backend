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
}
