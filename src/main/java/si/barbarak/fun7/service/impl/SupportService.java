package si.barbarak.fun7.service.impl;

import org.springframework.stereotype.Service;
import si.barbarak.fun7.service.ISupportService;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.TimeZone;

@Service
public class SupportService implements ISupportService {

    LocalTime START_TIME = LocalTime.of(9, 0);
    LocalTime END_TIME = LocalTime.of(15, 0);

    private static boolean isWorkDay(Calendar calendar) {
        return !(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
                || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY);
    }

    @Override
    public boolean isEnabled() {
        TimeZone zone = TimeZone.getTimeZone("Europe/Ljubljana");
        Calendar calendar = Calendar.getInstance(zone);
        LocalTime time = LocalTime.now(zone.toZoneId());

        return isWorkDay(calendar) && isTimeInWorkingHours(time);
    }

    private boolean isTimeInWorkingHours(LocalTime time) {
        return time.isBefore(END_TIME) && time.isAfter(START_TIME);
    }
}
