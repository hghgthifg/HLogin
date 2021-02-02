package com.hghgthifg.HLogin.command.login;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LoginCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player){
            Player player = (((Player) commandSender).getPlayer());
            commandSender.sendMessage("Hello,"+player.getName()+'.');
        }else {
            commandSender.sendMessage("只有玩家可以执行该命令");
        }
        return false;
    }
}
