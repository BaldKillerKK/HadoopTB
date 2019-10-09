package com.wisdom.mapreduce.mr7_outputformat;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
/*
*   自定义outputformat步骤
*   1、创建类继承FileOutputFormat，重写getRecordWriter，这个方法要求返回一个RecordWriter
*   2、创建类MyRecordWriter,继承RecordWriter，重写write和close方法
*   3、write方法主要是写出，需要创建FSDataOutputStream用于写出数据到本地或者hdfs
*   4、close方法主要是关闭流
*
*
* */
import java.io.IOException;

public class MyOutputFormat extends FileOutputFormat<LongWritable, Text> {
    @Override
    public RecordWriter<LongWritable, Text> getRecordWriter(TaskAttemptContext job) throws IOException, InterruptedException {
        MyRecordWriter myRecordWriter = new MyRecordWriter();
        myRecordWriter.initialize(job);
        return myRecordWriter;
    }
}
