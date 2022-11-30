package it.zs0bye.bettersecurity.bungee.utils;

import it.zs0bye.bettersecurity.bungee.BetterSecurityBungee;
import it.zs0bye.bettersecurity.bungee.imgs.ChatImg;
import it.zs0bye.bettersecurity.bungee.imgs.enums.ImageChar;
import lombok.SneakyThrows;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static String colorize(String message) {
        if(message == null) {
            BetterSecurityBungee.getInstance().getLogger()
                    .log(Level.SEVERE, "There was an error searching for a missing message!");
            return "";
        }
        final Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
        Matcher matcher = pattern.matcher(message);
        while (matcher.find()) {
            final String hexCode = message.substring(matcher.start(), matcher.end());
            final String replaceSharp = hexCode.replace('#', 'x');

            char[] ch = replaceSharp.toCharArray();
            final StringBuilder builder = new StringBuilder();
            for (char c : ch) builder.append("&").append(c);

            message = message.replace(hexCode, builder.toString());
            matcher = pattern.matcher(message);
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }


    @SneakyThrows
    public static void image(final CommandSender sender, final String[] description) {
        final InputStream png = BetterSecurityBungee.getInstance().getResourceAsStream("chest.png");
        if(png == null) return;
        final BufferedImage imageToSend = ImageIO.read(png);
        new ChatImg(imageToSend, 8, ImageChar.BLOCK.getChar()).appendCenteredText(description).send(sender);
    }

    public static void send(final CommandSender sender, final String msg) {
        if (msg.isEmpty()) return;
        sender.sendMessage(TextComponent.fromLegacyText(msg));
    }

}
