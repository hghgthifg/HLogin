package com.hghgthifg.HLogin.command.register;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.logging.Logger;

public class RegisterCommand implements CommandExecutor {
    private final File userDataFolder;
    private final Logger logger;

    /*
     * 构造函数
     */
    public RegisterCommand(File folder,Logger logger)
    {
        this.userDataFolder=folder;
        this.logger=logger;
    }

    /*
     * register指令
     */
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player){
            //判断输入长度
            if (strings.length!=2)
            {
                return false;
            }
            //判断两次输入是否相同
            if (!Objects.equals(strings[0], strings[1]))
            {
                commandSender.sendMessage("输入的密码不同");
                return false;
            }
            Player player = (((Player) commandSender).getPlayer());
            File userData = new File(userDataFolder, player.getUniqueId().toString());
            try {
                if (userData.createNewFile()) {
                    logger.info("为" + player.getName() + "创建了数据文件");

                    //将密码加密
                    MessageDigest md5 = null;
                    try {
                        md5 = MessageDigest.getInstance("MD5");
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                    assert md5 != null;
                    md5.update(strings[0].getBytes(StandardCharsets.UTF_8));
                    byte[] str =md5.digest();
                    StringBuilder result= new StringBuilder();
                    for (byte b : str) {
                        result.append(Integer.toHexString((0x000000ff & b) | 0xffffff00).substring(6));
                    }

                    //写入数据文件
                    OutputStream f = new FileOutputStream(userData);
                    f.write(result.toString().getBytes(StandardCharsets.UTF_8));
                    f.close();

                    return true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            commandSender.sendMessage("只有玩家可以执行该命令");
        }
        return false;
    }
}
