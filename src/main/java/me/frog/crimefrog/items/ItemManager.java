package me.frog.crimefrog.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    public static ItemStack HackT1;
    public static ItemStack HackT2;
    public static ItemStack HackT3;
    public static ItemStack Zwart_Geld;


    public static void init() {
        createT1();
        createT2();
        createT3();
        createMoney();
    }

    private static void createT1() {
        ItemStack item = new ItemStack(Material.ANGLER_POTTERY_SHERD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§rHacking Device T1");
        List<String> lore = new ArrayList<>();
        lore.add("§f- Hacking time 10 seconds");
        lore.add("§f- Instant police alert");
        lore.add("§f- Can only use on ATM");
        lore.add("§cOfficial HelixMT item");
        meta.setLore(lore);
        item.setItemMeta(meta);
        HackT1 = item;
    }

    private static void createT2() {
        ItemStack item = new ItemStack(Material.ARCHER_POTTERY_SHERD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§rHacking Device T2");
        List<String> lore = new ArrayList<>();
        lore.add("§f- Hacking time 10 seconds");
        lore.add("§f- Instant before police alert");
        lore.add("§f- Can only use on ATM");
        lore.add("§cOfficial HelixMT item");
        meta.setLore(lore);
        item.setItemMeta(meta);
        HackT2 = item;
    }

    private static void createT3() {
        ItemStack item = new ItemStack(Material.ARMS_UP_POTTERY_SHERD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§rHacking Device T3");
        List<String> lore = new ArrayList<>();
        lore.add("§f- Hacking time 5 seconds");
        lore.add("§f- Instant before police alert");
        lore.add("§f- Can only use on PoliceSafe");
        lore.add("§cOfficial HelixMT item");
        meta.setLore(lore);
        item.setItemMeta(meta);
        HackT3 = item;

    }
    private static void createMoney() {
        ItemStack item = new ItemStack(Material.GHAST_TEAR, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§f€500");
        List<String> lore = new ArrayList<>();
        lore.add("§cOfficial HelixMT Zwart Geld");
        meta.setLore(lore);
        item.setItemMeta(meta);
        Zwart_Geld = item;

    }
}
/*
§ voor kleuren
 */
