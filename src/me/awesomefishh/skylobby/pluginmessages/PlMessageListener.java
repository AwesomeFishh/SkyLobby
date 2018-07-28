package me.awesomefishh.skylobby.pluginmessages;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import me.awesomefishh.skylobby.Main;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class PlMessageListener implements PluginMessageListener {

    private Main plugin;

    public PlMessageListener() {
        this.plugin = Main.getInstance();
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {

        if (!channel.equals("BungeeCord")) {
            return;
        }

        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();

        if (subchannel.equals("GetServer")) {
            plugin.setServerName(in.readUTF());
        }
        //
        else if (subchannel.equals("GameState")) {

            short len = in.readShort();
            byte[] msgbytes = new byte[len];
            in.readFully(msgbytes);

            DataInputStream msgin = new DataInputStream(new ByteArrayInputStream(msgbytes));

            try {
                String serverName = msgin.readUTF();
                String gameState = msgin.readUTF();
                plugin.getGameStates().put(serverName, gameState);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //
        else if (subchannel.equals("PlayerData")) {

            short len = in.readShort();
            byte[] msgbytes = new byte[len];
            in.readFully(msgbytes);

            DataInputStream msgin = new DataInputStream(new ByteArrayInputStream(msgbytes));

            try {
                String serverName = msgin.readUTF();
                String playersAlive = msgin.readUTF();
                String playersSpectating = msgin.readUTF();
                String playersDied = msgin.readUTF();
                String maxPlayers = msgin.readUTF();
                plugin.getGameStates().put(serverName, gameState);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
