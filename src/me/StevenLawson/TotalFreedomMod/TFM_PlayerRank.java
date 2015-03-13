package me.StevenLawson.TotalFreedomMod;

import me.StevenLawson.TotalFreedomMod.Config.TFM_ConfigEntry;
import static me.StevenLawson.TotalFreedomMod.TFM_Util.DEVELOPERS;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public enum TFM_PlayerRank
{
    EXECUTIVE("an " + ChatColor.YELLOW + "Executive", ChatColor.YELLOW + "(Executive)"),
    CoOwner("the " + ChatColor.BLUE + "Co-Owner", ChatColor.BLUE + "(Co-Owner)"),
    SECURITY("the chief of " + ChatColor.RED + "Security", ChatColor.DARK_RED + "(Chief of Security)"),
    SYS("a " + ChatColor.DARK_RED + "System Admin", ChatColor.DARK_RED + "(Sys-Admin)"),
    CDEV("the " + ChatColor.DARK_PURPLE + "FunFreedomMod Creator", ChatColor.DARK_PURPLE + "(FFM-Creator)"),
    DEVELOPER("a " + ChatColor.DARK_PURPLE + "Developer", ChatColor.DARK_PURPLE + "(Dev)"),
    LDEVELOPER("a " + ChatColor.DARK_PURPLE + "Lead-Developer", ChatColor.DARK_PURPLE + "(Lead-Dev)"),
    FOUNDER("the " + ChatColor.DARK_RED + "Founder", ChatColor.DARK_RED + "(Founder)"),
    IMPOSTOR("an " + ChatColor.YELLOW + ChatColor.UNDERLINE + "Impostor", ChatColor.YELLOW.toString() + ChatColor.UNDERLINE + "(IMP)"),
    NON_OP("a " + ChatColor.GREEN + "Non-OP", ChatColor.GREEN.toString()),
    OP("an " + ChatColor.RED + "OP", ChatColor.RED + "(OP)"),
    SUPER("a " + ChatColor.GOLD + "Super Admin", ChatColor.GOLD + "(SA)"),
    TELNET("a " + ChatColor.DARK_GREEN + "Super Telnet Admin", ChatColor.DARK_GREEN + "(STA)"),
    SENIOR("a " + ChatColor.LIGHT_PURPLE + "Senior Admin", ChatColor.LIGHT_PURPLE + "(SrA)"),
    AGGELOS("a " + ChatColor.YELLOW + "The Chief Web Dev and Executive", ChatColor.YELLOW + "[Exec Dev]"),
    OWNER("the mother fuckin' " + ChatColor.DARK_RED + "Owner!", ChatColor.DARK_RED + "(Owner)"),
    CONSOLE("The " + ChatColor.DARK_PURPLE + "Console", ChatColor.DARK_PURPLE + "(Console)"),
    WEBDEVS("a " + ChatColor.RED + "Web Developer", ChatColor.DARK_RED + "(Web-Dev)"),
    HELPER("a " + ChatColor.RED + "Helper", ChatColor.RED + "(Helper)"),
    MAN("the " + ChatColor.DARK_RED + "Admin Manager", ChatColor.DARK_RED + "(Admin Manager)"),
    SRA("a " + ChatColor.LIGHT_PURPLE + "Senior Helper", ChatColor.LIGHT_PURPLE + "(Senior-Helper)"),
    EXCD("the " + ChatColor.BLUE + "Executive Chief Designer", ChatColor.BLUE + "(Executive Chief)"),
    AC("a " + ChatColor.DARK_BLUE + "Admin trainer", ChatColor.DARK_BLUE + "(Admin Trainer)"),
    LSYS("a " + ChatColor.DARK_RED + "Lead System Admin", ChatColor.DARK_RED + "(Lead Sys-Admin)");
    private String loginMessage;
    private String prefix;

    private TFM_PlayerRank(String loginMessage, String prefix)
    {
        this.loginMessage = loginMessage;
        this.prefix = prefix;
    }

    public static String getLoginMessage(CommandSender sender)
    {
        // Handle console
        if (!(sender instanceof Player))
        {
            return fromSender(sender).getLoginMessage();
        }

        // Handle admins
        final TFM_Admin entry = TFM_AdminList.getEntry((Player) sender);
        if (entry == null)
        {
            // Player is not an admin
            return fromSender(sender).getLoginMessage();
        }

        // Custom login message
        final String loginMessage = entry.getCustomLoginMessage();

        if (loginMessage == null || loginMessage.isEmpty())
        {
            return fromSender(sender).getLoginMessage();
        }

        return ChatColor.translateAlternateColorCodes('&', loginMessage);
    }

    public static TFM_PlayerRank fromSender(CommandSender sender)
    {
        if (!(sender instanceof Player))
        {
            return CONSOLE;
        }

        if (TFM_AdminList.isAdminImpostor((Player) sender))
        {
            return IMPOSTOR;
        }
        if (sender.getName().equals("lynxlps"))
        {
            return EXECUTIVE;
        }
        if (sender.getName().equals("buildcarter8"))
        {
            return CDEV;
        }
        if (sender.getName().equals("cowgomooo12"))
        {
            return SYS;
        }
        if (sender.getName().equals("EnderLolzeh"))
        {
            return SYS;
        }
        if (sender.getName().equals("SupItsDillon"))
        {
            return SECURITY;
        }
        if (sender.getName().equals("y0mama57"))
        {
            return CoOwner;
        }
        if (sender.getName().equals("looperXD"))
        {
            return CDEV;
        }
        if (sender.getName().equals("DF_Crafted"))
        {
            return FOUNDER;
        }
        if (sender.getName().equals("TokyoShadowWolf"))
        {
            return CoOwner;
        }
        if (sender.getName().equals("aggelosQQ"))
        {
            return AGGELOS;
        }
        if (DEVELOPERS.contains(sender.getName()))
        {
            return DEVELOPER;
        }


        final TFM_Admin entry = TFM_AdminList.getEntry((Player) sender);

        final TFM_PlayerRank rank;

        if (entry != null && entry.isActivated())
        {
            if (TFM_ConfigEntry.SERVER_OWNERS.getList().contains(sender.getName()))
            {
                return OWNER;
            }

            if (entry.isSeniorAdmin())
            {
                rank = SENIOR;
            }
            else if (entry.isTelnetAdmin())
            {
                rank = TELNET;
            }
            else
            {
                rank = SUPER;
            }
        }
        else
        {
            if (sender.isOp())
            {
                rank = OP;
            }
            else
            {
                rank = NON_OP;
            }

        }
        return rank;
    }

    public String getPrefix()
    {
        return prefix;
    }

    public String getLoginMessage()
    {
        return loginMessage;
    }
}
