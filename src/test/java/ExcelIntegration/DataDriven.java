package ExcelIntegration;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
public class DataDriven {

    public ArrayList<String> getData(String sheetName, String rowName, String columnName) throws IOException {
        ArrayList<String> a = new ArrayList<String>();
        FileInputStream fi = new FileInputStream("/Users/Faraha179271/Desktop/Datadriven.xlsx");
        XSSFWorkbook book = new XSSFWorkbook(fi);
        int count = book.getNumberOfSheets();
        System.out.println("tset");
        for (int i = 0; i < count; i++) {


            XSSFSheet sheet = book.getSheetAt(i);
            System.out.println("sheet name is" + sheet.getSheetName());
            if (sheet.getSheetName().equalsIgnoreCase(sheetName)) {
                System.out.println("tset2");

                Iterator<Row> rows = sheet.rowIterator();
                Row firstRow = rows.next();

                Iterator<Cell> cell = firstRow.cellIterator();
                int k = 0;
                int column = 0;
                while (cell.hasNext()) {
                    Cell value = cell.next();
                    if (value.getStringCellValue().equalsIgnoreCase(rowName)) {
                        column = k;

                    }
                    k++;

                }
                System.out.println(column);
//                Once column is identified then scan entire column to identify purchase in a test cases row
                int j = 0;
                while (rows.hasNext()) {
                    Row r = rows.next();
                    if (r.getCell(column).getStringCellValue().equalsIgnoreCase(columnName)) {
                        Iterator<Cell> ci = r.cellIterator();
                        while (ci.hasNext())
                        {
                            Cell c=ci.next();
                            if(c.getCellType()== CellType.STRING)
                            {
                                a.add(c.getStringCellValue());

                            }
                            else
                            {
                                a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
                            }

                        }
                    }

                }

                break;

            }
        }


        return a;
    }
}









