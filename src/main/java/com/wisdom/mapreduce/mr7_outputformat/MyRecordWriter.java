package com.wisdom.mapreduce.mr7_outputformat;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/*
*   需求：过滤log文件，包含wisdom的字段输出到wisdom.log中，其他的输出到other.log中
*
*
* */
public class MyRecordWriter extends RecordWriter<LongWritable, Text> {
    private FSDataOutputStream atguigu;
    private FSDataOutputStream other;

    /**
     * 初始化方法
     * @param job
     */
    public void initialize(TaskAttemptContext job) throws IOException {
        // 获取输出文件夹
        String outdir = job.getConfiguration().get(FileOutputFormat.OUTDIR);

        FileSystem fileSystem = FileSystem.get(job.getConfiguration());

        // 将流开到输出文件夹里面
        atguigu = fileSystem.create(new Path(outdir + "/wisdom.log"));
        other = fileSystem.create(new Path(outdir + "/other.log"));
    }


    /**
     * 将KV写出，每对KV调用一次
     * @param key
     * @param value
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public void write(LongWritable key, Text value) throws IOException, InterruptedException {
        String out = value.toString() + "\n";
        if (out.contains("wisdom")) {
            atguigu.write(out.getBytes());
        } else {
            other.write(out.getBytes());
        }
    }

    /**
     * 关闭资源
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public void close(TaskAttemptContext context) throws IOException, InterruptedException {
        IOUtils.closeStream(atguigu);
        IOUtils.closeStream(other);
    }
}
