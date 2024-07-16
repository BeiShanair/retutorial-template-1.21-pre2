package com.besson.retutotial.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    // 这个类用于生成玩家面对方块的位置列表
    public static boolean @NotNull [] checkDirection(Entity entity) {
        // 比如说，玩家面对东南方向，那么返回的数组为{true, false, true}
        // 在游戏中，东为X轴正方向，南为Z轴正方向，上为Y轴正方向
        boolean[] data = new boolean[3];
        data[0] = Direction.getLookDirectionForAxis(entity, Direction.Axis.X).equals(Direction.EAST);
        data[1] = Direction.getLookDirectionForAxis(entity, Direction.Axis.Y).equals(Direction.UP);
        data[2] = Direction.getLookDirectionForAxis(entity, Direction.Axis.Z).equals(Direction.SOUTH);
        return data;
    }
    public static @NotNull List<BlockPos> checkPlayerFace(BlockPos pos, Vec3i size, Entity entity) {
        // 根据玩家的位置和方向生成方块的位置列表
        boolean[] data = checkDirection(entity);
        return checkPlayerFace(pos, size, data);
    }
    public static @NotNull List<BlockPos> checkPlayerFace(BlockPos pos, @NotNull Vec3i size, boolean[] data) {
        // 创建空的位置列表
        List<BlockPos> posList = new ArrayList<>();
        // 根据size来判断生成多少个位置
        for (int i = 0; i < size.getX(); i++) {
            for (int j = 0; j < size.getY(); j++) {
                for (int k = 0; k < size.getZ(); k++) {
                    // 利用三元运算符来判断正负，如果为true则为正，否则为负
                    posList.add(pos.east(i * (data[0] ? 1 : -1))
                            .up(j * (data[1] ? 1 : -1))
                            .south(k * (data[2] ? 1 : -1)));
                }
            }
        }
        return posList;
    }
}
