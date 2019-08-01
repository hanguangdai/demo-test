package com.test.demo.nio;

import org.junit.Test;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class ChannelTest {

    @Test
    public void TestTransferFrom() throws Exception{
        RandomAccessFile fromFile = new RandomAccessFile("C:\\Users\\Administrator\\Desktop\\a.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("C:\\Users\\Administrator\\Desktop\\b.txt", "rw");
        FileChannel toChannel = toFile.getChannel();

        long position = 0;
        long count = fromChannel.size();

        toChannel.transferFrom(fromChannel, position, count);

    }

    @Test
    public void TestTransferTo() throws Exception{
        RandomAccessFile fromFile = new RandomAccessFile("C:\\Users\\Administrator\\Desktop\\b.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("C:\\Users\\Administrator\\Desktop\\c.txt", "rw");
        FileChannel toChannel = toFile.getChannel();

        long position = 0;
        long count = fromChannel.size();

        fromChannel.transferTo(0, count, toChannel);

    }

    public static void main(String[] args) throws Exception{
        RandomAccessFile randomAccessFile = new RandomAccessFile("C:\\Users\\Administrator\\Desktop\\a.txt", "rw");
        FileChannel readChannel = randomAccessFile.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int hasRemind = readChannel.read(buffer);
        while (hasRemind != -1){
            buffer.flip();
            CharBuffer charBuffer = CharBuffer.allocate(1024);
            Charset charset = Charset.forName("UTF-8");
            CharsetDecoder decoder = charset.newDecoder();
            decoder.decode(buffer, charBuffer, true);
            while (buffer.hasRemaining()){
                System.out.print((char)charBuffer.get());
            }
            buffer.clear();
            hasRemind = readChannel.read(buffer);
        }
    }
}
