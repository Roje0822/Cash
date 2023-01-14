package com.roje.cashshop.data;

import com.github.nicklib.data.utils.Tuple;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class CashShopMapData {

    public static Map<Player, String> inventoryClickMap = new HashMap<>();

    public static Map<Player, Tuple<String, Integer>> priceMap = new HashMap<>();



}
