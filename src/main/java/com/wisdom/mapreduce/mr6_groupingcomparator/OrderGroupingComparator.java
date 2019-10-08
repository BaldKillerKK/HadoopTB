package com.wisdom.mapreduce.mr6_groupingcomparator;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class OrderGroupingComparator extends WritableComparator {
    protected OrderGroupingComparator(){
        super(OrderBean.class,true);
    }
    @Override
    public int compare(Object a, Object b) {
        OrderBean aBean = (OrderBean) a;
        OrderBean bBean = (OrderBean) b;

        return aBean.getOrderID().compareTo(bBean.getOrderID());
    }
}
