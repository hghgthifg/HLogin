package com.hghgthifg.HLogin.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class PlayerCommand implements Command{

    @Override
    public void execute(CommandSender sender, List<String> args) {
        if (sender instanceof Player){
            runCommand((Player) sender,args);
        }else{
            sender.sendMessage("只有玩家可以使用此命令");
        }
    }

    protected abstract void runCommand(Player player,List<String> args);

}
