import com.spring.sportch.SportChApplication;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.util.ArrayUtil;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.apache.poi.ss.usermodel.CellStyle.*;

@SpringBootTest(classes = SportChApplication.class)
public class Tests {






    @Test
    public void print() {

        Map ff = new HashMap();

    }


    public void algorithm() throws IOException {


        String[] firstSheetHeader = {"통화코드", "전반기말 시재금", "대고객 외국환 매입액", "대외국환은행 외국환 매입액", "대고객 외국환 매각액", "대외국환은행 외국환 매각액", "반기중 예치액 증감액", "예치잔액", "금반기말 시재금"};
        String[] secondSheetHeader = {"연변", "항목명", "형식", "자리수", "항목내용 및  작성방법"};

        String[][] secondSheetContent = {
            {"1|통화코드|숫자|2|ㅇ \"01\" : 미화(USD), \"02\" : 엔화(JPY), \"03\" : 위안화(CNY), \"04\" : 유로화(EUR), \"08\" : 기타 \n* 통화가 미화가 아닌경우 미화로 환산하여 작성(해당반기 말일(6.30,12.31)현재 외국환중개회사의 장이 고시하는 환산율(CROSS RATE 적용)"},
            {"2|전반기말_시재금|숫자|20이내|ㅇ 통화별 미화로 환산하여 소수점 2자리까지 기재"},
            {"3|대고객_외국환_매입액|숫자|20이내|ㅇ 통화별 미화로 환산하여 소수점 2자리까지 기재"},
            {"4|대외국환은행_외국환_매입액|숫자|20이내|ㅇ 통화별 미화로 환산하여 소수점 2자리까지 기재"},
            {"5|대고객_외국환_매각액|숫자|20이내|ㅇ 통화별 미화로 환산하여 소수점 2자리까지 기재"},
            {"6|대외국환은행_외국환_매각액|숫자|20이내|ㅇ 통화별 미화로 환산하여 소수점 2자리까지 기재"},
            {"7|반기중_예치액_증감액|숫자|20이내|ㅇ 통화별 미화로 환산하여 소수점 2자리까지 기재"},
            {"8|예치잔액|숫자|20이내|ㅇ 통화별 미화로 환산하여 소수점 2자리까지 기재"},
            {"9|금반기말_시재금|숫자|20이내|ㅇ 전반기말 시재금+대고객 외국환 매입액 + 대외국환은행 외국환 매입액 - 대고객 외국환 매각액 - 대외국환은행 외국환 매각액 - 반기중 예치액 증감액"}
        };


        String[] testHeader = {"one", "two", "three", "four", "five", "six" ,"seven", "eight", "nine", "ten"};
        String testContent = "1. hello\n 2.world\n 3.zzzzz\n 4.fesase";

        Workbook workbook = new SXSSFWorkbook();

        // ========================= 환전 업무 보고용 =========================

        Sheet sheet = workbook.createSheet("환전영업자_입력양식(USD환산)");

        CellStyle header = workbook.createCellStyle();
        header.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        header.setVerticalAlignment(VERTICAL_CENTER);
        header.setAlignment(ALIGN_CENTER);
        header.setBorderTop(BORDER_THIN);
        header.setBorderBottom(BORDER_THIN);
        header.setBorderLeft(BORDER_THIN);
        header.setBorderRight(BORDER_THIN);
        header.setWrapText(true);

        CellStyle wrapText = workbook.createCellStyle();
        wrapText.setBorderTop(BORDER_THIN);
        wrapText.setBorderBottom(BORDER_THIN);
        wrapText.setBorderLeft(BORDER_THIN);
        wrapText.setBorderRight(BORDER_THIN);
        wrapText.setWrapText(true);


        Font headerFont = workbook.createFont();
        headerFont.setFontName("맑은 고딕");
        headerFont.setFontHeightInPoints((short) 14);
        header.setFont(headerFont);

        Row row = sheet.createRow(0);
        Cell cell = null;
        for (int cellNumber = 0; cellNumber < firstSheetHeader.length; cellNumber++) {

            cell = row.createCell(cellNumber);
            cell.setCellStyle(header);
            cell.setCellValue(firstSheetHeader[cellNumber]);

        }

        row = sheet.createRow(1);
        for (int cellNumber = 0; cellNumber < firstSheetHeader.length; cellNumber++) {
            cell = row.createCell(cellNumber);
            cell.setCellStyle(header);
        }

        for (int columnNumber = 0; columnNumber < firstSheetHeader.length; columnNumber++) {
            sheet.autoSizeColumn(columnNumber);
            sheet.setColumnWidth(columnNumber, sheet.getColumnWidth(columnNumber)+1024);
        }
        // ================================================================================

        // ============================== 환전장부 보고용 ===================================

        CellStyle test = workbook.createCellStyle();
        test.setBorderTop(BORDER_THIN);
        test.setBorderRight(BORDER_THIN);
        test.setBorderLeft(BORDER_THIN);
        test.setBorderBottom(BORDER_THIN);
        test.setWrapText(true);

        sheet = workbook.createSheet("테스트");
        row = sheet.createRow(0);
        cell = row.createCell(0);
        cell.setCellStyle(test);
        cell.setCellValue(testContent);

        CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 3, 0, 9);
        RegionUtil.setBorderBottom(BORDER_THIN, cellRangeAddress, sheet, workbook);
        RegionUtil.setBorderTop(BORDER_THIN, cellRangeAddress, sheet, workbook);
        RegionUtil.setBorderLeft(BORDER_THIN, cellRangeAddress, sheet, workbook);
        RegionUtil.setBorderRight(BORDER_THIN, cellRangeAddress, sheet, workbook);
        sheet.addMergedRegion(cellRangeAddress);

        row = sheet.createRow(4);
        for (int cellNumber = 0; cellNumber < testHeader.length; cellNumber++) {
            cell = row.createCell(cellNumber);
            cell.setCellValue(testHeader[cellNumber]);
            cell.setCellStyle(test);
        }

        for (int columnNumber = 0; columnNumber < testHeader.length; columnNumber++) {
            sheet.autoSizeColumn(columnNumber);
            sheet.setColumnWidth(columnNumber, sheet.getColumnWidth(columnNumber)+1024);
        }
        // ============================================================


        // ======================== 작성법 ========================
//        sheet = workbook.createSheet("작성방법)환전영업자용)");
//        row = sheet.createRow(0);
//        for (int cellNumber = 0; cellNumber < secondSheetHeader.length; cellNumber++) {
//            cell = row.createCell(cellNumber);
//            cell.setCellStyle(header);
//            cell.setCellValue(secondSheetHeader[cellNumber]);
//        }
//
//
//        for (int rowNumber = 0; rowNumber < secondSheetContent.length; rowNumber++) {
//
//            row = sheet.createRow(rowNumber + 1);
//            String[] contents = secondSheetContent[rowNumber][0].split("\\|");
//
//            for (int cellNumber = 0; cellNumber < secondSheetHeader.length; cellNumber++) {
//                cell = row.createCell(cellNumber);
//                cell.setCellValue(contents[cellNumber]);
//
//                if (cellNumber < secondSheetHeader.length-1) {
//                    cell.setCellStyle(header);
//
//                } else {
//                    cell.setCellStyle(wrapText);
//                }
//
//            }
//        }
//
//        for (int number = 0; number < secondSheetHeader.length; number++) {
//            sheet.autoSizeColumn(number);
//            if (number == secondSheetHeader.length-1) {
//                sheet.setColumnWidth(number, sheet.getColumnWidth(number) + 5102);
//            } else {
//                sheet.setColumnWidth(number, sheet.getColumnWidth(number) + 1024);
//            }
//        }
//        ========================================================================

        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + "ffsdf.xlsx";

        FileOutputStream outputStream = new FileOutputStream(fileLocation);
        workbook.write(outputStream);

    }



}
