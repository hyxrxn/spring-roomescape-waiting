package roomescape.service.dto;

import java.util.List;
import roomescape.model.ReservationTime;

public class ReservationTimeInfoDto {

    private final List<ReservationTime> bookedTimes;
    private final List<ReservationTime> notBookedTimes;

    public ReservationTimeInfoDto(List<ReservationTime> bookedTimes, List<ReservationTime> allTimes) {
        this.bookedTimes = bookedTimes;
        this.notBookedTimes = allTimes.stream()
                .filter(time -> !bookedTimes.contains(time))
                .toList();
    }

    public List<ReservationTime> getBookedTimes() {
        return bookedTimes;
    }

    public List<ReservationTime> getNotBookedTimes() {
        return notBookedTimes;
    }
}
