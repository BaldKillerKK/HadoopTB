package com.wisdom.mapreduce.mr2_writable;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
/*
*   实现序列化的步骤：
*   1、implements Writable
*   2、实现父类的write和readFields方法
*   3、子类必须有空参构造
*
* */
public class FlowBean implements Writable {

    private long upFolw;
    private long downFlow;
    private long sumFlow;

    public void set(long upFolw,long downFlow){
        this.upFolw = upFolw;
        this.downFlow = downFlow;
        this.sumFlow = upFolw + downFlow;
    }

    @Override
    public String toString() {
        return  upFolw +
                "\t" + downFlow +
                "\t" + sumFlow;
    }

    public FlowBean() {
    }

    public long getUpFolw() {
        return upFolw;
    }

    public void setUpFolw(long upFolw) {
        this.upFolw = upFolw;
    }

    public long getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(long downFlow) {
        this.downFlow = downFlow;
    }

    public long getSumFlow() {
        return sumFlow;
    }

    public void setSumFlow(long sumFlow) {
        this.sumFlow = sumFlow;
    }

    /*
    *   序列化的顺序必须要要一致，也就是怎么序列化的就要怎么反序列化
    * */
    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(upFolw);
        dataOutput.writeLong(downFlow);
        dataOutput.writeLong(sumFlow);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        upFolw = dataInput.readLong();
        downFlow = dataInput.readLong();
        sumFlow = dataInput.readLong();
    }
}
