package com.wisdom.mapreduce.mr2_writable;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlowMapper extends Mapper<LongWritable, Text,Text,FlowBean> {
    FlowBean flowBean = new FlowBean();
    Text phone = new Text();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] words = value.toString().split("\t");
        phone.set(words[1]);

        long upflow = Long.parseLong(words[words.length - 3]);
        long downFlow = Long.parseLong(words[words.length - 2]);
        flowBean.set(upflow,downFlow);

        context.write(phone,flowBean);
    }
}
