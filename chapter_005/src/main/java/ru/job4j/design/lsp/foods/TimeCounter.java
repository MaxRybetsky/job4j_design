package ru.job4j.design.lsp.foods;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class TimeCounter {
    /**
     * Control date to count percentage.
     */
    private final LocalDate controlDate;

    public TimeCounter() {
        controlDate = null;
    }

    public TimeCounter(LocalDate controlDate) {
        this.controlDate = controlDate;
    }

    /**
     * Counts percentage of the expiration date to today.
     *
     * @param food Food to check.
     * @return Percentage of the expiration date to today
     */
    public long getExpiredPercentage(Food food) {
        LocalDate today = controlDate == null ? LocalDate.now() : controlDate;
        long currentPeriod = ChronoUnit.DAYS.between(food.getCreateDate(), today);
        long totalPeriod = ChronoUnit.DAYS.between(food.getCreateDate(),
                food.getExpiredDate());
        return currentPeriod * 100L / totalPeriod;
    }
}
