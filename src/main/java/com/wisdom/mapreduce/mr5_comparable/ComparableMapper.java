package com.wisdom.mapreduce.mr5_comparable;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.yarn.webapp.hamlet.HamletSpec;

import java.io.IOException;

public class ComparableMapper extends Mapper<LongWritable, Text,FlowBean,Text> {
    FlowBean flowBean = new FlowBean();
    Text phone = new Text();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] words = value.toString().split("\t");
        phone.set(words[0]);

        long upflow = Long.parseLong(words[1]);
        long downFlow = Long.parseLong(words[2]);
        flowBean.set(upflow,downFlow);

        context.write(flowBean,phone);
    }
}
