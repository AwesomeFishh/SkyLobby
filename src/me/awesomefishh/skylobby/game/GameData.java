package me.awesomefishh.skylobby.game;

public class GameData {

    private String serverName, chosenArena, gameState;
    private int playersAlive, playersMax, countdownLeft;

    public GameData(String serverName, String chosenArena, String gameState, int playersAlive, int playersMax, int countdownLeft) {
        this.serverName = serverName;
        this.chosenArena = chosenArena;
        this.gameState = gameState;
        this.playersAlive = playersAlive;
        this.playersMax = playersMax;
        this.countdownLeft = countdownLeft;
    }


    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getChosenArena() {
        return chosenArena;
    }

    public void setChosenArena(String chosenArena) {
        this.chosenArena = chosenArena;
    }

    public String getGameState() {
        return gameState;
    }

    public void setGameState(String gameState) {
        this.gameState = gameState;
    }

    public int getPlayersAlive() {
        return playersAlive;
    }

    public void setPlayersAlive(int playersAlive) {
        this.playersAlive = playersAlive;
    }

    public int getPlayersMax() {
        return playersMax;
    }

    public void setPlayersMax(int playersMax) {
        this.playersMax = playersMax;
    }

    public int getCountdownLeft() {
        return countdownLeft;
    }

    public void setCountdownLeft(int countdownLeft) {
        this.countdownLeft = countdownLeft;
    }
}
