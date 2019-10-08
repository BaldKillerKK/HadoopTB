package com.wisdom.hdfs.test;

import com.wisdom.utils.HDFSUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

import java.io.IOException;

public class HdfsTest {
    public static void main(String[] args) {
        Configuration conf = HDFSUtils.initConfiguration();
        try {
            FileSystem fs = FileSystem.get(conf);
            HDFSUtils.mkdir(fs,"/user/hdfstests");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
