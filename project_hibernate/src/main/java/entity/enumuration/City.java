package entity.enumuration;

public enum City  {
    TEHRAN,BIG_CITY,OTHER_CITIES, GILAN, ISFAHAN, AZERBAIJAN_EAST, FARS, KHUZESTAN, KHORASAN_RAZAVI, QOM, ALBORZ;

    public static void checkCityType(String city) {
        City[] bigCities = { GILAN, ISFAHAN, AZERBAIJAN_EAST, FARS, KHUZESTAN, QOM, KHORASAN_RAZAVI, ALBORZ };


    }
}
