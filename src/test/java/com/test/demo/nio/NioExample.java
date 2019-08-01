package com.test.demo.nio;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class NioExample {

    @Test
    public void test1(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.mark());
        byteBuffer.put("ddd".getBytes());
        System.out.println("***************");
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());
        System.out.println("----" + byteBuffer.position());
        System.out.println("**********flip（）********");
        byteBuffer.flip();
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());
        System.out.println("----"+byteBuffer.position());
        System.out.println("**********get（）********");
        System.out.println((char) byteBuffer.get());
        System.out.println((char) byteBuffer.get());
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());
        System.out.println("----" + byteBuffer.position());

        byteBuffer.rewind(); //重置position
        byteBuffer.clear();

        System.out.println("----" + byteBuffer.get());
        System.out.println("----" + byteBuffer.position());

    }

    @Test
    public void test2() throws Exception{
        FileInputStream fis = new FileInputStream("D:\\a.txt");
        FileOutputStream fos = new FileOutputStream("D:\\b.txt");

        FileChannel inChannel = fis.getChannel();
        FileChannel outChanel = fos.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while ((inChannel.read(buffer)) != -1){
            buffer.flip();
            outChanel.write(buffer);
            buffer.clear();
        }

        fis.close();
        fos.close();
        inChannel.close();
        outChanel.close();
    }

    @Test
    public void test3() throws Exception{

        long start = System.currentTimeMillis();

        FileChannel inChanel = FileChannel.open(Paths.get("D:\\a.txt"), StandardOpenOption.READ);
        FileChannel outChanel = FileChannel.open(Paths.get("D:\\b.txt"), StandardOpenOption.WRITE,StandardOpenOption.READ, StandardOpenOption.CREATE);

        MappedByteBuffer byteBuffer = inChanel.map(FileChannel.MapMode.READ_ONLY,0,inChanel.size());
        MappedByteBuffer outMappedBuffer = outChanel.map(FileChannel.MapMode.READ_WRITE,0, inChanel.size());

        byte[] dst = new byte[byteBuffer.limit()];
        byteBuffer.get(dst);
        outMappedBuffer.put(dst);

        inChanel.close();
        outChanel.close();

        long end = System.currentTimeMillis();
        System.out.println("直接缓冲区：" + (end - start));
    }
}
