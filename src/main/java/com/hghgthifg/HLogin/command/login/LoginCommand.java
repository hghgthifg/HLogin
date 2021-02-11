package com.hghgthifg.HLogin.command.login;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Objects;
import java.util.logging.Logger;

public class LoginCommand implements CommandExecutor {
    private final File userDataFolder;
    private final Logger logger;
    private final HashMap<String,Boolean> userLoginState;

    public LoginCommand(File folder, Logger logger, HashMap<String,Boolean> state)
    {
        this.userDataFolder=folder;
        this.logger=logger;
        this.userLoginState=state;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player){
            if (strings.length!=1)
            {
                return false;
            }
            Player player = (((Player) commandSender).getPlayer());

            File userData = new File(userDataFolder, player.getUniqueId().toString());
            try {
                if (userLoginState.get(player.getUniqueId().toString()))
                {
                    commandSender.sendMessage("已经登录");
                    return true;
                }
                //判断是否注册
                if (userData.exists()) {
                    //将输入加密
                    MessageDigest md5 = null;
                    try {
                        md5 = MessageDigest.getInstance("MD5");
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                    assert md5 != null;
                    md5.update(strings[0].getBytes(StandardCharsets.UTF_8));
                    byte[] str = md5.digest();
                    StringBuilder result= new StringBuilder();
                    for (byte b : str) {
                        result.append(Integer.toHexString((0x000000ff & b) | 0xffffff00).substring(6));
                    }

                    //读取数据
                    BufferedReader in = new BufferedReader(new FileReader(userData));
                    String r;
                    r = in.readLine();
                    in.close();

                    //判断密码是否正确
                    if (Objects.equals(result.toString(), r))
                    {
                        logger.info(player.getName()+"登录成功");
                        commandSender.sendMessage("Hello,"+player.getName()+'.');
                        userLoginState.put(player.getUniqueId().toString(),true);
                        return true;
                    }else{
                        logger.info(player.getName()+"输入了错误的密码");
                        commandSender.sendMessage("密码错误");
                        return false;
                    }
                }else
                {
                    commandSender.sendMessage("请先注册");
                    return true;
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }else {
            commandSender.sendMessage("只有玩家可以执行该命令");
        }
        return false;
    }
}
