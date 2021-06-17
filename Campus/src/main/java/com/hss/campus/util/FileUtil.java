package com.hss.campus.util;

import com.hss.campus.entity.util.ImageResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileUtil {

    public static List<String> uploadMultiple(String path,@RequestParam MultipartFile[] file) throws InterruptedException {
        //MultipleFileUtil fileUtil=new MultipleFileUtil();
        List<String> list=new ArrayList<>();
        for (MultipartFile multipartFile : file) {
            Thread thread=new Thread(() ->{
                String filename = multipartFile.getOriginalFilename();
                String prefix=filename.substring(filename.lastIndexOf(".")+1);
                String newFileName = "" + new Date().getTime() +"."+ prefix;
                list.add(OtherUtil.IP_ADDRESS+"image/dynamic/"+newFileName);
               // fileUtil.setFileName(list);
                //File files=new File("D:\\Study\\Campus\\Image\\"+path,newFileName);
               File files=new File("C:\\apache-tomcat-9.0.45-windows-x64\\apache-tomcat-9.0.45\\webapps\\image\\"+path,newFileName);
                try {
                    multipartFile.transferTo(files);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
            thread.join();
        }
        return list;
    }

    public static ImageResponse uploadAPicture(String path,MultipartFile file) throws IOException {
        ImageResponse imageHeader=new ImageResponse();
        if (file.getSize()>0){
            String filename = file.getOriginalFilename();
            String prefix=filename.substring(filename.lastIndexOf(".")+1);
            String fileName = "" + new Date().getTime() +"."+ prefix;
            imageHeader.setFileName(fileName);
            File files=new File("D:\\Study\\Campus\\Image\\"+path,fileName);
            //File files=new File("C:\\apache-tomcat-9.0.45-windows-x64\\apache-tomcat-9.0.45\\webapps\\image\\"+path,fileName);
            file.transferTo(files);
        }
        //item.write(new File("C:\\apache-tomcat-9.0.45-windows-x64\\apache-tomcat-9.0.45\\webapps\\image\\"+path+newFileName));
       // item.write(new File("D:\\Study\\Campus\\Image\\"+path+newFileName));
        return imageHeader;
    }

}
