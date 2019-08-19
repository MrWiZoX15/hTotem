package fr.mrwizox.totem;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import fr.mrwizox.totem.cmd.TotemCmd;
import fr.mrwizox.totem.listener.BlockBreak;
import fr.mrwizox.totem.utils.Utils;

public class MainTotem extends JavaPlugin
{
    private static MainTotem instance;
    private static FileConfiguration config;
    private static FileConfiguration stats;
    private static File filestorage;
    public static File filestats;
    
    public void onEnable() {
        MainTotem.instance = this;
        Bukkit.getPluginCommand("totem").setExecutor((CommandExecutor)new TotemCmd());
        Bukkit.getPluginManager().registerEvents((Listener)new BlockBreak(), (Plugin)getInstance());
        this.createConfig();
    }
    
    public void onDisable() {
        MainTotem.instance = null;
        Totem.destroy();
        Bukkit.getConsoleSender().sendMessage("§cTotem: §4OFF");
    }
    
    public static MainTotem getInstance() {
        return MainTotem.instance;
    }
    
    public static FileConfiguration getStats() {
        return MainTotem.stats;
    }
    
    public void createConfig() {
        MainTotem.filestorage = new File(String.valueOf(getInstance().getDataFolder().getPath()) + "/setpoint.yml");
        MainTotem.filestats = new File(String.valueOf(getInstance().getDataFolder().getPath()) + "/stats.yml");
        getInstance().getDataFolder().mkdirs();
        if (!MainTotem.filestats.exists()) {
            try {
                MainTotem.filestats.createNewFile();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!MainTotem.filestorage.exists()) {
            try {
                MainTotem.filestorage.createNewFile();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        MainTotem.config = (FileConfiguration)YamlConfiguration.loadConfiguration(MainTotem.filestorage);
        MainTotem.stats = (FileConfiguration)YamlConfiguration.loadConfiguration(MainTotem.filestats);
        if (MainTotem.config.contains("Location")) {
            Totem.location = Utils.stringToLoc(MainTotem.config.getString("Location"));
        }
    }
    
    public static void setLocation(final Location loc) {
        Totem.location = loc;
        MainTotem.config.set("Location", (Object)Utils.locToString(loc));
        try {
            MainTotem.config.save(MainTotem.filestorage);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
