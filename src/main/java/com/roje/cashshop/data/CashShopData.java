package com.roje.cashshop.data;

import com.github.nicklib.data.Config;
import com.roje.cashshop.CashShopPlugin;

public class CashShopData implements CashShopImpl {


    private Config config;
    private String name;

    public CashShopData(String name) {
        this.config = new Config("shop/" + name, CashShopPlugin.getPlugin());
        this.name = name;

    }

    @Override    @Override
    public void deleteShop() {
        config.deleteFile();
    }


    public Boolean isShopExist() {
        return config.isFileExist();
    }


}
