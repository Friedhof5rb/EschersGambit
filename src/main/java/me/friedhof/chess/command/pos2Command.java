package me.friedhof.chess.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import me.friedhof.chess.Chess;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;

public class pos2Command {

    public static void register (CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
        dispatcher.register(CommandManager.literal("chessPos2").executes(pos2Command::run));
    }


    private static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {

        if(!context.getSource().getPlayer().isCreative()){
            context.getSource().getPlayer().sendMessage(new LiteralText("You can only use this Command in Creative."), false);
            return -1;
        }

        PlayerEntity p = context.getSource().getPlayer();
        Chess.pos2.put(p.getUuidAsString(),p.getBlockPos());
        p.sendMessage(new LiteralText("Pos2: "+ p.getBlockPos().getX() + ", " + p.getBlockPos().getY()+ ", " + p.getBlockPos().getZ()),false);
        return 1;
    }



}
