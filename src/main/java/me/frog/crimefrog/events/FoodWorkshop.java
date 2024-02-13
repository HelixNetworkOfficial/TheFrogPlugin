package me.frog.crimefrog.events;

import me.frog.crimefrog.items.ItemManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.block.Block;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FoodWorkshop implements org.bukkit.event.Listener {
    private JavaPlugin plugin;
    private Map<UUID, Integer> chickenCookingProgress;

    public FoodWorkshop(JavaPlugin plugin) {
        this.plugin = plugin;
        this.chickenCookingProgress = new HashMap<>();
    }

    @EventHandler
    public void onWorkshopInteraction(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        UUID playerId = p.getUniqueId();
        Action action = event.getAction();
        ItemStack itemInHand = p.getInventory().getItemInMainHand();
        ItemStack WheatToRemove = new ItemStack(Material.WHEAT, 16);

        int totalWheat = 0;
        for (ItemStack stack : p.getInventory()) {
            if (stack != null && stack.getType() == Material.WHEAT) {
                totalWheat += stack.getAmount();
            }
        }

        if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {

            Block clickedBlock = event.getClickedBlock();

            if (clickedBlock != null && clickedBlock.getType().equals(Material.CRAFTING_TABLE)) {

                if (itemInHand.getType().equals(Material.WHEAT)) {
                    if (totalWheat > 15) {
                        int currentProgress = chickenCookingProgress.getOrDefault(playerId,  0);
                        if (currentProgress >=  10) {
                            chickenCookingProgress.put(playerId,  0);
                            p.getInventory().removeItem(WheatToRemove);
                            p.getInventory().addItem(new ItemStack(Material.HAY_BLOCK,  1));
                        } else {
                            chickenCookingProgress.put(playerId, currentProgress +  1);
                            if (chickenCookingProgress.get(playerId) == 2) {
                                p.sendMessage("Compacting hay Progress: 20%");
                            }
                            if (chickenCookingProgress.get(playerId) == 4) {
                                p.sendMessage("Compacting hay Progress: 40%");
                            }
                            if (chickenCookingProgress.get(playerId) == 6) {
                                p.sendMessage("Compacting hay Progress: 60%");
                            }
                            if (chickenCookingProgress.get(playerId) == 8) {
                                p.sendMessage("Compacting hay Progress: 80%");
                            }
                            if (chickenCookingProgress.get(playerId) == 10) {
                                p.sendMessage("Compacting hay Progress: 100%");
                            }
                        }
                    } else {
                        p.sendMessage("For Compacting hay you need at least 16 wheat in your inventory");
                    }
                }
            }
        }
    }
}
