package com.tm.cspirit.data;

import com.google.gson.reflect.TypeToken;
import com.tm.cspirit.util.helper.FileHelper;
import com.tm.cspirit.util.helper.TimeHelper;
import net.minecraft.entity.player.PlayerEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DailyPresentDataFile {

    public static Map<UUID, Integer> receivedGiftList;

    public static void init() {
        receivedGiftList = FileHelper.readFileOrCreate("DailyPresentData", new HashMap<>(), new TypeToken<Map<UUID, Integer>>(){});
    }

    public static void clearEntries() {
        receivedGiftList.clear();
        FileHelper.saveToFile("DailyPresentData", receivedGiftList);
    }

    public static boolean hasReceivedPreset(PlayerEntity player) {

        if (receivedGiftList.containsKey(player.getUniqueID())) {

            if (receivedGiftList.get(player.getUniqueID()).equals(TimeHelper.getCurrentDay())) {
                return true;
            }

            else receivedGiftList.replace(player.getUniqueID(), TimeHelper.getCurrentDay());
        }

        receivedGiftList.put(player.getUniqueID(), TimeHelper.getCurrentDay());
        FileHelper.saveToFile("DailyPresentData", receivedGiftList);

        return false;
    }
}