package com.wisdom.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.security.UserGroupInformation;

import java.io.IOException;

/**
 * @author BaldKillerKK
 * @explain: kerberos认证工具类
 * @return
 * @creed: Talk is cheap,show me the code
 * @date 2019/10/10 9:52
 */
public class KerberosUtil {

    /**
     * @param conf          hadoop Configuration
     * @param domainName    authentication domain name
     * @param userName      user name
     * @param keytabPath    path of keytab file
     * @param krb5Path      path of krb5.conf
     * @return void
     */
    private static void kerberosAuth(Configuration conf, String domainName, String userName, String keytabPath, String krb5Path) {
        System.setProperty("java.security.krb5.conf",krb5Path);
        conf.set("hadoop.security.authentication","Kerberos");
        conf.set("java.security.krb5.relams",domainName);
        UserGroupInformation.setConfiguration(conf);
        try {
            UserGroupInformation.loginUserFromKeytab(userName,keytabPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Kerberos认证
     * @param conf  hadoop configuration
     * @return void
     */
    public static void kerberosAuth(Configuration conf){
        String domainName = ConfigUtil.getProperty("kerberos.domainname");
        String userName = ConfigUtil.getProperty("kerberos.username");
        String keytabPath = ConfigUtil.getProperty("kerberos.keytab");
        String krb5Path = ConfigUtil.getProperty("kerberos.krb5");
        kerberosAuth(conf,domainName,userName,keytabPath,krb5Path);
    }
}
