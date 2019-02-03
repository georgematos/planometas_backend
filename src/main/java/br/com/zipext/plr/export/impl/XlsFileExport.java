package br.com.zipext.plr.export.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import br.com.zipext.plr.enums.EnumXlsEspecificasCells;
import br.com.zipext.plr.enums.EnumXlsGeraisCells;
import br.com.zipext.plr.enums.EnumXlsIdCells;
import br.com.zipext.plr.enums.EnumXlsSection;
import br.com.zipext.plr.export.FileExport;
import br.com.zipext.plr.model.ColaboradorCargoModel;
import br.com.zipext.plr.model.ColaboradorMetaEspecificaModel;
import br.com.zipext.plr.model.ColaboradorMetaGeralModel;
import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.model.MetaGeralModel;
import br.com.zipext.plr.utils.PLRUtils;

public class XlsFileExport extends FileExport {
	
	private Workbook workbook;
	
	double sumPontuacaoTotal = 0;
	
	public XlsFileExport(String templatePath) {
		this.initWorkbook(templatePath);
	}
	
	public ByteArrayInputStream processXlsForColaborador(ColaboradorModel colaboradorModel) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		this.writeContent(colaboradorModel);
		
		this.workbook.write(out);
		
		closeFileInputStream();

		return
				new ByteArrayInputStream(out.toByteArray());
	}
	
	public Workbook initWorkbook(String templatePath) {
		try {
			this.workbook = WorkbookFactory.create(getFileInputStream(templatePath));
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}
		
		return
				this.workbook;
	}
	
	public void writeContent(ColaboradorModel colaborador) {
		Sheet metaSheet = this.workbook.getSheet(PLRUtils.XLS_SHEET_NAME);
		
		//Identificação
		Row identRow = metaSheet.getRow(EnumXlsSection.ID.getRowNum());
		Row ebitdaRow = metaSheet.getRow(EnumXlsSection.IBTIDA.getRowNum());
		Row indivRow = metaSheet.getRow(EnumXlsSection.INDIVIDUAL.getRowNum());
		Row partRow = metaSheet.getRow(EnumXlsSection.PARTICIPACAO.getRowNum());
		Row perfRow = metaSheet.getRow(EnumXlsSection.PERFORMANCE.getRowNum());
		Row metaExtraRow = metaSheet.getRow(EnumXlsSection.EXTRA.getRowNum());
		
		Cell nome = identRow.getCell(EnumXlsIdCells.NOME.getColIndex());
		Cell matricula = identRow.getCell(EnumXlsIdCells.MATRICULA.getColIndex());
		Cell cargo = identRow.getCell(EnumXlsIdCells.CARGO.getColIndex());
		Cell diretoria = identRow.getCell(EnumXlsIdCells.DIRETORIA.getColIndex());
		
		ColaboradorCargoModel carg = (ColaboradorCargoModel) colaborador.getColaboradoresCargos().toArray()[0];
		
		nome.setCellValue(colaborador.getNome());
		matricula.setCellValue(colaborador.getMatricula());
		cargo.setCellValue(carg.getPk().getCargo().getNome());
		diretoria.setCellValue(carg.getPk().getCargo().getDiretoria().getNome());
		
		Set<ColaboradorMetaGeralModel> metasGerais = colaborador.getColaboradoresMetasGerais();
		if (metasGerais != null && !metasGerais.isEmpty()) {
			for (ColaboradorMetaGeralModel metaGeral : metasGerais) {
				Cell val = null;
				Cell bonus = null;
				Cell observacao = null;
				MetaGeralModel m = metaGeral.getPk().getMetaGeral();
				switch (m.getId().intValue()) {
				case 1:
					val = ebitdaRow.getCell(EnumXlsGeraisCells.VAL_EBITDA.getColIndex());
					bonus = ebitdaRow.getCell(EnumXlsGeraisCells.BON_EBITDA.getColIndex());
					observacao = ebitdaRow.getCell(EnumXlsGeraisCells.OBS_EBITDA.getColIndex());
					
					val.setCellValue(metaGeral.getValor() != null ? metaGeral.getValor().doubleValue() : 0);
					
					break;
				case 2:
					bonus = indivRow.getCell(EnumXlsGeraisCells.BON_INDIV.getColIndex());
					observacao = indivRow.getCell(EnumXlsGeraisCells.OBS_INDIV.getColIndex());
					
					break;
				case 3:
					bonus = partRow.getCell(EnumXlsGeraisCells.BON_PARTIC.getColIndex());
					observacao = partRow.getCell(EnumXlsGeraisCells.OBS_PARTIC.getColIndex());
					
					break;
				case 4:
					bonus = perfRow.getCell(EnumXlsGeraisCells.BON_PERFOR.getColIndex());
					observacao = perfRow.getCell(EnumXlsGeraisCells.OBS_PERFOR.getColIndex());
					
					break;
				case 5:
					bonus = metaExtraRow.getCell(EnumXlsGeraisCells.BON_EXTRA.getColIndex());
					observacao = metaExtraRow.getCell(EnumXlsGeraisCells.OBS_EXTRA.getColIndex());
					
					break;
				default:
					break;
				}
				
				bonus.setCellValue(metaGeral.getBonus() != null ? (metaGeral.getBonus().doubleValue() / 100) : 0);
				observacao.setCellValue(metaGeral.getObservacao() != null ? metaGeral.getObservacao() : "N/I");
			}
			
			List<ColaboradorMetaEspecificaModel> quantitativas = colaborador.getColaboradoresMetasEspecificas().stream()
					.filter(m -> m.getPk().getMetaEspecifica().getId().equals(1L))
					.sorted((m1, m2) -> m1.getPk().getSequencia().compareTo(m2.getPk().getSequencia()))
					.collect(Collectors.toList());
			
			List<ColaboradorMetaEspecificaModel> projetos = colaborador.getColaboradoresMetasEspecificas().stream()
					.filter(m -> m.getPk().getMetaEspecifica().getId().equals(2L))
					.sorted((m1, m2) -> m1.getPk().getSequencia().compareTo(m2.getPk().getSequencia()))
					.collect(Collectors.toList());
			
			if (!quantitativas.isEmpty()) {
				this.fillMetasEspecificas(quantitativas, EnumXlsSection.QUANTITATIVAS, metaSheet);
			}
			
			if (!projetos.isEmpty()) {
				this.fillMetasEspecificas(projetos, EnumXlsSection.PROJETOS, metaSheet);
			}
			
			metaSheet.getRow(EnumXlsSection.PONTUACAO.getRowNum()).getCell(EnumXlsEspecificasCells.PONTUACAO.getColIndex())
																  .setCellValue(this.sumPontuacaoTotal / 100);
			
		}
	}
	
	private void fillMetasEspecificas(List<ColaboradorMetaEspecificaModel> itens, EnumXlsSection section, Sheet sheet) {
		int rowNum = section.getRowNum() + 2;
		double sumPesos = 0;
		Cell sumPesosCell = sheet.getRow(rowNum).getCell(EnumXlsEspecificasCells.SUMPESOS.getColIndex());
		for (ColaboradorMetaEspecificaModel item: itens) {
			Row row = sheet.getRow(rowNum);
			
			row.getCell(EnumXlsEspecificasCells.SEQUENCIA.getColIndex()).setCellValue(item.getPk().getSequencia());
			row.getCell(EnumXlsEspecificasCells.DESCRICAO.getColIndex()).setCellValue(item.getDescricao() != null ? item.getDescricao() : "");
			row.getCell(EnumXlsEspecificasCells.FREQUENCIA.getColIndex()).setCellValue(item.getFrequenciaMedicao());
			row.getCell(EnumXlsEspecificasCells.PESOS.getColIndex()).setCellValue(item.getPeso().doubleValue() / 100);
			row.getCell(EnumXlsEspecificasCells.META.getColIndex()).setCellValue(item.getMeta() != null ? item.getMeta() : "");
			row.getCell(EnumXlsEspecificasCells.OBSERVACOES.getColIndex()).setCellValue(item.getObservacao() != null ? item.getObservacao() : "");
			row.getCell(EnumXlsEspecificasCells.PRAZOS.getColIndex()).setCellValue(item.getPrazo().format(DateTimeFormatter.ofPattern(PLRUtils.DATE_PATTERN_JS)));
			
			sumPesos += item.getPeso().doubleValue();
			rowNum++;
			
		}
		
		sumPesosCell.setCellValue(sumPesos / 100);
		
		this.sumPontuacaoTotal += sumPesos;
	}
}
