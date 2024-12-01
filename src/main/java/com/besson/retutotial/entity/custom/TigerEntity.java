package com.besson.retutotial.entity.custom;

import com.besson.retutotial.entity.ModEntities;
import com.besson.retutotial.entity.ai.TigerAttackGoal;
import com.besson.retutotial.screen.TestScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class TigerEntity extends AnimalEntity {
    private static final TrackedData<Boolean> ATTACKING =
            DataTracker.registerData(TigerEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    // 设置动画状态
    public static final AnimationState idleAnimationState = new AnimationState();
    public int idleAnimationTimeOut = 0;
    public static final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeOut = 0;
    private void setUpAnimationStates(){
        if (idleAnimationTimeOut <= 0){
            idleAnimationTimeOut = this.random.nextInt(40) + 80;
            idleAnimationState.start(this.age);
        } else {
            --idleAnimationTimeOut;
        }
        if (this.isAttacking() && attackAnimationTimeOut <= 0){
            attackAnimationTimeOut = 40;
            attackAnimationState.start(this.age);
        } else {
            --attackAnimationTimeOut;
        }
        if (!this.isAttacking()){
            attackAnimationState.stop();
        }
    }
    // 自定义生物实体，继承AnimalEntity
    public TigerEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    // 重写tick方法
    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient()){
            setUpAnimationStates();
        }
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

        this.goalSelector.add(1, new TigerAttackGoal(this,1.0D,true));
        this.targetSelector.add(1,new RevengeGoal(this));
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

    @Override
    protected void updateLimbs(float posDelta) {
        float f = this.getPose() == EntityPose.STANDING ? Math.min(posDelta * 6.0f, 1.0f) : 0.0f;
        this.limbAnimator.updateLimbs(f, 0.2f);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(ATTACKING, false);
    }
    public void setAttacking(boolean attacking){
        this.dataTracker.set(ATTACKING,attacking);
    }
    public boolean isAttacking(){
        return this.dataTracker.get(ATTACKING);
    }

//    @Override
//    public ActionResult interactMob(PlayerEntity player, Hand hand) {
//        MinecraftClient.getInstance().execute(() ->
//                MinecraftClient.getInstance().setScreen(new TestScreen(Text.empty(), this)));
//
//        return ActionResult.SUCCESS;
//    }
}
