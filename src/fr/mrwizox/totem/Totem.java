package fr.mrwizox.totem;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import fr.mrwizox.totem.utils.Task;

public class Totem
{
    public static boolean isCreated;
    public static Location location;
    public static ArrayList<Location> totemblocks;
    public static String currentfac;
    
    static {
        Totem.totemblocks = new ArrayList<Location>();
    }
    
    public static void create() {
        Totem.isCreated = true;
        final Location tmp = Totem.location;
        Block tmpb = tmp.getBlock();
        for (int a = 0; a != 5; ++a) {
            tmpb.setType(Material.QUARTZ_BLOCK);
            Totem.totemblocks.add(tmpb.getLocation());
            tmpb = tmpb.getRelative(BlockFace.UP);
        }
    }
    
    public static void delete() {
        Totem.isCreated = false;
        final Location tmp = Totem.location;
        Block tmpb = tmp.getBlock();
        for (int a = 0; a != 5; ++a) {
            tmpb.setType(Material.AIR);
            Totem.totemblocks.add(tmpb.getLocation());
            tmpb = tmpb.getRelative(BlockFace.UP);
        }
        Totem.currentfac = " ";
        Totem.totemblocks.clear();
    }
    
    public static void cancel() {
        Totem.isCreated = false;
        Task.countdown = false;
    }
    
    public static void destroy() {
        Totem.isCreated = false;
        for (final Location loc : Totem.totemblocks) {
            loc.getBlock().setType(Material.AIR);
        }
        Totem.currentfac = " ";
        Totem.totemblocks.clear();
    }
}
