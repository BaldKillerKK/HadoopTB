package com.wisdom.mapreduce.mr1_wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WordCountReducer extends Reducer<Text, IntWritable,Text, IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        IntWritable v = new IntWritable();
        for (IntWritable value : values) {
            sum += value.get();
        }

        v.set(sum);
        context.write(key,v);
    }
}
