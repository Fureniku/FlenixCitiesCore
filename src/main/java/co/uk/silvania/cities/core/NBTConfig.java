//Again, big thanks to Maxpowa. They wrote pretty much this entire file, and made me happy.
//Full credit to them.

package co.uk.silvania.cities.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import cpw.mods.fml.relauncher.ReflectionHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.chunk.storage.AnvilChunkLoader;
import net.minecraft.world.chunk.storage.IChunkLoader;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraftforge.common.DimensionManager;

public class NBTConfig {

    public static File getConfigFolder() {
        return new File(new File(Minecraft.getMinecraft().mcDataDir, "config"), "flenixcities");
    }
    
    public static File getWorldConfig() {
        return new File(getWorldDir(), "FC_Econ.dat");
    }
    
    public static File getWorldDir() {
        try {
        	World world = DimensionManager.getWorld(0);
        	
            ISaveHandler worldsaver = world.getSaveHandler();
            IChunkLoader loader = worldsaver.getChunkLoader(world.provider);
            File file = ReflectionHelper.<File, AnvilChunkLoader> getPrivateValue(AnvilChunkLoader.class, (AnvilChunkLoader) loader, 3);
            return file.getName().contains("DIM") ? file.getParentFile() : file;
        } catch (Exception e) {
            return null;
        }
    }
    
    public static File createAndGetNBTFile(File f) {
        try {
            CompressedStreamTools.readCompressed(new FileInputStream(f));
        } catch (Exception e) {
            NBTTagCompound cmp = new NBTTagCompound();
            try {
                CompressedStreamTools.writeCompressed(cmp, new FileOutputStream(f));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        return f;
    }
    
    private static boolean injectNBTToFile(NBTTagCompound cmp, File f) {
        try {
            CompressedStreamTools.writeCompressed(cmp, new FileOutputStream(f));
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    
    public static boolean saveConfig(NBTTagCompound cmp, File f) {
        return injectNBTToFile(cmp, f);
    }
    
    public static NBTTagCompound getTagCompoundInFile(File f) {
        try {
            NBTTagCompound cmp = CompressedStreamTools.readCompressed(new FileInputStream(f));
            return cmp;
        } catch (IOException e) {
            NBTTagCompound cmp = new NBTTagCompound();
            try {
                CompressedStreamTools.writeCompressed(cmp, new FileOutputStream(f));
                return getTagCompoundInFile(f);
            } catch (IOException e1) {
                return null;
            }
        }
    }
}