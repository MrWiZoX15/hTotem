package fr.mrwizox.totem.utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import fr.mrwizox.totem.MainTotem;
import fr.mrwizox.totem.Totem;

public class Task
{
    public static int a;
    public static boolean countdown;
    
    static {
        Task.countdown = false;
    }
    
    public static void startCountdown() {
        Task.countdown = true;
        Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)MainTotem.getInstance(), (Runnable)new Runnable() {
            private int a = 300;
            
            @Override
            public void run() {
                switch (this.a) {
                    case 300: {
                        Bukkit.broadcastMessage("§7[§3§lTOTEM§7] §6Apparition du §cTOTEM§6 dans §e5 minutes §6! §c/warp totem");
                        break;
                    }
                    case 240: {
                        Bukkit.broadcastMessage("§7[§3§lTOTEM§7] §6Apparition du §cTOTEM§6 dans §e4 minutes §6! §c/warp totem");
                        break;
                    }
                    case 180: {
                        Bukkit.broadcastMessage("§7[§3§lTOTEM§7] §6Apparition du §cTOTEM§6 dans §e3 minutes §6! §c/warp totem");
                        break;
                    }
                    case 120: {
                        Bukkit.broadcastMessage("§7[§3§lTOTEM§7] §6Apparition du §cTOTEM§6 dans §e2 minutes §6! §c/warp totem");
                        break;
                    }
                    case 60: {
                        Bukkit.broadcastMessage("§7[§3§lTOTEM§7] §6Apparition du §cTOTEM§6 dans §e1 minute §6! §c/warp totem");
                        break;
                    }
                    case 30: {
                        Bukkit.broadcastMessage("§7[§3§lTOTEM§7] §6Apparition du §cTOTEM§6 dans §e30 secondes §6!");
                        break;
                    }
                    case 10: {
                        Bukkit.broadcastMessage("§7[§3§lTOTEM§7] §6Apparition du §cTOTEM§6 dans §e10 secondes §6!");
                        break;
                    }
                    case 5: {
                        Bukkit.broadcastMessage("§7[§3§lTOTEM§7] §6Apparition du §cTOTEM§6 dans §e5 secondes §6!");
                        break;
                    }
                    case 4: {
                        Bukkit.broadcastMessage("§7[§3§lTOTEM§7] §6Apparition du §cTOTEM§6 dans §e4 secondes §6!");
                        break;
                    }
                    case 3: {
                        Bukkit.broadcastMessage("§7[§3§lTOTEM§7] §6Apparition du §cTOTEM§6 dans §e3 secondes §6!");
                        break;
                    }
                    case 2: {
                        Bukkit.broadcastMessage("§7[§3§lTOTEM§7] §6Apparition du §cTOTEM§6 dans §e2 secondes §6!");
                        break;
                    }
                    case 1: {
                        Bukkit.broadcastMessage("§7[§3§lTOTEM§7] §6Apparition du §cTOTEM§6 dans §e1 seconde §6!");
                        break;
                    }
                    case 0: {
                        Bukkit.broadcastMessage("§8§m----§7§m----§8§m----§7§m----§r §f§k:!§r §3§lTOTEM §f§k:!§r §8§m----§7§m----§8§m----§7§m----");
                        Bukkit.broadcastMessage(" ");
                        Totem.create();
                        Bukkit.broadcastMessage("§6Le §cTOTEM§6 est g\u00e9n\u00e9r\u00e9 ! Que la meilleur faction gagne !");
                        Bukkit.broadcastMessage(" ");
                        Bukkit.broadcastMessage("§8§m----§7§m----§8§m----§7§m----§r §f§k:!§r §3§lTOTEM §f§k:!§r §8§m----§7§m----§8§m----§7§m----");
                        break;
                    }
                }
                --this.a;
                if (this.a < 0) {
                    Task.countdown = false;
                }
            }
        }, 20L, 20L);
    }
}
