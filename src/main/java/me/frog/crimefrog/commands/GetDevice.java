package me.frog.crimefrog.commands;

import me.frog.crimefrog.items.ItemManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetDevice implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only for Players ingame");
            return true;
        }
        Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("HackT1")) {
            player.getInventory().addItem(ItemManager.HackT1);
            player.getInventory().addItem(ItemManager.HackT2);
            player.getInventory().addItem(ItemManager.HackT3);
        }

        return true;
    }
}
