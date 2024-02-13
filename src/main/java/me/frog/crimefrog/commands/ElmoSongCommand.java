package me.frog.crimefrog.commands;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.block.Block;
import org.bukkit.block.NoteBlock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ElmoSongCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        Location loc = player.getLocation();

        Block block = loc.getBlock();
        for(int i = 0; i < 8; i++) {
            block = block.getRelative(0, 0, i);
            block.setType(Material.NOTE_BLOCK);
        }

        NoteBlock note = (NoteBlock) block.getState();
        note.setNote(new Note(0));
        note.play();
        note = (NoteBlock) block.getRelative(0, 0, 1).getState();
        note.setNote(new Note(0));
        note.play();

        return true;
    }

}
