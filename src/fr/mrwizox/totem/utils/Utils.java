package fr.mrwizox.totem.utils;

import org.bukkit.*;

public class Utils
{
    public static String locToString(final Location l) {
        final String ret = String.valueOf(l.getWorld().getName()) + "," + l.getBlockX() + "," + l.getBlockY() + "," + l.getBlockZ();
        return ret;
    }
    
    public static Location stringToLoc(final String s) {
        final String[] a = s.split(",");
        final World w = Bukkit.getServer().getWorld(a[0]);
        final float x = Float.parseFloat(a[1]);
        final float y = Float.parseFloat(a[2]);
        final float z = Float.parseFloat(a[3]);
        return new Location(w, (double)x, (double)y, (double)z);
    }
}
