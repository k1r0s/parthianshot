package parthianshot.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;

@Mixin(AbstractHorseEntity.class)
public abstract class MountedEntity extends LivingEntity {
	
	protected MountedEntity(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
		// TODO Auto-generated constructor stub
	}

	public void setRotation(float yaw, float pitch) {
		if (((Entity)(Object)this).getControllingPassenger() instanceof PlayerEntity ridder) {
			if (ridder.getMainHandStack().getItem() == Items.LEAD) {
				double d = 0.2; // IDEA: this factor might be breed dependent 
		        float h = (float)MathHelper.lerpAngleDegrees(d, (double)this.getYaw(), ridder.getYaw());
		        float i = (float)MathHelper.lerp(d, (double)this.getPitch(), ridder.getPitch());
				this.setYaw(h % 360.0f);
		        this.setPitch(i % 360.0f);	
			}
		} else {
			super.setRotation(yaw, pitch);
		}
	}
}