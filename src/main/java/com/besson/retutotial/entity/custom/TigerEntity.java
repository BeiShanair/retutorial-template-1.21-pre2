package com.besson.retutotial.entity.custom;

import com.besson.retutotial.entity.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class TigerEntity extends AnimalEntity {
    // 自定义生物实体，继承AnimalEntity
    public TigerEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    // 重写tick方法
    @Override
    public void tick() {
        super.tick();
    }

    // 重写initGoals方法
    // 为生物实体设置目标
    // 数字越小，优先级越高
    @Override
    protected void initGoals() {
        this.goalSelector.add(0,new SwimGoal(this));
        this.goalSelector.add(1,new AnimalMateGoal(this,1.0D));
        this.goalSelector.add(2,new TemptGoal(this,1.25D, Ingredient.ofItems(Items.BEEF),false));
        this.goalSelector.add(3,new FollowParentGoal(this,1.0D));
        this.goalSelector.add(4,new WanderAroundFarGoal(this,1.0D));
        this.goalSelector.add(5,new LookAtEntityGoal(this, PlayerEntity.class,3f));
        this.goalSelector.add(6,new LookAroundGoal(this));
    }
    // 设置生物实体的属性
    // 见原版的EntityAttributes
    public static DefaultAttributeContainer.Builder createTigerAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH,200)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED,0.2f)
                .add(EntityAttributes.GENERIC_ARMOR,0.5f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE,5);
    }

    // 设置生物实体的繁殖物品
    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.BEEF);
    }

    // 设置生物实体的子生物
    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.TIGER.create(world);
    }
}
