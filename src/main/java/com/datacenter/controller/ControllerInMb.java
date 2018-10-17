package com.datacenter.controller;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.datacenter.utils.ExcelUtil;;


@Controller
public class ControllerInMb {
	
	@RequestMapping(value = "/test.do")
	public void export(HttpServletRequest request,HttpServletResponse response) throws Exception {
	 System.out.println("===========================");
	}
	
	@RequestMapping(value = "/export")
	public void export(HttpServletResponse response) throws Exception {


		class Test{
			String stuName;
			String stuSex;
			String stuAge;
			String stuSchoolName;
			String stuClassName;
			public Test(String stuName, String stuSex, String stuAge, String stuSchoolName, String stuClassName) {
				super();
				this.stuName = stuName;
				this.stuSex = stuSex;
				this.stuAge = stuAge;
				this.stuSchoolName = stuSchoolName;
				this.stuClassName = stuClassName;
			}
			public String getStuName() {
				return stuName;
			}
			public void setStuName(String stuName) {
				this.stuName = stuName;
			}
			public String getStuSex() {
				return stuSex;
			}
			public void setStuSex(String stuSex) {
				this.stuSex = stuSex;
			}
			public String getStuAge() {
				return stuAge;
			}
			public void setStuAge(String stuAge) {
				this.stuAge = stuAge;
			}
			public String getStuSchoolName() {
				return stuSchoolName;
			}
			public void setStuSchoolName(String stuSchoolName) {
				this.stuSchoolName = stuSchoolName;
			}
			public String getStuClassName() {
				return stuClassName;
			}
			public void setStuClassName(String stuClassName) {
				this.stuClassName = stuClassName;
			}

		}
		//获取数据
		List<Test> list = new ArrayList<Test>();
		list.add(new Test("1", "1", "1", "1", "1"));
		list.add(new Test("2", "2", "2", "2", "2"));
		list.add(new Test("3", "3", "3", "3", "3"));
		list.add(new Test("4", "4", "4", "4", "4"));
		list.add(new Test("5", "5", "5", "5", "5"));
		list.add(new Test("6", "6", "6", "6", "6"));
		list.add(new Test("7", "7", "7", "7", "7"));

		//excel标题
		String[] title = {"名称","性别","年龄","学校","班级"};
		//excel文件名
		String fileName = "学生信息表"+System.currentTimeMillis()+".xls";

		//sheet名
		String sheetName = "学生信息表";

		String[][] content=new String[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			content[i] = new String[title.length];
			Test obj = list.get(i);
			content[i][0] = obj.getStuName();
			content[i][1] = obj.getStuSex();
			content[i][2] = obj.getStuAge();
			content[i][3] = obj.getStuSchoolName();
			content[i][4] = obj.getStuClassName();
		}
		System.out.println("111111111");
		//创建HSSFWorkbook 
		HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);

		//响应到客户端
		try {
			this.setResponseHeader(response, fileName);
			OutputStream os = response.getOutputStream();
			wb.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//发送响应流方法
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
