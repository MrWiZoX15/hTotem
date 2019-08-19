package fr.mrwizox.totem.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import fr.mrwizox.totem.MainTotem;
import fr.mrwizox.totem.Totem;
import fr.mrwizox.totem.utils.Task;

public class TotemCmd implements CommandExecutor, Listener
{
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (cmd.getName().equalsIgnoreCase("totem")) {
            if (args.length == 0) {
                sender.sendMessage("§8§m----§7§m----§8§m----§7§m----§8§m----§7§m----§8§m----§7§m----§8§m----§7§m----");
                sender.sendMessage("§6Commandes : §c/totem §e(Plugin by Hermanos)");
                sender.sendMessage(" ");
                sender.sendMessage("§f§ §6/totem create §f\u2192 §eForce la cr\u00e9ation du totem");
                sender.sendMessage("§f§ §6/totem now §f\u2192 §eCr\u00e9e le totem sans compte \u00e0 rebours");
                sender.sendMessage("§f§ §6/totem setspawn §f\u2192 §eD\u00e9finit le point de spawn du totem");
                sender.sendMessage("§f§ §6/totem delete §f\u2192 §eSupprime et annule le totem en cours");
                sender.sendMessage(" ");
                sender.sendMessage("§8§m----§7§m----§8§m----§7§m----§8§m----§7§m----§8§m----§7§m----§8§m----§7§m----");
            }
            if (args.length == 1) {
                if ("create".equalsIgnoreCase(args[0])) {
                    if (sender.hasPermission("totem.create")) {
                        if (!Totem.isCreated) {
                            Bukkit.getScheduler().cancelTask(Task.a);
                            Task.startCountdown();
                        }
                        else {
                            sender.sendMessage("§7[§3§lTOTEM§7] §cUn totem \u00e0 d\u00e9j\u00e0§\u00e9t\u00e9§cr\u00e9e !");
                        }
                    }
                    else {
                        sender.sendMessage("§7[§3§lTOTEM§7] §cVous n'avez pas la permission pour ex\u00e9cuter cette commande !");
                    }
                }
                else if ("setspawn".equalsIgnoreCase(args[0])) {
                    if (sender.hasPermission("totem.setspawn")) {
                        if (sender instanceof Player) {
                            final Player p = (Player)sender;
                            MainTotem.setLocation(p.getLocation());
                            p.sendMessage("§7[§3§lTOTEM§7] §aLe spawn point du TOTEM a bien \u00e9t\u00e9 d\u00e9finie !");
                        }
                        else {
                            sender.sendMessage("§7[§3§lTOTEM§7] §cVous devez etre connect\u00e9 pour effectuer cette commande !");
                        }
                    }
                    else {
                        sender.sendMessage("§7[§3§lTOTEM§7] §cVous n'avez pas la permission pour ex\u00e9cuter cette commande !");
                    }
                }
                else if ("delete".equalsIgnoreCase(args[0])) {
                    if (sender.hasPermission("totem.delete")) {
                        if (Totem.isCreated) {
                            Bukkit.broadcastMessage("§7[§3§lTOTEM§7] §cANNULATION DU TOTEM");
                            Totem.delete();
                        }
                        else {
                            sender.sendMessage("§7[§3§lTOTEM§7] §cAucun totem en cours");
                        }
                    }
                    else {
                        sender.sendMessage("§7[§3§lTOTEM§7] §cVous n'avez pas la permission pour ex\u00e9cuter cette commande !");
                    }
                }
                else if ("now".equalsIgnoreCase(args[0]) && sender.hasPermission("totem.now")) {
                    if (!Totem.isCreated) {
                        Bukkit.broadcastMessage("§7[§3§lTOTEM§7] §6Le totem vient d'appara\u00eetre !");
                        Totem.create();
                    }
                    else {
                        sender.sendMessage("§7[§3§lTOTEM§7] §cUn totem est d\u00e9j\u00e0 \u00e9t\u00e9 cr\u00e9e");
                    }
                }
                else {
                    sender.sendMessage("§8§m----§7§m----§8§m----§7§m----§8§m----§7§m----§8§m----§7§m----§8§m----§7§m----");
                    sender.sendMessage(" ");
                    sender.sendMessage("§5Un Plugin §cTotem§5 pour §6Hazonia-Network");
                    sender.sendMessage("§5Commandes : §c/totem §d(Plugin by MrWiZoX)");
                    sender.sendMessage(" ");
                    sender.sendMessage("§f§ §5/totem create §f\u2192 §dForce la cr\u00e9ation du totem");
                    sender.sendMessage("§f§ §5/totem now §f\u2192 §dCr\u00e9e le totem sans compte \u00e0 rebours");
                    sender.sendMessage("§f§ §5/totem setspawn §f\u2192 §dD\u00e9finit le point de spawn du totem");
                    sender.sendMessage("§f§ §5/totem delete §f\u2192 §dSupprime et annule le totem en cours");
                    sender.sendMessage(" ");
                    sender.sendMessage("§8§m----§7§m----§8§m----§7§m----§8§m----§7§m----§8§m----§7§m----§8§m----§7§m----");
                }
            }
        }
        return false;
    }
}
