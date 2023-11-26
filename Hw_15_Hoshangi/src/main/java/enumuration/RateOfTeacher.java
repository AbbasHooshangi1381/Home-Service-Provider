package enumuration;

public enum RateOfTeacher {
     doctor , coTeacher;

    public void iterateRateOfTeacherEnum() {
        for (RateOfTeacher rate : RateOfTeacher.values()) {
            System.out.println(rate);
        }
    }
}