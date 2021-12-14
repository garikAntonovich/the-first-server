package by.iharantanovich.thefirstserver.model;

public class ZippedFile {

    private String fileName;
    private String data;

    public ZippedFile() {
    }

    public ZippedFile(String fileName, String data) {
        this.fileName = fileName;
        this.data = data;
    }

    public String getFileName() {
        return fileName;
    }

    public String getData() {
        return data;
    }
}
