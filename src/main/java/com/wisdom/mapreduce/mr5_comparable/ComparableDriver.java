package com.wisdom.mapreduce.mr5_comparable;

import com.wisdom.mapreduce.mr2_writable.FlowBean;
import com.wisdom.mapreduce.mr2_writable.FlowDriver;
import com.wisdom.mapreduce.mr2_writable.FlowMapper;
import com.wisdom.mapreduce.mr2_writable.FlowReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class ComparableDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if (args.length != 2 || args == null) {
            System.out.println("please input Path!");
            System.exit(0);
        }

        Configuration conf = new Configuration();
        //1. 获取一个Job实例
        Job job = Job.getInstance(conf);

        //2. 设置我们的类路径（Classpath）
        job.setJarByClass(ComparableDriver.class);

        //3. 设置Mapper和Reducer
        job.setMapperClass(ComparableMapper.class);
        job.setReducerClass(ComparableReducer.class);

        //4. 设置Mapper和Reducer 输出的类型
        job.setMapOutputKeyClass(com.wisdom.mapreduce.mr5_comparable.FlowBean.class);
        job.setMapOutputValueClass(Text.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);

        job.setPartitionerClass(ComparablePartitioner.class);
        job.setNumReduceTasks(5);

        //5. 设置输入输出数据
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        //6. 提交我们的Job
        boolean b = job.waitForCompletion(true);
        System.exit(b ? 0 : 1);
    }
}
