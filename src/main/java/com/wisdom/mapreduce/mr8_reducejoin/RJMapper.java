package com.wisdom.mapreduce.mr8_reducejoin;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class RJMapper extends Mapper<LongWritable, Text, RJBean, NullWritable> {
    RJBean rjBean = new RJBean();
    String fileName;

    /**
     * @param context
     * @return void
     * @explain: setup在每个切片只会执行一次
     */
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        FileSplit split = (FileSplit) context.getInputSplit();
        // 获取文件名 标记是哪个文件
        fileName = split.getPath().getName();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] words = value.toString().split("\t");

        if (fileName.equals("order.txt")) {
            rjBean.setId(words[0]);
            rjBean.setPid(words[1]);
            rjBean.setAmount(Integer.parseInt(words[2]));
            rjBean.setPname("");
        }else{
            rjBean.setPid(words[0]);
            rjBean.setPname(words[1]);
            rjBean.setId("");
            rjBean.setAmount(0);
        }

        context.write(rjBean,NullWritable.get());
    }
}
