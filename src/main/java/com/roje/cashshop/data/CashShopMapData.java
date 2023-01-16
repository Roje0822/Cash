package com.roje.cashshop.data;

import com.github.nicklib.data.utils.Tuple;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CashShopMapData {

    public static Map<Player, String> inventoryClickMap = new HashMap<>();

    public static Map<Player, Tuple<String, Integer>> priceMap = new HashMap<>();

    public static Map<Player, String> priceTypeMap = new HashMap<>();

    public static Map<Player, GuiType> shopTypeMap = new HashMap<>();

    public static Map<ItemStack, List<String>> loreMap = new HashMap<>();



}
