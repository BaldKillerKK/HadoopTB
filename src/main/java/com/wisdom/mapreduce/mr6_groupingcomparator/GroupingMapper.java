package com.wisdom.mapreduce.mr6_groupingcomparator;


import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class GroupingMapper extends Mapper<LongWritable, Text,OrderBean, NullWritable> {

    OrderBean orderBean = new OrderBean();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] words = value.toString().split("\t");
        orderBean.setOrderID(words[0]);
        orderBean.setCommodityID(words[1]);
        orderBean.setPrice(Double.parseDouble(words[2]));

        context.write(orderBean,NullWritable.get());
    }
}
