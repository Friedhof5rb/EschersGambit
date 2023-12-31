package me.friedhof.chess.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import me.friedhof.chess.Chess;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;

import java.util.ArrayList;

public class printPool {
    public static void register (CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
        dispatcher.register(CommandManager.literal("printPool").executes(printPool::run));
    }


    private static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {

        if(!context.getSource().getPlayer().isCreative()){
            context.getSource().getPlayer().sendMessage(new LiteralText("You can only use this Command in Creative."), false);
            return -1;
        }

        if(Chess.pool.containsKey(context.getSource().getPlayer().getUuidAsString())) {
            ArrayList<ItemStack> list = Chess.pool.get(context.getSource().getPlayer().getUuidAsString());
            if (list.isEmpty()) {
                context.getSource().getPlayer().sendMessage(new LiteralText("The Pool is Empty"), false);
                return -1;
            }
            Chess.printPool(context.getSource().getPlayer());
        }else{
            context.getSource().getPlayer().sendMessage(new LiteralText("The Pool is Empty"), false);
            return -1;
        }
        return 1;
    }


}
