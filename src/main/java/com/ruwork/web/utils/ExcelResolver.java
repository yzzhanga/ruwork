package com.ruwork.web.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelResolver {


    /**
     * 处理上传的文件
     *
     * @param in
     * @param fileName
     * @return
     * @throws Exception
     */
    public List<List<List<String>>> getBankListByExcel(InputStream in, String fileName) throws Exception {
        //创建Excel工作薄
        Workbook work = this.getWorkbook(in, fileName);
        if (null == work) {
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;

        List<List<List<String>>> worksheets = new ArrayList<>();
        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if (sheet == null) {
                continue;
            }

            List<List<String>> rows = new ArrayList<>();
            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                row = sheet.getRow(j);
                if (row == null || row.getFirstCellNum() == j) {
                    continue;
                }

                List<String> cells = new ArrayList<>();
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    cell = row.getCell(y);
                    int CELL_TYPE_NUMERIC = 0;
                    int CELL_TYPE_STRING = 1;
                    int CELL_TYPE_FORMULA = 2;
                    int CELL_TYPE_BLANK = 3;
                    int CELL_TYPE_BOOLEAN = 4;
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            cells.add(String.valueOf(cell.getNumericCellValue()));
                            break;
                        case Cell.CELL_TYPE_STRING:
                            cells.add(cell.getStringCellValue());
                            break;
                        case Cell.CELL_TYPE_FORMULA:
                        case Cell.CELL_TYPE_BLANK:
                        case Cell.CELL_TYPE_BOOLEAN:
                        default:
                    }
                }
                rows.add(cells);
            }
            worksheets.add(rows);
        }
        work.close();
        return worksheets;
    }

    /**
     * 判断文件格式
     *
     * @param inStr
     * @param fileName
     * @return
     * @throws Exception
     */
    public Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
        Workbook workbook = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (".xls".equals(fileType)) {
            workbook = new HSSFWorkbook(inStr);
        } else if (".xlsx".equals(fileType)) {
            workbook = new XSSFWorkbook(inStr);
        } else {
            throw new Exception("请上传excel文件！");
        }
        return workbook;
    }


    public List<List<List<String>>> resolve(File dest) throws Exception {
       return  getBankListByExcel(new FileInputStream(dest),dest.getName());
    }
}
