package ru.buseso.dreamtime.dtplayersettings;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class PlayerSettingsCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command cmd, String l, String[] args) {
        s.sendMessage("§aОткрываем меню Ваших настроек!");
        ((Player)s).openInventory(createInv((Player)s));
        return false;
    }

    private synchronized Inventory createInv(Player p) {
        Inventory inv = Bukkit.createInventory(null, 36, "§9Ваши настройки");

        for(int i = 0; i < 36; i++) {
            if(i == 11) {
                Material mat = Material.BARRIER;
                String name = "§aПолёт в хабе §c{НЕДОСТУПНО}";
                List<String> lore = new ArrayList<>();

                lore.add("§7Возможность переключать флай в хабе.");

                if(p.hasPermission("dreamtime.fly")) {
                    if(p.getAllowFlight()) {
                        mat = Material.FEATHER;
                        name = "§aПолёт в хабе §c[ЖМИ, ЧТОБЫ ВЫКЛЮЧИТЬ]";
                    } else {
                        mat = Material.REDSTONE;
                        name = "§aПолёт в хабе §c[ЖМИ, ЧТОБЫ ВКЛЮЧИТЬ]";
                    }
                } else {
                    lore.add("");
                    lore.add("§0    §cПриобретите ранг §aVIP§c и вы   §0");
                    lore.add("§0    §cсможете летать в хабе!   ");
                    lore.add("");
                }

                ItemStack pero = new ItemStack(mat);
                ItemMeta peroMeta = pero.getItemMeta();
                peroMeta.setDisplayName(name);
                peroMeta.setLore(lore);
                pero.setItemMeta(peroMeta);
                inv.setItem(i, pero);
            } else if(i == 13) {
                ItemStack paper = new ItemStack(Material.PAPER);
                ItemMeta paperMeta = paper.getItemMeta();
                paperMeta.setDisplayName("§9Звания");
                paper.setItemMeta(paperMeta);
                inv.setItem(i, paper);
            } else if(i == 15) {
                ItemStack chest = new ItemStack(Material.CHEST);
                ItemMeta chestMeta = chest.getItemMeta();
                chestMeta.setDisplayName("§6Сокровищницы");
                chest.setItemMeta(chestMeta);
            } else if(i == 20) {
                ItemStack color = new ItemStack(Material.INK_SACK);
                ItemMeta colorMeta = color.getItemMeta();
                colorMeta.setDisplayName("§fЦвет в скорборде");
                color.setItemMeta(colorMeta);
            } else if(i == 22) {
                ItemStack prefix = new ItemStack(Material.MAP);
                ItemMeta pref = prefix.getItemMeta();
                pref.setDisplayName("§fПрефикс");
                prefix.setItemMeta(pref);
            }
        }

        return inv;
    }
}
