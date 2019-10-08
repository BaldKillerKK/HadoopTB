package com.wisdom.mapreduce.mr6_groupingcomparator;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class GroupingReducer extends Reducer<OrderBean, NullWritable,OrderBean, NullWritable> {
    @Override
    protected void reduce(OrderBean key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        Iterator<NullWritable> iterator = values.iterator();

        for (int i = 0; i < 1; i++) {
            if (iterator.hasNext()){
                context.write(key,iterator.next());
            }
        }
    }
}
