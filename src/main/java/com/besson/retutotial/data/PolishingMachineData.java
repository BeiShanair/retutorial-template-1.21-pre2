package com.besson.retutotial.data;

import com.besson.retutotial.network.BlockPosPayload;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.util.math.BlockPos;

public record PolishingMachineData(BlockPos pos) implements BlockPosPayload {
    // 这个是服务器和客户端之间传输数据的一个数据文件，保存了方块的位置
    // BlockPosPayload是一个接口，来自RebornCore（科技复兴的核心mod）
    public static final PacketCodec<RegistryByteBuf, PolishingMachineData> CODEC =
            PacketCodec.tuple(BlockPos.PACKET_CODEC, PolishingMachineData::pos, PolishingMachineData::new);

}
