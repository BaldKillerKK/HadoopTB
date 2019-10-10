package com.wisdom.mapreduce.mr8_reducejoin;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class RJReducer extends Reducer<RJBean, NullWritable,RJBean,NullWritable> {
    @Override
    protected void reduce(RJBean key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        Iterator<NullWritable> iterator = values.iterator();
        iterator.next();
        String pName = key.getPname();

        while(iterator.hasNext()){
            iterator.next();
            key.setPname(pName);
            context.write(key,NullWritable.get());
        }
    }
}
