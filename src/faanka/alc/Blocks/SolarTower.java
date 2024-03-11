package faanka.alc.Blocks;

import arc.Core;
import arc.util.Log;
import arc.util.Strings;
import mindustry.Vars;
import mindustry.gen.Building;
import mindustry.graphics.Pal;
import mindustry.graphics.Drawf;
import mindustry.ui.Bar;
import mindustry.world.blocks.power.PowerGenerator;

public class SolarTower extends PowerGenerator {
    public int maxConnectCount = 0;
    public int searchRange = 0;

    public SolarTower(String name)
    {
        super(name);
        this.buildType = SolarTowerBuild::new;
    }

    @Override
    public void setBars() {
        super.setBars();
        addBar("connected-nodes", entity -> new Bar(() ->
                Core.bundle.format("bar.connectednodes", ((SolarTowerBuild)entity).connectedCount, maxConnectCount),
                () -> Pal.items,
                () -> ((SolarTowerBuild)entity).connectedCount / (float)maxConnectCount
        ));
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid)
    {
        super.drawPlace(x, y, rotation, valid);
        Drawf.dashSquare(Pal.placing, (x + 0.25f * size) * Vars.tilesize, (y + 0.25f * size) * Vars.tilesize, 2 * (searchRange + 0.25f * size) * Vars.tilesize);
    }

    public class SolarTowerBuild extends GeneratorBuild
    {
        public boolean needUpdate = false;
        private float updateTileTimer = 0;
        public int connectedCount = 0;

        @Override
        public void updateTile()
        {
            if(needUpdate)
            {
                productionEfficiency = (float) connectedCount / maxConnectCount;
                needUpdate = false;
            }

            updateTileTimer += 1;

            if(updateTileTimer < 60)
            {
                return;
            }

            for (int dx = -searchRange; dx < searchRange + size; dx++)
            {
                for(int dy = -searchRange; dy < searchRange + size; dy++)
                {
                    Building build = Vars.world.build(tileX() + dx, tileY() + dy);
                    if((build instanceof SolarNode.SolarNodeBuild) && connectedCount < maxConnectCount && ((SolarNode.SolarNodeBuild)build).connectedTowerBuild == null)
                    {
                        connectedCount += 1;
                        SolarNode.SolarNodeBuild validBuild = (SolarNode.SolarNodeBuild)build;
                        validBuild.connectedTowerBuild = this;

                        needUpdate = true;
                    }
                }
            }

            updateTileTimer = 0;
        }

        @Override
        public void drawSelect()
        {
            Drawf.dashSquare(team.color, (tileX() + 0.25f * size) * Vars.tilesize, (tileY() + 0.25f * size) * Vars.tilesize, 2 * (searchRange + 0.25f * size) * Vars.tilesize);
        }
    }
}
