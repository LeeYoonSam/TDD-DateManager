package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HistoryDate {

    public enum FormatterType {
        DEFAULT,
        YYMMDD,
    }

    private FormatterType formatterType = FormatterType.DEFAULT;
    private SimpleDateFormat dateFormat;

    private String startDate;
    private String endDate;

    public void setDefaultFormat(FormatterType formatterType) {
        this.formatterType = formatterType;

        switch (formatterType) {
            case DEFAULT:
                dateFormat = new SimpleDateFormat("yyyyMMdd");
                break;
            case YYMMDD:
                dateFormat = new SimpleDateFormat("yyMMdd");
                break;
        }

    }

    public FormatterType getFormatterType() {
        return formatterType;
    }

    public SimpleDateFormat getCurrentFormat() {
        return dateFormat;
    }

    public void setToday() {
        Date today = new Date();
        startDate = getCurrentFormat().format(today);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_WEEK, 1);

        Date nextDay = calendar.getTime();
        endDate = getCurrentFormat().format(nextDay);
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
