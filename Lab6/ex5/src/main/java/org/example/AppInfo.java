package org.example;

public class AppInfo {

    private String name;
    private String version;
    private String author;

    public AppInfo(String name, String version, String author) {
        this.name = name;
        this.version = version;
        this.author = author;
    }

    @Override
    public String toString() {
        return "AppInfo{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    // Getters and Setters
}
