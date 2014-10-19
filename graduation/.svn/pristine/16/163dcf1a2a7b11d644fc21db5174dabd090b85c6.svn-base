package com.mvc.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;


public class ExcelIO {
	
	public static String[][] ExcelOperate(HttpServletRequest request, MultipartFile excelFile){
		try{
			ExcelIO.saveFileFromInputStream(
					excelFile.getInputStream(), request.getRealPath("/uploadfiles"), excelFile.getOriginalFilename());
		}catch(Exception e){
			System.out.println(e.toString());
		}
		File file = new File(request.getRealPath("/uploadfiles") + ( "/" + excelFile.getOriginalFilename()));
		String[][] result = null;
		try{
			result = ExcelIO.getData(file, 1);
		}catch(Exception e){
			System.out.println(e.toString());
		}
		return result;
	}
	
	/**
	 * 
	 * 设置表头 格式
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-15 上午11:54:21
	 * @return HSSFCellStyle
	 */
	public static HSSFCellStyle stylecreateTitle(HSSFWorkbook wb, 
			int layer) throws Exception{
		HSSFCellStyle style = wb.createCellStyle();
		/**
		 * 设置各边框实线并设置线条颜色
		 */
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);

		if(layer == 1){
			/**
			 * 设置背景颜色
			 */
			style.setBorderRight(HSSFCellStyle.BORDER_THIN);
			style.setRightBorderColor(HSSFColor.BLACK.index);
			//style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			//设置自动换行
			//style.setWrapText(true);
			/**
			 * 设置字体
			 */
			HSSFFont fonts = wb.createFont();
			fonts.setFontName("楷体");  //字体
			fonts.setColor(HSSFColor.BLACK.index);  //字体颜色
			fonts.setFontHeight((short) 250);  //字高
			fonts.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  //字体加粗
			style.setFont(fonts);
			
		}else if(layer == 2){
			//style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			HSSFFont font = wb.createFont();
			font.setFontName("宋体");
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			
		}else if(layer == 3){
			style.setFillForegroundColor(HSSFColor.WHITE.index);
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		}else{
			
		}
		return style;
	}
	
	/**
	 * 
	 * 将MultipartFile 转换为File 
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-15 上午10:43:25
	 * @return void
	 */
	private static void saveFileFromInputStream(InputStream inputStream,
			String realPath, String savefile) throws IOException{
		FileOutputStream fs = new FileOutputStream(realPath + "/" + savefile);
		//System.out.println("-----------" + realPath + "/" + savefile);
		byte[] buffer = new byte[1024*1024];
		int bytesum = 0;
		int byteread = 0;
		while((byteread = inputStream.read(buffer)) != -1){
			bytesum = bytesum + byteread;
			fs.write(buffer, 0, byteread);
			fs.flush();
		}
		fs.close();
		inputStream.close();
	}
	
	/**
	 * 
	 * 读取Excel的内容
	 * 第一维数组存储的是一行中格列的值
	 * 二维数组存储的是 多少行
	 * file 读取数据的源excel
	 * ignoreRows 读取数据忽略的行数，比喻行头不需要读入，忽略行数为1
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-15 上午10:43:32
	 * @return String[][] 读出的excel数据内容
	 */
	private static  String[][] getData(File file, int ignoreRows) throws Exception{
		List<String[]> result = new ArrayList<String[]>();
		int rowSize = 0;
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		
		//打开HSSFWorkbook
		POIFSFileSystem fs = new POIFSFileSystem(bis);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFCell cell = null;
		for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
			HSSFSheet st = wb.getSheetAt(sheetIndex);
			//第一行为标题　不取
			for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
				HSSFRow row = st.getRow(rowIndex);
				if(row == null){
					continue;
				}
				int tempRowSize = row.getLastCellNum() + 1;
				if(tempRowSize > rowSize){
					rowSize = tempRowSize;
				}
				String[] values = new String[rowSize];
				Arrays.fill(values, "");
				boolean hasValue = false;
				for (short columnIndex = 0; columnIndex < row.getLastCellNum(); columnIndex++) {
					String value = "";
					cell = row.getCell(columnIndex);
					if(cell != null){
						switch(cell.getCellType()){
							case HSSFCell.CELL_TYPE_STRING: value = cell.getStringCellValue();
								break;
							case HSSFCell.CELL_TYPE_NUMERIC:
								if(HSSFDateUtil.isCellDateFormatted(cell)){
									Date date = cell.getDateCellValue();
									if(date != null){
										value = new SimpleDateFormat("yyyy-MM-dd").format(date);
									}else{
										value = "";
									}
								}else{
									value = new DecimalFormat("0").format(cell.getNumericCellValue());
								}
								break;
							case HSSFCell.CELL_TYPE_FORMULA:
								if(!cell.getStringCellValue().equals("")){
									value = cell.getStringCellValue();
								}else{
									value = cell.getNumericCellValue() + "";
								}
								break;
							case HSSFCell.CELL_TYPE_BLANK: break;
							case HSSFCell.CELL_TYPE_ERROR: value = ""; break;
							case HSSFCell.CELL_TYPE_BOOLEAN:
								value = (cell.getBooleanCellValue() == true ? "Y" : "N");
								break;
							default: value = "";
						}
					}
					if(columnIndex == 0 && value.trim().equals("")){
						break;
					}
					values[columnIndex] = rightTrim(value);
					hasValue = true;
				}
				if(hasValue){
					result.add(values);
				}
			}
		}
		bis.close();
		String[][] returnArray = new String[result.size()][rowSize];
		for (int i = 0; i < returnArray.length; i++) {
			returnArray[i] = (String[]) result.get(i);
		}
		return returnArray;
	}

	/**
	 * 
	 * 去掉字符串右边的空格 
	 * @Description value--要处理的字符串 
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-15 上午11:49:25
	 * @return String--处理后的字符串
	 */
	private static String rightTrim(String value) {
		if(value == null){
			return "";
		}
		int length = value.length();
		for (int i = length - 1; i >= 0 ; i--) {
			if (value.charAt(i) != 0x20) {
				break;
			}
			length--;
		}
		return value.substring(0, length);
	}
	
	/**
	 * 
	 * TODO 文件中文名处理
	 * @Description  
	 * @author Happy_Jqc@163.com
	 * @date 2014-7-18 上午11:52:31
	 * @return String
	 */
	/*public static String encodeFilename(String filename, HttpServletRequest request){
		String agent = request.getHeader("USER-AGENT");
		try{
			if ((agent != null) && (-1 != agent.indexOf("MSIE"))) {
				String newFilename = URLEncoder.encode(filename, "UTF-8");
				newFilename = StringUtils.replace(newFilename, "+", "20%");
				if (newFilename.length() > 150) {
					newFilename = new String(filename.getBytes("GB2312"), "ISO8859-1");
					newFilename = StringUtils.replace(newFilename, " ", "20%");
				}
				return newFilename;
			}
			if (agent != null && (-1 != agent.indexOf("Mozilla"))) {
				return MimeUtility.encodeText(filename, "UTF-8", "B");
			}
		}catch(Exception e){
			System.out.println(e.toString());
			return filename;
		}
		return filename;
	}*/
}
