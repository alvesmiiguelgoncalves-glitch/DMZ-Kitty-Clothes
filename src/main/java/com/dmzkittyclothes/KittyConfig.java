package com.dmzkittyclothes;

import net.minecraftforge.common.ForgeConfigSpec;

/** Config de cliente: config/dmz_kitty_clothes-client.toml. Aplica na hora (recarrega ao salvar o arquivo). */
public final class KittyConfig {

    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.DoubleValue SKIRT_SCALE_X, SKIRT_SCALE_Y, SKIRT_SCALE_Z;
    public static final ForgeConfigSpec.DoubleValue SKIRT_OFFSET_X, SKIRT_OFFSET_Y, SKIRT_OFFSET_Z;
    public static final ForgeConfigSpec.DoubleValue ARM_SCALE_X, ARM_SCALE_Y, ARM_SCALE_Z;

    static {
        ForgeConfigSpec.Builder b = new ForgeConfigSpec.Builder();

        b.comment("Saia da jaqueta (as caixas das pernas). Escala 1.0 = tamanho original.",
                "A escala parte do quadril: Y menor = saia mais curta (sobe a ponta de baixo).").push("skirt");
        SKIRT_SCALE_X = b.comment("Largura").defineInRange("scaleX", 1.0, 0.1, 3.0);
        SKIRT_SCALE_Y = b.comment("Altura").defineInRange("scaleY", 0.8, 0.1, 3.0);
        SKIRT_SCALE_Z = b.comment("Profundidade (frente/tras). Menor = mais espaco pra cauda").defineInRange("scaleZ", 0.85, 0.1, 3.0);
        SKIRT_OFFSET_X = b.comment("Abre (+) ou fecha (-) as duas metades, em pixels").defineInRange("offsetX", 0.0, -8.0, 8.0);
        SKIRT_OFFSET_Y = b.comment("Sobe (-) ou desce (+), em pixels").defineInRange("offsetY", 0.0, -8.0, 8.0);
        SKIRT_OFFSET_Z = b.comment("Move pra frente (-) ou pra tras (+), em pixels").defineInRange("offsetZ", 0.0, -8.0, 8.0);
        b.pop();

        b.comment("Mangas da jaqueta. Escala 1.0 = tamanho original.").push("arms");
        ARM_SCALE_X = b.defineInRange("scaleX", 1.0, 0.1, 3.0);
        ARM_SCALE_Y = b.comment("Comprimento").defineInRange("scaleY", 1.06, 0.1, 3.0);
        ARM_SCALE_Z = b.defineInRange("scaleZ", 1.0, 0.1, 3.0);
        b.pop();

        SPEC = b.build();
    }

    private KittyConfig() {
    }

    private static final java.nio.file.Path FILE =
            net.minecraftforge.fml.loading.FMLPaths.CONFIGDIR.get().resolve(DmzKittyClothes.MOD_ID + "-client.toml");
    private static com.electronwill.nightconfig.core.Config disk;
    private static long lastCheck;
    private static long lastModified = -1;

    /** Le direto do arquivo (recheca a cada ~0,5 s), sem depender do recarregamento automatico do Forge. */
    public static float get(ForgeConfigSpec.DoubleValue v) {
        refresh();
        if (disk != null) {
            Object o = disk.get(v.getPath());
            if (o instanceof Number n) {
                return (float) n.doubleValue();
            }
        }
        try {
            return v.get().floatValue();
        } catch (IllegalStateException e) {
            return v.getDefault().floatValue();
        }
    }

    private static void refresh() {
        long now = System.currentTimeMillis();
        if (now - lastCheck < 500) return;
        lastCheck = now;
        try {
            long m = java.nio.file.Files.getLastModifiedTime(FILE).toMillis();
            if (m == lastModified) return;
            try (com.electronwill.nightconfig.core.file.CommentedFileConfig c =
                         com.electronwill.nightconfig.core.file.CommentedFileConfig.builder(FILE).build()) {
                c.load();
                disk = com.electronwill.nightconfig.core.Config.copy(c);
            }
            lastModified = m;
        } catch (Exception ignored) {
            // arquivo ainda nao existe ou esta sendo gravado: tenta de novo depois
        }
    }
}
