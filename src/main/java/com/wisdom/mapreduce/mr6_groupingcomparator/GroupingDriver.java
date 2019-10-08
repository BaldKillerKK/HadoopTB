package com.wisdom.mapreduce.mr6_groupingcomparator;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class GroupingDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if (args.length != 2 || args == null) {
            System.out.println("please input Path!");
            System.exit(0);
        }

        Configuration conf = new Configuration();
        //1. 获取一个Job实例
        Job job = Job.getInstance(conf);

        //2. 设置我们的类路径（Classpath）
        job.setJarByClass(GroupingDriver.class);

        //3. 设置Mapper和Reducer
        job.setMapperClass(GroupingMapper.class);
        job.setReducerClass(GroupingReducer.class);

        //4. 设置Mapper和Reducer 输出的类型
        job.setMapOutputKeyClass(OrderBean.class);
        job.setMapOutputValueClass(NullWritable.class);

        job.setOutputKeyClass(OrderBean.class);
        job.setOutputValueClass(NullWritable.class);

        job.setGroupingComparatorClass(OrderGroupingComparator.class);

        //5. 设置输入输出数据
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        //6. 提交我们的Job
        boolean b = job.waitForCompletion(true);
        System.exit(b ? 0 : 1);
    }
}
