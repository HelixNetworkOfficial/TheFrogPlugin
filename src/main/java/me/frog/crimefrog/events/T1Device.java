package me.frog.crimefrog.events;

import me.frog.crimefrog.items.ItemManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.event.player.PlayerDropItemEvent;
import java.util.Timer;
import java.util.TimerTask;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class T1Device implements Listener {

    private JavaPlugin plugin; // Add a private field to hold the plugin instance

    public T1Device(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    private Map<UUID, BukkitTask> playerCooldownTasks = new HashMap<>();
    private boolean onCooldown = false;
    private boolean active = false;
    private UUID hackActivatorUUID;


    @EventHandler
    public void PlayerUseT1(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        UUID playerId = p.getUniqueId();
        Action action = event.getAction();
        ItemStack itemInHand = p.getInventory().getItemInMainHand();

        int cooldown = 0;

        if (p.getInventory().getItemInMainHand().getType().equals(Material.ANGLER_POTTERY_SHERD)) {
            if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
                if (event.getClickedBlock().getType().equals(Material.RED_GLAZED_TERRACOTTA)) {
                    // Only lets the player do it again if count is equal to 0
                    if (!onCooldown) {
                        active = true;
                        hackActivatorUUID = p.getUniqueId();
                        p.performCommand("112 Robbing a ATM with a T1 device");
                        p.sendMessage("you are now frozen for 10 seconds to complete the hack");
                        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 30));
                        p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 200, 128));
                        FileConfiguration config = plugin.getConfig();
                        int amount = plugin.getConfig().getInt("HackT1.amount");
                        for (int i = 0; i < amount; i++) {
                            p.getInventory().addItem(ItemManager.Zwart_Geld);
                        }
                        p.getInventory().removeItem(ItemManager.HackT1);
                        // Sets the count to 36000 when the event is triggered
                        cooldown = 1800;
                    } else {
                        p.sendMessage("This can only done once every 30 minutes in the server");
                    }
                }
            }
        }
        if (p.getInventory().getItemInMainHand().getType().equals(Material.ARCHER_POTTERY_SHERD)) {
            if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
                if (event.getClickedBlock().getType().equals(Material.RED_GLAZED_TERRACOTTA)) {
                    if (!onCooldown) {
                        active = true;
                        p.performCommand("112 Robbing a ATM with a T2 device");
                        p.sendMessage("you are now frozen for 10 seconds to complete the hack");
                        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 30));
                        p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 200, 128));
                        FileConfiguration config = plugin.getConfig();
                        int amount = plugin.getConfig().getInt("HackT2.amount");
                        for (int i = 0; i < amount; i++) {
                            p.getInventory().addItem(ItemManager.Zwart_Geld);
                        }
                        p.getInventory().removeItem(ItemManager.HackT2);
                        cooldown = 1800;
                    } else {
                        p.sendMessage("This can only done once every 30 minutes in the server");
                    }
                }
            }
        }
        if (p.getInventory().getItemInMainHand().getType().equals(Material.ARMS_UP_POTTERY_SHERD)) {
            if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
                if (event.getClickedBlock().getType().equals(Material.BLUE_GLAZED_TERRACOTTA)) {
                    if (!onCooldown) {
                        active = true;
                        p.performCommand("112 Robbing a Police Vault in the Police building");
                        p.sendMessage("you are now frozen for 5 seconds to complete the hack");
                        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 30));
                        p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100, 128));
                        FileConfiguration config = plugin.getConfig();
                        int amount = plugin.getConfig().getInt("HackT3.amount");
                        for (int i = 0; i < amount; i++) {
                            p.getInventory().addItem(ItemManager.Zwart_Geld);
                        }
                        p.getInventory().removeItem(ItemManager.HackT3);
                        cooldown = 1800;
                    } else {
                        p.sendMessage("This can only done once every 30 minutes in the server");
                    }
                }
            }
        }

        if (cooldown > 0) {
            onCooldown = true;
            BukkitTask cooldownTask = new BukkitRunnable() {
                @Override
                public void run() {
                    playerCooldownTasks.remove(playerId);
                    onCooldown = false;
                }
            }.runTaskLater(plugin, 20L * cooldown); // Run every tick (20 ticks =  1 second)
            playerCooldownTasks.put(playerId, cooldownTask);
        }
    }
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        Player droppingPlayer = event.getPlayer();
        if (active && droppingPlayer.getUniqueId().equals(hackActivatorUUID)) {
            event.setCancelled(true);

            // Create a new Timer object
            Timer timer = new Timer();

            // Define a TimerTask that sets active to false
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    active = false;
                    hackActivatorUUID = null; // Reset the UUID after the hack has been completed
                }
            };

            // Schedule the task to run after  10 seconds
            timer.schedule(task,  10 *  1000); //  10 seconds in milliseconds
        }
    }
}
