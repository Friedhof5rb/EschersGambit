package me.friedhof.chess.event;

import me.friedhof.chess.Chess;
import me.friedhof.chess.gamerule.ModGamerules;
import me.friedhof.chess.item.ModItems;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class AttackEntityHandler implements AttackEntityCallback {
    private static Item[] items = {ModItems.BLACK_BISHOP,ModItems.BLACK_KING,ModItems.BLACK_KNIGHT,ModItems.BLACK_PAWN, ModItems.BLACK_QUEEN, ModItems.BLACK_TOWER,
            ModItems.WHITE_BISHOP,ModItems.WHITE_KING,ModItems.WHITE_KNIGHT,ModItems.WHITE_PAWN,ModItems.WHITE_QUEEN,ModItems.WHITE_TOWER,
            ModItems.CAPTURE_BLACK_BISHOP,ModItems.CAPTURE_BLACK_KING,ModItems.CAPTURE_BLACK_KNIGHT,ModItems.CAPTURE_BLACK_PAWN,ModItems.CAPTURE_BLACK_QUEEN,ModItems.CAPTURE_BLACK_TOWER,
            ModItems.CAPTURE_WHITE_BISHOP,ModItems.CAPTURE_WHITE_KING,ModItems.CAPTURE_WHITE_KNIGHT,ModItems.CAPTURE_WHITE_PAWN,ModItems.CAPTURE_WHITE_QUEEN,ModItems.CAPTURE_WHITE_TOWER,ModItems.MOVE_HIGHLIGHTER,
            ModItems.SELECTED_WHITE_BISHOP, ModItems.SELECTED_WHITE_KING, ModItems.SELECTED_WHITE_KNIGHT, ModItems.SELECTED_WHITE_PAWN, ModItems.SELECTED_WHITE_QUEEN, ModItems.SELECTED_WHITE_TOWER,
            ModItems.SELECTED_BLACK_BISHOP, ModItems.SELECTED_BLACK_KING, ModItems.SELECTED_BLACK_KNIGHT, ModItems.SELECTED_BLACK_PAWN, ModItems.SELECTED_BLACK_QUEEN, ModItems.SELECTED_BLACK_TOWER,
            ModItems.START_WHITE_PAWN,ModItems.START_BLACK_PAWN,ModItems.START_SELECTED_BLACK_PAWN,ModItems.START_SELECTED_WHITE_PAWN,ModItems.START_CAPTURE_BLACK_PAWN,ModItems.START_CAPTURE_WHITE_PAWN,
    ModItems.CASTLE_WHITE_TOWER, ModItems.CASTLE_BLACK_TOWER, ModItems.CASTLE_WHITE_KING, ModItems.CASTLE_BLACK_KING, ModItems.CASTLE_SWITCH_BLACK_TOWER, ModItems.CASTLE_SWITCH_WHITE_TOWER,
    ModItems.CASTLE_CAPTURE_WHITE_TOWER,ModItems.CASTLE_CAPTURE_BLACK_TOWER,ModItems.CASTLE_CAPTURE_WHITE_KING,ModItems.CASTLE_CAPTURE_BLACK_KING,
    ModItems.CASTLE_SELECTED_WHITE_TOWER, ModItems.CASTLE_SELECTED_BLACK_TOWER, ModItems.CASTLE_SELECTED_WHITE_KING, ModItems.CASTLE_SELECTED_BLACK_KING};

    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {

        if(world.isClient()) {
            return ActionResult.SUCCESS;
        }

        if(entity instanceof ZombieEntity && world.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)){
          if(((ZombieEntity) entity).getHealth() < 1) {
              Item item = ModItems.CHESS_CORE;
              ItemStack stack = new ItemStack(item);
              ItemEntity e = new ItemEntity(world,entity.getX(),entity.getY(),entity.getZ(),stack);
              world.spawnEntity(e);
          }
        }



        if (world.getGameRules().getBoolean(ModGamerules.isChessSurvivalOptimized)) {
            return ActionResult.PASS;
        }
        if(player.getInventory().getMainHandStack().getItem() == ModItems.ROD_OF_REMOVAL){
            return ActionResult.PASS;
        }
        if(entity instanceof ItemFrameEntity){
            ItemFrameEntity e = (ItemFrameEntity) entity;
            if(Chess.arrayContains(items, e.getHeldItemStack().getItem() )){
                return ActionResult.FAIL;
            }
        }
        return ActionResult.PASS;

    }






}
