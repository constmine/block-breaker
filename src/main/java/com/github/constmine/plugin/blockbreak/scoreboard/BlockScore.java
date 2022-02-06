package com.github.constmine.plugin.blockbreak.scoreboard;

import com.github.constmine.plugin.blockbreak.util.cf;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;
import org.jetbrains.annotations.NotNull;

public class BlockScore {

//    private BlockScore() {}
//
//    private static class InnerInstanceClazz {
//        private static final BlockScore instance = new BlockScore();
//    }
//
//    public static BlockScore getInstance() {
//        return InnerInstanceClazz.instance;
//    }

    private Score score;

    boolean setting = false;

    public void onSetting() {
        if (setting == true) {
            return;
        }

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();

        //§cBlock §bBreaker
        TextComponent textComponent = Component.text("Block ", NamedTextColor.AQUA)
                .append(Component.text("Breaker", NamedTextColor.RED));

        Objective objective = board.registerNewObjective("Block Breaker", "dummy", textComponent);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        score = objective.getScore(cf.getPlayer());
        score.setScore(-12);

        showScoreBoard(board);
        setting = true;
    }

    public void showScoreBoard(Scoreboard board) {
        for(Player player : Bukkit.getOnlinePlayers()) {
            player.setScoreboard(board);
        }
    }

    public Score getScore() {
        return score;
    }

    public void initialize() {
        score = null;
        setting = false;
    }
}
