package com.fdj.nicemallbackend.common.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @Classname BASE64DecodeMultipartFile
 * @Description TODO
 * @Date 19-8-30 下午1:33
 * @Created by xns
 */
public class BASE64DecodeMultipartFile implements MultipartFile {

    private final byte[] imgContent;
    private final String header;

    public BASE64DecodeMultipartFile(byte[] imgContent, String header) {
        this.imgContent = imgContent;
        this.header = header.split(";")[0];
    }

    @Override
    public String getName() {
        return System.currentTimeMillis()+Math.random()+"."+header.split("/")[1];
    }

    @Override
    public String getOriginalFilename() {
        return System.currentTimeMillis()+Math.random()+"."+header.split("/")[1];
    }

    @Override
    public String getContentType() {
        return header.split(";")[1];
    }

    @Override
    public boolean isEmpty() {
        return imgContent == null || imgContent.length == 0;
    }

    @Override
    public long getSize() {
        return imgContent.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return imgContent;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(imgContent);
    }

    @Override
    public void transferTo(File file) throws IOException, IllegalStateException {
        new FileOutputStream(file).write(imgContent);
    }
}
