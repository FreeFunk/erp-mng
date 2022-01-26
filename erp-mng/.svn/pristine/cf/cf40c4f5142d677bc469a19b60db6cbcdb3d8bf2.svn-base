package com.edgedo.timedtask;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.InputStream;

import org.apache.poi.ss.util.CellReference;

@Component
public class ReadExcelCarInfo {
    //判断文件类型是否满足要求
    public static Workbook getWorkBook(String file, InputStream fis)throws Exception{
        Workbook workbook = null;
        if(!file.endsWith(".xls") && !file.endsWith(".xlsx")){
            throw new Exception("上传表格的格式错误！");
        }
        if(file.endsWith(".xls")){
            workbook = new HSSFWorkbook(fis,true);
        }
        if(file.endsWith(".xlsx")){
            workbook = new XSSFWorkbook(fis);
        }
        return workbook;
    }

    //获取每一行的数据，并且返回一个字符串数组
    public static String[] getExcelRows(Row row , int columuNum){
        String[] s ;
        StringBuilder builder = new StringBuilder();
        for(int x = 0;x<columuNum;x++){
            Cell cell = row.getCell(x);
            builder.append(getStringCellValue(cell)+",");
        }
        s = builder.toString().split(",");
        return s;
    }

    //获取单元内的有效数据
    public static String getStringCellValue(Cell cell){
        StringBuilder sb = new StringBuilder();
        switch (cell.getCellType()){
            //数字
            case Cell.CELL_TYPE_NUMERIC:
                if(DateUtil.isCellDateFormatted(cell)){
                    sb.append(cell.getDateCellValue());
                }else {
                    //将该数字强制转化为字符串类型获取
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    sb.append(cell.getStringCellValue());
                }
                break;

            //字符串
            case Cell.CELL_TYPE_STRING:
                sb.append(cell.getStringCellValue());
                break;

            //布尔
            case Cell.CELL_TYPE_BOOLEAN:
                sb.append(cell.getBooleanCellValue());
                break;

            //公式
            case Cell.CELL_TYPE_FORMULA:
                sb.append(cell.getCellFormula());
                break;

            //空值
            case Cell.CELL_TYPE_BLANK:
                sb.append("");
                break;

            //故障
            case Cell.CELL_TYPE_ERROR:
                sb.append("");
                break;

            default:
                sb.append("");
                break;
        }
        return sb.toString();
    }

    //过滤掉表中的无意义空白行，因为getLastRowNum（）在获取Row行数时，对于表中没有值但却有格式的无意义空白行也将计入
    public static Sheet resetSheet(Sheet sheet){
        CellReference cellReference = new CellReference("A4");
        int lastRowNum = sheet.getLastRowNum();
        for(int i=1;i<lastRowNum;i++){
            Row r = sheet.getRow(i);
            if(r != null){
                for(Cell c : r){
                    if(c.getCellType()!=Cell.CELL_TYPE_BLANK){
                        break;
                    }
                }
            }
            if(r == null){
                if(isRowEmpty(r)){
                    sheet.removeRow(r);
                }
                sheet.shiftRows(i+1,lastRowNum,-1);
                i--;
                lastRowNum--;
            }
        }
        return sheet;
    }

    public static boolean isRowEmpty(Row row) {
        for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK)
                return false;
        }
        return true;
    }

}
