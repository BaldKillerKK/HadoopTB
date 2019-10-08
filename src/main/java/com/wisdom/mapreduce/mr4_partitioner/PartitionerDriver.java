package com.wisdom.mapreduce.mr4_partitioner;

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

public class PartitionerDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if (args.length != 2 || args == null) {
            System.out.println("please input Path!");
            System.exit(0);
        }

        Configuration conf = new Configuration();
        //1. 获取一个Job实例
        Job job = Job.getInstance(conf);

        //2. 设置我们的类路径（Classpath）
        job.setJarByClass(PartitionerDriver.class);

        //3. 设置Mapper和Reducer
        job.setMapperClass(FlowMapper.class);
        job.setReducerClass(FlowReducer.class);

        //4. 设置Mapper和Reducer 输出的类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);

        //5.设置分区类 以及reducetask的数量
        job.setPartitionerClass(MyPartitioner.class);
        job.setNumReduceTasks(5);

        //6. 设置输入输出数据
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        //7. 提交我们的Job
        boolean b = job.waitForCompletion(true);
        System.exit(b ? 0 : 1);
    }
}
