package com.zenika.file;

public class UserFile {

    private final String fileName;
    private final byte[] content;
    private final String userName;

    public UserFile(String fileName, byte[] content, String userName) {
        this.fileName = fileName;
        this.content = content;
        this.userName = userName;
    }

    public byte[] getContent() {
        return content;
    }

    public String getPath() {
        return userName + "/files/" + fileName;
    }
}
