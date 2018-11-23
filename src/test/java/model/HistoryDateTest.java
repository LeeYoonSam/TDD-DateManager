package model;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class HistoryDateTest {

    HistoryDate historyDate;

    @Before
    public void setUp() {
        historyDate = new HistoryDate();
    }

    @Test
    public void defaultFormat() {
        setDefaultFormat();
        assertThat(historyDate.getFormatterType()).isEqualTo(HistoryDate.FormatterType.DEFAULT);
        assertThat(historyDate.getFormatterType()).isNotEqualTo(HistoryDate.FormatterType.YYMMDD);
    }

    @Test
    public void hmsFormat() {
        setYymmddFormat();
        assertThat(historyDate.getFormatterType()).isEqualTo(HistoryDate.FormatterType.YYMMDD);
        assertThat(historyDate.getFormatterType()).isNotEqualTo(HistoryDate.FormatterType.DEFAULT);
    }

    @Test
    public void startDate() {
        setTodayDefault();
        assertThat(historyDate.getStartDate()).isEqualTo(expectedTodayDefaultFormat());

        setTodayYymmdd();
        assertThat(historyDate.getStartDate()).isEqualTo(expectedTodayYymmddFormat());
    }

    @Test
    public void endDate() {
        SimpleDateFormat defaultFormat = new SimpleDateFormat("yyyyMMdd");
        Date now = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DAY_OF_WEEK, 1);

        final Date endDate = calendar.getTime();
        String expectStartDate = defaultFormat.format(endDate);
        System.out.println(expectStartDate);

        setTodayDefault();
        assertThat(historyDate.getEndDate()).isEqualTo(expectStartDate);
    }

    public void setDefaultFormat() {
        historyDate.setDefaultFormat(HistoryDate.FormatterType.DEFAULT);
    }

    public void setYymmddFormat() {
        historyDate.setDefaultFormat(HistoryDate.FormatterType.YYMMDD);
    }

    public void setTodayDefault() {
        setDefaultFormat();
        historyDate.setToday();
    }

    public void setTodayYymmdd() {
        setYymmddFormat();
        historyDate.setToday();
    }

    public String expectedTodayDefaultFormat() {
        SimpleDateFormat defaultFormat = new SimpleDateFormat("yyyyMMdd");
        Date now = new Date();
        String expectStartDate = defaultFormat.format(now);
        System.out.println(expectStartDate);

        return expectStartDate;
    }

    public String expectedTodayYymmddFormat() {
        SimpleDateFormat yymmddFormat = new SimpleDateFormat("yyMMdd");
        Date now = new Date();
        String expectStartDate = yymmddFormat.format(now);
        System.out.println(expectStartDate);

        return expectStartDate;
    }
}
