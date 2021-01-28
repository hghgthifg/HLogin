package com.hghgthifg.HLogin.command;

import org.bukkit.command.CommandSender;
import java.util.List;

public interface Command {
    void execute(CommandSender sender,List<String> args);
}
