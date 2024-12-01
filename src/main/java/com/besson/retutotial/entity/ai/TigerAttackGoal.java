package com.besson.retutotial.entity.ai;

@Deprecated
public class TigerAttackGoal { // extends MeleeAttackGoal
//    private final TigerEntity entity;
//    private int attackDelay = 20;
//    private int tickUntilNextAttack = 20;
//    private boolean shouldCountTillNextAttack = false;
//    public TigerAttackGoal(PathAwareEntity mob, double speed, boolean pauseWhenMobIdle) {
//        super(mob, speed, pauseWhenMobIdle);
//        entity = ((TigerEntity) mob);
//    }
//
//    @Override
//    public void start() {
//        super.start();
//        attackDelay = 20;
//        tickUntilNextAttack = 20;
//    }
//
//    @Override
//    public void stop() {
//        super.stop();
//        entity.setAttacking(false);
//    }
//
//    @Override
//    public void tick() {
//        super.tick();
//        if (shouldCountTillNextAttack){
//            this.tickUntilNextAttack = Math.max(this.tickUntilNextAttack - 1, 0);
//        }
//    }
//
//    @Override
//    protected void attack(LivingEntity target) {
//        if (isEnemyWithinAttackDistance(target)) {
//            shouldCountTillNextAttack = true;
//
//            if(isTimeToStartAttackAnimation()) {
//                entity.setAttacking(true);
//            }
//
//            if(isTimeToAttack()) {
//                this.mob.getLookControl().lookAt(target.getX(), target.getEyeY(), target.getZ());
//                performAttack(target);
//            }
//        } else {
//            resetAttackCooldown();
//            shouldCountTillNextAttack = false;
//            entity.setAttacking(false);
//            entity.attackAnimationTimeOut = 0;
//        }
//    }
//
//    private void resetAttackCooldown() {
//        this.tickUntilNextAttack = this.getTickCount(attackDelay * 2);
//    }
//
//    private void performAttack(LivingEntity target) {
//        this.resetAttackCooldown();
//        this.mob.swingHand(Hand.MAIN_HAND);
//        this.mob.tryAttack(target);
//    }
//
//    private boolean isTimeToAttack() {
//        return this.tickUntilNextAttack <= 0;
//    }
//
//    private boolean isTimeToStartAttackAnimation() {
//        return this.tickUntilNextAttack <= attackDelay;
//    }
//
//    private boolean isEnemyWithinAttackDistance(LivingEntity target) {
//        return this.entity.distanceTo(target) <= 2.0f;
//    }
}
