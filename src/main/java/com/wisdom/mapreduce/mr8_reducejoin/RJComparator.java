package com.wisdom.mapreduce.mr8_reducejoin;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class RJComparator extends WritableComparator {
    public RJComparator() {
        super(RJBean.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        RJBean ra = (RJBean) a;
        RJBean rb = (RJBean) b;

        return ra.getPid().compareTo(rb.getPid());
    }
}
