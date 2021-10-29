package www.test.com.medical_system.bean;

public class DoctorTime {
    String timeText;
    int remainingNum;
    int reservedNum;
    int timeValue;

    public int getTimeValue() {
        return timeValue;
    }

    public void setTimeValue(int timeValue) {
        this.timeValue = timeValue;
    }

    @Override
    public String toString() {
        return timeText + "(剩余" + remainingNum + "位)";
    }

    public String getTimeText() {
        return timeText;
    }

    public void setTimeText(String timeText) {
        this.timeText = timeText;
    }

    public int getRemainingNum() {
        return remainingNum;
    }

    public void setRemainingNum(int remainingNum) {
        this.remainingNum = remainingNum;
    }

    public int getReservedNum() {
        return reservedNum;
    }

    public void setReservedNum(int reservedNum) {
        this.reservedNum = reservedNum;
    }
}
