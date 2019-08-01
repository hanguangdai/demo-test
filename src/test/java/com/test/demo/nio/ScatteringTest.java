package com.test.demo.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class ScatteringTest {

    public static void main(String[] args) throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile("C:\\Users\\Administrator\\Desktop\\a.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        ByteBuffer header = ByteBuffer.allocate(12);
        ByteBuffer body = ByteBuffer.allocate(1024);
        long read = fileChannel.read(new ByteBuffer[]{header, body});
        while (read != -1){
            header.flip();
            body.flip();
            System.out.println(header.position());
            System.out.println(body.position());
            CharBuffer headerChar = CharBuffer.allocate(10);
            CharBuffer bodyChar = CharBuffer.allocate(1024);
            Charset charset = Charset.forName("UTF-8");
            CharsetDecoder decoder = charset.newDecoder();
            decoder.decode(header, headerChar, true);
            decoder.decode(body, bodyChar, true);
            headerChar.flip();
            bodyChar.flip();
            while (headerChar.hasRemaining()){
                System.out.print((char) headerChar.get());
            }
            while (bodyChar.hasRemaining()){
                System.out.print((char)bodyChar.get());
            }
            header.clear();
            body.clear();
            read = fileChannel.read(body);
        }
    }
}
