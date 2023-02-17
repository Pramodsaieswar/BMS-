package com.bms.floor.Helper;

import com.bms.floor.entity.Floors;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FloorsExcel {

    public static boolean checkExcelFormat(MultipartFile file) {

        String contentType = file.getContentType();

        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return true;
        } else {
            return false;
        }
    }

    //convert excel to list
    public static List<Floors> convertExcelToListOfProduct(InputStream


                                                                     is) {
        List<Floors> list = new ArrayList<>();
        try {

            XSSFWorkbook workbook =  new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheet("data");
//for every excel we have to change name as data//
            int rowNumber=0;
            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext())
            {
                Row row = iterator.next();
                if(rowNumber==0){
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cells = row.iterator();

                int cid=0;

                Floors flr =    new Floors();
                while (cells.hasNext())
                {
                    Cell cell = cells.next();

                    switch (cid)
                    {

                        case 0:
                            flr.setFloorNo((int) cell.getNumericCellValue());
                            break;
                        case 1:
                            flr.setFlatNo((int) cell.getNumericCellValue());
                            break;
                        case 2:
                            flr.setFlatStatus(cell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cid++;

                }
                list.add(flr);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
