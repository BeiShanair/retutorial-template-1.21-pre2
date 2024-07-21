package com.besson.retutotial.sounds;

import com.besson.retutotial.ReTutorial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSoundEvents {
    // 注册声音事件
    public static final SoundEvent PROSPECTOR_FOUND_ORE = register("prospector_found_ore");

    // 同样的，我们可以注册更多的声音事件，用于组成声音组，给方块使用
    // 不过要注意，声音组的声音事件是有顺序的，分别是破坏、踩踏、放置、击打、掉落
    public static final SoundEvent BLOCK_BREAK = register("block_break");
    public static final SoundEvent BLOCK_STEP = register("block_step");
    public static final SoundEvent BLOCK_PLACE = register("block_place");
    public static final SoundEvent BLOCK_HIT = register("block_hit");
    public static final SoundEvent BLOCK_FALL = register("block_fall");

    public static final BlockSoundGroup BLOCK_SOUND_GROUP = new BlockSoundGroup(1.0F, 1.0F,
            BLOCK_BREAK, BLOCK_STEP, BLOCK_PLACE, BLOCK_HIT, BLOCK_FALL);
    // 由原版改编的注册方法
    // 原版中有多种声音事件，这个注册方法是最普通的
    // 像特定生物群系的声音事件，是由另外的方法注册的（山羊角发出的声音也是单独的）
    private static SoundEvent register(String name) {
        Identifier id = Identifier.of(ReTutorial.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }
}
