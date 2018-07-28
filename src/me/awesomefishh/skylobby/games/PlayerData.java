package me.awesomefishh.skylobby.games;

public class PlayerData {

    private String serverName;
    private int playersAlive;
    private int playersSpectating;
    private int playersDied;
    private int maxPlayers;

    public PlayerData(String serverName, int playersAlive, int playersSpectating, int playersDied, int maxPlayers) {
        this.serverName = serverName;
        this.playersAlive = playersAlive;
        this.playersSpectating = playersSpectating;
        this.playersDied = playersDied;
        this.maxPlayers = maxPlayers;
    }

}
