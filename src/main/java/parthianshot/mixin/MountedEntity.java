package parthianshot.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Entity.class)
public abstract class MountedEntity {
	@Inject(method = "setRotation", at = @At("HEAD"), cancellable = true)
	public void beforeSetRotation(float yaw, float pitch, CallbackInfo ci) {		
		if (((Entity)(Object)this).getControllingPassenger() instanceof PlayerEntity ridder && ridder.getMainHandStack().getItem() != Items.LEAD) {
			ci.cancel();
		}
	}
}