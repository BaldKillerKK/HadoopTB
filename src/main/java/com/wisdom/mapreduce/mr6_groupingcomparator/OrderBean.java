package com.wisdom.mapreduce.mr6_groupingcomparator;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/*
 *  需求：求出每一个订单中最贵的商品。
 *  1、先对订单号和价格进行二次排序，相等的订单号在按照价格进行排序
 *  2、编写GroupingComparator类，按照订单号进行reduce分组
**/
public class OrderBean implements WritableComparable<OrderBean> {

    private String orderID;
    private String commodityID;
    private double price;

    public OrderBean() {
    }

    @Override
    public String toString() {
        return orderID + "\t" + commodityID + "\t" + price;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getCommodityID() {
        return commodityID;
    }

    public void setCommodityID(String commodityID) {
        this.commodityID = commodityID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int compareTo(OrderBean o) {
        // 先对订单号作比较
        int result = this.orderID.compareTo(o.orderID);

        if(result == 0){
            return Double.compare(o.price,this.price);
        }else{
            return result;
        }
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(orderID);
        out.writeUTF(commodityID);
        out.writeDouble(price);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.orderID = in.readUTF();
        this.commodityID = in.readUTF();
        this.price = in.readDouble();
    }
}
