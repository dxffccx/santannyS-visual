package com.santannys.visual;

import com.santannys.visual.config.SantannysConfig;
import com.santannys.visual.modules.ModuleManager;
import com.santannys.visual.render.SantannysWorldRenderer;
import com.santannys.visual.ui.ClickGui;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class SantannysClient implements ClientModInitializer {
    public static final String MOD_ID = "santannys";
    public static final String NAME = "santannyS visual";

    public static ModuleManager moduleManager;
    public static SantannysConfig config;
    public static ClickGui clickGui;
    public static KeyBinding menuKey;

    @Override
    public void onInitializeClient() {
        config = new SantannysConfig();
        config.load();

        moduleManager = new ModuleManager();
        clickGui = new ClickGui();
        SantannysWorldRenderer.register();

        menuKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.santannys.menu",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                "category.santannys"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (menuKey.wasPressed()) {
                if (client.currentScreen == null) client.setScreen(clickGui);
            }
            moduleManager.onTick();
        });
    }
			}
