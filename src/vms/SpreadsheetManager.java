package vms;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.util.Iterator;

public class SpreadsheetManager {
    private String filePath;

    public SpreadsheetManager(String filePath) {
        this.filePath = filePath;
        createFileIfNotExists();
    }

    private void createFileIfNotExists() {
        File file = new File(filePath);
        if (!file.exists()) {
            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("Visitors");
                Row header = sheet.createRow(0);
                header.createCell(0).setCellValue("Name");
                header.createCell(1).setCellValue("Phone");
                header.createCell(2).setCellValue("Purpose");
                header.createCell(3).setCellValue("Check-In");
                header.createCell(4).setCellValue("Check-Out");

                try (FileOutputStream fos = new FileOutputStream(filePath)) {
                    workbook.write(fos);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addVisitor(Visitor visitor) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            int rowCount = sheet.getLastRowNum();
            Row row = sheet.createRow(rowCount + 1);
            row.createCell(0).setCellValue(visitor.getName());
            row.createCell(1).setCellValue(visitor.getPhone());
            row.createCell(2).setCellValue(visitor.getPurpose());
            row.createCell(3).setCellValue(visitor.getCheckInTime());
            row.createCell(4).setCellValue(visitor.getCheckOutTime());

            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateCheckOut(String name, String checkOutTime) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row row = iterator.next();
                Cell cell = row.getCell(0);
                if (cell != null && cell.getStringCellValue().equalsIgnoreCase(name)) {
                    row.createCell(4).setCellValue(checkOutTime);
                    break;
                }
            }

            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
