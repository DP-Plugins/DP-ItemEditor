package com.darksoldier1404.die.functions;

import com.darksoldier1404.dppc.utils.ColorUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

import static com.darksoldier1404.die.ItemEditor.prefix;

public class DPIEFunction {

    public static void setItemName(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) return;
        Player p = (Player) sender;
        ItemStack item = p.getInventory().getItemInMainHand();
        if (item == null || item.getType().isAir()) {
            p.sendMessage(prefix + "§cYou must hold an item in your main hand to set its name.");
            return;
        }
        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            p.sendMessage(prefix + "§cThis item cannot have a name.");
            return;
        }
        String name = ColorUtils.applyColor(String.join(" ", Arrays.copyOfRange(args, 1, args.length)));
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        p.sendMessage(prefix + "§aItem name set to: " + name);
    }

    public static void addItemLore(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) return;
        Player p = (Player) sender;
        ItemStack item = p.getInventory().getItemInMainHand();
        if (item == null || item.getType().isAir()) {
            p.sendMessage(prefix + "§cYou must hold an item in your main hand to add lore.");
            return;
        }
        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            p.sendMessage(prefix + "§cThis item cannot have lore.");
            return;
        }
        String lore = ColorUtils.applyColor(String.join(" ", Arrays.copyOfRange(args, 1, args.length)));
        java.util.List<String> loreList = meta.hasLore() && meta.getLore() != null ? new java.util.ArrayList<>(meta.getLore()) : new java.util.ArrayList<>();
        loreList.add(lore);
        meta.setLore(loreList);
        item.setItemMeta(meta);
        p.sendMessage(prefix + "§aItem lore added: " + lore);
    }

    public static void setItemLore(CommandSender sender, String line, String[] args) {
        if (!(sender instanceof Player)) return;
        Player p = (Player) sender;
        ItemStack item = p.getInventory().getItemInMainHand();
        if (item == null || item.getType().isAir()) {
            p.sendMessage(prefix + "§cYou must hold an item in your main hand to set lore.");
            return;
        }
        int lineNumber;
        try {
            lineNumber = Integer.parseInt(line) - 1;
        } catch (NumberFormatException e) {
            p.sendMessage(prefix + "§cInvalid line number.");
            return;
        }
        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            p.sendMessage(prefix + "§cThis item cannot have lore.");
            return;
        }
        String lore = ColorUtils.applyColor(String.join(" ", Arrays.copyOfRange(args, 2, args.length)));
        java.util.List<String> loreList = meta.hasLore() && meta.getLore() != null ? new java.util.ArrayList<>(meta.getLore()) : new java.util.ArrayList<>();
        if (lineNumber < 0 || lineNumber >= loreList.size()) {
            p.sendMessage(prefix + "§cInvalid lore line number.");
            return;
        }
        loreList.set(lineNumber, lore);
        meta.setLore(loreList);
        item.setItemMeta(meta);
        p.sendMessage(prefix + "§aSet lore line " + (lineNumber + 1) + " to: " + lore);
    }

    public static void removeItemLore(CommandSender sender, String line) {
        if (!(sender instanceof Player)) return;
        Player p = (Player) sender;
        ItemStack item = p.getInventory().getItemInMainHand();
        if (item == null || item.getType().isAir()) {
            p.sendMessage(prefix + "§cYou must hold an item in your main hand to remove lore.");
            return;
        }
        int lineNumber;
        try {
            lineNumber = Integer.parseInt(line) - 1;
        } catch (NumberFormatException e) {
            p.sendMessage(prefix + "§cInvalid line number.");
            return;
        }
        ItemMeta meta = item.getItemMeta();
        if (meta == null || !meta.hasLore() || meta.getLore() == null) {
            p.sendMessage(prefix + "§cThis item has no lore to remove.");
            return;
        }
        java.util.List<String> loreList = new java.util.ArrayList<>(meta.getLore());
        if (lineNumber < 0 || lineNumber >= loreList.size()) {
            p.sendMessage(prefix + "§cInvalid lore line number.");
            return;
        }
        loreList.remove(lineNumber);
        meta.setLore(loreList);
        item.setItemMeta(meta);
        p.sendMessage(prefix + "§aRemoved lore line " + (lineNumber + 1) + " from the item.");
    }

    public static void clearItemLore(CommandSender sender) {
        if (!(sender instanceof Player)) return;
        Player p = (Player) sender;
        ItemStack item = p.getInventory().getItemInMainHand();
        if (item == null || item.getType().isAir()) {
            p.sendMessage(prefix + "§cYou must hold an item in your main hand to clear its lore.");
            return;
        }
        ItemMeta meta = item.getItemMeta();
        if (meta == null || !meta.hasLore() || meta.getLore() == null || meta.getLore().isEmpty()) {
            p.sendMessage(prefix + "§cThis item has no lore to clear.");
            return;
        }
        meta.setLore(null);
        item.setItemMeta(meta);
        p.sendMessage(prefix + "§aCleared all lore from the item.");
    }

    public static void setItemType(CommandSender sender, String type) {
        if (!(sender instanceof Player)) return;
        Player p = (Player) sender;
        ItemStack item = p.getInventory().getItemInMainHand();
        if (item == null || item.getType().isAir()) {
            p.sendMessage(prefix + "§cYou must hold an item in your main hand to set its type.");
            return;
        }
        try {
            item.setType(org.bukkit.Material.valueOf(type.toUpperCase()));
            p.sendMessage(prefix + "§aItem type set to: " + type);
        } catch (IllegalArgumentException e) {
            p.sendMessage(prefix + "§cInvalid item type: " + type);
        }
    }

    public static void setCustomModelData(CommandSender sender, String id) {
        if (!(sender instanceof Player)) return;
        Player p = (Player) sender;
        ItemStack item = p.getInventory().getItemInMainHand();
        if (item == null || item.getType().isAir()) {
            p.sendMessage(prefix + "§cYou must hold an item in your main hand to set its custom model data.");
            return;
        }
        try {
            int data = Integer.parseInt(id);
            ItemMeta meta = item.getItemMeta();
            if (meta == null) {
                p.sendMessage(prefix + "§cThis item cannot have custom model data.");
                return;
            }
            meta.setCustomModelData(data);
            item.setItemMeta(meta);
            p.sendMessage(prefix + "§aCustom model data set to: " + data);
        } catch (NumberFormatException e) {
            p.sendMessage(prefix + "§cInvalid custom model data. Please provide a valid number.");
        }
    }

    public static void removeCustomModelData(CommandSender sender) {
        if (!(sender instanceof Player)) return;
        Player p = (Player) sender;
        ItemStack item = p.getInventory().getItemInMainHand();
        if (item == null || item.getType().isAir()) {
            p.sendMessage(prefix + "§cYou must hold an item in your main hand to remove its custom model data.");
            return;
        }
        ItemMeta meta = item.getItemMeta();
        if (meta == null || !meta.hasCustomModelData()) {
            p.sendMessage(prefix + "§cThis item has no custom model data to remove.");
            return;
        }
        meta.setCustomModelData(null);
        item.setItemMeta(meta);
        p.sendMessage(prefix + "§aRemoved custom model data from the item.");
    }

    public static void addEnchantment(CommandSender sender, String enchantmentName, String level) {
        if (!(sender instanceof Player)) return;
        Player p = (Player) sender;
        ItemStack item = p.getInventory().getItemInMainHand();
        if (item == null || item.getType().isAir()) {
            p.sendMessage(prefix + "§cYou must hold an item in your main hand to add an enchantment.");
            return;
        }
        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            p.sendMessage(prefix + "§cThis item cannot have enchantments.");
            return;
        }
        try {
            Enchantment enchantment = Enchantment.getByName(enchantmentName.toUpperCase());
            if (enchantment == null) {
                p.sendMessage(prefix + "§cInvalid enchantment: " + enchantmentName);
                return;
            }
            int enchantmentLevel = Integer.parseInt(level);
            meta.addEnchant(enchantment, enchantmentLevel, true);
            item.setItemMeta(meta);
            p.sendMessage(prefix + "§aAdded enchantment: " + enchantmentName + " Level: " + enchantmentLevel);
        } catch (NumberFormatException e) {
            p.sendMessage(prefix + "§cInvalid level: " + level);
        }
    }

    public static void removeEnchantment(CommandSender sender, String enchantmentName) {
        if (!(sender instanceof Player)) return;
        Player p = (Player) sender;
        ItemStack item = p.getInventory().getItemInMainHand();
        if (item == null || item.getType().isAir()) {
            p.sendMessage(prefix + "§cYou must hold an item in your main hand to remove an enchantment.");
            return;
        }
        ItemMeta meta = item.getItemMeta();
        if (meta == null || !meta.hasEnchants()) {
            p.sendMessage(prefix + "§cThis item has no enchantments to remove.");
            return;
        }
        Enchantment enchantment = Enchantment.getByName(enchantmentName.toUpperCase());
        if (enchantment == null) {
            p.sendMessage(prefix + "§cInvalid enchantment: " + enchantmentName);
            return;
        }
        meta.removeEnchant(enchantment);
        item.setItemMeta(meta);
        p.sendMessage(prefix + "§aRemoved enchantment: " + enchantmentName);
    }

    public static void addFlag(CommandSender sender, String flag) {
        if (!(sender instanceof Player)) return;
        Player p = (Player) sender;
        ItemStack item = p.getInventory().getItemInMainHand();
        if (item == null || item.getType().isAir()) {
            p.sendMessage(prefix + "§cYou must hold an item in your main hand to add a flag.");
            return;
        }
        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            p.sendMessage(prefix + "§cThis item cannot have flags.");
            return;
        }
        try {
            ItemFlag itemFlag = ItemFlag.valueOf(flag.toUpperCase());
            if (meta.hasItemFlag(itemFlag)) {
                p.sendMessage(prefix + "§cThis item already has the flag: " + flag);
                return;
            }
            meta.addItemFlags(itemFlag);
            item.setItemMeta(meta);
            p.sendMessage(prefix + "§aAdded flag: " + flag);
        } catch (IllegalArgumentException e) {
            p.sendMessage(prefix + "§cInvalid flag: " + flag);
        }
    }

    public static boolean hasFlag(ItemStack item, ItemFlag flag) {
        if (item == null || item.hasItemMeta()) return false;
        ItemMeta meta = item.getItemMeta();
        return meta.hasItemFlag(flag);
    }

    public static void removeFlag(CommandSender sender, String flag) {
        if (!(sender instanceof Player)) return;
        Player p = (Player) sender;
        ItemStack item = p.getInventory().getItemInMainHand();
        if (item == null || item.getType().isAir()) {
            p.sendMessage(prefix + "§cYou must hold an item in your main hand to remove a flag.");
            return;
        }
        ItemMeta meta = item.getItemMeta();
        if (meta == null || meta.getItemFlags().isEmpty()) {
            p.sendMessage(prefix + "§cThis item has no flags to remove.");
            return;
        }
        try {
            ItemFlag itemFlag = ItemFlag.valueOf(flag.toUpperCase());
            if (!meta.hasItemFlag(itemFlag)) {
                p.sendMessage(prefix + "§cThis item does not have the flag: " + flag);
                return;
            }
            meta.removeItemFlags(itemFlag);
            item.setItemMeta(meta);
            p.sendMessage(prefix + "§aRemoved flag: " + flag);
        } catch (IllegalArgumentException e) {
            p.sendMessage(prefix + "§cInvalid flag: " + flag);
        }
    }

    public static void setDurability(CommandSender sender, String durability) {
        if (!(sender instanceof Player)) return;
        Player p = (Player) sender;
        ItemStack item = p.getInventory().getItemInMainHand();
        if (item == null || item.getType().isAir()) {
            p.sendMessage(prefix + "§cYou must hold an item in your main hand to set its durability.");
            return;
        }
        try {
            int dur = Integer.parseInt(durability);
            if (dur < 0 || dur > item.getType().getMaxDurability()) {
                p.sendMessage(prefix + "§cInvalid durability value. It must be between 0 and " + item.getType().getMaxDurability() + ".");
                return;
            }
            item.setDurability((short) dur);
            p.sendMessage(prefix + "§aItem durability set to: " + dur);
        } catch (NumberFormatException e) {
            p.sendMessage(prefix + "§cInvalid durability value. Please provide a valid number.");
        }
    }
}
