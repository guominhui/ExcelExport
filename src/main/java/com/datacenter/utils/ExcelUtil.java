package com.datacenter.utils;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;






public class ExcelUtil {

	/**
	 * ����Excel
	 * @param sheetName sheet����
	 * @param title ����
	 * @param values ����
	 * @param wb HSSFWorkbook����
	 * @return
	 */
	public static HSSFWorkbook getHSSFWorkbook(String sheetName,String []title,String [][]values, HSSFWorkbook wb){

		// ��һ��������һ��HSSFWorkbook����Ӧһ��Excel�ļ�
		if(wb == null){
			wb = new HSSFWorkbook();
		}

		// �ڶ�������workbook�����һ��sheet,��ӦExcel�ļ��е�sheet
		HSSFSheet sheet = wb.createSheet(sheetName);

		// ����������sheet����ӱ�ͷ��0��,ע���ϰ汾poi��Excel����������������
		HSSFRow row = sheet.createRow(0);

		// ���Ĳ���������Ԫ�񣬲�����ֵ��ͷ ���ñ�ͷ����
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER); // ����һ�����и�ʽ

		//�����ж���
		HSSFCell cell = null;

		//��������
		for(int i=0;i<title.length;i++){
			cell = row.createCell(i);
			cell.setCellValue(title[i]);
			cell.setCellStyle(style);
		}

		//��������
		for(int i=0;i<values.length;i++){
			row = sheet.createRow(i + 1);
			for(int j=0;j<values[i].length;j++){
				//�����ݰ�˳�򸳸���Ӧ���ж���
				row.createCell(j).setCellValue(values[i][j]);
			}
		}
		return wb;
	}

	//������Ӧ������
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(),"ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
