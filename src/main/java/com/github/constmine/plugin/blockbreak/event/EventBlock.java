package com.github.constmine.plugin.blockbreak.event;

import com.github.constmine.plugin.blockbreak.BlockBreak;
import com.github.constmine.plugin.blockbreak.scheduler.RepeatingScheduler;
import com.github.constmine.plugin.blockbreak.scoreboard.BlockScore;
import com.github.constmine.plugin.blockbreak.util.cf;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.scoreboard.Score;

import java.time.Duration;

public class EventBlock implements Listener {
    /*Todo
     * 게임 진행 중 -> 블록이 설치됨 ->
     */
    private final BlockBreak plugin;

    private final BlockScore bs = new BlockScore();

    private int tick_count = 3;

    private RepeatingScheduler scheduler;

    public EventBlock(BlockBreak plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (!cf.isGameStart()) { return; }

        if (event.getPlayer() == cf.getPlayer()) {

            scheduler.cancel();

            if(equalBlock(event.getBlock())) {
                //카운트 증가

                bs.onSetting();
                Score score =  bs.getScore();
                score.setScore(score.getScore() + tick_count);

                tick_count = 3;

            } else {

                for(Player player : Bukkit.getOnlinePlayers()) {
                    player.showTitle(Title.title(
                            Component.text(player.getName()),
                            Component.text(ChatColor.YELLOW + "" + bs.getScore().getScore() ),
                            Title.Times.of(Duration.ofMillis(0), Duration.ofMillis(1000 * 3), Duration.ofMillis(1000))));
                }

                cf.initialize();
                bs.initialize();

                //게임 종료
            }
        }

    }


    //득점 방식 3틱(0.15초) 마다 tick_count를 줄임으로써 점수 획득을 낮춤. (정확한 시간은 잘 모르겠음...)
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if(!cf.isGameStart()) { return; }

        if(equalBlock(event.getBlock())) {
            scheduler = new RepeatingScheduler(plugin, 0L, 1L) {
                int tick = 0;

                @Override
                public void run() {
                    tick++;

                    if (tick == 3) {
                        if(tick_count == 1) {
                            tick_count--;
                        }
                        tick_count--;

                        tick = 0;
                    }
                }
            };
        }
    }

    public boolean equalBlock(Block block) {
        return block.getType() == cf.getBlock();
    }

}
