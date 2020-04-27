package com.example.team3.mode;

public class CartItem {
    private String goodsName;
    private double price;
    private int buyNum;

    public CartItem() {}

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setBuyNum(int buyNum) {
        this.buyNum = buyNum;
    }

    public int getBuyNum() {
        return buyNum;
    }
}
