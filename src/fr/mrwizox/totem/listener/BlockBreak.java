package fr.mrwizox.totem.listener;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.meta.FireworkMeta;

import com.massivecraft.factions.entity.MPlayer;

import fr.mrwizox.totem.MainTotem;
import fr.mrwizox.totem.Totem;

public class BlockBreak implements Listener
{
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@EventHandler(priority = EventPriority.HIGHEST)
    public void BBreak(final BlockBreakEvent event) {
        final Player p = event.getPlayer();
        final Block breakedb = event.getBlock();
        final MPlayer fp = MPlayer.get((Object)p);
        final String fname = fp.getFaction().getName();
        if (Totem.totemblocks.contains(breakedb.getLocation())) {
            if (p.getItemInHand().getType().equals((Object)Material.DIAMOND_SWORD)) {
                Totem.totemblocks.remove(breakedb.getLocation());
                if (Totem.totemblocks.size() == 4) {
                    Bukkit.broadcastMessage("§7[§3§lTOTEM§7]§6 Le joueur §e" + p.getName() + "§6 d\u00e9bute la destruction du totem pour la faction §b" + fname + " §6!");
                    Totem.currentfac = fname;
                    event.setCancelled(false);
                    breakedb.setType(Material.AIR);
                }
                else if (Totem.currentfac == fname) {
                    Bukkit.broadcastMessage("§7[§3§lTOTEM§7]§6 Le joueur §e" + p.getName() + "§6 d\u00e9truit un block du totem ! §7" + "(Plus que " + Totem.totemblocks.size() + "§7)");
                    event.setCancelled(false);
                    breakedb.setType(Material.AIR);
                }
                else {
                    Bukkit.broadcastMessage("§7[§3§lTOTEM§7]§6 Le joueur §e" + p.getName() + "§6 bloque la faction §b" + Totem.currentfac + "§6 ! §cTotem remis \u00e0 z\u00e9ro §6!");
                    Totem.destroy();
                    Totem.create();
                    event.setCancelled(true);
                    final Firework f = (Firework)event.getPlayer().getWorld().spawn(event.getPlayer().getLocation(), (Class)Firework.class);
                    final FireworkMeta fm = f.getFireworkMeta();
                    fm.addEffect(FireworkEffect.builder().flicker(false).trail(true).with(FireworkEffect.Type.STAR).withColor(Color.RED).withFade(Color.BLUE).build());
                    fm.setPower(0);
                    f.setFireworkMeta(fm);
                }
                if (Totem.totemblocks.isEmpty()) {
                    final Firework f = (Firework)event.getPlayer().getWorld().spawn(event.getPlayer().getLocation(), (Class)Firework.class);
                    final FireworkMeta fm = f.getFireworkMeta();
                    fm.addEffect(FireworkEffect.builder().flicker(false).trail(true).with(FireworkEffect.Type.BALL_LARGE).withColor(Color.GREEN).withFade(Color.ORANGE).build());
                    fm.setPower(0);
                    f.setFireworkMeta(fm);
                    Bukkit.broadcastMessage("§8§m----§7§m----§8§m----§7§m----§r §f§k:!§r §3§lTOTEM §f§k:!§r §8§m----§7§m----§8§m----§7§m----");
                    Bukkit.broadcastMessage("§6 Le joueur §e" + p.getName() + " §6vient de d\u00e9truire §cle dernier bloc§6 du totem !");
                    Bukkit.broadcastMessage("");
                    Totem.destroy();
                    int current = 0;
                    if (MainTotem.getStats().contains(fname)) {
                        current = MainTotem.getStats().getInt(fname);
                    }
                    ++current;
                    MainTotem.getStats().set(fname, (Object)current);
                    Bukkit.broadcastMessage("§f §6La faction §b" + fname + " §6a d\u00e9j\u00e0 remporter §c" + current + " §6fois le totem !");
                    Bukkit.broadcastMessage("");
                    Bukkit.broadcastMessage("§6 Le joueur §e" + p.getName() + " §6vient de remporter une §ccl\u00e9e Event§6 !");
                    Bukkit.broadcastMessage("");
                    Bukkit.broadcastMessage("§2\u2714 §6Prochain totem ? §f\u25ba §c/planning §2\u2714");
                    Bukkit.broadcastMessage("§8§m----§7§m----§8§m----§7§m----§r §f§k:!§r §3§lTOTEM §f§k:!§r §8§m----§7§m----§8§m----§7§m----");
                    Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "crate give " + p.getName() + " Event 1");
                    try {
                        MainTotem.getStats().save(MainTotem.filestats);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else {
                event.setCancelled(true);
                p.sendMessage("§7[§3§lTOTEM§7] §cVous devez casser le totem avec une §b\u00e9p\u00e9e en diamant§c !");
            }
        }
    }
}
