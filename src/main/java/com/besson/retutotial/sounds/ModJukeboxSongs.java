package com.besson.retutotial.sounds;

import com.besson.retutotial.ReTutorial;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

public interface ModJukeboxSongs {
    public static final RegistryKey<JukeboxSong> TEST = of("test");
    private static RegistryKey<JukeboxSong> of(String id) {
        return RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(ReTutorial.MOD_ID, id));
    }
    private static void register(Registerable<JukeboxSong> registry, RegistryKey<JukeboxSong> key, RegistryEntry.Reference<SoundEvent> soundEvent, int lengthInSeconds, int comparatorOutput) {
        registry.register(key, new JukeboxSong(soundEvent, Text.translatable(Util.createTranslationKey("jukebox_song", key.getValue())), lengthInSeconds, comparatorOutput));
    }
    public static void bootstrap(Registerable<JukeboxSong> registry) {
        register(registry, TEST, ModSoundEvents.MUSIC_DISC_TEST, 247, 15);
        ReTutorial.LOGGER.info("Registered jukebox songs");
    }
}
