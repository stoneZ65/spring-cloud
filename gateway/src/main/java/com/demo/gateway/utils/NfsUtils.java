package com.demo.gateway.utils;

import com.emc.ecs.nfsclient.nfs.NfsCreateMode;
import com.emc.ecs.nfsclient.nfs.NfsSetAttributes;
import com.emc.ecs.nfsclient.nfs.io.Nfs3File;
import com.emc.ecs.nfsclient.nfs.io.NfsFileInputStream;
import com.emc.ecs.nfsclient.nfs.io.NfsFileOutputStream;
import com.emc.ecs.nfsclient.nfs.nfs3.Nfs3;
import com.emc.ecs.nfsclient.rpc.CredentialUnix;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @description: nsf 文件上传工具类
 * @author: zhanglei
 * @date: 2022/7/4 11:17
 **/
@Slf4j
public class NfsUtils {

    private final static String NFS_IP = "";
    private final static String NFS_DIR = "";

    public static boolean upload(String path, String fileName, byte[] content) {
        NfsFileOutputStream outputStream = null;
        NfsSetAttributes nfsSetAttr = new NfsSetAttributes();
        nfsSetAttr.setMode((long) (0x00100 + 0x00080 + 0x00040 + 0x00020 + 0x00010 + 0x00008 + 0x00004 + 0x00002));
        try {
            Nfs3 nfs3 = new Nfs3(NFS_IP, NFS_DIR, new CredentialUnix(-2, -2, null), 3);
            String paths[] = path.substring(1).split("/");//去掉第一个/之后进行分割处理

            StringBuilder p = new StringBuilder();

            //首先判断目录是否存在，如果不存在则进行创建目录
            for (String s : paths) {
                p.append("/").append(s);
                Nfs3File filePath = new Nfs3File(nfs3, p.toString());
                if (!filePath.exists()) {
                    filePath.mkdir(nfsSetAttr);
                }
            }

            //创建文件
            Nfs3File desFile = new Nfs3File(nfs3, path + "/" + fileName);
            desFile.create(NfsCreateMode.GUARDED, nfsSetAttr, null);

            outputStream = new NfsFileOutputStream(desFile);
            outputStream.write(content);
            return true;
        } catch (IOException ex) {
            log.error(NfsUtils.class.getName(), ex);
        } finally {
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException ex) {
                    log.error(NfsUtils.class.getName(), ex);
                }
            }
        }
        return false;
    }


    /**
     * 文件下载
     *
     * @param filePath NFS上面的文件路径信息
     * @return
     */
    public static byte[] download(String filePath) {
        ByteArrayOutputStream bos = null;
        NfsFileInputStream inputStream = null;
        BufferedInputStream bis = null;
        try {
            Nfs3 nfs3 = new Nfs3(NFS_IP, NFS_DIR, new CredentialUnix(-2, -2, null), 3);
            Nfs3File file = new Nfs3File(nfs3, filePath);

            inputStream = new NfsFileInputStream(file);

            bis = new BufferedInputStream(inputStream);
            bos = new ByteArrayOutputStream();

            int date = -1;
            while ((date = bis.read()) != -1) {
                bos.write(date);
            }
            return bos.toByteArray();
        } catch (IOException ex) {
            log.error(NfsUtils.class.getName() + "_download", ex);
        } finally {
            if (null != bos) {
                try {
                    bos.close();
                } catch (IOException ex) {
                    log.error(NfsUtils.class.getName() + "_download", ex);
                }
            }
            if (null != bis) {
                try {
                    bis.close();
                } catch (IOException ex) {
                    log.error(NfsUtils.class.getName() + "_download", ex);
                }
            }
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException ex) {
                    log.error(NfsUtils.class.getName() + "_download", ex);
                }
            }
        }
        return null;
    }
}
