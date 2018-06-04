package com.xul.util;

import java.io.File;  
import java.io.IOException;  
import java.util.ArrayList;  
import java.util.List;

import com.xul.entity.User;

import jxl.Sheet;  
import jxl.Workbook;  
import jxl.format.Alignment;  
import jxl.format.Border;  
import jxl.format.BorderLineStyle;  
import jxl.format.Colour;  
import jxl.format.VerticalAlignment;  
import jxl.read.biff.BiffException;  
import jxl.write.Label;  
import jxl.write.Number;  
import jxl.write.NumberFormats;  
import jxl.write.WritableCellFormat;  
import jxl.write.WritableFont;  
import jxl.write.WritableSheet;  
import jxl.write.WritableWorkbook;  
import jxl.write.WriteException;  


/**
 * @author Silencn
 *
 * excel操作工具类
 */
public class ExcelUtil {
	
    private static WritableCellFormat titleFormat=null; // 标题单元格格式  
    private static WritableCellFormat bodyFormat=null; // 主题内容单元格格式
    private static WritableCellFormat noteFormat=null; // 注释单元格格式
    private static WritableCellFormat floatFormat=null; // 浮点型数据的单元格格式
    private static WritableCellFormat intFormat=null; //整型数据的单元格格式 
    private static boolean init=false; // 初始化数据
  
    private ExcelUtil(){}// 私有构造方法，防止错误使用Excel类
  
    private static void init() throws WriteException{  // 初始化各单元格格式  @throws WriteException 初始化失败  
        WritableFont font1,font2,font3,font4;  
        //Arial字体，9号，粗体，单元格黄色，田字边框，居中对齐  
        font1 = new WritableFont(WritableFont.ARIAL, 9, WritableFont.BOLD, false);  
        titleFormat = new WritableCellFormat (font1);  
        titleFormat.setBackground(Colour.YELLOW);  
        titleFormat.setBorder(Border.ALL, BorderLineStyle.THIN);  
        titleFormat.setAlignment(Alignment.CENTRE);  
        //Arial字体，9号，粗体，单元格黄色，田字边框，左右居中对齐，垂直居中对齐，自动换行  
        font2 = new WritableFont(WritableFont.ARIAL, 9, WritableFont.BOLD, false);  
        noteFormat = new WritableCellFormat (font2);  
        noteFormat.setBackground(Colour.YELLOW);  
        noteFormat.setBorder(Border.ALL, BorderLineStyle.THIN);  
        noteFormat.setAlignment(Alignment.CENTRE);  
        noteFormat.setVerticalAlignment(VerticalAlignment.CENTRE);  
        noteFormat.setWrap(true);  
        //Arial字体，9号，非粗体，单元格淡绿色，田字边框  
        font3 = new WritableFont(WritableFont.ARIAL, 9, WritableFont.NO_BOLD, false);  
        bodyFormat = new WritableCellFormat (font3);  
        bodyFormat.setBackground(Colour.LIGHT_GREEN);  
        bodyFormat.setBorder(Border.ALL, BorderLineStyle.THIN);  
        //Arial字体，9号，非粗体，单元格淡绿色，田字边框  
        font4 = new WritableFont(WritableFont.ARIAL, 9, WritableFont.NO_BOLD, false);  
        floatFormat = new WritableCellFormat (font4,NumberFormats.FLOAT);  
        floatFormat.setBackground(Colour.LIGHT_GREEN);  
        floatFormat.setBorder(Border.ALL, BorderLineStyle.THIN);  
        //Arial字体，9号，非粗体，单元格淡绿色，田字边框  
        font4 = new WritableFont(WritableFont.ARIAL, 9, WritableFont.NO_BOLD, false);  
        intFormat = new WritableCellFormat (font4,NumberFormats.INTEGER);  
        intFormat.setBackground(Colour.LIGHT_GREEN);  
        intFormat.setBorder(Border.ALL, BorderLineStyle.THIN);  
        init=true;  
    }  
    
    //导出
    public static void createUserExcelFile(List<User> userList,File destFile) throws WriteException, IOException{  
        if(init==false) init();  
        int index,row;  
        WritableSheet sheet=null;  
        WritableWorkbook book=null;  
        book = Workbook.createWorkbook(destFile);  
        sheet = book.createSheet("用户表", 0);  
        sheet.setColumnView(0, 15);  
        sheet.setColumnView(1, 15);  
        sheet.setColumnView(2, 15);  
        sheet.setColumnView(3, 40);  
        //字段变量名  
        index=0;  
        sheet.addCell(new Label(index++,0,"uid",titleFormat));  
        sheet.addCell(new Label(index++,0,"username",titleFormat));  
        sheet.addCell(new Label(index++,0,"password",titleFormat));  
        sheet.addCell(new Label(index++,0,"tel",titleFormat));  
        //字段名  
        index=0;  
        sheet.addCell(new Label(index++,1,"ID",titleFormat));  
        sheet.addCell(new Label(index++,1,"用户名",titleFormat));  
        sheet.addCell(new Label(index++,1,"密码",titleFormat));  
        sheet.addCell(new Label(index++,1,"电话",titleFormat));  
        //字段注释  
        index=0;  
        sheet.addCell(new Label(index++,2,null,noteFormat));  
        sheet.addCell(new Label(index++,2,null,noteFormat));  
        sheet.addCell(new Label(index++,2,null,noteFormat));  
        sheet.addCell(new Label(index++,2,null,noteFormat));  
        row=2;//改变行数  
        for(User user:userList){  
            if(user==null) continue;  
            index=0;  
            sheet.addCell(new Number(index++,row,user.getUid(),bodyFormat));  
            sheet.addCell(new Label(index++,row,user.getUsername(),bodyFormat));  
            sheet.addCell(new Label(index++,row,user.getPassword(),bodyFormat));  
            sheet.addCell(new Label(index++,row,user.getTel(),bodyFormat));  
            row++;  
        }  
        book.write();  
        if(book!=null) book.close();  
    }  
    
    //读取
    public static List<User> readUserExcelFile(File file) throws IOException, BiffException{  
        if(file==null) return null;  
        int row,column;  
        String temp=null;  
        Workbook book =null;  
        Sheet sheet=null;  
        List<User> userList=new ArrayList<User>();  
        book = Workbook.getWorkbook(file);  
        sheet = book.getSheet(0);  
        row=3;  
        while(row<sheet.getRows()){  
            column=0;  
            User user=new User();  
            //编号 
            temp=sheet.getCell(column++,row).getContents().trim();  
            if(temp!=null && !temp.equals("") && temp.matches("//d+")) user.setUid(Integer.parseInt(temp));  
            else break;  
            //用户名
            temp=sheet.getCell(column++,row).getContents().trim();  
            if(temp!=null && !temp.equals("")) user.setUsername(temp);
            //密码
            temp=sheet.getCell(column++,row).getContents().trim();  
            if(temp!=null && !temp.equals("") && temp.matches("//d+")) user.setPassword(temp);  
            //电话
            temp=sheet.getCell(column++,row).getContents().trim();  
            if(temp!=null && !temp.equals("")) user.setTel(temp);  
  
            userList.add(user);  
            row++;  
        }  
        if(book!=null) book.close();  
        return userList;  
    }
    public static void main(String[] args) {
    	try {
    		File file = new File("C:/Users/Administrator/Desktop/me.xlsx");
    		List<User> users = new ArrayList<User>();
    		User user1 = new User();
    		user1.setUid(0);
    		user1.setUsername("admin1");
    		user1.setPassword("admin1");
    		user1.setTel("110");
    		User user2 = new User();
    		user2.setUid(1);
    		user2.setUsername("admin2");
    		user2.setPassword("admin2");
    		user2.setTel("119");
    		users.add(user1);
    		users.add(user2);
			createUserExcelFile(users,file);
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
