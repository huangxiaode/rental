package edu.dongnao.rental.house.domain;

/**
 * 地区行政区域划分
 */
public enum AreaLevel {
    CITY("city"),
    REGION("region");

    private String value;

    AreaLevel(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static AreaLevel of(String value) {
        for (AreaLevel level : AreaLevel.values()) {
            if (level.getValue().equals(value)) {
                return level;
            }
        }
        throw new IllegalArgumentException();
    }
}
