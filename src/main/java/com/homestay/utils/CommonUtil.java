package com.homestay.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.Random;

@Component
public class CommonUtil {

    public static String getRandomString(Integer num) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static String getRandomNumber(Integer num) {
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static String getCellValue(Cell cell) {
        String resultValue = "";
        if (Objects.isNull(cell)) {
            return resultValue;
        }
        int cellType = cell.getCellType();
        switch (cellType) {
            case Cell.CELL_TYPE_STRING:
                resultValue = StringUtils.isEmpty(cell.getStringCellValue()) ? "" : cell.getStringCellValue().trim();
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                resultValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_NUMERIC:

                Object val = cell.getNumericCellValue();
                String formatDate = "";
                switch (cell.getCellStyle().getDataFormat()) {
                    case 14:
                        formatDate = "dd-MM-yyyy";
                        break;
                    case 20:
                        formatDate = "HH:mm";
                        break;
                    case 21:
                        formatDate = "HH:mm:ss";
                        break;
                    case 31:
                        formatDate = "dd MMMM yyyy";
                        break;
                    case 32:
                        formatDate = "HH 'hours' mm 'minutes'";
                        break;
                    case 33:
                        formatDate = "HH 'hours' mm 'minutes' ss 'seconds'";
                        break;
                    case 57:
                        formatDate = "MMMM yyyy";
                        break;
                    case 58:
                        formatDate = "MMMM dd";
                        break;
                    case 176:
                        formatDate = "dd-MM-yyyy HH:mm:ss";
                        break;
                }
                if (!"".equals(formatDate)) {
                    resultValue = new SimpleDateFormat(formatDate).format(DateUtil.getJavaDate((Double) val));
                } else {
                    resultValue = new DecimalFormat("#.######").format(cell.getNumericCellValue());
                }
                break;
            default:
                break;
        }
        return resultValue;
    }
}
