package com.wisdom.mapreduce.mr2_writable;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FlowReducer extends Reducer<Text,FlowBean,Text,FlowBean> {
    FlowBean sumFlow = new FlowBean();
    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
        long sumUpFlow = 0;
        long sumDownFolw = 0;

        for (FlowBean value : values) {
            sumUpFlow += value.getUpFolw();
            sumDownFolw += value.getDownFlow();
        }
        sumFlow.set(sumUpFlow,sumDownFolw);
        context.write(key,sumFlow);

    }
}
