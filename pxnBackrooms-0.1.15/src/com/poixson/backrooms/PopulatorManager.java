package com.poixson.backrooms;

import com.poixson.commonmc.tools.plotter.BlockPlotter;
import java.util.LinkedList;
import java.util.Random;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.LimitedRegion;
import org.bukkit.generator.WorldInfo;

public class PopulatorManager extends BlockPopulator {

    private BackroomsLevel backroomsLevel; // Add this field

    public PopulatorManager(BackroomsLevel backroomsLevel) {
        this.backroomsLevel = backroomsLevel;
    }

    public void populate(WorldInfo worldInfo, Random rnd, int chunkX, int chunkZ, LimitedRegion region) {
        LinkedList<BlockPlotter> delayed_plotters = new LinkedList<>();
        for (BackroomsPop pop : backroomsLevel.pops) // Access pops through backroomsLevel
            pop.populate(chunkX, chunkZ, region, delayed_plotters);
        if (!delayed_plotters.isEmpty()) {
            for (BlockPlotter plot : delayed_plotters)
                plot.run();
            delayed_plotters.clear();
        }
    }
}
