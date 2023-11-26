package enumuration;

public enum LessonStatus {
    PASS,
    FAIL;
    public void LessonStatusLoop() {
        for (LessonStatus status : LessonStatus.values()) {
            System.out.println(status);
        }
    }

}
