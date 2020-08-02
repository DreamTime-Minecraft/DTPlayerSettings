package ru.buseso.dreamtime.dtplayersettings;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class DTPlayerSettings extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginCommand("playersettings").setExecutor(new PlayerSettingsCmd());
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if(p.getOpenInventory().getTitle().equalsIgnoreCase("§9Настройки")) {
            e.setCancelled(true);
            int slot = e.getSlot();
            p.closeInventory();
            if(slot == 11) {
                if(p.hasPermission("dreamtime.fly")) {
                    if(p.getAllowFlight()) {
                        p.setAllowFlight(false);
                        p.setFlying(false);
                        p.sendMessage("§aФлай выключен!");
                    } else {
                        p.setAllowFlight(true);
                        p.setFlying(true);
                        p.sendMessage("§aФлай включен!");
                    }
                } else {
                    p.sendMessage("§cПриобритите ранг §aVIP§c, чтобы летать в хабе! Ссылка на покупку ранга: §ehttps://dreamtime.su/");
                }
            } else if(slot == 13) {
                p.performCommand("dm open titles");
            } else if(slot == 15) {
                p.performCommand("dm open cases");
            } else if(slot == 20) {
                p.performCommand("dm open color");
            } else if(slot == 22) {
                p.performCommand("dm open prefix");
            }
        }
    }
}
