package br.com.zipext.plr.export.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import br.com.zipext.plr.enums.EnumXlsGeraisCells;
import br.com.zipext.plr.enums.EnumXlsIdCells;
import br.com.zipext.plr.enums.EnumXlsSection;
import br.com.zipext.plr.export.FileExport;
import br.com.zipext.plr.model.ColaboradorCargoModel;
import br.com.zipext.plr.model.ColaboradorMetaGeralModel;
import br.com.zipext.plr.model.ColaboradorModel;
import br.com.zipext.plr.model.MetaGeralModel;
import br.com.zipext.plr.utils.PLRUtils;

public class XlsFileExport extends FileExport {

	private Workbook workbook;
	
	public ByteArrayInputStream processXlsForColaborador(ColaboradorModel colaboradorModel) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		if (this.workbook == null) {
			this.initWorkbook();
		}
		
		this.writeContent(colaboradorModel);
		
		this.workbook.write(out);
		
		closeFileInputStream();

		return
				new ByteArrayInputStream(out.toByteArray());
	}
	
	public Workbook initWorkbook() {
		try {
			this.workbook = WorkbookFactory.create(getFileInputStream("/opt/plr/templates/TEMPLATE_METAS.xlsx"));
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
				default:
					break;
				}
				
				bonus.setCellValue(metaGeral.getBonus() != null ? (metaGeral.getBonus().doubleValue() / 100) : 0);
				observacao.setCellValue(metaGeral.getObservacao() != null ? metaGeral.getObservacao() : "N/I");
			}
		}
	}
}
