package com.jinhui.scheduler.biz.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {

	private static SimpleDateFormat sfd = new SimpleDateFormat("yyyy/MM/dd");
	
	
	private static int START = 1;

	public static List<String> Excel2List(File file) {

		try (FileInputStream in = new FileInputStream(file)) {
			Workbook book = WorkbookFactory.create(in);

			Sheet sheet = book.getSheetAt(0);
			int rowNum = sheet.getLastRowNum();// 获取行数

			ArrayList list = new ArrayList();
			for (int i = START; i <= rowNum; i++) {
				Row row = sheet.getRow(i);
				Cell cell = row.getCell(0);

				// 获取单元格的值
				String value = getCellValue(cell);
				list.add(value);
			}
			return list;

		} catch (InvalidFormatException | IOException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}

}

	/**
	 * 把cell的值转换为String
	 */
	private static String getCellValue(Cell cell) {
		Object value = null;
		if (null == cell) {
			return null;
		}
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_BOOLEAN:
			value = cell.getBooleanCellValue();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				value = sfd.format(cell.getDateCellValue());
			}
			break;
		default:
			value = cell.getStringCellValue();
			break;
		}
		return String.valueOf(value);
	}

	public static void main(String[] args) throws Exception {

		List<String> list = ExcelUtils.Excel2List(new File("C:/Users/jinhui/Desktop/交易日.xlsx"));

		for (Object object : list) {
			System.out.println(object);
		}
	}

}
