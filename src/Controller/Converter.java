package Controller;

public class Converter {
    public String fioToConvert;
    public String scanToConvert;

    public Converter() {
    }

    public Converter(String fioToConvert, String scanToConvert) {
        this.fioToConvert = fioToConvert;
        this.scanToConvert = scanToConvert;
    }

    public String getFioToConvert() {
        return fioToConvert;
    }

    public void setFioToConvert(String fioToConvert) {
        this.fioToConvert = fioToConvert;
    }

    public String getScanToConvert() {
        return scanToConvert;
    }

    public void setScanToConvert(String scanToConvert) {
        this.scanToConvert = scanToConvert;
    }
}
