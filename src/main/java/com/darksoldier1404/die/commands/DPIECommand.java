package com.darksoldier1404.die.commands;

import com.darksoldier1404.die.functions.DPIEFunction;
import com.darksoldier1404.dppc.builder.command.CommandBuilder;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.Collectors;

import static com.darksoldier1404.die.ItemEditor.prefix;

public class DPIECommand {
    private final CommandBuilder builder = new CommandBuilder(prefix);

    public DPIECommand() {
        builder.addSubCommand("name", "dpie.name", "/dpie name <item display name>", true, (p, args) -> {
            if (args.length >= 2) {
                DPIEFunction.setItemName(p, args);
            } else {
                p.sendMessage(prefix + "Usage: /die name <item display name>");
            }
        });
        builder.addSubCommand("loreadd", "dpie.lore", "/dpie loreadd <item lore>", true, (p, args) -> {
            if (args.length >= 2) {
                DPIEFunction.addItemLore(p, args);
            } else {
                p.sendMessage(prefix + "Usage: /die loreadd <item lore>");
            }
        });
        builder.addSubCommand("loreset", "dpie.lore", "/dpie loreset <index> <item lore>", true, (p, args) -> {
            if (args.length >= 3) {
                String line = args[1];
                if (!line.matches("\\d+")) {
                    p.sendMessage(prefix + "§cInvalid index. Please provide a valid number.");
                    return;
                }
                DPIEFunction.setItemLore(p, line, args);
            } else {
                p.sendMessage(prefix + "Usage: /die loreset <index> <item lore>");
            }
        });
        builder.addSubCommand("loredel", "dpie.lore", "/dpie loredel <index>", true, (p, args) -> {
            if (args.length >= 2) {
                String line = args[1];
                if (!line.matches("\\d+")) {
                    p.sendMessage(prefix + "§cInvalid index. Please provide a valid number.");
                    return;
                }
                DPIEFunction.removeItemLore(p, line);
            } else {
                p.sendMessage(prefix + "Usage: /die loredel <index>");
            }
        });
        builder.addSubCommand("loreclear", "dpie.lore", "/dpie loreclear", true, (p, args) -> {
            DPIEFunction.clearItemLore(p);
        });

        builder.addSubCommand("type", "dpie.type", "/dpie type <item material>", true, (p, args) -> {
            if (args.length >= 2) {
                DPIEFunction.setItemType(p, args[1]);
            } else {
                p.sendMessage(prefix + "Usage: /die type <item material>");
            }
        });

        builder.addSubCommand("custommodeldata", "dpie.custommodeldata", "/dpie custommodeldata <Integer>", true, (p, args) -> {
            if (args.length >= 2) {
                DPIEFunction.setCustomModelData(p, args[1]);
            } else {
                p.sendMessage(prefix + "Usage: /die custommodeldata <Integer>");
            }
        });

        builder.addSubCommand("custommodeldataremove", "dpie.custommodeldataremove", "/dpie custommodeldataremove <Integer>", true, (p, args) -> {
            if (args.length >= 1) {
                DPIEFunction.removeCustomModelData(p);
            } else {
                p.sendMessage(prefix + "Usage: /die custommodeldataremove <Integer>");
            }
        });

        builder.addSubCommand("enchant", "dpie.enchant", "/dpie enchant <enchantment> <level>", true, (p, args) -> {
            if (args.length >= 3) {
                DPIEFunction.addEnchantment(p, args[1], args[2]);
            } else {
                p.sendMessage(prefix + "Usage: /die enchant <enchantment> <level>");
            }
        });

        builder.addSubCommand("enchantremove", "dpie.enchantremove", "/dpie enchantremove <enchantment>", true, (p, args) -> {
            if (args.length >= 2) {
                DPIEFunction.removeEnchantment(p, args[1]);
            } else {
                p.sendMessage(prefix + "Usage: /die enchantremove <enchantment>");
            }
        });

        builder.addSubCommand("addflag", "dpie.addflag", "/dpie addflag <flag>", true, (p, args) -> {
            if (args.length >= 2) {
                DPIEFunction.addFlag(p, args[1]);
            } else {
                p.sendMessage(prefix + "Usage: /die addflag <flag>");
            }
        });

        builder.addSubCommand("removeflag", "dpie.removeflag", "/dpie removeflag <flag>", true, (p, args) -> {
            if (args.length >= 2) {
                DPIEFunction.removeFlag(p, args[1]);
            } else {
                p.sendMessage(prefix + "Usage: /die removeflag <flag>");
            }
        });

        // 내구도
        builder.addSubCommand("durability", "dpie.durability", "/dpie durability <value>", true, (p, args) -> {
            if (args.length >= 2) {
                DPIEFunction.setDurability(p, args[1]);
            } else {
                p.sendMessage(prefix + "Usage: /die durability <value>");
            }
        });

        for (String c : builder.getSubCommandNames()) {
            builder.addTabCompletion(c, (sender, args) -> {
                if (args.length == 2 && args[0].equalsIgnoreCase("name")) {
                    if (sender instanceof Player) {
                        Player p = (Player) sender;
                        ItemStack item = p.getInventory().getItemInMainHand();
                        if (item == null || !item.getType().isAir() || !item.hasItemMeta()) {
                            return null;
                        }
                        return Collections.singletonList(Objects.requireNonNull(item.getItemMeta()).getDisplayName().replace("§", "&"));
                    }
                }
                if (args.length == 3 && args[0].equalsIgnoreCase("loreset")) {
                    if (sender instanceof Player) {
                        Player p = (Player) sender;
                        int currentLine = args[1].matches("\\d+") ? Integer.parseInt(args[1]) : -1;
                        ItemStack item = p.getInventory().getItemInMainHand();
                        if (item == null || item.getType().isAir() || !item.hasItemMeta()) {
                            return null;
                        }
                        if (item.hasItemMeta() && item.getItemMeta().hasLore()) {
                            return item.getItemMeta().getLore().get(currentLine - 1) != null ?
                                    Collections.singletonList(item.getItemMeta().getLore().get(currentLine - 1).replace("§", "&")) : null;
                        }
                    }
                }
                if (args.length == 2 && args[0].equalsIgnoreCase("type")) {
                    return Arrays.stream(org.bukkit.Material.values())
                            .map(material -> material.name().toLowerCase(Locale.ROOT))
                            .collect(Collectors.toList());
                }
                if (args.length == 2 && args[0].equalsIgnoreCase("enchant")) {
                    return Arrays.stream(org.bukkit.enchantments.Enchantment.values())
                            .map(enchantment -> enchantment.getKey().getKey().toLowerCase(Locale.ROOT))
                            .collect(Collectors.toList());
                }
                if (args.length == 2 && args[0].equalsIgnoreCase("enchantremove")) {
                    if (sender instanceof Player) {
                        Player p = (Player) sender;
                        ItemStack item = p.getInventory().getItemInMainHand();
                        if (item == null || item.getType().isAir() || !item.hasItemMeta()) {
                            return null;
                        }
                        return item.getItemMeta().getEnchants().keySet().stream()
                                .map(enchantment -> enchantment.getKey().getKey().toLowerCase(Locale.ROOT))
                                .collect(Collectors.toList());
                    }
                }
                if (args.length == 2 && args[0].equalsIgnoreCase("addflag")) {
                    return Arrays.stream(org.bukkit.inventory.ItemFlag.values())
                            .map(flag -> flag.name().toUpperCase(Locale.ROOT))
                            .collect(Collectors.toList());
                }
                if (args.length == 2 && args[0].equalsIgnoreCase("removeflag")) {
                    if (sender instanceof Player) {
                        Player p = (Player) sender;
                        ItemStack item = p.getInventory().getItemInMainHand();
                        if (item == null || item.getType().isAir() || !item.hasItemMeta()) {
                            return null;
                        }
                        return item.getItemMeta().getItemFlags().stream()
                                .map(flag -> flag.name().toUpperCase(Locale.ROOT))
                                .collect(Collectors.toList());
                    }
                }
                if (args.length == 2 && args[0].equalsIgnoreCase("durability")) {
                    if (sender instanceof Player) {
                        Player p = (Player) sender;
                        ItemStack item = p.getInventory().getItemInMainHand();
                        if (item == null || item.getType().isAir()) {
                            return null;
                        }
                        int maxDurability = item.getType().getMaxDurability();
                        int currentDurability = item.getDurability();
                        return Arrays.asList(String.valueOf(currentDurability), String.valueOf(maxDurability));
                    }
                }
                return null;
            });
        }
    }

    public CommandBuilder getExecutor() {
        return builder;
    }
}
