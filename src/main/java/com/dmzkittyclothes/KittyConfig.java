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

    /** Le com seguranca: se a config ainda nao carregou, usa o padrao. */
    public static float get(ForgeConfigSpec.DoubleValue v) {
        try {
            return v.get().floatValue();
        } catch (IllegalStateException e) {
            return v.getDefault().floatValue();
        }
    }
}
