package br.com.zipext.plr.export.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;

import br.com.zipext.plr.enums.EnumXLSSheets;
import br.com.zipext.plr.export.FileExport;
import br.com.zipext.plr.model.TemplateCampoModel;

public class XlsFileExport extends FileExport {

	private Workbook workbook;
	
	private Sheet sheet;
	
	public XlsFileExport(String templatePath, EnumXLSSheets sheet) {
		this.initWorkbook(templatePath, sheet);
	}
	
	public Workbook initWorkbook(String templatePath, EnumXLSSheets sheet) {
		try {
			workbook = WorkbookFactory.create(getFileInputStream(templatePath));
			this.sheet = workbook.getSheet(sheet.getNome());
		} catch (EncryptedDocumentException | IOException e) {
		
		}
		
		return
				this.workbook;
	}


	public void processField(Object model, List<TemplateCampoModel> campos) {
		campos.forEach(campo -> this.processFieldData(model, campo));
	}
	
	public void processFieldData(Object model, TemplateCampoModel campo) {
		try {
			Cell cell = this.getCell(campo);
			Object cellValue = this.getValue(model, campo.getMetodo().split(":"));
			
			if (cell != null) {
				this.setCellValue(cell, cellValue);
			}
		} catch (Exception e) {
			System.out.println("Erro no processamento da célula. Erro: " + e.getMessage());
		}
	}
	
	public void processTable(Collection<?> models, List<TemplateCampoModel> campos) {
		System.out.println(campos);
		models.forEach(m -> {
			campos.forEach(campo -> {
				this.processFieldData(m, campo);
				campo.incremetaIndiceLinha();
			});
		});
	}
	
	public ByteArrayInputStream writeToFile() throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		XSSFFormulaEvaluator.evaluateAllFormulaCells(this.workbook);
		this.workbook.write(out);
		
		this.closeFileInputStream();
		
		return
				new ByteArrayInputStream(out.toByteArray());

	}
	
	private Cell getCell(TemplateCampoModel campo) throws Exception {
		Cell cell = this.sheet.getRow(campo.getLinha()).getCell(campo.getColuna());
		if (cell == null) {
			String message = "Erro ao criar célula para campo " +
					campo.getNome() + " da aba " + 
					(campo.getIndiceAba() + 1) + " - Linha " + campo.getLinha();
			throw new Exception(message);
		}
		return cell;
	}
	
	private Object getValue(Object object, String[] array) throws Exception {

		Class<? extends Object> c = object.getClass();

		Object result = null;

		try {
			Method m = c.getMethod(array[0]);
			result = m.invoke(object);
			if (result != null && array.length > 1) {
				result = getValue(result, ArrayUtils.subarray(array, 1, array.length));
			}
			if (result == null || "".equals(result)) {
				String fieldClass = m.getReturnType().getSimpleName(); 
				if (fieldClass.equals("String"))
					result = "N/A";
				else 
					result = "-";
			}
		}
		catch (InvocationTargetException e) {
			System.out.println("Erro na chamada do método: " + array[0] + ". Classe: " + c);
			e.printStackTrace();
		}
		catch (Exception e) {
			throw new Exception("Verificar o mapeamento: " + e.getMessage() + ". Objeto: " + object.getClass().getSimpleName() +
					". Métodos: " + ArrayUtils.toString(array));
		}
		return result;
	}
	
	private void setCellValue(Cell cell, Object cellValue) {
		if (cellValue instanceof String) {
			cell.setCellValue((String) cellValue);
		} else if (cellValue instanceof RichTextString) {
			cell.setCellValue((RichTextString) cellValue);
		} else if (cellValue instanceof Double) {
			cell.setCellValue((Double) cellValue);
		} else if (cellValue instanceof BigDecimal) {
			cell.setCellValue( ((BigDecimal)cellValue).doubleValue() );
		} else if (cellValue instanceof Integer) {
			cell.setCellValue((Integer)cellValue);
		} else if (cellValue instanceof Long) {
			cell.setCellValue((Long)cellValue);
		} else if (cellValue instanceof Boolean) {
			cell.setCellValue((Boolean) cellValue);
		} else if (cellValue instanceof Date) {
			cell.setCellValue((Date) cellValue);
		} else if (cellValue instanceof Calendar) {
			cell.setCellValue((Calendar) cellValue);
		} else if (cellValue instanceof LocalTime) {
			DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm");
			cell.setCellValue((String)((LocalTime)cellValue).format(timeFormatter));
		}
	}
}
