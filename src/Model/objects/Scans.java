package Model.objects;

public class Scans {
    public String scanname;
    public String date;

    public Scans(String scanname, String date) {
        this.scanname = scanname;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getScanname() {
        return scanname;
    }

    public void setScanname(String scanname) {
        this.scanname = scanname;
    }

    public Scans(String scanname) {
        this.scanname = scanname;
    }

}
