package si.barbarak.fun7.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.TimeZone;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SupportServiceTest {

    @InjectMocks
    private SupportService supportService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIsEnabled_true() {
        LocalTime time = LocalTime.of(9, 1);
        try (MockedStatic<LocalTime> mockedLocalDateTime = Mockito.mockStatic(LocalTime.class)) {
            mockedLocalDateTime.when(() -> LocalTime.now(any(ZoneId.class))).thenReturn(time);
            Calendar calendar = mock(Calendar.class);
            try (MockedStatic<Calendar> mockedCalendar = Mockito.mockStatic(Calendar.class)) {
                mockedCalendar.when(() -> Calendar.getInstance(any(TimeZone.class))).thenReturn(calendar);
                when(calendar.get(Calendar.DAY_OF_WEEK)).thenReturn(Calendar.MONDAY);
                assert supportService.isEnabled();
            }
        }
    }

    @Test
    void testIsEnabled_falseHours() {
        LocalTime time = LocalTime.of(16, 0);

        try (MockedStatic<LocalTime> mockedLocalDateTime = Mockito.mockStatic(LocalTime.class)) {
            mockedLocalDateTime.when(() -> LocalTime.now(any(ZoneId.class))).thenReturn(time);

            Calendar calendar = mock(Calendar.class);
            try (MockedStatic<Calendar> mockedCalendar = Mockito.mockStatic(Calendar.class)) {
                mockedCalendar.when(() -> Calendar.getInstance(any(TimeZone.class))).thenReturn(calendar);
                when(calendar.get(Calendar.DAY_OF_WEEK)).thenReturn(Calendar.MONDAY);

                assert !supportService.isEnabled();
            }
        }
    }

    @Test
    void testIsEnabled_falseDays() {
        LocalTime time = LocalTime.of(14, 0);
        try (MockedStatic<LocalTime> mockedLocalDateTime = Mockito.mockStatic(LocalTime.class)) {
            mockedLocalDateTime.when(() -> LocalTime.now(any(ZoneId.class))).thenReturn(time);

            Calendar calendar = mock(Calendar.class);
            try (MockedStatic<Calendar> mockedCalendar = Mockito.mockStatic(Calendar.class)) {
                mockedCalendar.when(() -> Calendar.getInstance(any(TimeZone.class))).thenReturn(calendar);

                when(calendar.get(Calendar.DAY_OF_WEEK)).thenReturn(Calendar.SATURDAY);

                assert !supportService.isEnabled();
            }
        }
    }
}